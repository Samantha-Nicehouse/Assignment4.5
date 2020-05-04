import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ValuableTransporter implements Runnable
{
  private Deposit deposit;
  private TreasureRoomGuardsman treasureRoomGuardsman;
  private List<Valuable> transportersValues = null;
  private int targetWorth;

  public ValuableTransporter(Deposit deposit, TreasureRoomGuardsman treasureRoomGuardsman)
  {
    this.deposit = deposit;
    this.treasureRoomGuardsman = treasureRoomGuardsman;
    this.transportersValues = new ArrayList<>();
  }

  public int generateRandomAmt()
  {

    int randomNum = ThreadLocalRandom.current().nextInt(50, 201);
    return randomNum;
  }

  public int TranportersListWorth()
  {
    int sum = 0;
    for (int i = 0; i < transportersValues.size(); i++)
    {
      sum += transportersValues.get(i).getValue();
    }
    return sum;
  }

  @Override public void run()
  {
    targetWorth = generateRandomAmt(); // create initial target
    while (true)
    {
      try
      {
        Valuable valuable = deposit.take();
        transportersValues.add(valuable);

        if(TranportersListWorth() >= targetWorth)
        {
          moveToTreasureRoom();// see method for write access
          Thread.sleep(4000);
          targetWorth = generateRandomAmt();// creates new goal
        }

      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }
  }

  private void moveToTreasureRoom()
  {//1. acquire write access
    //2. call the add method
    //3. release write access
    //4. clear the arraylist
    TreasureRoomWrite treasureRoomWrite = treasureRoomGuardsman.acquireWriteAccess();
    treasureRoomWrite.add(transportersValues);
    try
    {
      Thread.sleep(2000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    transportersValues.clear();
    treasureRoomGuardsman.releaseWriteAccess();

  }
}
