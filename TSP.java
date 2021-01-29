
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Object; 


public class TSP {

    private City [] original_list;
    private City [] Solution;
    private City Lowest;
    private City current_city;
    private int num_cities;
    private double total_distance = 0;
    private City start;

    //Finished
    //Counts line in order to determine the size of Object array original_list and Solution
    private int count_lines()
    {
       
       Scanner inputStream = null;
       String lines = null;
       int count = 0;
       
       //Try to open file. For the argument for FileInputStream, use the directory where the inputfile is located
       try
       {
           inputStream = new Scanner(new FileInputStream("/home/light/NetBeansProjects/TravelingSalesmanProblem/src/main/java/input.txt"));
       }
       
       catch(FileNotFoundException e)
       {
           System.out.println("Error: File not found");
           System.exit(0); 
       }
       
       //For each line, increment count by one
       while(inputStream.hasNextLine())
       {
           lines = inputStream.nextLine();
           count++;
       }
       
       //Close text file
       inputStream.close();
       
       return count; 
        
    }
    
    
    //Finished
    //Set starting city
    //Remove break and use boolean instead
    private City setStartCity(String name)
    {
       
        boolean found = false;
        //City start = null;
        for (int i = 0; i < original_list.length; i++)
        {
            String target = original_list[i].getName();
            String target_city = new String(target);
            String city  = new String(name);
            
            if(city.equals(target_city))
                
                start = new City(original_list[i]);
                found = true;
         
            if (found == true)
            {
                break; 
            }
        }
         
        return start;
    }
    
    private double calculateDistance(Edge Future_city)
    {
        double distance = Future_city.getDistance(); 
        total_distance = total_distance + distance;
        return total_distance; 
    }
    
    //Finished
    //Create new objects in array original_solution
    private void AddCities(int num) 
    {
        num_cities = num; 
        original_list = new City[num_cities];
        Solution = new City[num_cities];
        
       Scanner inputStream = null;
       String name;
       int x;
       int y;
       int i = 0;
       
       try
       {   //For the argument in FileInputStream, the user must provide the working directory where the project files are stored. 
           inputStream = new Scanner(new FileInputStream("/home/light/NetBeansProjects/TravelingSalesmanProblem/src/main/java/input.txt"));
       }
           //Read error if file doesn't exist
       catch(FileNotFoundException e)
       {
               System.out.println("Error: File not found");
               System.exit(0); 
       }
       
        //For each line in the file, extract the name, gpa and ID of each student and store into an object of Students[], as long as Students[index] points to null 
        while(inputStream.hasNextLine() && i < num_cities)
        {
            name = inputStream.next();
            x = inputStream.nextInt();
            y = inputStream.nextInt();
            if(original_list[i] == null)
            {
                original_list[i] = new City(name, x, y); 
                i++;
            }
                   
        }
                   
        //Close file
        inputStream.close();
    }
    
    private double getFinalDistance(double Distance_to_start)
    {
        total_distance = total_distance + Distance_to_start;
        return total_distance;
        
    }
    
    //Finished
    //Display original list of cities
    private void displayOriginalList()
    {
        String name;
        int x;
        int y;
        
        System.out.println("Original Order");
        for(int i = 0; i < original_list.length; i++)
        {
            name = original_list[i].getName();
            x = original_list[i].getXCoordinate();
            y = original_list[i].getYCoordinate();
            System.out.print(name);
            System.out.print("\t");
            System.out.printf("%d", x);
            System.out.print("\t");
            System.out.printf("%d", y);
            System.out.print("\t");
            System.out.println();
        }
        
    }
    
    //Finished
    //Display solution to the problem 
    private void displaySolution()
    {
        System.out.println();
        String name;
        int x;
        int y;
        int Solution_Size = Solution.length;
        int final_x = Solution[Solution_Size-2].getXCoordinate();
        int final_y = Solution[Solution_Size-2].getYCoordinate();
        int start_x = start.getXCoordinate();
        int start_y = start.getYCoordinate();
        double Distance_to_start = Math.sqrt(Math.pow(start_x - final_x, 2) + Math.pow(start_y - final_y, 2));
        total_distance = getFinalDistance(Distance_to_start);
        
        System.out.println("Final Route");
        for(int i = 0; i < Solution.length-1; i++)
        {
            name = Solution[i].getName();
            x = Solution[i].getXCoordinate();
            y = Solution[i].getYCoordinate();
            System.out.print(name);
            System.out.print("\t");
            System.out.printf("%d", x);
            System.out.print("\t");
            System.out.printf("%d", y);
            System.out.print("\t");
            System.out.println();
            
        }
        System.out.println();
        System.out.print("Total Distance");
        System.out.printf("%.2f", total_distance);
        System.out.println();
        
    }
    
    
    
    private void cityEdgetoCurrentCity(City current_city, Edge [] city_edges)
    {
       current_city.CopyEdges(city_edges); 
       
        
    }
    
    
    //Finished
    //Read a text file and generate a list of cities and a solution list
    public TSP() 
    {
        int num = count_lines(); 
        AddCities(num);
        Lowest = new City();
        current_city = new City();
    }

