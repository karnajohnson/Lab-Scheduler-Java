/*
 * Karna Johnson
 * CSC 240-040
 * Project #3
 * Description: getting the collection
 */

//package 
package labscheduler;

//all import statements
import java.io.*;
import java.util.*;
import javafx.beans.property.*;
import javafx.collections.*;
/**
 *
 * @author Karna Johnson
 */
public class EventCollectionModel {
    //making an arraylist of eventmodel for the items
    private ArrayList<EventModel> items;
    //making an eventmodel item
    private EventModel item;
    //default constructor
    public EventCollectionModel(){
        //initializing the arraylist
        items = new ArrayList<EventModel>();
        //initializing the eventmodel
        item = new EventModel();
    }
    //an additems method
    public void addItems(EventModel lab){
        //adding the eventmodel to the items
        items.add(lab);
    }
    //an get items method of arraylist eventmodel
    public ArrayList<EventModel> getItems() {
        //returing items
        return(items);
    } 
    //a sort method
    public void sort() {
        //sorting the collection
        Collections.sort(items);
    }
    //a readfile method
    public void readFile(){
        //try
        try{
            //making a new file from the events.csv file
            File myFile = new File("events.csv");
            //making a new filereader
            FileReader fileReader = new FileReader(myFile);
            //making a new bufferedreader
            BufferedReader reader = new BufferedReader(fileReader);
            //making a record string
            String record = "";
            //making a temp model 
            EventModel modelTmp;
            //whil the record is not null
            while ((record = reader.readLine()) != null) {
                //model is a new eventModel
		modelTmp = new EventModel();
                //parse the info 
                modelTmp.parseInfo(record);
                //add the model
                items.add(modelTmp);
            }
            //closing the reader
            reader.close();
        //catching an IOException    
        }catch(IOException ex){
            //printing out that there is an error
            System.out.println("Error reading file");
        }
    }
    //a writefile method
    public void writeFile(){
        //try
        try{
            //making a new file from events.csv
            File myFile = new File("events.csv");
            //making a new filewriter
            FileWriter fileWriter = new FileWriter(myFile);
            //making a new bufferedwriter
            BufferedWriter writer = new BufferedWriter(fileWriter);
            //having a for loop that goes through the collection
            for (int i = 0; i < items.size(); i++){
                //writing the items 
                writer.write(items.get(i).toString());
                //adding a new line
                writer.newLine();
            }           
            //closing the writer
            writer.close();
        }
        //catching an ioexception
        catch (IOException ex){
            //printing out that there was an error
            System.out.println("Error writing file");
        }
    }
}
