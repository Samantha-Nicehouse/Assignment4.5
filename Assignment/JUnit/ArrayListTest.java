import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ArrayListTest
{
  private ArrayList<String> list;
  @BeforeEach public void setup()
  {
    list = new ArrayList<>();
  }

  @Test public void addOne()
  {
    list.add(0, "Zero");
    assertEquals("Zero", list.get(0));
  }

  @Test public void addMany()
  {
    list.add(0, "one");
    list.add(1, "two");
    list.add(2, "three");
    list.add(3, "five");
    assertEquals("one", list.get(0));
    assertEquals("two", list.get(1));
    assertEquals("three", list.get(2));
    assertEquals("five", list.get(3));
  }

  @Test public void addBoundary()
  {

    assertThrows(IndexOutOfBoundsException.class, ()->{list.add(-1, "minus");});

  }

  @Test public void addExceptions()
  {
    assertThrows(IndexOutOfBoundsException.class, ()->{list.add(5, "five");});
  }

  @Test public void getZero()
  {
    assertThrows(IndexOutOfBoundsException.class, ()->{list.get(0);});
  }

  @Test public void getOne()
  {
    list.add(0, "one");
    assertEquals("one", list.get(0));
  }

  @Test public void getMany()
  {
    list.add(0, "one");
    list.add(1, "two");
    list.add(2, "three");
    list.add(3, "two");
    assertEquals("one", list.get(0));
    assertEquals("three", list.get(2));

  }

  @Test public void getBoundary()
  {
    list.add(0, "one");
    list.add(1, "two");
    list.add(2, "three");
    list.add(3, "four");
    list.add(4, "five");

    //lower boundary
    assertThrows(IndexOutOfBoundsException.class, ()->{list.get(-1);});
    assertEquals("two", list.get(1));

    //high boundary
    assertEquals("four", list.get(3));
    assertEquals("five", list.get(4));
    assertThrows(IndexOutOfBoundsException.class, ()->{list.get(5);});
  }


}
