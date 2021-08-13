



//Class for objects to hold our info on directories and files/directories contained
//underneath them.
public class dirEntry
{
    public  String path;
    public  int fileCount;
    public  int dirCount;
    
    //Print all funtion to display the info in our desired way
    public void printAll()
    {
        System.out.println("\"" + path + "\"" + "," + dirCount + "," + fileCount);


        //Just another way to display the data
        /*        
        System.out.println("Path: " + path);
        System.out.println("Directory Count: " + dirCount);
        System.out.println("File Count: " + fileCount);
        */
    }
}