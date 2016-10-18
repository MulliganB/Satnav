package satnav;

public class RouteInfo implements RouteInfoInterface
{
  public double distance = 0;
  public double Time = 0;
  public double AverageSpeed = 0;
  public int NoVertices = 0;
  public int[] RouteVertex = new int[50];
  public String Route;
  @Override
  public double GetDistanceInMiles()
  {
      return distance;
  }

  @Override
  public double GetTimeInMinues()
  {
      Time = distance/AverageSpeed;
      return Time;
  }
  
  @Override
  public int[] GetRouteVertexIDs()
  {
      return RouteVertex;
  }

  @Override
  public int GetNoOfExploredVertices()
  {
      return NoVertices;
  }

  @Override
  public String GetRoute(boolean incvertices)
  {
      return Route;
  }
}
