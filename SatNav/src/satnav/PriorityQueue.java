package satnav;

public class PriorityQueue {
    Vertex[] Queue = new Vertex[10]; 
    int Size = 0;

  public PriorityQueue()
  {
  }
  public boolean IsEmpty()
  {
    if (Size == 0)
    {
        return true;
    }
    else
    {
        return false;
    }
  }
  public int getQueueSize()
  {
    return Size;
  }

  public Vertex removeVertex(int info)
  {
      int i = 0;
      boolean found = false;
      Vertex data = null;
      for (i = 0; i < Queue.length && found == false; i++)
      {
          if(Queue[i].VertexId == info)
          {
              found = true;
              data = Queue[i];
          }          
      }
      if (found == true)
      {
          for (i = i; i < Queue.length-1; i++)
          {
              Queue[i] = Queue[i+1];
          }
          Queue[Size] = null;
          Size--;
      }
      return data;
  }

  public void addItem(Vertex item)
  {    
      if (Size == Queue.length)
      {
          Vertex[] Queue2 = new Vertex[Size*2];          
          System.arraycopy(Queue, 0, Queue2, 0, Size);           
          Queue = Queue2;         
      }
    if (Size == 0)
    {
      Queue[Size] = item; 
      Size++;
    }
    else
    {
        int index=Size;
        Queue[index] = item;
        Size++;

        int parent=(index-1)/2;
        while (index!=0 && Queue[index].combinedDistance < Queue[parent].combinedDistance)
        {
            Vertex temp = Queue[parent];
            Queue[parent] = Queue[index];
            Queue[index] = temp;
            index=parent;
            parent=(index-1)/2;
        } 
    }     
  }
  public void addItem1(Vertex item)
  {    
      if (Size == Queue.length)
      {
          Vertex[] Queue2 = new Vertex[Size*2];          
          System.arraycopy(Queue, 0, Queue2, 0, Size);           
          Queue = Queue2;         
      }
    if (Size == 0)
    {
      Queue[Size] = item; 
      Size++;
    }
    else
    {
        int index=Size;
        Queue[index] = item;
        Size++;

        int parent=(index-1)/2;
        while (index!=0 && Queue[index].Time < Queue[parent].Time)
        {
            Vertex temp = Queue[parent];
            Queue[parent] = Queue[index];
            Queue[index] = temp;
            index=parent;
            parent=(index-1)/2;
        } 
    }     
  }

  public Vertex getNextItem()
  {
      if (Size == 0)
      {
          return null;
      }
      Vertex temp = Queue[0];
      --Size;
      if (Size > 0)
      {
         Queue[0] = Queue[Size];
         swapNodes(0);
      }
      Queue[Size] = null;
      return temp;
   }

   public void swapNodes(int root)
   {
      int child;                        
      if ((2*root+1) >= Size)
      {
        child = root;        //no children
      }
      else 
          if ((2*root)+2 == Size)
          {
            child = (2*root)+1;     
          }
          else 
            if (Queue[(2*root)+1].combinedDistance< Queue[(2*root)+2].combinedDistance)
            {
                child = (2*root)+1;   //left child  
            }
            else
            {
                child = (2*root)+2;     //right child
            }
      //swapping nodes
      if (Queue[root].combinedDistance > Queue[child].combinedDistance)
      {
         Vertex temp = Queue[root];
         Queue[root] = Queue[child];
         Queue[child] = temp;
         swapNodes(child);
      }
   }


   public void MoveUp(int root)
   {       
       while (root > 0 && root < Size && (Queue[root].combinedDistance < Queue[(root-1)/2].combinedDistance))
       {
           Vertex temp = Queue[root];
           Queue[root] = Queue[(root-1)/2];
           Queue[(root-1)/2] = temp;
           root = (root-1)/2;
       }        
   }


  public String toString()
  {
    return super.toString();
  }
}
