/*
 * Karna Johnson
 * CSC 240-040
 * Project #3
 * Description: Using a model to communicate between the 
 * the two forms.
 */
//package labscheduler
package labscheduler;
//all imports
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Karna Johnson
 */
public class LabModel{
    //declarations
    private static String requestName;
    private static String requestEmail;
    private static String eventTitle;
    private static Integer participants;
    private static String startTime;
    private static String endTime;
    private static String specialRequest;
    private static String beginDate;
    private static String endDate;
    private static String day;
    private static boolean startampm;
    private static boolean endampm;
    
    
    //constructor
    public LabModel(){       
        //if requestName is null
        if (requestName == null){
            //then set the requestName to an empty string
            requestName = "";
        }
        //if requestEmail is null
        if (requestEmail == null){
            //then set requestEmail to an empty string
            requestEmail = "";
        }
        //if eventTitle is null
        if (eventTitle == null){
            //then set eventTitle to an empty string
            eventTitle = "";
        }
        //if participants is null
        if (participants == null){
            //set it to 1
            participants = 1;
        }
        //if startTime is null
        if (startTime == null){
            //then set the startTime to an empty string
            startTime = "";
        }
        //if endTime is null
        if (endTime == null){
            //then set endTime to an empty string
            endTime = "";
        }
        //if specialRequest is null
        if (specialRequest == null){
            //then set specialRequest to an empty string
            specialRequest = "";
        }
        //if beginDate is null
        if (beginDate == null){
            //then set beginDate to an empty string
            beginDate = "";
        }
        //if endDate is null
        if (endDate == null){
            //then set endDate to an empty string
            endDate = "";
        }
        //if day is null
        if (day == null){
            //then set day to an empty string
            day = "";
        } 
        
    }
    
    //all the sets
    public static void setRequestName(String name){requestName = name;}
    public static void setRequestEmail(String email){requestEmail = email;}
    public static void setEventTitle(String title){eventTitle = title;}
    public static void setParticipants(Integer part){participants = part;}
    public static void setSpecialRequest (String request){specialRequest = request;}
    public static void setStartTime(String time){startTime = time;}
    public static void setEndTime(String time){endTime = time;}
    public static void setBeginDate(String date){beginDate = date;}
    public static void setEndDate(String date){endDate = date;}
    public static void setDay(String today){day = today;}
    public static void setStartAMPM (boolean ampm){startampm = ampm;}
    public static void setEndAMPM (boolean ampm) {endampm = ampm;}
        
    //all the gets
    public static String getRequestName(){return requestName;}
    public static String getRequestEmail(){return requestEmail;}
    public static String getEventTitle(){return eventTitle;}
    public static Integer getParticipants (){return participants;}
    public static String getStartTime(){return startTime;}
    public static String getEndTime(){return endTime;}
    public static String getSpecialRequest(){return specialRequest;}
    public static String getBeginDate(){return beginDate;}
    public static String getEndDate(){return endDate;}
    public static String getDay(){return day;}
    public static boolean getStartAMPM() {return startampm;}
    public static boolean getEndAMPM() {return endampm;}
    
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
        //returning the getCalendar with beginDate and startTime
        return getCalendar(beginDate + " " + startTime + " "  + (startampm?"PM":"AM"));
    }
    //making a calendar method that will take the end of the calendar
    public Calendar getEndCalendar(){
        //returning the getCalendar with beginDate and endTime
        return getCalendar(beginDate + " " + endTime + " " + (endampm?"PM":"AM"));       
    }  
}
