import java.util.HashMap;
import java.util.Map;

public class Mine //flyweight factory
{
  private static Map<String, Valuable> map = new HashMap<>();

  public static Valuable getValuable(String name)
  {
    Valuable valuable = map.get(name);

    if (valuable == null)
    {
      switch(name)
      {
        case "Diamond":
          valuable = new Diamond();
          map.put("Diamond", valuable); break;

        case "GoldNugget":
          valuable = new GoldNugget();
          map.put("GoldNugget", valuable); break;

        case "Jewel":
          valuable = new Jewel();
          map.put("Jewel", valuable); break;

        case "Ruby":
          valuable = new Ruby();
          map.put("Ruby", valuable); break;

        case "WoodenCoin":
          valuable = new WoodenCoin();
          map.put("WoodenCoin", valuable);
    }
  }
      return valuable;
  }
}
