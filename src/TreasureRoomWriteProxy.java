import java.util.List;

public class TreasureRoomWriteProxy implements TreasureRoomWrite
{
  private TreasureRoom treasureRoom;

  public TreasureRoomWriteProxy(TreasureRoom treasureRoom)
  {
    this.treasureRoom = treasureRoom;
  }
  @Override public void add(List<Valuable> treasureList)
  {
    treasureRoom.add(treasureList);
  }

  @Override public Valuable retrieve()
  {
    return treasureRoom.retrieve();
  }

  @Override public List<Valuable> look()
  {
    return treasureRoom.look();
  }
}
