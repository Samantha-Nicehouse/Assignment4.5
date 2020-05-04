import java.util.List;

public class Accountant implements Runnable
{
  private TreasureRoomGuardsman treasureRoomGuardsman;


  public Accountant(TreasureRoomGuardsman treasureRoomGuardsman)
  {
    this.treasureRoomGuardsman = treasureRoomGuardsman;

  }
  @Override public void run()
  {
    while(true)
    {
      TreasureRoomRead treasureRoomRead = treasureRoomGuardsman.acquireReadAccess();
      List<Valuable> treasureListCopy =  treasureRoomRead.look();
      int size = treasureListCopy.size();
      int total = 0;
      try
      {
        for(int i = 0; i < size-1; i++)
        {
          int value = treasureListCopy.get(i).getValue();
          total += value;
        }
        Printer.getInstance().print("The treasure room has " + total );
        treasureRoomGuardsman.releaseReadAccess();
        Thread.sleep(4000);

      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }
  }
}
