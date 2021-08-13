import java.io.*;
import java.util.ArrayList;

/*
Application to recursively search through a path
and return the amount of files and directories per 
each directory. The root is given as a command line 
argument and don't forget that spaces in directories 
in windows need to be surrounded by quotation marks
*/

//Our main class where everything is really going down.
//We do reference another class, dirEntry, for our container of data
public class App {

    //Arraylist to hold our objects of dirEntry. For more info, look at the dirEntry.java
    public static ArrayList<dirEntry> dirList = new ArrayList<dirEntry>();

    //Our method to perform our recursive search taking the arguments of a path and the parent directory 
    public void getSub(String path, dirEntry parent)
    {
        
        File root = new File(path);                     //Creates the root file for our method, its the starting point for every iteration 
        File[] list = root.listFiles();                 //Creates an array of the objects below the root
        if (list == null) return;                       //Empty directory check
      
        for(File currentFile : list )                   //Loop to go through and check for directories
        {
            if(currentFile.isDirectory())
            {
                dirEntry test = new dirEntry();                     //Creating a new dirEntry object
                test.path = currentFile.getAbsolutePath();          //adding the path to the path field in our object
                setCounts(test);                                    //Running the setCounts method which determines our count fields
                dirList.add(test);                                  //Adds the object to the arrayList
                getSub(currentFile.getAbsolutePath(), test);        //Recurses back through the method
                parent.dirCount += test.dirCount;                   //These two add up the counts to the parent path obj counts
                parent.fileCount += test.fileCount;
            }
        }
    }


    //Adds the root to the array list and starts the recursive method
    public void startSearch(String path)
    {
        dirEntry ourRoot = new dirEntry();                  //Same beginning as before, we make a dirEntry obj and populate fields
        ourRoot.path = path;
        setCounts(ourRoot);
        dirList.add(ourRoot);                               //Add to arrayList here
        
        getSub(path, ourRoot);                              //Starts the getSub method
    }


    //This method takes a dirEntry object and will set the count fields to the 
    //amount of files/directories that are in that directory
    //Pretty much the same as the FolderViewer app I wrote, located in Java Projects/FolderView
    public void setCounts(dirEntry directoryInfo)
    {
        File root = new File(directoryInfo.path);
        File[] list = root.listFiles();
        if(list != null)                                
        {
            for(File currentFile : list)                        //Loop to add counters                
            {
                if(currentFile.isDirectory())
                    directoryInfo.dirCount++;
                else 
                    directoryInfo.fileCount++;
            }
        }
    }


    //Main
    public static void main(String[] args) throws Exception 
    {
        //OUTPUT : "PATH" + ", "  DIR COUNT + ", " FILE COUNT + ","
        App r = new App();                                                  //Create an App object
        r.startSearch(args[0]);                                             //command line args as input

        //Enhanced for loop to go through our arraylist of dirEntry objects
        for(dirEntry f : dirList)
        {
            f.printAll();               //calls the printAll method from dirEntry class
        }
    }
}
