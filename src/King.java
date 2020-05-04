import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class King implements Runnable
{
  private TreasureRoom treasureRoom;
  private List<Valuable> kingsList;
  public King(TreasureRoom treasureRoom)
  {
    this.treasureRoom = treasureRoom;
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
       while(runningTotal < partyCost)
        {
          treasureRoom.acquireWriteAccess();
          Valuable valuable = treasureRoom.retrieve();
            if(valuable == null)
             {
               break;
              }
          kingsList.add(valuable);
          runningTotal += valuable.getValue();
        }
       // when the while loop is done check if we met the target
        if(runningTotal >= partyCost)
        {
          Printer.getInstance().print("Party Time!!!!");
        }
        else
          {
            Printer.getInstance().print("Party Cancelled:(");
            treasureRoom.add(kingsList);
          }
          treasureRoom.releaseWriteAccess();
          kingsList.clear();
          Thread.sleep(20000);


        }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }
  }
}
