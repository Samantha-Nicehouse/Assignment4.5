public class Kingdom
{
  public static void main(String[] args)
  {

    Buffer d1 = new Deposit();
    TreasureRoom t1 = new TreasureRoom();
    TreasureRoomRead treasureRoomRead = new TreasureRoomReadProxy(t1);
    TreasureRoomWrite treasureRoomWrite = new TreasureRoomWriteProxy(t1);
    TreasureRoomGuardsman treasureRoomGuardsman = new TreasureRoomGuardsman(treasureRoomWrite, treasureRoomRead);
    //will be guardsman
    Thread miner1 = new Thread(new Miner(d1), "Miner 1");


    miner1.start();
    Thread miner2 = new Thread(new Miner(d1), "Miner 2");
    miner2.start();


    Thread valuableTransporter1 = new Thread(new ValuableTransporter(d1, treasureRoomGuardsman), "Valuable Transporter 1");
    Thread valuableTransporter2 = new Thread(new ValuableTransporter(d1, treasureRoomGuardsman), "Valuable Transporter 2");


    valuableTransporter1.start();
    valuableTransporter2.start();


    Thread accountant1 = new Thread(new Accountant(treasureRoomGuardsman),"Accountant 1");
    accountant1.start();

    Thread king = new Thread(new King(treasureRoomGuardsman), "Your Majesty");
    king.start();

    Thread accountant2 = new Thread(new Accountant(treasureRoomGuardsman),"Accountant 2");
    accountant2.start();

    ValuableTransporter v3 = new ValuableTransporter(d1, treasureRoomGuardsman);
    Thread valuableTransporter3 = new Thread(v3,"Valuable Transporter 3");
    valuableTransporter3.start();
  }
}
