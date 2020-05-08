public interface Buffer<Valuable>
{
  public void put(Valuable element);
  public Valuable take();
  public Valuable look();
  public boolean isEmpty();
  public boolean isFull();
  public int size();
}
