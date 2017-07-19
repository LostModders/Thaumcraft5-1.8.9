/*     */ package thaumcraft.client.lib.models;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WavefrontObject
/*     */   implements IModelCustom
/*     */ {
/*  28 */   private static Pattern vertexPattern = Pattern.compile("(v( (\\-){0,1}\\d+\\.\\d+){3,4} *\\n)|(v( (\\-){0,1}\\d+\\.\\d+){3,4} *$)");
/*  29 */   private static Pattern vertexNormalPattern = Pattern.compile("(vn( (\\-){0,1}\\d+\\.\\d+){3,4} *\\n)|(vn( (\\-){0,1}\\d+\\.\\d+){3,4} *$)");
/*  30 */   private static Pattern textureCoordinatePattern = Pattern.compile("(vt( (\\-){0,1}\\d+\\.\\d+){2,3} *\\n)|(vt( (\\-){0,1}\\d+\\.\\d+){2,3} *$)");
/*  31 */   private static Pattern face_V_VT_VN_Pattern = Pattern.compile("(f( \\d+/\\d+/\\d+){3,4} *\\n)|(f( \\d+/\\d+/\\d+){3,4} *$)");
/*  32 */   private static Pattern face_V_VT_Pattern = Pattern.compile("(f( \\d+/\\d+){3,4} *\\n)|(f( \\d+/\\d+){3,4} *$)");
/*  33 */   private static Pattern face_V_VN_Pattern = Pattern.compile("(f( \\d+//\\d+){3,4} *\\n)|(f( \\d+//\\d+){3,4} *$)");
/*  34 */   private static Pattern face_V_Pattern = Pattern.compile("(f( \\d+){3,4} *\\n)|(f( \\d+){3,4} *$)");
/*  35 */   private static Pattern groupObjectPattern = Pattern.compile("([go]( [\\w\\d\\.]+) *\\n)|([go]( [\\w\\d\\.]+) *$)");
/*     */   private static Matcher vertexMatcher;
/*     */   private static Matcher vertexNormalMatcher;
/*     */   private static Matcher textureCoordinateMatcher;
/*     */   private static Matcher face_V_VT_VN_Matcher;
/*     */   private static Matcher face_V_VT_Matcher;
/*  41 */   private static Matcher face_V_VN_Matcher; private static Matcher face_V_Matcher; private static Matcher groupObjectMatcher; public ArrayList<Vertex> vertices = new ArrayList();
/*  42 */   public ArrayList<Vertex> vertexNormals = new ArrayList();
/*  43 */   public ArrayList<TextureCoordinate> textureCoordinates = new ArrayList();
/*  44 */   public ArrayList<GroupObject> groupObjects = new ArrayList();
/*     */   private GroupObject currentGroupObject;
/*     */   private String fileName;
/*     */   
/*     */   public WavefrontObject(ResourceLocation resource) throws WavefrontObject.ModelFormatException
/*     */   {
/*  50 */     this.fileName = resource.toString();
/*     */     
/*     */     try
/*     */     {
/*  54 */       IResource res = Minecraft.func_71410_x().func_110442_L().func_110536_a(resource);
/*  55 */       loadObjModel(res.func_110527_b());
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  59 */       throw new ModelFormatException("IO Exception reading model format", e);
/*     */     }
/*     */   }
/*     */   
/*     */   public WavefrontObject(String filename, InputStream inputStream) throws WavefrontObject.ModelFormatException
/*     */   {
/*  65 */     this.fileName = filename;
/*  66 */     loadObjModel(inputStream);
/*     */   }
/*     */   
/*     */   private void loadObjModel(InputStream inputStream) throws WavefrontObject.ModelFormatException
/*     */   {
/*  71 */     BufferedReader reader = null;
/*     */     
/*  73 */     String currentLine = null;
/*  74 */     int lineCount = 0;
/*     */     
/*     */     try
/*     */     {
/*  78 */       reader = new BufferedReader(new InputStreamReader(inputStream));
/*     */       
/*  80 */       while ((currentLine = reader.readLine()) != null)
/*     */       {
/*  82 */         lineCount++;
/*  83 */         currentLine = currentLine.replaceAll("\\s+", " ").trim();
/*     */         
/*  85 */         if ((!currentLine.startsWith("#")) && (currentLine.length() != 0))
/*     */         {
/*     */ 
/*     */ 
/*  89 */           if (currentLine.startsWith("v "))
/*     */           {
/*  91 */             Vertex vertex = parseVertex(currentLine, lineCount);
/*  92 */             if (vertex != null)
/*     */             {
/*  94 */               this.vertices.add(vertex);
/*     */             }
/*     */           }
/*  97 */           else if (currentLine.startsWith("vn "))
/*     */           {
/*  99 */             Vertex vertex = parseVertexNormal(currentLine, lineCount);
/* 100 */             if (vertex != null)
/*     */             {
/* 102 */               this.vertexNormals.add(vertex);
/*     */             }
/*     */           }
/* 105 */           else if (currentLine.startsWith("vt "))
/*     */           {
/* 107 */             TextureCoordinate textureCoordinate = parseTextureCoordinate(currentLine, lineCount);
/* 108 */             if (textureCoordinate != null)
/*     */             {
/* 110 */               this.textureCoordinates.add(textureCoordinate);
/*     */             }
/*     */           }
/* 113 */           else if (currentLine.startsWith("f "))
/*     */           {
/*     */ 
/* 116 */             if (this.currentGroupObject == null)
/*     */             {
/* 118 */               this.currentGroupObject = new GroupObject("Default");
/*     */             }
/*     */             
/* 121 */             Face face = parseFace(currentLine, lineCount);
/*     */             
/* 123 */             if (face != null)
/*     */             {
/* 125 */               this.currentGroupObject.faces.add(face);
/*     */             }
/*     */           }
/* 128 */           else if ((currentLine.startsWith("g ") | currentLine.startsWith("o ")))
/*     */           {
/* 130 */             GroupObject group = parseGroupObject(currentLine, lineCount);
/*     */             
/* 132 */             if (group != null)
/*     */             {
/* 134 */               if (this.currentGroupObject != null)
/*     */               {
/* 136 */                 this.groupObjects.add(this.currentGroupObject);
/*     */               }
/*     */             }
/*     */             
/* 140 */             this.currentGroupObject = group;
/*     */           }
/*     */         }
/*     */       }
/* 144 */       this.groupObjects.add(this.currentGroupObject); return;
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 148 */       throw new ModelFormatException("IO Exception reading model format", e);
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 154 */         reader.close();
/*     */       }
/*     */       catch (IOException e) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 163 */         inputStream.close();
/*     */       }
/*     */       catch (IOException e) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderAll()
/*     */   {
/* 176 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/* 178 */     if (this.currentGroupObject != null)
/*     */     {
/* 180 */       tessellator.func_178180_c().func_181668_a(this.currentGroupObject.glDrawingMode, DefaultVertexFormats.field_181710_j);
/*     */     }
/*     */     else
/*     */     {
/* 184 */       tessellator.func_178180_c().func_181668_a(4, DefaultVertexFormats.field_181710_j);
/*     */     }
/* 186 */     tessellateAll(tessellator);
/*     */     
/* 188 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void tessellateAll(Tessellator tessellator)
/*     */   {
/* 194 */     for (GroupObject groupObject : this.groupObjects)
/*     */     {
/* 196 */       groupObject.render(tessellator);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderOnly(String... groupNames)
/*     */   {
/* 204 */     for (GroupObject groupObject : this.groupObjects)
/*     */     {
/* 206 */       for (String groupName : groupNames)
/*     */       {
/* 208 */         if (groupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 210 */           groupObject.render();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void tessellateOnly(Tessellator tessellator, String... groupNames) {
/* 218 */     for (GroupObject groupObject : this.groupObjects)
/*     */     {
/* 220 */       for (String groupName : groupNames)
/*     */       {
/* 222 */         if (groupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 224 */           groupObject.render(tessellator);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public String[] getPartNames()
/*     */   {
/* 233 */     ArrayList<String> l = new ArrayList();
/* 234 */     for (GroupObject groupObject : this.groupObjects)
/*     */     {
/* 236 */       l.add(groupObject.name);
/*     */     }
/* 238 */     return (String[])l.toArray(new String[0]);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderPart(String partName)
/*     */   {
/* 245 */     for (GroupObject groupObject : this.groupObjects)
/*     */     {
/* 247 */       if (partName.equalsIgnoreCase(groupObject.name))
/*     */       {
/* 249 */         groupObject.render();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void tessellatePart(Tessellator tessellator, String partName) {
/* 256 */     for (GroupObject groupObject : this.groupObjects)
/*     */     {
/* 258 */       if (partName.equalsIgnoreCase(groupObject.name))
/*     */       {
/* 260 */         groupObject.render(tessellator);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderAllExcept(String... excludedGroupNames)
/*     */   {
/* 269 */     for (GroupObject groupObject : this.groupObjects)
/*     */     {
/* 271 */       boolean skipPart = false;
/* 272 */       for (String excludedGroupName : excludedGroupNames)
/*     */       {
/* 274 */         if (excludedGroupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 276 */           skipPart = true;
/*     */         }
/*     */       }
/* 279 */       if (!skipPart)
/*     */       {
/* 281 */         groupObject.render();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void tessellateAllExcept(Tessellator tessellator, String... excludedGroupNames)
/*     */   {
/* 290 */     for (GroupObject groupObject : this.groupObjects)
/*     */     {
/* 292 */       boolean exclude = false;
/* 293 */       for (String excludedGroupName : excludedGroupNames)
/*     */       {
/* 295 */         if (excludedGroupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 297 */           exclude = true;
/*     */         }
/*     */       }
/* 300 */       if (!exclude)
/*     */       {
/* 302 */         groupObject.render(tessellator);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private Vertex parseVertex(String line, int lineCount) throws WavefrontObject.ModelFormatException
/*     */   {
/* 309 */     Vertex vertex = null;
/*     */     
/* 311 */     if (isValidVertexLine(line))
/*     */     {
/* 313 */       line = line.substring(line.indexOf(" ") + 1);
/* 314 */       String[] tokens = line.split(" ");
/*     */       
/*     */       try
/*     */       {
/* 318 */         if (tokens.length == 2)
/*     */         {
/* 320 */           return new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]));
/*     */         }
/* 322 */         if (tokens.length == 3)
/*     */         {
/* 324 */           return new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
/*     */         }
/*     */       }
/*     */       catch (NumberFormatException e)
/*     */       {
/* 329 */         throw new ModelFormatException(String.format("Number formatting error at line %d", new Object[] { Integer.valueOf(lineCount) }), e);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 334 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     }
/*     */     
/* 337 */     return vertex;
/*     */   }
/*     */   
/*     */   private Vertex parseVertexNormal(String line, int lineCount) throws WavefrontObject.ModelFormatException
/*     */   {
/* 342 */     Vertex vertexNormal = null;
/*     */     
/* 344 */     if (isValidVertexNormalLine(line))
/*     */     {
/* 346 */       line = line.substring(line.indexOf(" ") + 1);
/* 347 */       String[] tokens = line.split(" ");
/*     */       
/*     */       try
/*     */       {
/* 351 */         if (tokens.length == 3) {
/* 352 */           return new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
/*     */         }
/*     */       }
/*     */       catch (NumberFormatException e) {
/* 356 */         throw new ModelFormatException(String.format("Number formatting error at line %d", new Object[] { Integer.valueOf(lineCount) }), e);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 361 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     }
/*     */     
/* 364 */     return vertexNormal;
/*     */   }
/*     */   
/*     */   private TextureCoordinate parseTextureCoordinate(String line, int lineCount) throws WavefrontObject.ModelFormatException
/*     */   {
/* 369 */     TextureCoordinate textureCoordinate = null;
/*     */     
/* 371 */     if (isValidTextureCoordinateLine(line))
/*     */     {
/* 373 */       line = line.substring(line.indexOf(" ") + 1);
/* 374 */       String[] tokens = line.split(" ");
/*     */       
/*     */       try
/*     */       {
/* 378 */         if (tokens.length == 2)
/* 379 */           return new TextureCoordinate(Float.parseFloat(tokens[0]), 1.0F - Float.parseFloat(tokens[1]));
/* 380 */         if (tokens.length == 3) {
/* 381 */           return new TextureCoordinate(Float.parseFloat(tokens[0]), 1.0F - Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
/*     */         }
/*     */       }
/*     */       catch (NumberFormatException e) {
/* 385 */         throw new ModelFormatException(String.format("Number formatting error at line %d", new Object[] { Integer.valueOf(lineCount) }), e);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 390 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     }
/*     */     
/* 393 */     return textureCoordinate;
/*     */   }
/*     */   
/*     */   private Face parseFace(String line, int lineCount) throws WavefrontObject.ModelFormatException
/*     */   {
/* 398 */     Face face = null;
/*     */     
/* 400 */     if (isValidFaceLine(line))
/*     */     {
/* 402 */       face = new Face();
/*     */       
/* 404 */       String trimmedLine = line.substring(line.indexOf(" ") + 1);
/* 405 */       String[] tokens = trimmedLine.split(" ");
/* 406 */       String[] subTokens = null;
/*     */       
/* 408 */       if (tokens.length == 3)
/*     */       {
/* 410 */         if (this.currentGroupObject.glDrawingMode == -1)
/*     */         {
/* 412 */           this.currentGroupObject.glDrawingMode = 4;
/*     */         }
/* 414 */         else if (this.currentGroupObject.glDrawingMode != 4)
/*     */         {
/* 416 */           throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Invalid number of points for face (expected 4, found " + tokens.length + ")");
/*     */         }
/*     */       }
/* 419 */       else if (tokens.length == 4)
/*     */       {
/* 421 */         if (this.currentGroupObject.glDrawingMode == -1)
/*     */         {
/* 423 */           this.currentGroupObject.glDrawingMode = 7;
/*     */         }
/* 425 */         else if (this.currentGroupObject.glDrawingMode != 7)
/*     */         {
/* 427 */           throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Invalid number of points for face (expected 3, found " + tokens.length + ")");
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 432 */       if (isValidFace_V_VT_VN_Line(line))
/*     */       {
/* 434 */         face.vertices = new Vertex[tokens.length];
/* 435 */         face.textureCoordinates = new TextureCoordinate[tokens.length];
/* 436 */         face.vertexNormals = new Vertex[tokens.length];
/*     */         
/* 438 */         for (int i = 0; i < tokens.length; i++)
/*     */         {
/* 440 */           subTokens = tokens[i].split("/");
/*     */           
/* 442 */           face.vertices[i] = ((Vertex)this.vertices.get(Integer.parseInt(subTokens[0]) - 1));
/* 443 */           face.textureCoordinates[i] = ((TextureCoordinate)this.textureCoordinates.get(Integer.parseInt(subTokens[1]) - 1));
/* 444 */           face.vertexNormals[i] = ((Vertex)this.vertexNormals.get(Integer.parseInt(subTokens[2]) - 1));
/*     */         }
/*     */         
/* 447 */         face.faceNormal = face.calculateFaceNormal();
/*     */ 
/*     */       }
/* 450 */       else if (isValidFace_V_VT_Line(line))
/*     */       {
/* 452 */         face.vertices = new Vertex[tokens.length];
/* 453 */         face.textureCoordinates = new TextureCoordinate[tokens.length];
/*     */         
/* 455 */         for (int i = 0; i < tokens.length; i++)
/*     */         {
/* 457 */           subTokens = tokens[i].split("/");
/*     */           
/* 459 */           face.vertices[i] = ((Vertex)this.vertices.get(Integer.parseInt(subTokens[0]) - 1));
/* 460 */           face.textureCoordinates[i] = ((TextureCoordinate)this.textureCoordinates.get(Integer.parseInt(subTokens[1]) - 1));
/*     */         }
/*     */         
/* 463 */         face.faceNormal = face.calculateFaceNormal();
/*     */ 
/*     */       }
/* 466 */       else if (isValidFace_V_VN_Line(line))
/*     */       {
/* 468 */         face.vertices = new Vertex[tokens.length];
/* 469 */         face.vertexNormals = new Vertex[tokens.length];
/*     */         
/* 471 */         for (int i = 0; i < tokens.length; i++)
/*     */         {
/* 473 */           subTokens = tokens[i].split("//");
/*     */           
/* 475 */           face.vertices[i] = ((Vertex)this.vertices.get(Integer.parseInt(subTokens[0]) - 1));
/* 476 */           face.vertexNormals[i] = ((Vertex)this.vertexNormals.get(Integer.parseInt(subTokens[1]) - 1));
/*     */         }
/*     */         
/* 479 */         face.faceNormal = face.calculateFaceNormal();
/*     */ 
/*     */       }
/* 482 */       else if (isValidFace_V_Line(line))
/*     */       {
/* 484 */         face.vertices = new Vertex[tokens.length];
/*     */         
/* 486 */         for (int i = 0; i < tokens.length; i++)
/*     */         {
/* 488 */           face.vertices[i] = ((Vertex)this.vertices.get(Integer.parseInt(tokens[i]) - 1));
/*     */         }
/*     */         
/* 491 */         face.faceNormal = face.calculateFaceNormal();
/*     */       }
/*     */       else
/*     */       {
/* 495 */         throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 500 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     }
/*     */     
/* 503 */     return face;
/*     */   }
/*     */   
/*     */   private GroupObject parseGroupObject(String line, int lineCount) throws WavefrontObject.ModelFormatException
/*     */   {
/* 508 */     GroupObject group = null;
/*     */     
/* 510 */     if (isValidGroupObjectLine(line))
/*     */     {
/* 512 */       String trimmedLine = line.substring(line.indexOf(" ") + 1);
/*     */       
/* 514 */       if (trimmedLine.length() > 0)
/*     */       {
/* 516 */         group = new GroupObject(trimmedLine);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 521 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     }
/*     */     
/* 524 */     return group;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isValidVertexLine(String line)
/*     */   {
/* 534 */     if (vertexMatcher != null)
/*     */     {
/* 536 */       vertexMatcher.reset();
/*     */     }
/*     */     
/* 539 */     vertexMatcher = vertexPattern.matcher(line);
/* 540 */     return vertexMatcher.matches();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isValidVertexNormalLine(String line)
/*     */   {
/* 550 */     if (vertexNormalMatcher != null)
/*     */     {
/* 552 */       vertexNormalMatcher.reset();
/*     */     }
/*     */     
/* 555 */     vertexNormalMatcher = vertexNormalPattern.matcher(line);
/* 556 */     return vertexNormalMatcher.matches();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isValidTextureCoordinateLine(String line)
/*     */   {
/* 566 */     if (textureCoordinateMatcher != null)
/*     */     {
/* 568 */       textureCoordinateMatcher.reset();
/*     */     }
/*     */     
/* 571 */     textureCoordinateMatcher = textureCoordinatePattern.matcher(line);
/* 572 */     return textureCoordinateMatcher.matches();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isValidFace_V_VT_VN_Line(String line)
/*     */   {
/* 582 */     if (face_V_VT_VN_Matcher != null)
/*     */     {
/* 584 */       face_V_VT_VN_Matcher.reset();
/*     */     }
/*     */     
/* 587 */     face_V_VT_VN_Matcher = face_V_VT_VN_Pattern.matcher(line);
/* 588 */     return face_V_VT_VN_Matcher.matches();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isValidFace_V_VT_Line(String line)
/*     */   {
/* 598 */     if (face_V_VT_Matcher != null)
/*     */     {
/* 600 */       face_V_VT_Matcher.reset();
/*     */     }
/*     */     
/* 603 */     face_V_VT_Matcher = face_V_VT_Pattern.matcher(line);
/* 604 */     return face_V_VT_Matcher.matches();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isValidFace_V_VN_Line(String line)
/*     */   {
/* 614 */     if (face_V_VN_Matcher != null)
/*     */     {
/* 616 */       face_V_VN_Matcher.reset();
/*     */     }
/*     */     
/* 619 */     face_V_VN_Matcher = face_V_VN_Pattern.matcher(line);
/* 620 */     return face_V_VN_Matcher.matches();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isValidFace_V_Line(String line)
/*     */   {
/* 630 */     if (face_V_Matcher != null)
/*     */     {
/* 632 */       face_V_Matcher.reset();
/*     */     }
/*     */     
/* 635 */     face_V_Matcher = face_V_Pattern.matcher(line);
/* 636 */     return face_V_Matcher.matches();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isValidFaceLine(String line)
/*     */   {
/* 646 */     return (isValidFace_V_VT_VN_Line(line)) || (isValidFace_V_VT_Line(line)) || (isValidFace_V_VN_Line(line)) || (isValidFace_V_Line(line));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isValidGroupObjectLine(String line)
/*     */   {
/* 656 */     if (groupObjectMatcher != null)
/*     */     {
/* 658 */       groupObjectMatcher.reset();
/*     */     }
/*     */     
/* 661 */     groupObjectMatcher = groupObjectPattern.matcher(line);
/* 662 */     return groupObjectMatcher.matches();
/*     */   }
/*     */   
/*     */ 
/*     */   public String getType()
/*     */   {
/* 668 */     return "obj";
/*     */   }
/*     */   
/*     */   public class TextureCoordinate {
/*     */     public float u;
/*     */     public float v;
/*     */     public float w;
/*     */     
/*     */     public TextureCoordinate(float u, float v) {
/* 677 */       this(u, v, 0.0F);
/*     */     }
/*     */     
/*     */     public TextureCoordinate(float u, float v, float w)
/*     */     {
/* 682 */       this.u = u;
/* 683 */       this.v = v;
/* 684 */       this.w = w;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Face {
/*     */     public Vertex[] vertices;
/*     */     public Vertex[] vertexNormals;
/*     */     public Vertex faceNormal;
/*     */     public WavefrontObject.TextureCoordinate[] textureCoordinates;
/*     */     
/*     */     public Face() {}
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public void addFaceForRender(Tessellator tessellator) {
/* 698 */       addFaceForRender(tessellator, 5.0E-4F);
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public void addFaceForRender(Tessellator tessellator, float textureOffset)
/*     */     {
/* 704 */       if (this.faceNormal == null)
/*     */       {
/* 706 */         this.faceNormal = calculateFaceNormal();
/*     */       }
/*     */       
/*     */ 
/* 710 */       float averageU = 0.0F;
/* 711 */       float averageV = 0.0F;
/*     */       
/* 713 */       if ((this.textureCoordinates != null) && (this.textureCoordinates.length > 0))
/*     */       {
/* 715 */         for (int i = 0; i < this.textureCoordinates.length; i++)
/*     */         {
/* 717 */           averageU += this.textureCoordinates[i].u;
/* 718 */           averageV += this.textureCoordinates[i].v;
/*     */         }
/*     */         
/* 721 */         averageU /= this.textureCoordinates.length;
/* 722 */         averageV /= this.textureCoordinates.length;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 727 */       for (int i = 0; i < this.vertices.length; i++)
/*     */       {
/*     */ 
/* 730 */         if ((this.textureCoordinates != null) && (this.textureCoordinates.length > 0))
/*     */         {
/* 732 */           float offsetU = textureOffset;
/* 733 */           float offsetV = textureOffset;
/*     */           
/* 735 */           if (this.textureCoordinates[i].u > averageU)
/*     */           {
/* 737 */             offsetU = -offsetU;
/*     */           }
/* 739 */           if (this.textureCoordinates[i].v > averageV)
/*     */           {
/* 741 */             offsetV = -offsetV;
/*     */           }
/*     */           
/* 744 */           tessellator.func_178180_c().func_181662_b(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z).func_181673_a(this.textureCoordinates[i].u + offsetU, this.textureCoordinates[i].v + offsetV).func_181663_c(this.faceNormal.x, this.faceNormal.y, this.faceNormal.z).func_181675_d();
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 749 */           tessellator.func_178180_c().func_181662_b(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z).func_181663_c(this.faceNormal.x, this.faceNormal.y, this.faceNormal.z).func_181675_d();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public Vertex calculateFaceNormal()
/*     */     {
/* 756 */       Vec3 v1 = new Vec3(this.vertices[1].x - this.vertices[0].x, this.vertices[1].y - this.vertices[0].y, this.vertices[1].z - this.vertices[0].z);
/* 757 */       Vec3 v2 = new Vec3(this.vertices[2].x - this.vertices[0].x, this.vertices[2].y - this.vertices[0].y, this.vertices[2].z - this.vertices[0].z);
/* 758 */       Vec3 normalVector = null;
/*     */       
/* 760 */       normalVector = v1.func_72431_c(v2).func_72432_b();
/*     */       
/* 762 */       return new Vertex((float)normalVector.field_72450_a, (float)normalVector.field_72448_b, (float)normalVector.field_72449_c);
/*     */     }
/*     */   }
/*     */   
/*     */   public class GroupObject
/*     */   {
/*     */     public String name;
/* 769 */     public ArrayList<WavefrontObject.Face> faces = new ArrayList();
/*     */     public int glDrawingMode;
/*     */     
/*     */     public GroupObject()
/*     */     {
/* 774 */       this("");
/*     */     }
/*     */     
/*     */     public GroupObject(String name)
/*     */     {
/* 779 */       this(name, -1);
/*     */     }
/*     */     
/*     */     public GroupObject(String name, int glDrawingMode)
/*     */     {
/* 784 */       this.name = name;
/* 785 */       this.glDrawingMode = glDrawingMode;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public void render()
/*     */     {
/* 791 */       if (this.faces.size() > 0)
/*     */       {
/* 793 */         Tessellator tessellator = Tessellator.func_178181_a();
/* 794 */         tessellator.func_178180_c().func_181668_a(this.glDrawingMode, DefaultVertexFormats.field_181710_j);
/* 795 */         render(tessellator);
/* 796 */         tessellator.func_78381_a();
/*     */       }
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public void render(Tessellator tessellator)
/*     */     {
/* 803 */       if (this.faces.size() > 0)
/*     */       {
/* 805 */         for (WavefrontObject.Face face : this.faces)
/*     */         {
/* 807 */           face.addFaceForRender(tessellator);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public class ModelFormatException
/*     */     extends RuntimeException
/*     */   {
/*     */     private static final long serialVersionUID = 2023547503969671835L;
/*     */     
/*     */ 
/*     */     public ModelFormatException() {}
/*     */     
/*     */     public ModelFormatException(String message, Throwable cause)
/*     */     {
/* 824 */       super(cause);
/*     */     }
/*     */     
/*     */     public ModelFormatException(String message)
/*     */     {
/* 829 */       super();
/*     */     }
/*     */     
/*     */     public ModelFormatException(Throwable cause)
/*     */     {
/* 834 */       super();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/WavefrontObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */