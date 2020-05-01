

import java.nio.Buffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
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
    Printer.getInstance().print("Valuable" + valuable.getName() + valuable.getValue() + " has been added to the deposit");

  }

public synchronized Valuable take()
  {
    while (isEmpty())
    {
      try
      {
        System.out.println("There are no valuables" + size());
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    notifyAll();
   Valuable v = valuableArrayList.remove(0);
  Printer.getInstance().print("Valuable has been removed from the deposit: " + v.getName() + " now we have " + worth() + " in the deposit");
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
    return valuableArrayList.isEmpty();
  }



 public synchronized int size()
  {
    return valuableArrayList.size();
  }

  @Override public synchronized String toString()
  {
    return super.toString();
  }
}
