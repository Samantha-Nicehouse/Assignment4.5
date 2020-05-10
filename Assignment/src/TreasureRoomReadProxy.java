import utility.collection.ListADT;

import javax.swing.*;
import java.util.List;

public class TreasureRoomReadProxy implements TreasureRoomRead
{
  private TreasureRoom treasureRoom;
  private boolean hasReadAccess;


  public TreasureRoomReadProxy(TreasureRoom treasureRoom)
  {
    this.treasureRoom = treasureRoom;
    this.hasReadAccess = true;//for protection proxy
  }
  @Override public List<Valuable> look()
  {
    if (hasReadAccess)
    { //returns the treasure room look method
      return treasureRoom.look();
    }
  else
    {
      throw new IllegalStateException("no read access");
    }
  }
  public void restrictReadAccess()
  {
    hasReadAccess = false;
  }
}
