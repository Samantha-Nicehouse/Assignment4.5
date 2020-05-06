import org.junit.jupiter.api.*;
public class KingdomTest
{
  Deposit d1 = new Deposit();
  TreasureRoom t1 = new TreasureRoom();
  TreasureRoomRead treasureRoomRead = new TreasureRoomReadProxy(t1);
  TreasureRoomWrite treasureRoomWrite = new TreasureRoomWriteProxy(t1);
  TreasureRoomGuardsman treasureRoomGuardsman = new TreasureRoomGuardsman(treasureRoomWrite, treasureRoomRead);
  //will be guardsman

  public void transporterHasValuables() throws InterruptedException
  {
    Thread miner1 = new Thread(new Miner(d1), "Miner 1");
    miner1.start();


    ValuableTransporter valuableTransporter = new ValuableTransporter(d1, treasureRoomGuardsman);
    Thread t1 = new Thread(valuableTransporter, "valuableTransporter");
    t1.start();
    Thread.sleep(10000);
    AsserttvaluableTransporter.TransportersListWorth() > 0;

  }
}