    //Algorithm used to determine the route
    public void closestNeighborHeuristic()
    {
        
        displayOriginalList();
        
        Edge Future_city = new Edge();                //Edge that leads to thefuture city
        Scanner inputCity = new Scanner(System.in);   //Used to input the start city 
        int count = 0;                                //Count the lines needed for the size of object arrays city_edges and current city edges 
        String next_city = "";                        //Stores the name of the potential city to be visited in the array city_edges
        String future;                                //The name of the city that is confirmed to be the next place to go
        Edge [] city_edges;                           //Stores all possible edges for the current city
        double distance;                              //Distance from current city to future city
        int x = 0;                                    //x-Coordinate for next city
        int y = 0;                                    //y-Coordinate for next city
        int current_x = 0;                            //x-Coordinate for current city
        int current_y = 0;                            //y-Coordinate for current city
        boolean found = false;                        //Used to verify the future city in the original list
        String target ="";
        String name ="";
        City delete_city;
        boolean continue_search = false;
        boolean finished = false;
        int Solution_index = 0;
        
        //Ask user to set Starting city
        current_city = setStartCity("A"); 
        
        //For each city that is being currently visited, count the number of cities that can be visited in the future, excluding ones that are visited once 
        for (int index = 0; index < original_list.length; index++)
        {
            
            finished = false; 
            count = 0; 
            boolean start = false; 
            if(original_list[index] != null)
                start = true;
            else
                start = false; 
                
            if(start == true)
            {
                target = original_list[index].getName();
                name = new String(target);
                String current = current_city.getName();
                String verify = new String(current);
                
                if(verify.equals(name))
                {
                    for(int j = 0; j < original_list.length; j++)
                    {
                        if(original_list[j] != null)
                        {
                            target = original_list[j].getName();
                            name = new String(target);
                            boolean equal = false;
                            if(verify.equals(name))
                                equal = true;
                            if(equal == false)
                                count++;
                            
                        }
                        
                    }
    
        
                //Generate number of edges to each city in current city along with and array of the edges
                    current_city.setEdges(count);
                    city_edges = new Edge [count]; 
                
                    int city_edge_index = 0; 
                    //If an object in array is not equal to the current city, create a new object and store into array city_edges
                    for(int k = 0; k < original_list.length; k++)
                    {
                        if(original_list[k] != null)
                        {
                            target = original_list[k].getName();
                            name = new String(target);
                            current = current_city.getName();
                            verify = new String(current);
                            boolean equal = false; 
                            if(verify.equals(name))
                                equal = true;
                            if(equal == false)
                            {
                                next_city = original_list[k].getName();
                                x = original_list[k].getXCoordinate();
                                y = original_list[k].getYCoordinate();
                                current_x = current_city.getXCoordinate();
                                current_y = current_city.getYCoordinate();
                                distance = Math.sqrt(Math.pow(x - current_x, 2) + Math.pow(y - current_y, 2));
                                city_edges[city_edge_index] = new Edge(next_city, distance);
                                city_edge_index++;
                        
                            }
                            
                        }
                        
                    
                    }
                    
                //Copy the contents of city_edges into the edges in current city. Afterwards, determine the next city to visit        
                    cityEdgetoCurrentCity(current_city, city_edges);
                    Future_city = determineNextCity(city_edges);
                
                
                //Find future city and set it as current. Set previous current city as previous and delete from list
                    for(int i = 0; i < original_list.length; i++)
                    {
                        if(original_list[i] != null)
                        {
                            target = original_list[i].getName();
                            name = new String(target);
                            current = current_city.getName();
                            verify = new String(current);
                            boolean equal = false;
                            found = false;
                            if(verify.equals(name))
                                equal = true;
                            if(equal == false)    
                            {   
                                String next_city_name = Future_city.getName();
                                future = new String(next_city_name);
                                if(name.equals(future))
                                {
                                    City route = new City(original_list[i]);
                                    Solution[Solution_index] = new City(route);
                                    delete_city = new City(current_city); //
                                    current_city = route;
                                    for(int l = 0; l < original_list.length; l++)
                                    {
                                        if(original_list[l] != null)
                                        {
                                            target = delete_city.getName();
                                            name = new String(target);
                                            current = original_list[l].getName();
                                            verify = new String(current);
                                            if(verify.equals(name))
                                            {
                                                original_list[l] = null;
                                                break; 
                                            }
                                            
                                        }
                                        
                                    
                                    
                                    }
                                //delete_city = null;                   //
                                distance = calculateDistance(Future_city);
                                found = true;
                                Solution_index++;
                                finished = true; 
                            
                                }
                            }
                    
                        if(found == true)
                            break; 
                            
                            
                        }
                        
                        
                    
                    }
            
            
                
                }
                
                else
                    finished = false; 
                
            }
            
            else
            {
                city_edges = null; 
            }
            
            if(original_list == null)
            {
                break;
            }
                        
            if(finished == true)
            {
                city_edges = null;
                index = -1;            
                       
            }
            
        
        
            
        }
            
        displaySolution();
        
        
        
        
    }
    
    private Edge determineNextCity(Edge [] city_edges)
    {
        Edge Future_city = new Edge(); 
        double lowest_distance = 999999;
        double city_distance = 0;
        for(int i = 0; i < city_edges.length; i++)
        {
            city_distance = city_edges[i].getDistance();
            if(city_distance < lowest_distance)
            {
                lowest_distance = city_edges[i].getDistance();
                Future_city = city_edges[i];
            }
            
        }   
        return Future_city; 
        
    }
    
    
     
}
