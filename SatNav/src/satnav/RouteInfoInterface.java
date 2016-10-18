package satnav;

/**
 * This class is used to contain information about a calculated route from the 
 * SatNavEngineInterface object
 * @author Michael Meredith
 */
public interface RouteInfoInterface
{
   
  /**
   * Returns the distance of the route in miles.  Note a double return so you 
   * should not round your distance to whole miles
   * 
   * @return route distance in miles.
   */
  public double GetDistanceInMiles();

  
  /**
   * Returns the time to traverse the route in minutes.  Note a double return 
   * so you should not round your time to whole minutes
   * 
   * @return route time in minutes.
   */
  public double GetTimeInMinues();
  
  
  /**
   * Returns an array of vertex ids in the route as they appear along the path. 
   *
   * @return integer array of vertex ids that are traversed along the path.  The array should be the exact size required.
   */
  public int[] GetRouteVertexIDs();

  
  /**
   * Returns the total number of vertices that were explored as part of the path 
   * planning algorithms - this will typically be more than the number of vertices
   * in the actual path
   * 
   * @return integer number of vertices explored during the path planning algorithm
   */
  /*  
   * planning algorithm */
  public int GetNoOfExploredVertices();

  
  /**
   * Returns the route information using the edge labels.  You should try and 
   * avoid repeated sequential labels.  Thus if a route includes two or more 
   * edges, one after another, that share the same label text, that label text is 
   * only displayed once.  For example, if the underlying route were to suggest:
   *   Furnival Street -> Furnival Street -> Furnival Street -> Furnival Square -> ...
   * Furnival Street is only displayed once, thus making the output appear like:
   *   Furnival Street -> Furnival Square -> ...
   * Note, this behaviour is only if the labels are repeated one after another and
   * not if the same street name appears later in the route having been split by other
   * street names
   * 
   * if incvertices is true, you should also include the vertex numbers
   *
   * When incvertices is false, a suggested return format for this method would be:
   *   Furnival Street -> Furnival Square -> Arundel Gate -> Norfolk Street
   * and when incvertices is true, the return might look a little like:
   *   (498)Furnival Street (1958, 1776) -> Furnival Square (1109, 386, 405, 425) -> Arundel Gate (371, 3073, 1762, 3071, 2599, 2617, 462) -> Norfolk Street (669, 472, 1143)
   * note that the numbers in the brackets indicate the vertex ids and more than one 
   * number means that edge labels would have been repeated if not removed)
   * 
   * @param incvertices true when vertex ids should be included in the returned string
   * @return String giving the path calculated by the route planning algorithm defined by the edges traversed
   */
  public String GetRoute(boolean incvertices);
}
