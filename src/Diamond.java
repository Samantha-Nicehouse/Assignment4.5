public class Diamond implements Valuable
  //this is a concrete class of the flyweight interface valuable
  //the mine is the flyweight factory
{
  @Override public String getName()
  {
    return "Diamond";
  }

  @Override public int getValue()
  {
    return 100;
  }
}
