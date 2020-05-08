public class Printer//singleton
  //1.constructor is private and dont inititalize the class object in the constructor
  //2. public static method getInstance to create the printer
{
  private static Printer instance;

  private  Printer(){

  }

  public static Printer getInstance(){
    if (instance == null)
    {
      instance = new Printer();
    }
    return instance;
  }

  public void print(String text)
  {
    System.out.println(text);
  }
}
