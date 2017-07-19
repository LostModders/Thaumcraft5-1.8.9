package thaumcraft.api.golems.seals;

public abstract interface ISealGui
{
  public static final int CAT_PRIORITY = 0;
  public static final int CAT_FILTER = 1;
  public static final int CAT_AREA = 2;
  public static final int CAT_TOGGLES = 3;
  public static final int CAT_TAGS = 4;
  
  public abstract int[] getGuiCategories();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/seals/ISealGui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */