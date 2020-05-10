import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Miner implements Runnable// this is the job of the miner not the thread or person miner

{
  private Buffer deposit;
  private List<String> valuableNames;


  public Miner(Buffer deposit)//tells the miner where the deposit is
  {
    this.deposit = deposit;
    valuableNames = new ArrayList<>();
  }



  public String mineValuable()
  {
    valuableNames.add("Diamond");
    valuableNames.add("GoldNugget");
    valuableNames.add("Jewel");
    valuableNames.add("Ruby");
    valuableNames.add("WoodenCoin");
    Random random = new Random();
    int index = random.nextInt(valuableNames.size());
    return valuableNames.get(index);
  }


  @Override public void run()
  {

    while (true) //infinite loop
    {
      try
      {
      /*the miner gets one valuable, deposits one valuable and then starts again as per Assignment*/
        Thread.sleep(1000);// this is the time the miner is mining and not depositing
          Valuable valuable = Mine.getValuable(mineValuable());
          deposit.put(valuable);
      }

      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

}

