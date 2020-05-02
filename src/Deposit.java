
// an adapter implements the functionality of the target interface-
//here the Deposit is the adapter delegates to the arraylist, the arraylist does it for the deposit
// here is the deposit is a blockingque because we uses wait and notify with the put and take
//the adaptee is the listADT & arraylist
import utility.collection.ArrayList;
public class Deposit //blocking queue accepts threads and makes the threads wait
{
  private ArrayList<Valuable> valuableArrayList; //creates an array list of valuables

  public Deposit()
  {
    this.valuableArrayList = new ArrayList<>();
  }

 public synchronized void put(Valuable valuable)
  {

    if (valuable == null)
    {
      throw new IllegalArgumentException("Null valuable");
    }
    valuableArrayList.add(valuable);
    Printer.getInstance().print( "A miner has put a " + valuable.getName()  + " worth " + valuable.getValue() + " into the deposit.");

  }

public synchronized Valuable take()
  {
    while (isEmpty())
    {
      try
      {
        Printer.getInstance().print("There are no valuables, transporter is waiting for miner to produce.");
        wait(6000);// transporter thread has to wait until a producer puts in a valuable

      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    notifyAll();
   Valuable v = valuableArrayList.remove(0);
  Printer.getInstance().print("A valuable transporter has removed a " +  v.getName() + " from the deposit, now we have " + worth() + " in the deposit");
   return v;

  }

  private synchronized int worth()
  {
    int sum = 0;
    for (int i = 0; i < valuableArrayList.size(); i++)
    {
      sum += valuableArrayList.get(i).getValue();
    }
    return sum;
  }
 public synchronized Valuable look()
  {
    if (valuableArrayList.isEmpty())
    {
      return null;
    }

    return valuableArrayList.get(0);
  }

 public synchronized boolean isEmpty()
  {
    if(valuableArrayList.size() == 0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }


 public synchronized int size()
  {
    return valuableArrayList.size();
  }

 public synchronized String toString()
  {
    return super.toString();
  }
}
