import java.util.List;
import java.util.Random;

public class Miner implements Runnable// this is the job of the miner not the thread or person miner
{
  private Deposit deposit;
  private List<String> valuableNameList = Mine.allMineValuableNames();

  public Miner(Deposit deposit)//tells the miner where the deposit is
  {
    this.deposit = deposit;
  }

  public int getRandomIndex()
  {
    Random random = new Random();
    int index = random.nextInt(valuableNameList.size());
    return index;
  }

  @Override public void run()
  {

    while (true) //infinite loop
    {
      try
      {
        Thread.sleep(4000);// this is the time the miner is mining and not depositing
        deposit.put(Mine.getValuable(valuableNameList.get(getRandomIndex())));
        // puts a valuable that it gets from the mine and the string name is from the random index
        //of the arraylist of the miners valuables
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}

