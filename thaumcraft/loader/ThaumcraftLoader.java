/*     */ package thaumcraft.loader;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.Dialog.ModalityType;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.jar.Attributes;
/*     */ import java.util.jar.Attributes.Name;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.jar.Manifest;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.regex.PatternSyntaxException;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import javax.swing.BoundedRangeModel;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.event.HyperlinkEvent;
/*     */ import javax.swing.event.HyperlinkEvent.EventType;
/*     */ import javax.swing.event.HyperlinkListener;
/*     */ import net.minecraft.launchwrapper.LaunchClassLoader;
/*     */ import net.minecraftforge.fml.common.asm.transformers.ModAccessTransformer;
/*     */ import net.minecraftforge.fml.common.versioning.ComparableVersion;
/*     */ import net.minecraftforge.fml.relauncher.CoreModManager;
/*     */ import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
/*     */ import net.minecraftforge.fml.relauncher.FMLRelaunchLog;
/*     */ import net.minecraftforge.fml.relauncher.IFMLCallHook;
/*     */ import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import sun.misc.URLClassPath;
/*     */ import sun.net.util.URLUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThaumcraftLoader
/*     */   implements IFMLLoadingPlugin, IFMLCallHook
/*     */ {
/*  76 */   private static ByteBuffer downloadBuffer = ByteBuffer.allocateDirect(8388608);
/*     */   private static final String owner = "ThaumcraftLoader";
/*     */   private static DepLoadInst inst;
/*  79 */   private static final Logger logger = LogManager.getLogger("ThaumcraftLoader");
/*     */   
/*     */   public static abstract interface IDownloadDisplay
/*     */   {
/*     */     public abstract void resetProgress(int paramInt);
/*     */     
/*     */     public abstract void setPokeThread(Thread paramThread);
/*     */     
/*     */     public abstract void updateProgress(int paramInt);
/*     */     
/*     */     public abstract boolean shouldStopIt();
/*     */     
/*     */     public abstract void updateProgressString(String paramString, Object... paramVarArgs);
/*     */     
/*     */     public abstract Object makeDialog();
/*     */     
/*     */     public abstract void showErrorDialog(String paramString1, String paramString2);
/*     */   }
/*     */   
/*     */   public static class Downloader extends JOptionPane implements ThaumcraftLoader.IDownloadDisplay {
/*     */     private JDialog container;
/*     */     private JLabel currentActivity;
/*     */     private JProgressBar progress;
/*     */     boolean stopIt;
/*     */     Thread pokeThread;
/*     */     
/*     */     private Box makeProgressPanel() {
/* 106 */       Box box = Box.createVerticalBox();
/* 107 */       box.add(Box.createRigidArea(new Dimension(0, 10)));
/* 108 */       JLabel welcomeLabel = new JLabel("<html><b><font size='+1'>ThaumcraftLoader is setting up your minecraft environment</font></b></html>");
/* 109 */       box.add(welcomeLabel);
/* 110 */       welcomeLabel.setAlignmentY(0.0F);
/* 111 */       welcomeLabel = new JLabel("<html>Please wait, ThaumcraftLoader has some tasks to do before you can play</html>");
/* 112 */       welcomeLabel.setAlignmentY(0.0F);
/* 113 */       box.add(welcomeLabel);
/* 114 */       box.add(Box.createRigidArea(new Dimension(0, 10)));
/* 115 */       this.currentActivity = new JLabel("Currently doing ...");
/* 116 */       box.add(this.currentActivity);
/* 117 */       box.add(Box.createRigidArea(new Dimension(0, 10)));
/* 118 */       this.progress = new JProgressBar(0, 100);
/* 119 */       this.progress.setStringPainted(true);
/* 120 */       box.add(this.progress);
/* 121 */       box.add(Box.createRigidArea(new Dimension(0, 30)));
/* 122 */       return box;
/*     */     }
/*     */     
/*     */     public JDialog makeDialog()
/*     */     {
/* 127 */       if (this.container != null) {
/* 128 */         return this.container;
/*     */       }
/* 130 */       setMessageType(1);
/* 131 */       setMessage(makeProgressPanel());
/* 132 */       setOptions(new Object[] { "Stop" });
/* 133 */       addPropertyChangeListener(new PropertyChangeListener()
/*     */       {
/*     */         public void propertyChange(PropertyChangeEvent evt) {
/* 136 */           if ((evt.getSource() == ThaumcraftLoader.Downloader.this) && (evt.getPropertyName() == "value")) {
/* 137 */             ThaumcraftLoader.Downloader.this.requestClose("This will stop minecraft from launching\nAre you sure you want to do this?");
/*     */           }
/*     */         }
/* 140 */       });
/* 141 */       this.container = new JDialog(null, "Hello", Dialog.ModalityType.MODELESS);
/* 142 */       this.container.setResizable(false);
/* 143 */       this.container.setLocationRelativeTo(null);
/* 144 */       this.container.add(this);
/* 145 */       updateUI();
/* 146 */       this.container.pack();
/* 147 */       this.container.setMinimumSize(this.container.getPreferredSize());
/* 148 */       this.container.setVisible(true);
/* 149 */       this.container.setDefaultCloseOperation(0);
/* 150 */       this.container.addWindowListener(new WindowAdapter()
/*     */       {
/*     */         public void windowClosing(WindowEvent e) {
/* 153 */           ThaumcraftLoader.Downloader.this.requestClose("Closing this window will stop minecraft from launching\nAre you sure you wish to do this?");
/*     */         }
/* 155 */       });
/* 156 */       return this.container;
/*     */     }
/*     */     
/*     */     protected void requestClose(String message) {
/* 160 */       int shouldClose = JOptionPane.showConfirmDialog(this.container, message, "Are you sure you want to stop?", 0, 2);
/* 161 */       if (shouldClose == 0) {
/* 162 */         this.container.dispose();
/*     */       }
/* 164 */       this.stopIt = true;
/* 165 */       if (this.pokeThread != null) {
/* 166 */         this.pokeThread.interrupt();
/*     */       }
/*     */     }
/*     */     
/*     */     public void updateProgressString(String progressUpdate, Object... data)
/*     */     {
/* 172 */       if (this.currentActivity != null) {
/* 173 */         this.currentActivity.setText(String.format(progressUpdate, data));
/*     */       }
/*     */     }
/*     */     
/*     */     public void resetProgress(int sizeGuess) {
/* 178 */       if (this.progress != null) {
/* 179 */         this.progress.getModel().setRangeProperties(0, 0, 0, sizeGuess, false);
/*     */       }
/*     */     }
/*     */     
/*     */     public void updateProgress(int fullLength) {
/* 184 */       if (this.progress != null) {
/* 185 */         this.progress.getModel().setValue(fullLength);
/*     */       }
/*     */     }
/*     */     
/*     */     public void setPokeThread(Thread currentThread) {
/* 190 */       this.pokeThread = currentThread;
/*     */     }
/*     */     
/*     */     public boolean shouldStopIt()
/*     */     {
/* 195 */       return this.stopIt;
/*     */     }
/*     */     
/*     */     public void showErrorDialog(String name, String url)
/*     */     {
/* 200 */       JEditorPane ep = new JEditorPane("text/html", "<html>ThaumcraftLoader was unable to download required library " + name + "<br>Check your internet connection and try restarting or download it manually from" + "<br><a href=\"" + url + "\">" + url + "</a> and put it in your mods folder" + "</html>");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 207 */       ep.setEditable(false);
/* 208 */       ep.setOpaque(false);
/* 209 */       ep.addHyperlinkListener(new HyperlinkListener()
/*     */       {
/*     */         public void hyperlinkUpdate(HyperlinkEvent event) {
/*     */           try {
/* 213 */             if (event.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
/* 214 */               Desktop.getDesktop().browse(event.getURL().toURI());
/*     */             }
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/* 219 */       });
/* 220 */       JOptionPane.showMessageDialog(null, ep, "A download error has occured", 0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class DummyDownloader
/*     */     implements ThaumcraftLoader.IDownloadDisplay
/*     */   {
/*     */     public void resetProgress(int sizeGuess) {}
/*     */     
/*     */ 
/*     */     public void setPokeThread(Thread currentThread) {}
/*     */     
/*     */ 
/*     */     public void updateProgress(int fullLength) {}
/*     */     
/*     */ 
/*     */     public boolean shouldStopIt()
/*     */     {
/* 239 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */     public void updateProgressString(String string, Object... data) {}
/*     */     
/*     */ 
/*     */     public Object makeDialog()
/*     */     {
/* 248 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void showErrorDialog(String name, String url) {}
/*     */   }
/*     */   
/*     */   public static class VersionedFile
/*     */   {
/*     */     public final Pattern pattern;
/*     */     public final String filename;
/*     */     public final ComparableVersion version;
/*     */     public final String name;
/*     */     
/*     */     public VersionedFile(String filename, Pattern pattern)
/*     */     {
/* 264 */       this.pattern = pattern;
/* 265 */       this.filename = filename;
/* 266 */       Matcher m = pattern.matcher(filename);
/* 267 */       if (m.matches()) {
/* 268 */         this.name = m.group(1);
/* 269 */         this.version = new ComparableVersion(m.group(2));
/*     */       }
/*     */       else {
/* 272 */         this.name = null;
/* 273 */         this.version = null;
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean matches() {
/* 278 */       return this.name != null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class Dependency
/*     */   {
/*     */     public File source;
/*     */     
/*     */     public String repo;
/*     */     
/*     */     public String packed;
/*     */     
/*     */     public ThaumcraftLoader.VersionedFile file;
/*     */     
/*     */     public String testClass;
/*     */     
/*     */     public boolean coreLib;
/*     */     public String existing;
/*     */     
/*     */     public Dependency(File source, String repo, String packed, ThaumcraftLoader.VersionedFile file, String testClass, boolean coreLib)
/*     */     {
/* 300 */       this.source = source;
/* 301 */       this.repo = repo;
/* 302 */       this.packed = packed;
/* 303 */       this.file = file;
/* 304 */       this.coreLib = coreLib;
/* 305 */       this.testClass = testClass;
/*     */     }
/*     */     
/*     */     public void set(Dependency dep) {
/* 309 */       this.source = dep.source;
/* 310 */       this.repo = dep.repo;
/* 311 */       this.packed = dep.packed;
/* 312 */       this.file = dep.file;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DepLoadInst
/*     */   {
/*     */     private File modsDir;
/*     */     private File v_modsDir;
/*     */     private ThaumcraftLoader.IDownloadDisplay downloadMonitor;
/*     */     private JDialog popupWindow;
/* 322 */     private Map<String, ThaumcraftLoader.Dependency> depMap = new HashMap();
/* 323 */     private HashSet<String> depSet = new HashSet();
/*     */     
/*     */     private File scanning;
/* 326 */     private LaunchClassLoader loader = (LaunchClassLoader)ThaumcraftLoader.class.getClassLoader();
/*     */     
/*     */     public DepLoadInst() {
/* 329 */       String mcVer = (String)net.minecraftforge.fml.relauncher.FMLInjectionData.data()[4];
/* 330 */       File mcDir = (File)net.minecraftforge.fml.relauncher.FMLInjectionData.data()[6];
/*     */       
/* 332 */       this.modsDir = new File(mcDir, "mods");
/* 333 */       this.v_modsDir = new File(mcDir, "mods/" + mcVer);
/* 334 */       if (!this.v_modsDir.exists())
/* 335 */         this.v_modsDir.mkdirs();
/*     */     }
/*     */     
/*     */     private void addClasspath(File file) {
/*     */       try {
/* 340 */         this.loader.addURL(file.toURI().toURL());
/*     */       } catch (MalformedURLException e) {
/* 342 */         throw new RuntimeException(e);
/*     */       }
/*     */     }
/*     */     
/*     */     private void deleteMod(File mod) {
/* 347 */       if (mod.delete()) {
/* 348 */         return;
/*     */       }
/*     */       try {
/* 351 */         URL url = mod.toURI().toURL();
/* 352 */         Field f_ucp = URLClassLoader.class.getDeclaredField("ucp");
/* 353 */         Field f_loaders = URLClassPath.class.getDeclaredField("loaders");
/* 354 */         Field f_lmap = URLClassPath.class.getDeclaredField("lmap");
/* 355 */         f_ucp.setAccessible(true);
/* 356 */         f_loaders.setAccessible(true);
/* 357 */         f_lmap.setAccessible(true);
/*     */         
/* 359 */         URLClassPath ucp = (URLClassPath)f_ucp.get(this.loader);
/* 360 */         Closeable loader = (Closeable)((Map)f_lmap.get(ucp)).remove(URLUtil.urlNoFragString(url));
/* 361 */         if (loader != null) {
/* 362 */           loader.close();
/* 363 */           ((List)f_loaders.get(ucp)).remove(loader);
/*     */         }
/*     */       } catch (Exception e) {
/* 366 */         e.printStackTrace();
/*     */       }
/*     */       
/* 369 */       if (!mod.delete()) {
/* 370 */         mod.deleteOnExit();
/* 371 */         String msg = "ThaumcraftLoader was unable to delete file " + mod.getPath() + " the game will now try to delete it on exit. If this dialog appears again, delete it manually.";
/* 372 */         ThaumcraftLoader.logger.error(msg);
/* 373 */         if (!GraphicsEnvironment.isHeadless()) {
/* 374 */           JOptionPane.showMessageDialog(null, msg, "An update error has occured", 0);
/*     */         }
/* 376 */         System.exit(1);
/*     */       }
/*     */     }
/*     */     
/*     */     private void install(ThaumcraftLoader.Dependency dep) {
/* 381 */       this.popupWindow = ((JDialog)this.downloadMonitor.makeDialog());
/*     */       
/* 383 */       if (!extract(dep)) {
/* 384 */         download(dep);
/*     */       }
/* 386 */       dep.existing = dep.file.filename;
/* 387 */       scanDepInfo(new File(this.v_modsDir, dep.existing));
/*     */     }
/*     */     
/*     */     private boolean extract(ThaumcraftLoader.Dependency dep) {
/* 391 */       if (dep.packed == null) {
/* 392 */         return false;
/*     */       }
/* 394 */       ZipFile zip = null;
/*     */       try {
/* 396 */         zip = new ZipFile(dep.source);
/* 397 */         ZipEntry libEntry = zip.getEntry(dep.packed + dep.file.filename);
/* 398 */         if (libEntry == null) {
/* 399 */           return false;
/*     */         }
/* 401 */         this.downloadMonitor.updateProgressString("Extracting file %s\n", new Object[] { dep.source.getPath() + '!' + libEntry.toString() });
/* 402 */         ThaumcraftLoader.logger.info("Extracting file " + dep.source.getPath() + '!' + libEntry.toString());
/*     */         
/* 404 */         download(zip.getInputStream(libEntry), (int)libEntry.getSize(), dep);
/*     */         
/* 406 */         this.downloadMonitor.updateProgressString("Extraction complete", new Object[0]);
/* 407 */         ThaumcraftLoader.logger.info("Extraction complete");
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 417 */         return true;
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 409 */         installError(e, dep, "extraction");
/*     */       } finally {
/*     */         try {
/* 412 */           if (zip != null) zip.close();
/*     */         } catch (IOException e) {
/* 414 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private void download(ThaumcraftLoader.Dependency dep)
/*     */     {
/*     */       try {
/* 422 */         URL libDownload = new URL(dep.repo + dep.file.filename);
/* 423 */         this.downloadMonitor.updateProgressString("Downloading file %s", new Object[] { libDownload.toString() });
/* 424 */         ThaumcraftLoader.logger.info("Downloading file " + libDownload.toString());
/* 425 */         URLConnection connection = libDownload.openConnection();
/* 426 */         connection.setConnectTimeout(5000);
/* 427 */         connection.setReadTimeout(5000);
/* 428 */         connection.setRequestProperty("User-Agent", "ThaumcraftLoader Downloader");
/* 429 */         download(connection.getInputStream(), connection.getContentLength(), dep);
/* 430 */         this.downloadMonitor.updateProgressString("Download complete", new Object[0]);
/* 431 */         ThaumcraftLoader.logger.info("Download complete");
/*     */       } catch (Exception e) {
/* 433 */         installError(e, dep, "download");
/*     */       }
/*     */     }
/*     */     
/*     */     private void installError(Exception e, ThaumcraftLoader.Dependency dep, String s) {
/* 438 */       if (this.downloadMonitor.shouldStopIt()) {
/* 439 */         ThaumcraftLoader.logger.error("You have stopped the " + s + " before it could complete");
/* 440 */         System.exit(1);
/*     */       }
/* 442 */       this.downloadMonitor.showErrorDialog(dep.file.filename, dep.repo + '/' + dep.file.filename);
/* 443 */       throw new RuntimeException(s + " error", e);
/*     */     }
/*     */     
/*     */     private void download(InputStream is, int sizeGuess, ThaumcraftLoader.Dependency dep) throws Exception {
/* 447 */       File target = new File(this.v_modsDir, dep.file.filename);
/* 448 */       if (sizeGuess > ThaumcraftLoader.downloadBuffer.capacity()) {
/* 449 */         throw new Exception(String.format("The file %s is too large to be downloaded by ThaumcraftLoader - the download is invalid", new Object[] { target.getName() }));
/*     */       }
/* 451 */       ThaumcraftLoader.downloadBuffer.clear();
/*     */       
/* 453 */       int fullLength = 0;
/*     */       
/* 455 */       this.downloadMonitor.resetProgress(sizeGuess);
/*     */       try {
/* 457 */         this.downloadMonitor.setPokeThread(Thread.currentThread());
/* 458 */         byte[] buffer = new byte['Ѐ'];
/* 459 */         int read; while ((read = is.read(buffer)) >= 0) {
/* 460 */           ThaumcraftLoader.downloadBuffer.put(buffer, 0, read);
/* 461 */           fullLength += read;
/* 462 */           if (this.downloadMonitor.shouldStopIt()) {
/*     */             break;
/*     */           }
/* 465 */           this.downloadMonitor.updateProgress(fullLength);
/*     */         }
/* 467 */         is.close();
/* 468 */         this.downloadMonitor.setPokeThread(null);
/* 469 */         ThaumcraftLoader.downloadBuffer.limit(fullLength);
/* 470 */         ThaumcraftLoader.downloadBuffer.position(0);
/*     */       }
/*     */       catch (InterruptedIOException e) {
/* 473 */         Thread.interrupted();
/* 474 */         throw new Exception("Stop");
/*     */       }
/*     */       
/* 477 */       if (!target.exists()) {
/* 478 */         target.createNewFile();
/*     */       }
/* 480 */       ThaumcraftLoader.downloadBuffer.position(0);
/* 481 */       FileOutputStream fos = new FileOutputStream(target);
/* 482 */       fos.getChannel().write(ThaumcraftLoader.downloadBuffer);
/* 483 */       fos.close();
/*     */     }
/*     */     
/*     */     private String checkExisting(ThaumcraftLoader.Dependency dep) {
/* 487 */       for (File f : this.modsDir.listFiles()) {
/* 488 */         ThaumcraftLoader.VersionedFile vfile = new ThaumcraftLoader.VersionedFile(f.getName(), dep.file.pattern);
/* 489 */         if ((vfile.matches()) && (vfile.name.equals(dep.file.name)))
/*     */         {
/*     */ 
/* 492 */           if (!f.renameTo(new File(this.v_modsDir, f.getName())))
/*     */           {
/*     */ 
/* 495 */             deleteMod(f); }
/*     */         }
/*     */       }
/* 498 */       for (File f : this.v_modsDir.listFiles()) {
/* 499 */         ThaumcraftLoader.VersionedFile vfile = new ThaumcraftLoader.VersionedFile(f.getName(), dep.file.pattern);
/* 500 */         if ((vfile.matches()) && (vfile.name.equals(dep.file.name)))
/*     */         {
/*     */ 
/* 503 */           int cmp = vfile.version.compareTo(dep.file.version);
/* 504 */           if (cmp < 0) {
/* 505 */             ThaumcraftLoader.logger.info("Deleted old version " + f.getName());
/* 506 */             deleteMod(f);
/* 507 */             return null;
/*     */           }
/* 509 */           if (cmp > 0) {
/* 510 */             ThaumcraftLoader.logger.info("Warning: version of " + dep.file.name + ", " + vfile.version + " is newer than request " + dep.file.version);
/* 511 */             return f.getName();
/*     */           }
/* 513 */           return f.getName();
/*     */         } }
/* 515 */       return null;
/*     */     }
/*     */     
/*     */     public void load() {
/* 519 */       scanDepInfos();
/* 520 */       if (this.depMap.isEmpty()) {
/* 521 */         return;
/*     */       }
/* 523 */       loadDeps();
/* 524 */       activateDeps();
/*     */     }
/*     */     
/*     */     private void activateDeps() {
/* 528 */       for (ThaumcraftLoader.Dependency dep : this.depMap.values()) {
/* 529 */         File file = new File(this.v_modsDir, dep.existing);
/* 530 */         if ((!searchCoreMod(file)) && (dep.coreLib)) {
/* 531 */           addClasspath(file);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     private boolean searchCoreMod(File coreMod)
/*     */     {
/* 539 */       JarFile jar = null;
/*     */       Attributes mfAttributes;
/*     */       try {
/* 542 */         jar = new JarFile(coreMod);
/* 543 */         if (jar.getManifest() == null) {
/* 544 */           return false;
/*     */         }
/* 546 */         ModAccessTransformer.addJar(jar);
/* 547 */         mfAttributes = jar.getManifest().getMainAttributes();
/*     */         
/*     */ 
/*     */ 
/*     */         try
/*     */         {
/* 553 */           if (jar != null) { jar.close();
/*     */           }
/*     */         }
/*     */         catch (IOException ignored) {}
/* 557 */         fmlCorePlugin = mfAttributes.getValue("FMLCorePlugin");
/*     */       }
/*     */       catch (IOException ioe)
/*     */       {
/* 549 */         FMLRelaunchLog.log(Level.ERROR, ioe, "Unable to read the jar file %s - ignoring", new Object[] { coreMod.getName() });
/* 550 */         return 0;
/*     */       } finally {
/*     */         try {
/* 553 */           if (jar != null) jar.close();
/*     */         }
/*     */         catch (IOException ignored) {}
/*     */       }
/*     */       String fmlCorePlugin;
/* 558 */       if (fmlCorePlugin == null) {
/* 559 */         return false;
/*     */       }
/* 561 */       addClasspath(coreMod);
/*     */       try
/*     */       {
/* 564 */         Class<CoreModManager> c = CoreModManager.class;
/* 565 */         if (!mfAttributes.containsKey(new Attributes.Name("FMLCorePluginContainsFMLMod"))) {
/* 566 */           FMLRelaunchLog.finer("Adding %s to the list of known coremods, it will not be examined again", new Object[] { coreMod.getName() });
/* 567 */           Field f_loadedCoremods = c.getDeclaredField("loadedCoremods");
/* 568 */           f_loadedCoremods.setAccessible(true);
/* 569 */           ((List)f_loadedCoremods.get(null)).add(coreMod.getName());
/*     */         } else {
/* 571 */           FMLRelaunchLog.finer("Found FMLCorePluginContainsFMLMod marker in %s, it will be examined later for regular @Mod instances", new Object[] { coreMod.getName() });
/* 572 */           Field f_reparsedCoremods = c.getDeclaredField("reparsedCoremods");
/* 573 */           f_reparsedCoremods.setAccessible(true);
/* 574 */           ((List)f_reparsedCoremods.get(null)).add(coreMod.getName());
/*     */         }
/* 576 */         Method m_loadCoreMod = c.getDeclaredMethod("loadCoreMod", new Class[] { LaunchClassLoader.class, String.class, File.class });
/* 577 */         m_loadCoreMod.setAccessible(true);
/* 578 */         m_loadCoreMod.invoke(null, new Object[] { this.loader, fmlCorePlugin, coreMod });
/*     */       } catch (Exception e) {
/* 580 */         throw new RuntimeException(e);
/*     */       }
/*     */       
/* 583 */       return true;
/*     */     }
/*     */     
/*     */     private void loadDeps() {
/* 587 */       this.downloadMonitor = (FMLLaunchHandler.side().isClient() ? new ThaumcraftLoader.Downloader() : new ThaumcraftLoader.DummyDownloader());
/*     */       try {
/* 589 */         while (!this.depSet.isEmpty()) {
/* 590 */           Iterator<String> it = this.depSet.iterator();
/* 591 */           ThaumcraftLoader.Dependency dep = (ThaumcraftLoader.Dependency)this.depMap.get(it.next());
/* 592 */           it.remove();
/* 593 */           load(dep);
/*     */         }
/*     */       } finally {
/* 596 */         if (this.popupWindow != null) {
/* 597 */           this.popupWindow.setVisible(false);
/* 598 */           this.popupWindow.dispose();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private void load(ThaumcraftLoader.Dependency dep) {
/* 604 */       dep.existing = checkExisting(dep);
/* 605 */       if ((dep.existing == null) && (ThaumcraftLoader.class.getResource("/" + dep.testClass.replace('.', '/') + ".class") == null))
/* 606 */         install(dep);
/*     */     }
/*     */     
/*     */     private List<File> modFiles() {
/* 610 */       List<File> list = new LinkedList();
/* 611 */       list.addAll(Arrays.asList(this.modsDir.listFiles()));
/* 612 */       list.addAll(Arrays.asList(this.v_modsDir.listFiles()));
/* 613 */       return list;
/*     */     }
/*     */     
/*     */     private void scanDepInfos() {
/* 617 */       for (File file : modFiles()) {
/* 618 */         if ((file.getName().endsWith(".jar")) || (file.getName().endsWith(".zip")))
/*     */         {
/*     */ 
/* 621 */           scanDepInfo(file); }
/*     */       }
/*     */     }
/*     */     
/*     */     private void scanDepInfo(File file) {
/*     */       try {
/* 627 */         this.scanning = file;
/* 628 */         ZipFile zip = new ZipFile(file);
/* 629 */         ZipEntry e = zip.getEntry("dependencies.info");
/* 630 */         if (e != null) {
/* 631 */           loadJSon(zip.getInputStream(e));
/*     */         }
/* 633 */         zip.close();
/*     */       } catch (Exception e) {
/* 635 */         ThaumcraftLoader.logger.error("Failed to load dependencies.info from " + file.getName() + " as JSON", e);
/*     */       }
/*     */     }
/*     */     
/*     */     private void loadJSon(InputStream input) throws IOException {
/* 640 */       InputStreamReader reader = new InputStreamReader(input);
/* 641 */       JsonElement root = new JsonParser().parse(reader);
/* 642 */       if (root.isJsonArray()) {
/* 643 */         loadJSonArr(root);
/*     */       } else
/* 645 */         loadJson(root.getAsJsonObject());
/* 646 */       reader.close();
/*     */     }
/*     */     
/*     */     private void loadJSonArr(JsonElement root) throws IOException {
/* 650 */       for (JsonElement node : root.getAsJsonArray())
/* 651 */         loadJson(node.getAsJsonObject());
/*     */     }
/*     */     
/*     */     private void loadJson(JsonObject node) throws IOException {
/* 655 */       boolean obfuscated = ((LaunchClassLoader)ThaumcraftLoader.class.getClassLoader()).getClassBytes("net.minecraft.world.World") == null;
/*     */       
/*     */ 
/* 658 */       String repo = node.has("repo") ? node.get("repo").getAsString() : null;
/* 659 */       String packed = node.has("packed") ? node.get("packed").getAsString() : null;
/* 660 */       String testClass = node.get("class").getAsString();
/*     */       
/* 662 */       String filename = node.get("file").getAsString();
/* 663 */       if ((!obfuscated) && (node.has("dev"))) {
/* 664 */         filename = node.get("dev").getAsString();
/*     */       }
/* 666 */       boolean coreLib = (node.has("coreLib")) && (node.get("coreLib").getAsBoolean());
/*     */       
/* 668 */       Pattern pattern = null;
/*     */       try {
/* 670 */         if (node.has("pattern"))
/* 671 */           pattern = Pattern.compile(node.get("pattern").getAsString());
/*     */       } catch (PatternSyntaxException e) {
/* 673 */         ThaumcraftLoader.logger.error("Invalid filename pattern: " + node.get("pattern"), e);
/*     */       }
/* 675 */       if (pattern == null) {
/* 676 */         pattern = Pattern.compile("(\\w+).*?([\\d\\.]+)[-\\w]*\\.[^\\d]+");
/*     */       }
/* 678 */       ThaumcraftLoader.VersionedFile file = new ThaumcraftLoader.VersionedFile(filename, pattern);
/* 679 */       if (!file.matches()) {
/* 680 */         throw new RuntimeException("Invalid filename format for dependency: " + filename);
/*     */       }
/* 682 */       addDep(new ThaumcraftLoader.Dependency(this.scanning, repo, packed, file, testClass, coreLib));
/*     */     }
/*     */     
/*     */     private void addDep(ThaumcraftLoader.Dependency newDep) {
/* 686 */       ThaumcraftLoader.Dependency oldDep = (ThaumcraftLoader.Dependency)this.depMap.get(newDep.file.name);
/* 687 */       if (oldDep == null) {
/* 688 */         this.depMap.put(newDep.file.name, newDep);
/* 689 */         this.depSet.add(newDep.file.name);
/* 690 */         return;
/*     */       }
/*     */       
/*     */ 
/* 694 */       oldDep.coreLib |= newDep.coreLib;
/* 695 */       int cmp = newDep.file.version.compareTo(oldDep.file.version);
/* 696 */       if (cmp == 1) {
/* 697 */         oldDep.set(newDep);
/* 698 */       } else if (cmp == 0) {
/* 699 */         if (oldDep.repo == null)
/* 700 */           oldDep.repo = newDep.repo;
/* 701 */         if (oldDep.packed == null) {
/* 702 */           oldDep.source = newDep.source;
/* 703 */           oldDep.packed = newDep.packed;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void load() {
/* 710 */     if (inst == null) {
/* 711 */       inst = new DepLoadInst();
/* 712 */       inst.load();
/*     */     }
/*     */   }
/*     */   
/*     */   public String[] getASMTransformerClass()
/*     */   {
/* 718 */     return null;
/*     */   }
/*     */   
/*     */   public String getModContainerClass()
/*     */   {
/* 723 */     return null;
/*     */   }
/*     */   
/*     */   public String getSetupClass()
/*     */   {
/* 728 */     return getClass().getName();
/*     */   }
/*     */   
/*     */ 
/*     */   public void injectData(Map<String, Object> data) {}
/*     */   
/*     */ 
/*     */   public Void call()
/*     */   {
/* 737 */     load();
/*     */     
/* 739 */     return null;
/*     */   }
/*     */   
/*     */   public String getAccessTransformerClass()
/*     */   {
/* 744 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/loader/ThaumcraftLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */