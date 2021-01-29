public class Edge {

    private String name;
    private double distance;
   
    public Edge()
    {
        name = "";
        distance = 0;
    }
    
    public Edge(String name, double distance)
    {
        this.name = name;
        this.distance = distance; 
    }
    
    public Edge (Edge city_edges)
    {
        String Edge_name;
        Double distance;
        Edge_name = city_edges.getName();
        distance = city_edges.getDistance();
        
        name = Edge_name;
        this.distance = distance; 
        
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDistance(double distance)
    {
        this.distance = distance;
    }
    
    public String getName()
    {
        return name; 
    }
    
    public double getDistance()
    {
        return distance; 
    }
    
}
