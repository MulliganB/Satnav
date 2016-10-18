package satnav;

public class EdgeVector
{
  public int maxcap;
  public int growby;
  public int noofitems, length;
  public Edge[] data;

  public EdgeVector()
  {
    maxcap = 100;
    length = 0;
    growby = 10;
    data = new Edge[maxcap];
  }

  public int GetNoOfItems()
  {
    return noofitems;
  }

  public Edge GetItem(int index)
  {
    return (Edge)(index>=0 && index<noofitems?data[index]:null);
  }

  public void AddItem(Edge item)
  {
    if (noofitems==data.length)
      GrowData();
    data[noofitems++]=item;
  }

  public boolean InsertItem(int index, Edge item)
  {
    if (index>=0 && index<=noofitems)
    {
      if (noofitems==data.length)
        GrowData();
      for (int i=noofitems; i>index; i--)
        data[i]=data[i-1];
      data[index]=item;
      ++noofitems;
      return true;
    }
    else
      return false;
  }

  public boolean DeleteItem(int index)
  {
    if (index>=0 && index<noofitems)
    {
      --noofitems;
      for (int i=index; i<noofitems; i++)
        data[i]=data[i+1];
      return true;
    }
    else
      return false;
  }

  private void GrowData()
  {
    Edge[] tmp=new Edge[noofitems+growby];
    System.arraycopy(data, 0, tmp, 0, noofitems);
    data=tmp;
  }

}