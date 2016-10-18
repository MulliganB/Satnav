package satnav;

public class VertexVector
{  
  public int maxcap;
  public int growby;
  public int noofitems, length;
  public Vertex[] data;

  public VertexVector()
  {
      maxcap = 100;
      length = 0;
      growby = 10;
      data = new Vertex[maxcap];
  }

  public int GetNoOfItems()
  {
    return noofitems;
  }

  public Vertex GetItem(int index)
  {
    return (Vertex)(index>=0 && index<noofitems?data[index]:null);
  }
  public int FindItem(int searchId)
  {
      int low=0;
      int high = length - 1;
       
    return binarySearch(searchId, low, high);
  }

  public void AddItem(Vertex value)
  {
      int counter1 = 0;
      int location = 0;
      int locCounter = 0;
      if(maxcap > length)
      {
          if(length == 0)
          {
            data[0] = value;
            length++;
          }
          else
          {
            int counter = length;
            for (int i=1; i < counter; i++)
            {
               Vertex[] temp = new Vertex[maxcap];
                
               if(counter1 == 0)
               {
                if (data[i] == value)
                {
                  for (int j=0; j < length; j++)
                  {
                     temp[j+1] = data[j]; 
                  }
                  data=temp;
                  data[i-1] = value;
                  length++;
                  counter1++;
                }  
                else
                {
                    if(data[i] == value)
                        {
                            for (int j=0; j < length; j++)
                            {
                                if (data[j] == value)
                                {
                                    if(locCounter == 0)
                                    {
                                        location = j;
                                        locCounter++;
                                    }
                                    temp[j+1] = data[j];
                                }
                                else
                                     temp[j] = data[j];
                            }
                            if(locCounter == 1)
                            {
                              data = temp;
                              data[location] = value;
                              length++;
                            }
                            else
                            {
                              data=temp;
                              data[i+1] = value;
                              length++;
                            }
                            counter1++;
                        }
                    }
               }
            }
          }
      }
      else
      {
            maxcap += growby;
            Vertex[]temp3 = new Vertex[maxcap];
            for (int i=0; i < length; i++)
             {
                    if (data[i] == value)
                    {
                     for (int j=0; j < length; j++)
                     {
                         temp3[j+1] = data[j];
                     }
                     data=temp3;
                     data[i] = value;
                     length++;
                    }
                    else
                    {  
                        if(data[i] == value)
                        {
                            for (int j=0; j < length; j++)
                            {
                                if (data[i] == value)
                                {
                                    temp3[j+1] = data[j];
                                }
                                else
                                     temp3[j] = data[j];
                            }
                           data=temp3;
                           data[i+1] = value;
                           length++;
                        }
                    }
             }
            if(length == 0)
            {
                data[0] = value;
                length++;
            } 
      }
  }

  public boolean InsertItem(int index, Vertex item)
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

  public void DeleteItem(int index)
  { 
      if (index < length && index >= 0)
      {
          data[index] = null;
          if (data[index+1] != null)
          {
              int j = index;
              for(int i = (index+1); i<=length; i++)
              {
                  data[j] = data[i];
                  j++;
              }
          }
          length--;
      }
  }

  private void GrowData()
  {
    Vertex[] tmp=new Vertex[noofitems+growby];
    System.arraycopy(data, 0, tmp, 0, noofitems);
    data=tmp;
  }

  public int binarySearch(int searchId, int low, int high)
  {
      if(low>high)
          return -1;
      int mid = (low + high)/2;
      if (data[mid].VertexId == searchId)
          return mid;
      else
          if (data[mid].VertexId == searchId)
              return binarySearch(searchId, mid+1, high);
          else
              return  binarySearch(searchId, low, mid-1);
  }
}