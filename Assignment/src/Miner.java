import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Miner implements Runnable// this is the job of the miner not the thread or person miner

{
  private Buffer deposit;
  private ArrayList<Valuable> valuables;


  public Miner(Buffer deposit)//tells the miner where the deposit is
  {
    this.deposit = deposit;


  }



  public Valuable mineValuable()
  {
    valuables = new ArrayList<>();
    valuables.add(Mine.getValuable("Diamond"));
    valuables.add(Mine.getValuable("GoldNugget"));
    valuables.add(Mine.getValuable("Jewel"));
    valuables.add(Mine.getValuable("Ruby"));
    valuables.add(Mine.getValuable("WoodenCoin"));
    Random random = new Random();
    int index = random.nextInt(valuables.size());
    return valuables.get(index);
  }

  public synchronized void removeValuable()
  {
    valuables.remove(mineValuable());
  }
/////////////
  @Override public void run()
  {

    while (true) //infinite loop
    {
      try
      {
      /*the miner gets one valuable, deposits one valuable and then starts again as per Assignment*/
        Thread.sleep(2000);// this is the time the miner is mining and not depositing
          Valuable valuable = mineValuable();
          deposit.put(valuable);
          removeValuable();
      }

      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}

