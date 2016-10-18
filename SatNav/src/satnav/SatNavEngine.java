package satnav;

public class SatNavEngine implements SatNavEngineInterface
{
  public VertexVector Vertex = new VertexVector();
  public EdgeVector Edge = new EdgeVector();
  public Dijkstra result = new Dijkstra();
  public RouteInfo infoResult = new RouteInfo();
  
  @Override
  public String GetSHUID()
  {
    return "b3029142"; 
  }

  @Override
  public void AddVertex(int vertexid, int x, int y)
  {
      Vertex temp = new Vertex(vertexid, x, y);
      Vertex.AddItem(temp);
  }

  @Override
  public void AddEdge(String label, int fromid, int toid, double distance, boolean oneway, int speedlimit)
  {
      int i = 0;
      Edge temp = new Edge(label, fromid, toid, distance, oneway, speedlimit);
      
      while(i != Vertex.GetNoOfItems())
      {
          int temp1 = Vertex.GetItem(i).VertexId;
          if(temp1 == temp.fromid)
          {
              Vertex.GetItem(i).addEdge(temp);
          }
          else if(temp1 == temp.toid)
          {
              Vertex.GetItem(i).addEdge(temp);
          }
          i++;
      }
  }

  @Override
  public RouteInfoInterface GetRoute(String[] start, String[] end, int routetype)
  {
      int startLocation = 0;
      int endLocation = 0;
      //Searching for the start vertex  
      for(int i = 0; i <= Vertex.GetNoOfItems();i++)
      {
          for(int j = 0; j <= Vertex.GetItem(i).adjacencies.GetNoOfItems(); j++)
          {
              if(Vertex.GetItem(i).adjacencies.GetItem(j).label.equals(start[0]))
              {//found one possible vertex, checking if the other edge crosses the selected vertex
                  for(int k = 0; k <= Vertex.GetItem(i).adjacencies.GetNoOfItems(); k++)
                  {
                      if(Vertex.GetItem(i).adjacencies.GetItem(k).label.equals(start[1]))
                      {//both edges cross over this vertex, therefore coordinates are selected
                         startLocation = i;
                        //found vertex and stored location into temporary holder
                      }
                  }
              }
          }
      }
      //Searching for the end vertex
      for(int i = 0; i <= Vertex.GetNoOfItems();i++)
      {
          for(int j = 0; j <= Vertex.GetItem(i).adjacencies.GetNoOfItems(); j++)
          {
              if(Vertex.GetItem(i).adjacencies.GetItem(j).label.equals(start[0]))
              {//found one possible vertex, checking if the other edge crosses the selected vertex
                  for(int k = 0; k <= Vertex.GetItem(i).adjacencies.GetNoOfItems(); k++)
                  {
                      if(Vertex.GetItem(i).adjacencies.GetItem(k).label.equals(start[1]))
                      {//both edges cross over this vertex, therefore coordinates are selected
                        endLocation = i;
                      }
                  }
              }
          }
      }
      
      if(routetype == 1)
      {
        result.Dijkstra(Vertex.GetItem(startLocation), Vertex.GetItem(endLocation), routetype, Vertex, infoResult);
      }
      else if(routetype == 2)
      {
        result.Dijkstra(Vertex.GetItem(startLocation), Vertex.GetItem(endLocation), routetype, Vertex, infoResult);
      }
      else
      {
          return null;
      }
      return infoResult;
  } 
  
}
