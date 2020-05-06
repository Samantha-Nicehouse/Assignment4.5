import java.util.List;

public class TreasureRoomReadProxy implements TreasureRoomRead
{
  private TreasureRoom treasureRoom;

  public TreasureRoomReadProxy(TreasureRoom treasureRoom)
  {
    this.treasureRoom = treasureRoom;
  }
  @Override public List<Valuable> look()
  {
    //returns the treasure room look method
    return treasureRoom.look();
  }
}
