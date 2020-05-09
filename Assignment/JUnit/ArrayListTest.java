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

  @Test
  public void addZero()
  {
    assertThrows(IndexOutOfBoundsException.class,()->{list.get(0);});
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

  @Test void addWithNoIndexZero ()
  {
    assertThrows(IndexOutOfBoundsException.class, ()->{list.get(0);});
  }
  @Test void addWithNoIndexOne ()
  {
    list.add("0");
assertEquals("0",list.get(0));
  }
  @Test void addWithNoIndexMany ()
  {
    list.add("0");
    assertEquals("0",list.get(0));
    list.add("1");
    assertEquals("1",list.get(1));
    list.add("2");
    assertEquals("2",list.get(2));
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
  @Test void setZero()
  {
    assertThrows(IndexOutOfBoundsException.class, () ->
    {
      list.set(0, "69");
    });
  }

  @Test void setOne()
  {
    list.add("66");
    list.set(0, "69");
    assertEquals("69", list.get(0));
  }

  @Test void setMany()
  {
    for (int i = 0; i < 100; i++)
    {

      list.add(""+i);
    }

    for (int i = 0; i < 100; i++)
    {
      list.set(i,""+ i + 1);
      assertEquals(""+i + 1, list.get(i));
    }
  }

  @Test void setBoundary()
  {
    list.add(0, "69");
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.set(-1, "69");
    });

    list.set(0, "66");
    assertEquals("66", list.get(0));

    for(int i = 1; i < 100; i++) {
      list.add(""+i);
    }

    list.set(99, "69");
    assertEquals("69", list.get(99));

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.set(100, "69");
    });
  }

  @Test void setExceptions()
  {
    for(int i = 0; i < 100; i++) {

      list.add(""+i);
    }

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.set(-100, "101");
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.set(175, "101");
    });
  }
  @Test public void sizeZero(){
    assertEquals(0,list.size());
  }
  @Test public void sizeOne(){
  list.add("1");
  assertEquals(1,list.size());
  }
  @Test public void sizeMany(){
    //many can not be
  }
  @Test public void sizeBoundary(){
  //size by default is 0, can't test for -1

    //High boundary
    list.add("1");
    list.add("2");
    list.add("3");
    assertEquals(3,list.size());
  }
  @Test public void sizeException(){
  ArrayList list1 = null;
  assertThrows(NullPointerException.class, ()->{list1.size();});
  }

  @Test public void containsZero(){
    assertEquals(false,list.contains("0"));

  }
  @Test public void containsOne(){
    list.add("0");
    assertEquals(true,list.contains("0"));
  }

  @Test public void containsMany(){
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    assertEquals(true,list.contains("0"));
    assertEquals(true,list.contains("3"));
    assertEquals(false,list.contains("5"));
  }

  @Test public void containsBoundary(){
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    assertEquals(true,list.contains("0"));
    assertEquals(true,list.contains("1"));
    assertEquals(false,list.contains("-1"));

    assertEquals(true,list.contains("3"));
    assertEquals(true,list.contains("4"));
    assertEquals(false,list.contains("5"));
  }
  //No Exceptions for contains

  @Test public void indexOfElementZero()
  {
    assertEquals(-1,list.indexOf("0"));
  }
  @Test public void indexOfElementOne()
  {
    list.add("0");
    assertEquals(0,list.indexOf("0"));
  }
  @Test public void indexOfElementMany()
  {
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    assertEquals(1,list.indexOf("1"));
    assertEquals(3,list.indexOf("3"));
  }

  @Test public void indexOfElementBoundary()
  {
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    assertEquals(4,list.indexOf("4"));
    //can get bigger index than actual
  }

  @Test public void removeByIndexZero (){
assertThrows(IndexOutOfBoundsException.class,()->{list.remove(0);});
  }
  @Test public void removeByIndexOne (){
    list.add("0");
    assertEquals("0",list.remove(0));
  }
  @Test public void removeByIndexMany (){
list.add("0");
list.add("1");
list.add("2");
list.add("3");
    assertEquals("0",list.remove(0));
    assertEquals("1",list.remove(0));

    assertEquals("2",list.remove(0));
    assertEquals("3",list.remove(0));
  }
  @Test public void removeByIndexBoundary (){
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");

    assertThrows(IndexOutOfBoundsException.class,()->{
      list.remove(-1);
    });

    assertEquals("4",list.remove(4));
    assertThrows(IndexOutOfBoundsException.class,()->{
      list.remove(5);
    });
  }
  @Test public void removeByIndexException (){
    //done in boundary
  }

  @Test public void removeByElementZero (){
    //It should return illegalStateException but it returns true or false only
    assertThrows(IllegalStateException.class,()->{list.remove("0");});
  }
  @Test public void removeByElementOne (){
    //It should return element but it returns true or false only
    list.add("0");
    assertEquals("0",list.remove("0"));
  }
  @Test public void removeByElementMany (){
    //It should return elements but it returns true or false only
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    assertEquals("1",list.remove("1"));
    assertEquals("3",list.remove("3"));
  }
  @Test public void removeByElementBoundary (){
    //same errors as in up methods
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");

    assertThrows(IllegalStateException.class,()->{list.remove("-1");});

    assertEquals("4",list.remove("4"));

    assertThrows(IllegalStateException.class,()->{list.remove("5");});
  }
  @Test public void removeByElementExceptions (){
    //Done in boundary
     }
     @Test public void isEmptyZero()
     {
       assertEquals(true,list.isEmpty());
     }

  @Test public void isEmptyOne()
  {
    list.add("0");
    assertEquals(false,list.isEmpty());

  }
  @Test public void isEmptyMany()
  {
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    assertEquals(false,list.isEmpty());
  }

  @Test public void toStringZero (){
    //should be {} but instead is [0]

    assertEquals("[]",list.toString());
  }
  @Test public void toStringOne (){
    //should be {0} but instead is [0]
    list.add("0");

    assertEquals("[0]",list.toString());
  }
  @Test public void toStringMany (){
    //should be {0,1,2,3} but instead is [0, 1, 2, 3]
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");

    assertEquals("[0, 1, 2, 3]",list.toString());
  }
  @Test public void toStringBoundaries (){

  }
}
