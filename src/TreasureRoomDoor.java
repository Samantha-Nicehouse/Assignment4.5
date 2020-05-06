import java.util.List;

public interface TreasureRoomDoor
{
 /*
 */
  TreasureRoomRead acquireReadAccess();
  void releaseReadAccess();
  TreasureRoomWrite acquireWriteAccess();
  void releaseWriteAccess();
}
