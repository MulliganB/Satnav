package satnav;

import java.io.*;

public class SatNav
{
  public static void main(String[] args)
  {
    // Create a new SatNavEngine
    SatNavEngineInterface engine=new SatNavEngine();

    // Load the vertex and edge data using the AddEdge and AddVertex methods
    LoadVertices(engine, "SheffieldCentre - ADS2SatNavData-Vertices.dat");
    LoadEdges(engine, "SheffieldCentre - ADS2SatNavData-Edges.dat");
  }
  
  private static void LoadVertices(SatNavEngineInterface engine, String filename)
  {
    byte[] data=new byte[12];
    
    try
    {
      BufferedInputStream in=new BufferedInputStream(new FileInputStream(filename));
      while (in.read(data)==12)
      {
        int id=(data[0]&0xFF)|((data[1]&0xFF)<<8)|((data[2]&0xFF)<<16)|((data[3]&0xFF)<<24);
        int x=(data[4]&0xFF)|((data[5]&0xFF)<<8)|((data[6]&0xFF)<<16)|((data[7]&0xFF)<<24);
        int y=(data[8]&0xFF)|((data[9]&0xFF)<<8)|((data[10]&0xFF)<<16)|((data[11]&0xFF)<<24);
        engine.AddVertex(id, x, y);
      }
      in.close();
    }
    catch (IOException e) { System.out.println(e); }
  }  

  private static void LoadEdges(SatNavEngineInterface engine, String filename)
  {
    int len;
    byte[] data=new byte[256];
    try
    {
      BufferedInputStream in=new BufferedInputStream(new FileInputStream(filename));
      while ((len=in.read())!=-1)
      {
        in.read(data, 0, len);
        String label=new String(data, 0, len);
        
        in.read(data, 0, 13);
        int from=(data[0]&0xFF)|((data[1]&0xFF)<<8)|((data[2]&0xFF)<<16)|((data[3]&0xFF)<<24);
        int to=(data[4]&0xFF)|((data[5]&0xFF)<<8)|((data[6]&0xFF)<<16)|((data[7]&0xFF)<<24);
        int upper=(data[8]&0xFF)|((data[9]&0xFF)<<8);
        int lower=(data[10]&0xFF)|((data[11]&0xFF)<<8);
        double distance=upper+(lower/1000.0);

        engine.AddEdge(label, from, to, distance, (data[12]&0x80)==0x80, data[12]&0x7F);
      }
      in.close();
    }
    catch (IOException e) { System.out.println(e); }
  }
}
