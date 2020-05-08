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
    this.valuables = new ArrayList<>();
    valuables.add(new Diamond());
    valuables.add(new GoldNugget());
    valuables.add(new Jewel());
    valuables.add(new Ruby());
    valuables.add(new WoodenCoin());

  }

  private Valuable getRandom()
  {
    Random random = new Random();
    int index = random.nextInt(valuables.size());
    return valuables.get(index);
  }

/////////////
  @Override public void run()
  {

    while (true) //infinite loop
    {
      try
      {
      /*the miner gets one valuable, deposits one valuable and then starts again as per Assignment*/
        Thread.sleep(1000);// this is the time the miner is mining and not depositing
         // Valuable valuable = mine.getValuable();
          Valuable valuable = Mine.getValuable(getRandom().getName());
          deposit.put(valuable);

      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }

  }

}

