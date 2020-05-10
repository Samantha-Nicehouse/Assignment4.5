import javax.print.attribute.AttributeSet;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TreasureRoomGuardsman implements TreasureRoomDoor
{//the guardsman is a proxy for the treasure room because he has all of the methods for the treasure room
  private TreasureRoomRead treasureRoomRead;
  private TreasureRoomWrite treasureRoomWrite;
  private int readers;
  private int writers; // write means adding or removing
  private Queue<Thread> queue;

  public TreasureRoomGuardsman(TreasureRoomWrite treasureRoomWrite, TreasureRoomRead treasureRoomRead)
  {
    this.treasureRoomRead = treasureRoomRead;
    this.treasureRoomWrite = treasureRoomWrite;
    this.readers = 0;
    this.writers = 0;
    queue = new ConcurrentLinkedQueue<>();

  }


  @Override public synchronized TreasureRoomRead acquireReadAccess() //only an accountant needs read access
  {
    queue.offer(Thread.currentThread());//adds current thread to the back of the queue
    while(queue.peek() != Thread.currentThread())//reads the thread at the front of the queue
    {
      try
      {

       Printer.getInstance().print(Thread.currentThread().getName() + " there is a line, you have to wait");
        // tells the current thread that there is a line, you have to wait
        wait();
      }catch (InterruptedException e)
      {

      }
    }
    while(writers > 0 || readers > 0 ) // greater than 0 means there is someone in the room, the accountant has to wait until the king or transporter is done
    {
      try
      {
        Printer.getInstance().print(Thread.currentThread().getName() + " the room is occupied by the king or a transporter, you have to wait");
        wait();

      }catch (InterruptedException e)
      {
          e.printStackTrace();
      }
    }
   Printer.getInstance().print(Thread.currentThread().getName() + " is in the treasure room now");
    readers++;
    queue.remove();//removes the current thread from the queue
    notifyAll();
    return treasureRoomRead;
  }

  @Override public synchronized void releaseReadAccess()
  {
    readers--;
    Printer.getInstance().print(Thread.currentThread().getName() + " has left the treasure room");

    if(readers == 0)// if there are no more accountants, notify everyone in the queue
    {
      notifyAll();
    }
  }

  @Override public synchronized TreasureRoomWrite acquireWriteAccess()
  {
    queue.offer(Thread.currentThread()); // add the current thread who is asking for access to the back of the list
    //two whiles 1.to check if the person is at the head of the queue and 2. to check if there is a reader in the room
    while(queue.peek() != Thread.currentThread())// check if the current thread asking for access is at the front of the queue
    {
      try
      {

        Printer.getInstance().print(Thread.currentThread().getName() + " there is a line, you have to wait");
        wait();
      }catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    while(readers > 0 || writers > 0)//the accountant is in the room, the transporter or king has to wait
    {
      try
      {
        Printer.getInstance().print(Thread.currentThread().getName() + " the room is occupied, you have to wait");
        wait();

      }catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    Printer.getInstance().print(Thread.currentThread().getName() + " has accessed the treasure room.");
    writers++;
    queue.remove();//removes the current thread from the queue
    notifyAll();
    return treasureRoomWrite;
  }

  @Override public synchronized void releaseWriteAccess()
  {
    writers--;
    Printer.getInstance().print(Thread.currentThread().getName() + " is leaving the treasure room.");
    notifyAll();
  }


}
