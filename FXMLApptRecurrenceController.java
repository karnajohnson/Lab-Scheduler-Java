/*
Karna Johnson
CSC 240-040
Project #3
Description: To make a appointment recurrence form to see if the 
            user needs an appointment more than one day of the week.
*/

//the package
package labscheduler;

//all import statements
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.text.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * FXML Controller class
 *
 * @author Karna Johnson
 */
public class FXMLApptRecurrenceController implements Initializable {

    //all the buttons, textfields, checkboxes
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancel;
    @FXML
    private ToggleButton btnTogBeginTime;
    @FXML
    private ToggleButton btnTogEndTime;
    @FXML
    private TextField txtBeginTime;
    @FXML
    private TextField txtEndTime;
    @FXML 
    private TextField txtBeginDate;
    @FXML
    private TextField txtEndDate;
    @FXML
    private CheckBox chkSunday;
    @FXML
    private CheckBox chkMonday;
    @FXML
    private CheckBox chkTuesday;
    @FXML
    private CheckBox chkWednesday;
    @FXML
    private CheckBox chkThursday;
    @FXML
    private CheckBox chkFriday;
    @FXML
    private CheckBox chkSaturday;
    @FXML
    private Label lblBeginTime;
    @FXML
    private Label lblEndTime;
    @FXML
    private Label lblBeginDate;
    @FXML
    private Label lblEndDate;
   
