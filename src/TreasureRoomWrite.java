import java.util.List;

public interface TreasureRoomWrite extends TreasureRoomRead
{
  void add(List<Valuable> treasureList);
  Valuable retrieve();
}
