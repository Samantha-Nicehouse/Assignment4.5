import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class King implements Runnable
{
  private TreasureRoomGuardsman treasureRoomGuardsman;
  private List<Valuable> kingsList;
  public King(TreasureRoomGuardsman treasureRoomGuardsman)
  {
    this.treasureRoomGuardsman = treasureRoomGuardsman;
    this.kingsList = new ArrayList<>();
  }

  public int partyCost()
  {
    int randomNum = ThreadLocalRandom.current().nextInt(50, 151);
    return randomNum;
  }

  @Override public void run()
  {

    while (true)
    {
      try
      {
        int partyCost = partyCost();//party goal
        int runningTotal = 0;
        TreasureRoomWrite treasureRoomWrite = treasureRoomGuardsman.acquireWriteAccess();
       while(runningTotal < partyCost)
        {
          Valuable valuable = treasureRoomWrite.retrieve();
            if(valuable == null)
             {
               break;
              }
          kingsList.add(valuable);
          runningTotal += valuable.getValue();
          Thread.sleep(20000);
        }
       // when the while loop is done check if we met the target
        System.out.println(partyCost);
        if(runningTotal >= partyCost)
        {
          Printer.getInstance().print("Party Time!!!!");
          kingsList.clear();
          Thread.sleep(20000);
        }
        else
          {
            Printer.getInstance().print("Party Cancelled:(");
            treasureRoomWrite.add(kingsList);
          }
          treasureRoomGuardsman.releaseWriteAccess();
        }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }
  }
}
