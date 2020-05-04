import java.util.ArrayList;
import java.util.List;

public class TreasureRoom implements TreasureRoomDoor
{
  private List<Valuable> treasureList;
  private int total;

  public TreasureRoom()
  {
    this.total = 0;
    this.treasureList = new ArrayList<Valuable>();
  }

  @Override public void add(List<Valuable> transportersValuables)
  {//1. loop through the transporters valuables
    //2. for each valuable, add to treasure list

    for(int i = 0; i < transportersValuables.size()-1; i++)
    {
      treasureList.add(transportersValuables.get(i));
      int value = transportersValuables.get(i).getValue();
      total += value;
    }
  }

  @Override public Valuable retrieve()
  {//1. remove valuable from room
    //2. check that that the value is not null

    Valuable valuable = treasureList.remove(0);
    if (valuable != null)
    {
      total -= valuable.getValue();
    }
    return valuable;
  }

  @Override public int look()
  {
    return total;
  }

  @Override public void acquireReadAccess()
  {

  }

  @Override public void releaseReadAccess()
  {

  }

  @Override public void acquireWriteAccess()
  {

  }

  @Override public void releaseWriteAccess()
  {

  }
}
