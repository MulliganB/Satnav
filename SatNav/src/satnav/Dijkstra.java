package satnav;

public class Dijkstra 
{
    Vertex tempVertex = new Vertex(0,0,0);
    PriorityQueue priorityQueueIn = new PriorityQueue();
    PriorityQueue priorityQueueOut = new PriorityQueue();
    Edge tempEdge = new Edge(null, 0, 0, 0, false, 0);
    EdgeVector resultEdges = new EdgeVector();
    
    public void Dijkstra(Vertex startLocation, Vertex endLocation, int routetype, VertexVector allVertices, RouteInfo infoResult) 
    {
    Vertex vertexAdjacent;        
    startLocation.distanceFromSource = 0;
    startLocation.additionalDistance = 0;
    startLocation.combinedDistance = 0;
    startLocation.previous = startLocation;
    priorityQueueIn.addItem(startLocation);
    startLocation.queueIN = true;
    infoResult.RouteVertex[0] = startLocation.VertexId;
    int no = 0;
    
    while (!(priorityQueueIn.IsEmpty())) 
    {
        tempVertex = priorityQueueIn.getNextItem();  //making the next vertex ready for processing
        if (tempVertex != null && tempVertex.distanceFromSource != 0) 
        {
            if (tempVertex.VertexId == endLocation.VertexId) 
            {           //checking to see if the stored vertex is the end location
                for (int i = 0; i < priorityQueueOut.Size; i++)
                {
                    for (int j = 0; j < priorityQueueOut.Queue[i].adjacencies.GetNoOfItems(); j++)
                    {   //resetting the state of the vertices that were not needed
                        priorityQueueOut.Queue[i].adjacencies.GetItem(j).visited = false;
                    }
                }
                infoResult.distance = tempVertex.combinedDistance;
                for (int i = 1; i < priorityQueueIn.Size; i++)
                {
                    infoResult.RouteVertex[i] = priorityQueueIn.getNextItem().VertexId;
                }
                for (int i = 0; i < resultEdges.length; i++)
                {
                    infoResult.Route = resultEdges.GetItem(i).label+" -> ";
                }
            } 
            else 
            {
                priorityQueueOut.addItem(tempVertex);      //adds the vertex in question to the maybe store
                infoResult.RouteVertex[no] = tempVertex.VertexId;
                no++;
                for (int i = 0; i < tempVertex.adjacencies.GetNoOfItems() && tempVertex.adjacencies.GetItem(i).visited == false; i++) //for each adjacent edge of temp
                {
                    tempEdge = tempVertex.adjacencies.GetItem(i);       //storing the first edge in question
                    tempVertex.adjacencies.GetItem(i).visited = true;   //making sure that it is only visited once
                    if(infoResult.AverageSpeed == 0)
                        infoResult.AverageSpeed = (tempEdge.speedlimit+infoResult.AverageSpeed);
                    else
                        infoResult.AverageSpeed = (tempEdge.speedlimit+infoResult.AverageSpeed)/2;
                    vertexAdjacent = allVertices.GetItem(allVertices.FindItem(tempEdge.toid)); //getting the next vertex along that edge
                    //increasing the node counter
                    infoResult.NoVertices++;
                    //if the adjacent edge is in OUT queue, move to next adjacent edge
                    for (int j = 0; j < priorityQueueOut.getQueueSize(); j++) 
                    {
                        if (priorityQueueOut.Queue[j].VertexId == vertexAdjacent.VertexId && vertexAdjacent.queueIN ==  false) 
                        {
                            vertexAdjacent.queueOUT = true;
                            //setting the flag to state that it is in the OUT queue
                        }
                    }
                    if (vertexAdjacent.queueOUT == true && vertexAdjacent.queueIN == false) 
                    {
                        continue;
                    }
                    double tempDistance = (tempVertex.distanceFromSource + tempEdge.distance);
                    //checks if adjacent edge is in IN queue
                    boolean foundAdjacent = false;
                    for (int j = 0; j < priorityQueueIn.getQueueSize() && foundAdjacent == false; j++) 
                    {
                        if (priorityQueueIn.Queue[j].VertexId == vertexAdjacent.VertexId && vertexAdjacent.queueIN == true) 
                        {
                            foundAdjacent = true;   
                        }
                    }
                    if (!(foundAdjacent) || tempDistance < vertexAdjacent.distanceFromSource) 
                    {
                        vertexAdjacent.previous = tempVertex;
                        resultEdges.AddItem(tempEdge);
                        vertexAdjacent.distanceFromSource = tempDistance;
                        if (routetype == 2) 
                        {//quickest route choice
                            vertexAdjacent.Time = (vertexAdjacent.additionalDistance + vertexAdjacent.distanceFromSource)/(tempEdge.speedlimit);
                        }
                        else if(routetype == 1)
                        {//shortest distance choice
                            vertexAdjacent.combinedDistance = vertexAdjacent.additionalDistance + vertexAdjacent.distanceFromSource;
                        }
                        if (!(foundAdjacent) && routetype == 1) // if adjacent vertex isn't in IN queue, add it to In queue
                        {
                            priorityQueueIn.addItem(vertexAdjacent);    //if routetype one then order via distance
                            vertexAdjacent.queueIN = true;
                        }
                        else if(!(foundAdjacent) && routetype == 2)
                        {
                            priorityQueueIn.addItem1(vertexAdjacent);   //if routetype two then order via time taken
                            vertexAdjacent.queueIN = true;
                        }
                    }
                }
            }
        }
    }    
  }
}
