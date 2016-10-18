package satnav;

public class Edge {
    public String label;
    public int fromid, toid, speedlimit;
    public double distance;         
    public boolean visited;
    public boolean oneway;

    public Edge(String label, int fromid, int toid, double distance, boolean oneway, int speedlimit)
    {
        this.label = label;
        this.fromid = fromid;
        this.toid = toid;
        this.distance = distance; 
        this.speedlimit = speedlimit;
        this.visited = false;
        this.oneway = oneway;
    }    
}
