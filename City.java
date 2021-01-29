public class City {

    private String name;
    private int x;
    private int y;
    private int num_edges;
    private Edge [] edges;
    private Edge lowest;

    public City()
    {
        name = "";
        x = 0;
        y = 0; 
        lowest = new Edge(); 
    }
    public City(String name, int x, int y) 
    {
        this.name = name;  
        this.x = x;
        this.y = y;
        lowest = new Edge(); 
        
    }
    public City(String name, int x, int y, int number_of_edges) 
    {
        this.name = name; 
        num_edges = number_of_edges; 
        this.x = x;
        this.y = y;
        edges = new Edge[num_edges];
        for (int i = 0; i < edges.length; i++)
            edges[i] = new Edge(); 
        lowest = new Edge(); 
        
    }
    
    public City(City copy)
    {
        name = copy.name;
        x = copy.x;
        y = copy.y;
       
        
    }
    
    public void setName(String name)
    {
        this.name = name;
    
    }
   
    public void setXCoordinate(int x)
    {
        this.x = x;
    
    }
    
    public void setYCoordinate(int y)
    {
        this.y = y; 
    
    }
    
    public String getName()
    {
        return name;
    
    }
    
    public int getXCoordinate()
    {
        return x;
    
    }
    
    public int getYCoordinate()
    {
        return y;
    
    }
    
    public void setEdges(int count)
    {
        num_edges = count; 
        edges = new Edge[num_edges];

        
    }
    
    public boolean compare(City check)
    {
        if(this == check)
        {
            return true;
        }
        
        else
            return false; 
        
    }

    public void CopyEdges(Edge [] city_edges)
    {
        for (int i = 0; i < edges.length; i++)
        {
            edges[i] = new Edge(city_edges[i]); 
        }
    }
    
}
