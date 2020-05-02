import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ValuableTransporter implements Runnable
{
  private Deposit deposit;
  private List<Valuable> transportersValues = null;
  private int targetWorth;

  public ValuableTransporter(Deposit deposit)
  {
    this.deposit = deposit;
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
    targetWorth = generateRandomAmt();
    while (true)
    {
      try
      {
        Valuable valuable = deposit.take();
        transportersValues.add(valuable);

        if(TranportersListWorth() >= targetWorth)
        {
          transportersValues.clear();
          Thread.sleep(4000);
          Printer.getInstance().print("Valuable transporter is sleeping");
          targetWorth = generateRandomAmt();// creates new goal
        }

      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }
  }
}