    //declarations
    private String togEnd;
    private String togStart;
    private String startTime;
    private String endTime;
    private int indexEnd;
    private int indexBegin;
    private String beginDate;
    private String endDate;
    private String endDDate;
    private String startDate;
    private String startDateTime;
    private String endDateTime;
    //the model that will help talk between
    //the two forms
    private LabModel model;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initializing the variables
        model = new LabModel();
        togEnd = "pm";
        togStart = "pm";
        startTime = "";
        endTime = "";
        indexBegin = 0;
        indexEnd = 0;
        beginDate = "";
        endDate = "";
        endDDate = "";
        startDate = "";
        startDateTime = "";
        endDateTime = "";
        //set the labmodel startampm to pm
        model.setStartAMPM(!btnTogBeginTime.isSelected());
        //set the labmodel endampm to pm
        model.setEndAMPM(!btnTogEndTime.isSelected());
        
    }    
    @FXML
    public void handlesBtnOK(ActionEvent event){  
        //getting the text from the txtBeginTime text field
        startTime = txtBeginTime.getText();
        //setting the start time model
        model.setStartTime(startTime);
        //if startTime is empty
        if (startTime.equals("")){
             //the begintime label will turn red when the entry is empty
            lblBeginTime.setTextFill(Paint.valueOf("#FF0000"));
             //the begin time text field will get focus
             txtBeginTime.requestFocus();      
             //using return so that the program won't continue until this is done.
             return;
        }
        //otherwise if the user does enter something
        else{
            //the begintime label will turn black when all is okay
            lblBeginTime.setTextFill(Paint.valueOf("#000000"));
            //a try catch block is being used so that if the user
            //enters something other than a number
            try{
                //if starttime doesn't contain a :
                if (!startTime.contains(":")){
                    //if the user enters a number less than 1 or greater than 12
                    //for the hour
                    if ((Integer.parseInt(startTime) < 1 || 
                        Integer.parseInt(startTime) > 12)){
                        //The beginTime label will turn red if the entry is wrong
                        lblBeginTime.setTextFill(Paint.valueOf("#FF0000"));
                        //the begin time text field will get focus
                        txtBeginTime.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return; 
                    }
                    //otherwise
                    else{
                        //getting the text from beginTime to startTime
                        startTime = txtBeginTime.getText();
                        //adding :00 to the string
                        startTime += ":00";
                    }
                }
                //if the text the user entered contains a :
                else if (startTime.contains(":")){
                    //getting the index to where the colon is
                    indexBegin = startTime.indexOf(":");
                    //if the user enters a number less than 1 or greater than 12
                    //for the hour
                    //or the user enters a number less than 0 or greater than 59
                    //for the minute
                    if ((Integer.parseInt(startTime.substring(0,indexBegin)) < 1 || 
                        Integer.parseInt(startTime.substring(0,indexBegin)) > 12)
                        || (Integer.parseInt(startTime.substring(indexBegin+1)) < 0 ||
                        Integer.parseInt(startTime.substring(indexBegin+1)) > 59))
                    {
                       //the begintime label will turn red when the entry is wrong
                        lblBeginTime.setTextFill(Paint.valueOf("#FF0000"));
                        //the begin time text field will get focus
                        txtBeginTime.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                    //otherwise if the user enters the correct format
                    else{
                        //the begintime label will turn black when the entry is okay
                        lblBeginTime.setTextFill(Paint.valueOf("#000000"));
                        startTime = txtBeginTime.getText();
                    }
                    //if there are more than 2 digits in the minutes
                    if (startTime.substring(indexBegin+1).length() < 2){
                        //The beginTime label will turn red if the entry is wrong
                        lblBeginTime.setTextFill(Paint.valueOf("#FF0000"));
                        //the begin time text field will get focus
                        txtBeginTime.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                }
            }
            //catching if the user enters something other than a number
            catch (NumberFormatException ex){
                //the begintime label will turn red when the entry is wrong
                lblBeginTime.setTextFill(Paint.valueOf("#FF0000"));
                //the begin time text field will get focused
                txtBeginTime.requestFocus();
                //using return so that the program won't continue until this is done.
                return;
            } 
            //the begintime label will turn black when the entry is okay
            lblBeginTime.setTextFill(Paint.valueOf("#000000"));
        }
        //the string endTime will get the text from the end time text field
        endTime = txtEndTime.getText();
        //setting the end time model
        model.setEndTime(endTime);
        //if the user doesn't enter anything
        if (txtEndTime.getText().equals("")){
            //the end time label will turn red when the entry is empty
             lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
             //the end time text field will get focused
             txtEndTime.requestFocus();   
             //using return so that the program won't continue until this is done.
             return;
        }
        //otherwise if the user does enter something
        else{
            //the endtime label will turn black when the entry is okay
            lblEndTime.setTextFill(Paint.valueOf("#000000"));
            //a try catch block to makes sure that the user 
            //enters numbers into the text field
            try{
                //if endtime doesn't contain a :
                if (!endTime.contains(":")){
                    //if the user enters a number less than 1 or greater than 12
                    //for the hour
                    if ((Integer.parseInt(endTime) < 1 || 
                        Integer.parseInt(endTime) >12)){
                        //The endtime label will turn red if the entry is wrong.
                        lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                        //the end time text field is focused
                        txtEndTime.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                    //otherwise
                    else{
                       //setting the text from txtEndTime to endTime
                       endTime = txtEndTime.getText();
                       //adding :00 to the string
                       endTime += ":00";
                    }
                }
                //if the string contains a :
                else if (endTime.contains(":")){
                    //getting the index of where the : is in the string
                    indexBegin = endTime.indexOf(":");
                    //if the user enters a number less than 1 or greater than 12
                    //for the hour
                    //or the user enters a number less than 0 or greater than 59
                    //for the minute
                   if ((Integer.parseInt(endTime.substring(0,indexBegin)) < 1 || 
                        Integer.parseInt(endTime.substring(0,indexBegin)) >12)
                        || Integer.parseInt(endTime.substring(indexBegin+1)) < 0 ||
                        Integer.parseInt(endTime.substring(indexBegin+1)) > 59)
                    {
                        //the end time label will turn red when the entry is wrong
                        lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                        //the end time text field is focused
                        txtEndTime.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                   //otherwise if the user enters everything correctly
                   else{
                       //the endtime label will turn black when the entry is okay
                       lblEndTime.setTextFill(Paint.valueOf("#000000"));
                       //setting the text from txtEndTime to endTime
                       endTime = txtEndTime.getText();
                   }
                   //if there is more than 2 digits in the minutes
                   if (endTime.substring(indexBegin+1).length() < 2){
                        //The endTime label will turn red if the entry is wrong
                        lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                        //the end time text field will get focus
                        txtEndTime.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                }
            }
            //catching the error if the user enters something
            //other than a number
            catch(NumberFormatException ex)
            {
                //the end time label will turn red when the entry is wrong
                lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                //the end time text field is focused
                txtEndTime.requestFocus();
                 //using return so that the program won't continue until this is done.
                return;
            }            
            //the begintime label will turn black when the entry is okay
            lblBeginTime.setTextFill(Paint.valueOf("#000000"));
            //the endtime label will turn black when the entry is okay
            lblEndTime.setTextFill(Paint.valueOf("#000000"));
        }
        //if any of the Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
        //checkboxes are not selected 
        if (!chkSunday.isSelected() && !chkMonday.isSelected() && !chkTuesday.isSelected()
                && !chkWednesday.isSelected() && !chkThursday.isSelected() &&
                !chkFriday.isSelected() && !chkSaturday.isSelected()){
            //the Sunday checkbox text will be set to red to
            //to notify the user
            chkSunday.setTextFill(Paint.valueOf("#FF0000"));
            //the Sunday checkbox will be focused
            chkSunday.requestFocus();
            //using return so that the program won't continue until this is done.
            return;
        }
        //otherwise
        else{
            //The Sunday checkbox text will be set to black.
            chkSunday.setTextFill(Paint.valueOf("#000000"));
        }
        //using a checkbox method that I created
        CheckBox();
        //getting the text from the begin event date text field
        beginDate = txtBeginDate.getText();
        //setting the begindate model
        model.setBeginDate(beginDate);
        //if the user doesn't enter anything
        if (beginDate.equals(""))
        {      
            //the begindate label will turn red when the entry is empty
            lblBeginDate.setTextFill(Paint.valueOf("#FF0000"));
            //the begin date text field will get focus
            txtBeginDate.requestFocus();  
            //using return so that the program won't continue until this is done.
            return;
        }
        //otherwise if the user does enter something
        else 
        {
            //the begindate label will turn black when the entry is okay
            lblBeginDate.setTextFill(Paint.valueOf("#000000"));
            //using a try catch block to make sure that the program will
            //catch the error the user inputs
            try{
                //if the begin event contains a /
                if (beginDate.contains("/")){
                    //getting the index of the string to get
                    //where there is a / in the string
                    indexBegin = beginDate.indexOf("/");
                    //getting the index of the string after the first /
                    //and making sure that the index before is one over
                    //so that no error will pop up
                    indexEnd= beginDate.indexOf("/",indexBegin+1);
                    //if the user enters something less than one or greater than
                    //12 for the month or enters something less than 1 or greater than
                    //31 for the day or enters something less than 0 or greater than 2014
                    //for the year
                    if(
                        (Integer.parseInt(beginDate.substring(0,indexBegin)) < 1 
                        || 
                        Integer.parseInt(beginDate.substring(0,indexBegin)) > 12)
                        ||
                        (Integer.parseInt(beginDate.substring(indexBegin+1,indexEnd)) < 1
                        ||
                        Integer.parseInt(beginDate.substring(indexBegin+1,indexEnd)) > 31)
                        ||
                        (Integer.parseInt(beginDate.substring(indexEnd+1)) < 1000 
                        ||
                        Integer.parseInt(beginDate.substring(indexEnd+1)) > 2014))

                    {
                        //the begindate label will turn red when the entry is wrong
                        lblBeginDate.setTextFill(Paint.valueOf("#FF0000"));
                        //the begin date text field will get focus
                        txtBeginDate.requestFocus();
                        //if the enddate text field has stuff in it
                        if (!txtEndDate.equals("")){
                            //then it clears
                            txtEndDate.clear();
                        }
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                    //otherwise if the user enters everything correct
                    else
                    {  
                        //the begindate label will turn black when the entry is okay
                        lblBeginDate.setTextFill(Paint.valueOf("#000000"));
                        beginDate = txtBeginDate.getText();
                    }
                }
                //otherwise if the user doesn't enter a /
                else{
                    //the begindate label will turn red when the entry is wrong
                   lblBeginDate.setTextFill(Paint.valueOf("#FF0000"));
                    //the begin date text field gets focus
                    txtBeginDate.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }
            }
            //if the user enters something other than a number
            catch(NumberFormatException ex){
                //the begindate label will turn red when the entry is wrong
                lblBeginDate.setTextFill(Paint.valueOf("#FF0000"));
                //the begin date text field will get focus
                txtBeginDate.requestFocus();
                //using return so that the program won't continue until this is done.
                return;
            }
            //the begindate label will turn black when the entry is okay
            lblBeginDate.setTextFill(Paint.valueOf("#000000"));
        }
        //getting the text from the end date text field
        endDate = txtEndDate.getText();
        //setting the enddate model
        model.setEndDate(endDate);
        //if the user doesn't enter anything
        if (endDate.equals(""))
        {      
            //the enddate label will turn red when the entry is empty
            lblEndDate.setTextFill(Paint.valueOf("#FF0000"));
            //the end date text field will get focus
            txtEndDate.requestFocus();  
            //using return so that the program won't continue until this is done.
            return;
        }
        //otherwise if the user does enter something
        else 
        {
            //the enddate label will turn black when the entry is okay
            lblEndDate.setTextFill(Paint.valueOf("#000000"));
            //using a try catch block to make sure that the program will
            //catch the error the user inputs
            try{ 
                //if the end event contains a /
                if (endDate.contains("/")){
                    //getting the index of the string to get
                    //where there is a / in the string
                    indexBegin = endDate.indexOf("/");
                    //getting the index of the string after the first /
                    //and making sure that the index before is one over
                    //so that no error will pop up
                    indexEnd= endDate.indexOf("/",indexBegin+1);
                    //if the user enters something less than one or greater than
                    //12 for the month or enters something less than 1 or greater than
                    //31 for the day or enters something less than 0 or greater than 2014
                    //for the year
                    if(
                        (Integer.parseInt(endDate.substring(0,indexBegin)) < 1 
                        || 
                        Integer.parseInt(endDate.substring(0,indexBegin)) > 12)
                        ||
                        (Integer.parseInt(endDate.substring(indexBegin+1,indexEnd)) < 1
                        ||
                        Integer.parseInt(endDate.substring(indexBegin+1,indexEnd)) > 31)
                        ||
                        (Integer.parseInt(endDate.substring(indexEnd+1)) < 1000 
                        ||
                        Integer.parseInt(endDate.substring(indexEnd+1)) > 2014))

                    {
                        //the enddate label will turn red when the entry is wrong
                        lblEndDate.setTextFill(Paint.valueOf("#FF0000"));
                        //the end date text field will get focus
                        txtEndDate.requestFocus();   
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                    //otherwise if the user enters everything correct
                    else
                    {  
                        //the enddate label will turn black when the entry is okay
                        lblEndDate.setTextFill(Paint.valueOf("#000000"));
                        //setting the text to endDate
                        endDate = txtEndDate.getText();
                    }
                }
                //otherwise if the user doesn't enter a /
                else{
                    //the enddate label will turn red when the entry is wrong
                    lblEndDate.setTextFill(Paint.valueOf("#FF0000"));
                    //the end date text field gets focus
                    txtEndDate.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }
            }
            //if the user enters something other than a number
            catch(NumberFormatException ex){
                //the enddate label will turn red when the entry is wrong
                lblEndDate.setTextFill(Paint.valueOf("#FF0000"));
                //the end date text field will get focus
                txtEndDate.requestFocus();
                //using return so that the program won't continue until this is done.
                return;
            }
            //setting the startDate with begindate and a default 12:00am to compare dates
            startDate = beginDate + " 12:00 am";
            //setting the startDate with enddate and a default 12:00am to compare dates
            endDDate = endDate + " 12:00 am";
            //setting the startDateTime with begindate, starttime and togstart to match the 
            //string in MyCalendar
            startDateTime = beginDate + " " + startTime + " " + togStart;
            //setting the startDateTime with enddate, endtime and togend to match the 
            //string in MyCalendar
            endDateTime = beginDate + " " + endTime + " " + togEnd;
            //if endDate is before startdate
            if (MyCalendar(startDate).compareTo(MyCalendar(endDDate)) >= 0){
                //The startTime label will turn red if the entry is wrong
                lblBeginDate.setTextFill(Paint.valueOf("#FF0000"));
                //the start time text field will get focus
                txtBeginDate.requestFocus();
                //using return so that the program won't continue until this is done.
                return;
            }
            //if endDateTime is before startDateTime
            if (MyCalendar(startDateTime).compareTo(MyCalendar(endDateTime)) >= 0){
                //The startTime label will turn red if the entry is wrong
                lblBeginTime.setTextFill(Paint.valueOf("#FF0000"));
                //the start time text field will get focus
                txtBeginTime.requestFocus();
                //using return so that the program won't continue until this is done.
                return;
            }
            
            //the begindate label will turn black when the entry is okay
            lblBeginDate.setTextFill(Paint.valueOf("#000000"));
            //the enddate label will turn black when the entry is okay
            lblEndDate.setTextFill(Paint.valueOf("#000000"));
        }
        //this is to switch to the other form 
        try{
            //loading the other form
            AnchorPane labScene = (AnchorPane)FXMLLoader.load
        (FXMLApptRecurrenceController.class.getResource("FXMLLabDoc.fxml"));
            //getting the scene
            Scene scene = new Scene(labScene);
            //making a temperary button
            Button tmp = (Button)event.getSource();
            //setting the stage
            Stage theStage = (Stage)tmp.getScene().getWindow();
            //setting the scene
            theStage.setScene(scene);
        //catching any ioexception
        }catch (IOException ex){}
    }
    @FXML
    public void handlesBtnCancel(ActionEvent event){
        //setting the models to empty strings
        model.setDay("");
        model.setBeginDate("");
        model.setEndDate("");
        model.setStartTime("");
        model.setEndTime("");
         //this is to switch to the other form 
        try{
            //loading the other form
            AnchorPane labScene = (AnchorPane)FXMLLoader.load
        (FXMLApptRecurrenceController.class.getResource("FXMLLabDoc.fxml"));
            //getting the scene
            Scene scene = new Scene(labScene);
            //making a temperary button
            Button tmp = (Button)event.getSource();
            //setting the stage
            Stage theStage = (Stage)tmp.getScene().getWindow();
            //setting the scene
            theStage.setScene(scene);
        //catching any ioexception
        }catch (IOException ex){}
    }
    @FXML
    public void handlesBtnTogBeginTime(ActionEvent event){
        //if the begin time toggle button is selected
        if (btnTogBeginTime.isSelected()){
            //the text of the begin time toggle button will be set to AM
            btnTogBeginTime.setText("AM");
            //also the variable togStart will be set to am
            togStart = "am";
        }
        //otherwise if the start time toggle button is not selected
        else{
            //the begin time toggle button's text will be set to PM
            btnTogBeginTime.setText("PM");
            //and the variable togStart will be set to pm
            togStart = "pm";
        }
        //set the labmodel startampm to pm
        model.setStartAMPM(!btnTogBeginTime.isSelected());
        
    }
    @FXML
    public void handlesBtnTogEndTime(ActionEvent event){
        //if the end time toggle button is selected
        if (btnTogEndTime.isSelected()){
            //the end time toggle button's text will be set to AM
            btnTogEndTime.setText("AM");
            //the variable togEnd will be set to am
            togEnd = "am";
        }
        //otherwise if the end time toggle button is not selected
        else{
            //the end time toggle button's text will be set to PM
            btnTogEndTime.setText("PM");
            //the variable togEnd will be set to pm
            togEnd = "pm";

        } 
        //set the labmodel endampm to pm
        model.setEndAMPM(!btnTogEndTime.isSelected());
        
    }
    
    public void CheckBox(){
        //making the string day
        String day = "";       
        //if the Sunday checkbox is selected
        if (chkSunday.isSelected()){
            //then the day will add Sunday to the string
            day += "Sunday, ";
        }
        //if the Monday checkbox is selected
        if (chkMonday.isSelected()){
            //then day will add Monday to the string
            day += "Monday, ";
        }
        //if the Tuesday checkbox is selected
        if (chkTuesday.isSelected()){
            //then day will add Tuesday to the string
            day += "Tuesday, ";
        }
        //if the Wednesday checkbox is selected
        if (chkWednesday.isSelected()){
            //then day will add Wednesday to the string
            day += "Wednesday, ";
        }
        //if the Thursday checkbox is selected
        if (chkThursday.isSelected()){
            //then day will add Thursday to the string
            day += "Thursday, ";
        }
        //if the Friday checkbox is selected
        if (chkFriday.isSelected()){
            //then day will add Friday to the string
            day += "Friday, ";
        }
        //if the Saturday checkbox is selected
        if (chkSaturday.isSelected()){
            //then day will add Saturday to the string
            day += "Saturday, ";
        }
        
        //setting the day model
        model.setDay(day);
    }
     //MyCalendar method
    public Calendar MyCalendar(String date){
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
    
}
