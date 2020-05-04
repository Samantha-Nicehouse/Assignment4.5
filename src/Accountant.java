public class Accountant implements Runnable
{
  private TreasureRoom treasureRoom;


  public Accountant(TreasureRoom treasureRoom)
  {
    this.treasureRoom = treasureRoom;
    this.total = 0;
  }
  @Override public void run()
  {
    while(true)
    {
      treasureRoom.acquireReadAccess();
      int total = treasureRoom.look();

      try
      {

        Thread.sleep(4000);
        treasureRoom.releaseReadAccess();
        Printer.getInstance().print("The treasure room has " + total );
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }
  }
}
