import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreasureRoom implements TreasureRoomRead, TreasureRoomWrite
{
  private ArrayList<Valuable> treasureList;


  public TreasureRoom()
  {
    this.treasureList = new ArrayList<Valuable>();
  }

  @Override
 public void add(List<Valuable> transportersValuables)
  {//1. loop through the transporters valuables
    //2. for each valuable, add to treasure list

    for(int i = 0; i < transportersValuables.size(); i++)
    {
      treasureList.add(transportersValuables.get(i));
    }
  }
  @Override
  public Valuable retrieve()
  {//1. remove valuable from room
    //2. check that that the value is not null
    int size = treasureList.size();
   if(size > 0)
    {
      Valuable valuable = treasureList.remove(0);
      return valuable;
    }
    return null;
  }

  @Override
 public List<Valuable> look()
  {//unmodifiable list so that the accountant cannot steal, he can only read
    return Collections.unmodifiableList(treasureList);
  }

}
