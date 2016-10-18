package satnav;

public class Vertex {
    public int VertexId, x, y;
    public boolean visited = false;   
    public EdgeVector adjacencies;
    public double minDistance = 0;
    public Vertex previous;
    public double combinedDistance;  // combinedDistance = distanceFromSource + additionalDistance
    public double distanceFromSource;  
    public double additionalDistance;  // Distance from previous Vertex 
    public double Time;
    public boolean queueIN, queueOUT;
    

    public Vertex(int VertexId, int x, int y)
    {
        this.VertexId = VertexId;
        this.x = x;
        this.y = y; 
        this.adjacencies = new EdgeVector();
        this.distanceFromSource = 0;
        this.combinedDistance = 0;
        this.Time = 0;
        this.queueIN = false; //false = not in the IN queue
        this.queueOUT = false; //false = not selected
    }   
    public int information()
    {
        return VertexId;
    }
    public void updateVertexId(int Id)
    {
        this.VertexId = Id;
    }
    public void updateX(int X)
    {
        this.x = X;
    }
    public void updateY(int Y)
    {
        this.y = Y;
    }
    public void addEdge(Edge city)
    {
       this.adjacencies.AddItem(city);       
    }  
   
}
