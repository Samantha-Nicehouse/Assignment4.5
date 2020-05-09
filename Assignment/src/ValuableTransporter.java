import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ValuableTransporter implements Runnable
{
  private Buffer deposit;
  private TreasureRoomGuardsman treasureRoomGuardsman;
  private ArrayList<Valuable> valuables = null;
  private int targetWorth;

  public ValuableTransporter(Buffer deposit, TreasureRoomGuardsman treasureRoomGuardsman)
  {
    this.deposit= deposit;
    this.treasureRoomGuardsman = treasureRoomGuardsman;
    this.valuables = new ArrayList<>();
  }

  public int generateRandomAmt()
  {

    int randomNum = ThreadLocalRandom.current().nextInt(50, 201);
    return randomNum;
  }

  public int TransportersListWorth()
  {
    int sum = 0;
    for (int i = 0; i < valuables.size(); i++)
    {
      sum += valuables.get(i).getValue();
    }
    return sum;
  }

  @Override public void run()
  {
    targetWorth = generateRandomAmt(); // create initial target
    while (true)
    {

        Valuable valuable = (Valuable) deposit.take(); // cast the deposit object(Jewel,Ruby,Diamond,to a valuable it was already cast though so I don't know why this is happening
        valuables.add(valuable);

        if(TransportersListWorth() >= targetWorth)
        {
          moveToTreasureRoom();// see method for write access
          targetWorth = generateRandomAmt();// creates new goal
        }
      }


    }


  private void moveToTreasureRoom()
  {//1. acquire write access
    //2. call the add method
    //3. release write access
    //4. clear the arraylist
    TreasureRoomWrite treasureRoomWrite = treasureRoomGuardsman.acquireWriteAccess();
    treasureRoomWrite.add(valuables);
    try
    {
      Thread.sleep(2000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    valuables.clear();
    treasureRoomGuardsman.releaseWriteAccess();

  }
}
