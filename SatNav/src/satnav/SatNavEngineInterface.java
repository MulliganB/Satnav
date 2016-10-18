package satnav;

/**
 * This interface is the top-level entry-point for your SatNav engine
 * @author Michael Meredith
 */
public interface SatNavEngineInterface 
{
  /**
   * This method simply returns your SHU logon ID
   * 
   * @return your SHU logon ID 
   */
  public String GetSHUID();
  
  
  /**
   * This method will be repeatedly called to add all the vertices of the satnav
   * system before any edges are added.  The vertexid parameter is an integer value
   * representing the id of the vertex.  The x and y parameters are given using the
   * British Grid coordinate system and are in units of metres.
   * Vertices can be added in any order and their ids do not make a consecutive run 
   * of numbers.  Vertex ids will vary between different datasets
   * 
   * @param vertexid is a unique integer id that defines this vertex
   * @param x is the British Grid x-coordinate of this vertex with metre resolution
   * @param y is the British Grid y-coordinate of this vertex with metre resolution
   */
  public void AddVertex(int vertexid, int x, int y);

  
  /**
   * All vertices will be added to the satnav system before any edges are created
   * The fromid and toid will always be valid IDs within the vertex collection
   * distance is given in metres, speedlimit is given in miles per hour
   * If this edge is directed then the parameter oneway is true.
   * Edges can be added in any order.
   * 
   * @param label is the name of this edge, typically a street name
   * @param fromid is the vertex id this edge starts from
   * @param toid is the vertex id this edge goes to
   * @param distance is the distance in metres between the two vertices
   * @param oneway if this is true, this route only goes from the start to the end vertex.  If this is false, the edge can be traversed in both directions
   * @param speedlimit is the speed limit along this edge in miles per hours
   */
  public void AddEdge(String label, int fromid, int toid, double distance, boolean oneway, int speedlimit);

  
  /**
   * Constant variables that define the types of route that might be
   * requested during a call to the GetRoute method
   */
  public static final int ROUTE_SHORTEST_DISTANCE=1;
  public static final int ROUTE_QUICKEST_TIME=2;
  
  /**
   * Calculates the route from start to end
   * The start parameter is an array of two Strings that define the junction vertex
   * between the two edges that have these labels.  e.g. "Arundel Street", "Furnival Street"
   * would indicate that the start node is where these two edges/roads cross.  You can assume
   * that only one vertex will match the required junction point between.
   * The end parameter is an array of two strings that define the junction vertex
   * between the two edges that have these labels as per the start parameter
   * The routetype parameter is one of the ROUTE_ constants that defines the type of
   * route that is being asked for, i.e. shortest distance or quickest time
   * 
   * The return object is a RouteInfo object that contains the route information
   * from the start point to the end point
   * 
   * @param start contains two street names that define the junction vertex where this route should start
   * @param end contains two street names that define the junction vertex where this route should end
   * @param routetype is one of the ROUTE_ constants that define what kind of route should be returned
   * @return an instance of RouteInfoInterface that contains details about the calculated route
   */
  public RouteInfoInterface GetRoute(String start[], String end[], int routetype);
}
