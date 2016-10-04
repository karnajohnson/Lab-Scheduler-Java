/*
 * Karna Johnson
 * CSC 240-040
 * Project #3
 * Description: getting the model data
 */
//the package
package labscheduler;
//all the import statements
import javafx.beans.property.*;
import java.util.*;
import java.text.*;

/**
 *
 * @author Karna Johnson
 */
//public class EventModel that implements Comparable of EventModel
public class EventModel implements Comparable<EventModel> {
    //declaring instance variables
    private String eventTitle;
    private String eventDate;
    private String startTime;
    private String endTime;
    private boolean startampm;
    private boolean endampm;
    //declaring properties
    private SimpleStringProperty eventProp;
    private SimpleStringProperty dateProp;
    private SimpleStringProperty startProp;
    private SimpleStringProperty endProp;
    private SimpleBooleanProperty startampmprop;
    private SimpleBooleanProperty endampmprop;
    //default constructor
    public EventModel(){
        this("","","",false,"", false);
    }
    //other constructor taking in the strings that are going 
    //to be passed through this constructor
    public EventModel(String t, String d, String s,boolean sampm, String e, boolean eampm){
        //initializing the variables
        eventTitle = t;
        eventDate = d;
        startTime = s;
        endTime = e;
        startampm = sampm;
        endampm = eampm;
        //initializing the properties
        eventProp = new SimpleStringProperty(eventTitle);
        dateProp = new SimpleStringProperty(eventDate);
        startProp = new SimpleStringProperty(startTime + " "+ (startampm?"PM":"AM"));
        endProp = new SimpleStringProperty(endTime + " " + (endampm?"PM":"AM"));
        startampmprop = new SimpleBooleanProperty(startampm);
        endampmprop = new SimpleBooleanProperty(endampm);
    }
    //overriding the compareTo method
    @Override
    public int compareTo(EventModel obj) {
        //returning the comparing of the startCalendars
        return getStartCalendar().compareTo(obj.getStartCalendar());
    }
    //getCalendar method
    public Calendar getCalendar(String date){
        //making a simpledateformat
        SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy hh:mm a");
        //setting date to null
        Date eventDate = null;
        //try the parsing of the date
        try{
            //parsing the date
            eventDate = format.parse(date);
        }
        catch (ParseException ex){
            System.out.println("error parsing date");
        }
        //getting a calendar instance
        Calendar cal = Calendar.getInstance();
        //setting the eventdate
        cal.setTime(eventDate);
        //returning the calendar
        return cal;
    }
    //making a calendar method that will take the start of the calendar
    public Calendar getStartCalendar(){
        //returning the getCalendar with eventDate, startTime, start am/pm
        return getCalendar(eventDate + " " + startTime + " " + (startampm?"PM":"AM"));
    }
    //making a calendar method that will take the end of the calendar
    public Calendar getEndCalendar(){
        //returning the getCalendar with eventDate, endTime, end am/pm
        return getCalendar(eventDate + " " + endTime + " " + (endampm?"PM":"AM"));       
    }
    //gets of properties
    public SimpleStringProperty getEventTitleProp(){
        return(eventProp);
    }
    public SimpleStringProperty getEventDateProp(){
        return(dateProp);
    }
    public SimpleStringProperty getStartTimeProp(){
        return(startProp);
    }
    public SimpleStringProperty getEndTimeProp(){
        return(endProp);
    }
    public SimpleBooleanProperty getStartAMPMProp(){
        return startampmprop;
    }
    public SimpleBooleanProperty getEndAMPMProp(){
        return endampmprop;
    }
    //sets 
    public void setEventTitle(String event){ 
        eventTitle = event;
        eventProp.set(event); 
    } 
    public void setEventDate(String date){ 
        eventDate = date;
        dateProp.set(date); 
    } 
    public void setStartTime(String time){ 
        startTime = time;
        startProp.set(time + " " + startampm); 
    } 
    public void setEndTime(String time){ 
        endTime = time;
        endProp.set(time + " " + endampm); 
    } 
    public void setStartAMPM (boolean ampm){
        startampm = ampm;
        startProp.set(startTime + " " + (ampm?"PM":"AM"));
        startampmprop.set(ampm);
    }
    public void setEndAMPM(boolean ampm){
        endampm = ampm;
        endProp.set(endTime + " " + (ampm?"PM":"AM"));
        endampmprop.set(ampm);
    }


    //gets of instance 
    public String getEventTitle(){ return(eventProp.get()); } 
    public String getEventDate(){ return(dateProp.get()); } 
    public String getStartTime(){ return(startProp.get()); } 
    public String getEndTime(){ return(endProp.get()); } 
    public boolean getStartAMPM() {return startampmprop.get();}
    public boolean getEndAMPM() {return endampmprop.get();}
    //method parseInfo that takes a string record
    public void parseInfo(String record){
        //if the record contains a comma
        if (record.contains(",")){
            //setting the eventTitle
            setEventTitle(record.split(",")[0]);
            //setting the eventDate
            setEventDate(record.split(",")[1]);
            //if the record contains a space
            if(record.split(",")[2].contains(" "))
            {
                //set the start time
                setStartTime(record.split(",")[2].split(" ")[0]);
                //if the record equals pm
                if (record.split(",")[2].split(" ")[1].trim().compareTo("PM") == 0)
                    //set it to pm
                    setStartAMPM(true);
                //otherwise
                else 
                    //set it to am
                    setStartAMPM(false);
            }
            //otherwise
            else
                //set the start time without a space
                setStartTime(record.split(",")[2]);
            //if the record contains a space
            if (record.split(",")[3].contains(" "))
            {
                //set the endtime with a space
                setEndTime(record.split(",")[3].split(" ")[0]);
                //if the record equals pm
                if (record.split(",")[3].split(" ")[1].trim().compareTo("PM") == 0)
                    //set it to pm
                    setEndAMPM(true);
                //otherwise
                else 
                    //set it to am
                    setEndAMPM(false);
            }
            //otherwise
            else
                //set the endtime
                setEndTime(record.split(",")[3]);
        }
    }
    //making a method to know if the events are going to be conflict
    public boolean isInConflict(EventModel obj){
        //returning that if the start calendar is bigger than the event start calendar and
       return ((getStartCalendar().compareTo(obj.getStartCalendar()) == 1 &&
                //if the start calendar is less than the event's end calendar or
                getStartCalendar().compareTo(obj.getEndCalendar()) == -1) ||
                //if the start calendar is less than the event's start calendar and
                (getStartCalendar().compareTo(obj.getStartCalendar()) == -1 &&
                //if the end calendar is bigger than the event's end calendar or
                getEndCalendar().compareTo(obj.getEndCalendar()) == 1) ||
                //if the end calendar is bigger than the event's start calendar and
                (getEndCalendar().compareTo(obj.getStartCalendar()) == 1 && 
                //if the end calendar is less than the event's end calendar or
                getEndCalendar().compareTo(obj.getEndCalendar()) == -1) || 
               //if the start calendar is equal to the event's start calendar and
               (getStartCalendar().compareTo(obj.getStartCalendar()) == 0 &&
               //if the end calendar is equal to the event's end calendar
               getEndCalendar().compareTo(obj.getEndCalendar()) == 0)); 
    }
    //overriding the tostring
    @Override
    public String toString(){
        //returing the eventtitle, eventdate, starttime,the start am/pm, endtime, end am/pm
        return eventTitle + "," + eventDate + "," + startTime + " " + (startampm?"PM":"AM")+ ","
                 + endTime + " " +(endampm?"PM":"AM")+ ",";
    }
}
