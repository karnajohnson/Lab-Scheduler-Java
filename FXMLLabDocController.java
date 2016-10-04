/*
Karna Johnson
CSC 240-040
Project #3
Description: To make a lab scheduler for the user, so that they 
            can enter their lab work with their name, email
            event, number of participants, the date, the time,
            and any other requests with a appointment recurrence button.
*/
//the main package
package labscheduler;
//all the import statements
import java.awt.Color;
import java.io.*;
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
import javafx.scene.paint.Paint;




/**
 *
 * @author Karna Johnson
 */
public class FXMLLabDocController extends FXMLApptRecurrenceController implements Initializable  {
    
    
    //All of the buttons, textfields
    
    @FXML
    private Button btnClose;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCheck;
    @FXML
    private ToggleButton btnTogStart;
    @FXML
    private ToggleButton btnTogEnd;
    @FXML
    private TextField txtRequestName;
    @FXML
    private TextField txtRequestEmail;
    @FXML
    private TextField txtEvent;
    @FXML
    private TextField txtNumPart;
    @FXML
    private TextField txtStartTime;
    @FXML
    private TextField txtEventDate;
    @FXML
    private TextField txtEndTime;
    @FXML
    private CheckBox chkPrint;
    @FXML
    private RadioButton radYes;
    @FXML
    private RadioButton radNo;
    @FXML
    private TextArea txtSpecialSoft;
    @FXML
    private Label lblRequestName;
    @FXML
    private Label lblRequestEmail;
    @FXML
    private Label lblEventTitle;
    @FXML
    private Label lblNumPart;
    @FXML
    private Label lblEventDate;
    @FXML
    private Label lblStartTime;
    @FXML
    private Label lblEndTime;
    @FXML
    private Label lblSpecialSoft;
    @FXML
    private Label lblAppt;
    //Fields
    private String requestName;
    private String requestEmail;
    private String eventTitle;
    private String eventDate;
    private int  participants;
    private String  startTime;
    private String  endTime;
    private String specialRequest;
    private String printRequest;
    private String summary;
    private int indexEnd;
    private int indexBegin;
    private String togStart;
    private String togEnd;
    private String startDate;
    private String endDate;
    private LabModel model;
    private EventModel eventModel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // inititalizing variables        
        model = new LabModel();
        eventModel = new EventModel();
        lblAppt.setVisible(false);        
        requestName = "";
        requestEmail = "";
        eventTitle = "";
        eventDate = "";
        participants = 0;
        startTime = "";
        endTime = "";
        specialRequest = "";      
        printRequest = "";
        summary = "";
        indexBegin = 0;
        indexEnd = 0;
        startDate = "";
        endDate = "";
        //if the start model is AM
        if (!model.getStartAMPM())
        {
            //the toggle button will be selected
            btnTogStart.setSelected(true);
            //new button action event
            handlesbtnTogStart(new ActionEvent());
            //set togStart to am
            togStart = "am";
        }
        else
            //set togStart to pm
            togStart = "pm";
        //if the end model is AM
        if (!model.getEndAMPM())
        {
            //the toggle button will be selected
            btnTogEnd.setSelected(true);
            //new button action event
            handlesbtnTogEnd(new ActionEvent());
            //set togEnd to am
            togEnd = "am";
        }
        else
            //set togEnd to pm
            togEnd = "pm";
        //if the apptrecurrence is filled out
        if (!model.getStartTime().isEmpty() &&
                !model.getBeginDate().isEmpty() && !model.getEndDate().isEmpty()
                && !model.getEndTime().isEmpty()){
            //setting variables not visible
            txtStartTime.setVisible(false);
            txtEventDate.setVisible(false);
            txtEndTime.setVisible(false);
            btnTogStart.setVisible(false);
            btnTogEnd.setVisible(false);
            lblEventDate.setVisible(false);
            lblStartTime.setVisible(false);
            lblEndTime.setVisible(false);
            //setting the Appointment label to be visible
            lblAppt.setVisible(true);
            txtRequestName.setText(model.getRequestName());
            txtRequestEmail.setText(model.getRequestEmail());
            txtEvent.setText(model.getEventTitle());       
            txtNumPart.setText(((Integer)model.getParticipants()).toString());
            //setting the text for the lblAppt
            lblAppt.setText("Recurrence: Occurs every " + model.getDay() + "\neffective " 
                + model.getBeginDate()
                + " until "+ model.getEndDate() + "\nfrom " 
                + model.getStartTime() + (model.getStartAMPM()?"PM":"AM") + " to "+ model.getEndTime()
                    +(model.getEndAMPM()?"PM":"AM")+"\n");
            //setting the Check if event listings are available button to not visible
            btnCheck.setVisible(false);
        }
        //otherwise
        else{
            //setting the lblAppt to be not visible
            lblAppt.setVisible(false);
            //setting the text of the label to be ""
            lblAppt.setText("");
            //setting all the fields to be visible again
            lblEventDate.setVisible(true);
            lblStartTime.setVisible(true);
            lblEndTime.setVisible(true);
            txtStartTime.setVisible(true);
            txtEventDate.setVisible(true);
            txtEndTime.setVisible(true);
            btnTogStart.setVisible(true);
            btnTogEnd.setVisible(true);
            btnCheck.setVisible(true);
        }     
        
        //this is for when they come back to the form 
        //the info that they entered will still be there
        txtRequestName.setText(model.getRequestName());
        txtRequestEmail.setText(model.getRequestEmail());
        txtEvent.setText(model.getEventTitle());       
        txtNumPart.setText(((Integer)model.getParticipants()).toString());
        txtEventDate.setText(model.getBeginDate());
        txtStartTime.setText(model.getStartTime());
        txtEndTime.setText(model.getEndTime());
        
    } 
    @FXML
    public void handlesbtnCheck (ActionEvent event){
        //setting the sets of these models to ""
        model.setDay("");
        model.setBeginDate("");
        model.setEndDate("");
        model.setStartTime("");
        model.setEndTime("");
        //setting these to the text that are in them.
        model.setRequestName(txtRequestName.getText());
        model.setRequestEmail(txtRequestEmail.getText());
        model.setParticipants(Integer.parseInt(txtNumPart.getText()));
        
        //if the user does not put a request for the event title
        if (txtEvent.getText().equals(""))
        {
            //The eventTitle label will turn red if the user doesn't enter the event title
             lblEventTitle.setTextFill(Paint.valueOf("#FF0000"));
             //the event title text field will get focus
             txtEvent.requestFocus();   
             //using return so that the program won't continue until this is done.
             return;
        }
        //otherwise if the user does enter something
        else{
            //The eventTitle label will turn black if the user does enter a event title
            lblEventTitle.setTextFill(Paint.valueOf("#000000"));
            //setting the event model
            model.setEventTitle(txtEvent.getText());           
        }
        //if the eventdate textfield is visible
        if (txtEventDate.isVisible()){
            //getting the text from the event date text field
            eventDate = txtEventDate.getText();
            //setting the begindate model
            model.setBeginDate(eventDate);         
            //if the user doesn't enter anything
            if (eventDate.equals(""))
            {      
                //The eventDate label will turn red saying to please enter the format MM/DD/YYYY
                lblEventDate.setTextFill(Paint.valueOf("#FF0000"));
                //the event text field will get focus
                txtEventDate.requestFocus();  
                //using return so that the program won't continue until this is done.
                return;
            }
            //otherwise if the user does enter something
            else 
            {
                //The eventDate label will turn black if all is good.
                lblEventDate.setTextFill(Paint.valueOf("#000000"));
                //using a try catch block to make sure that the program will
                //catch the error the user inputs
                try{
                    
                    //if the event contains a /
                    if (eventDate.contains("/")){
                        //getting the index of the string to get
                        //where there is a / in the string
                        indexBegin = eventDate.indexOf("/");
                        //getting the index of the string after the first /
                        //and making sure that the index before is one over
                        //so that no error will pop up
                        indexEnd= eventDate.indexOf("/",indexBegin+1);
                        //if the user enters something less than one or greater than
                        //12 for the month or enters something less than 1 or greater than
                        //31 for the day or enters something less than 0 or greater than 2014
                        if(
                            (Integer.parseInt(eventDate.substring(0,indexBegin)) < 1 
                            || 
                            Integer.parseInt(eventDate.substring(0,indexBegin)) > 12)
                            ||
                            (Integer.parseInt(eventDate.substring(indexBegin+1,indexEnd)) < 1
                            ||
                            Integer.parseInt(eventDate.substring(indexBegin+1,indexEnd)) > 31)
                            ||
                            (Integer.parseInt(eventDate.substring(indexEnd+1)) < 1000 
                            ||
                            Integer.parseInt(eventDate.substring(indexEnd+1)) > 2014))

                        {                           
                            //The eventDate label will turn red if the entry is wrong.
                            lblEventDate.setTextFill(Paint.valueOf("#FF0000"));
                            //the event date text field will get focus
                            txtEventDate.requestFocus();   
                            //using return so that the program won't continue until this is done.
                            return;
                            
                        }
                        //otherwise if the user enters everything correct
                        else
                        {  
                            //The eventDate label will turn black if all is good.
                            lblEventDate.setTextFill(Paint.valueOf("#000000"));
                            //the string eventDate will get the text form the text field
                            eventDate = txtEventDate.getText();
                            //setting the begindate model
                            model.setBeginDate(eventDate);
                        }
                    }
                    //otherwise if the user doesn't enter a /
                    else{
                        //The eventDate label will turn red if the entry is wrong
                        lblEventDate.setTextFill(Paint.valueOf("#FF0000"));
                        //the event date text field gets focus
                        txtEventDate.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                }
                //if the user enters something other than a number
                catch(NumberFormatException ex){
                    //The eventdate label will turn red if the entry is wrong.
                    lblEventDate.setTextFill(Paint.valueOf("#FF0000"));
                    //the event date text field will get focus
                    txtEventDate.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }
                //The eventdate label will turn black if all is good.
                lblEventDate.setTextFill(Paint.valueOf("#000000"));
            }
        }       
        //if the start time text field is visible
        if (txtStartTime.isVisible()){
            //getting the text from the txtStartTime text field
            startTime = txtStartTime.getText();
            //setting the starttime model 
            model.setStartTime(startTime);
            //setting the startAMPM model
            model.setStartAMPM(!btnTogStart.isSelected());
            //if the user doesn't enter anything
            if (startTime.equals("")){
                //The starttime label will turn red if the entry is empty
                 lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                 //the start time text field will get focus
                 txtStartTime.requestFocus();      
                 //using return so that the program won't continue until this is done.
                 return;
            }
            //otherwise if the user does enter something
            else{
                //The starttime label will turn black if all is good.
                lblStartTime.setTextFill(Paint.valueOf("#000000"));
                //a try catch block is being used so that if the user
                //enters something other than a number
                try{
                    //if start time doesn't contain a :
                    if (!startTime.contains(":")){
                        //if the user enters a number less than 1 or greater than 12
                        //for the hour
                        if ((Integer.parseInt(startTime) < 1 || 
                            Integer.parseInt(startTime) > 12)){
                            //The startTime label will turn red if the entry is wrong
                            lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                            //the start time text field will get focus
                            txtStartTime.requestFocus();
                            //using return so that the program won't continue until this is done.
                            return; 
                        }
                        //otherwise
                        else{
                            //the starttime variable will get the text from the txtStartTime text field
                            startTime = txtStartTime.getText();
                            //adding :00 to the string
                            startTime += ":00";
                            //setting the startTime model
                            model.setStartTime(startTime);
                            //setting the startAMPM model
                            model.setStartAMPM(!btnTogStart.isSelected());
                        }
                    }
                    //if the text the user entered contained a :
                    else if (startTime.contains(":")){
                        //getting the index to where the colon is
                        indexBegin = startTime.indexOf(":");                       
                        //if the user enters a number less than 1 or greater than 12
                        //for the hour
                        //or the user enters a number less than 0 or greater than 59
                        if ((Integer.parseInt(startTime.substring(0,indexBegin)) < 1 || 
                            Integer.parseInt(startTime.substring(0,indexBegin)) > 12)
                            || (Integer.parseInt(startTime.substring(indexBegin+1)) < 0 ||
                            Integer.parseInt(startTime.substring(indexBegin+1)) > 59))
                        {
                            //The startTime label will turn red if the entry is wrong
                            lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                            //the start time text field will get focus
                            txtStartTime.requestFocus();
                            //using return so that the program won't continue until this is done.
                            return;
                        }
                        //otherwise if the user enters the correct format
                        else{                           
                            //The starttime label will turn black if all is good.
                            lblStartTime.setTextFill(Paint.valueOf("#000000"));
                            //startTime will get the text from the txtStartTime textfield
                            startTime = txtStartTime.getText();
                            //setting the startTime model
                            model.setStartTime(startTime);
                            //setting the startAMPM model
                            model.setStartAMPM(!btnTogStart.isSelected());
                            
                        }
                        //if there is less than 2 digits in the minute
                        if (startTime.substring(indexBegin+1).length() < 2){
                            //The startTime label will turn red if the entry is wrong
                            lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                            //the start time text field will get focus
                            txtStartTime.requestFocus();
                            //using return so that the program won't continue until this is done.
                            return;
                        }
                        
                    }                    
                }
                //catching if the user enters something other than a number
                catch (NumberFormatException ex){
                    //The starttime label will turn red if the entry is wrong.
                    lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                    //the start time text field will get focused
                    txtStartTime.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                } 
                //The starttime label will turn black if all is good.
                lblStartTime.setTextFill(Paint.valueOf("#000000"));
            }
        }
        //if the endtime text field is visible
        if (txtEndTime.isVisible()){
            //getting the text from the endtime text field
            endTime = txtEndTime.getText();
            //setting the endtime model 
            model.setEndTime(endTime);
            //setting the endAMPM model
            model.setEndAMPM(!btnTogEnd.isSelected());
            //if the user doesn't enter anything
            if (txtEndTime.getText().equals("")){
                //The endTime label will turn red if the entry is empty
                 lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                 //the end time text field will get focused
                 txtEndTime.requestFocus();   
                 //using return so that the program won't continue until this is done.
                 return;
            }
            //otherwise if the user does enter something
            else{
                //The endtime label will turn black if all is good.
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
                            //endTime will get the text of txtEndTime textfield
                            endTime = txtEndTime.getText();
                            //endTime will add :00 to the string
                            endTime += ":00";
                            //setting the endtime model
                            model.setEndTime(endTime);
                            //setting the endAMPM model
                            model.setEndAMPM(!btnTogEnd.isSelected());
                        }
                    }
                    //if the string contains a :
                    else if (endTime.contains(":")){
                        //getting the index of where the : is in the string
                        indexBegin = endTime.indexOf(":");
                        //if the user enters a number less than 1 or greater than 12
                        //for the hour
                        //or the user enters a number less than 0 or greater than 59
                       if ((Integer.parseInt(endTime.substring(0,indexBegin)) < 1 || 
                            Integer.parseInt(endTime.substring(0,indexBegin)) >12)
                            || Integer.parseInt(endTime.substring(indexBegin+1)) < 0 ||
                            Integer.parseInt(endTime.substring(indexBegin+1)) > 59)
                        {
                            //The endtime label will turn red if the entry is wrong.
                            lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                            //the end time text field is focused
                            txtEndTime.requestFocus();
                            //using return so that the program won't continue until this is done.
                            return;
                        }
                       //otherwise if the user enters everthing correctly
                       else{
                           //The endtime label will turn black if all is good.
                           lblEndTime.setTextFill(Paint.valueOf("#000000"));
                           //setting the endTime variable to the text of txtEndTime
                           endTime = txtEndTime.getText();
                           //setting the endTime model with endTime and togEnd
                           model.setEndTime(endTime);
                           //setting the endAMPM model
                           model.setEndAMPM(!btnTogEnd.isSelected());
                       }
                       //if there are less than 2 digits in the minutes
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
                    //The endtime label will turn red if the entry is wrong
                    lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                    //the end time text field is focused
                    txtEndTime.requestFocus();
                     //using return so that the program won't continue until this is done.
                    return;
                }
                //setting startDate with the eventDate, startTime and togStart
                //to match the string in the calendar method
                startDate = eventDate + " " + startTime + " " + togStart;
                //setting startDate with the eventDate, endTime and togEnd
                //to match the string in the calendar method
                endDate = eventDate + " " + endTime + " " + togEnd;
                
                //if startDate is less than endDate
                if (MyCalendar(startDate).compareTo(MyCalendar(endDate)) >= 0){
                    //The startTime label will turn red if the entry is wrong
                    lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                    //the start time text field will get focus
                    txtStartTime.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }
                
                //The endtime label will turn black if all is good.
                lblEndTime.setTextFill(Paint.valueOf("#000000"));
                //The starttime label will turn black if all is good.
                lblStartTime.setTextFill(Paint.valueOf("#000000"));
            }
            
        }
        //a try catch block to communicate between the 2 forms
        try{
            //getting the anchor pane
            AnchorPane labScene = (AnchorPane)FXMLLoader.load(FXMLApptRecurrenceController.class.getResource("FXMLEventListings.fxml"));
            //making a new scene
            Scene scene = new Scene(labScene);
            //making a temperary button
            Button tmp = (Button)event.getSource();
            //getting the stage
            Stage theStage = (Stage)tmp.getScene().getWindow();
            //setting the scene
            theStage.setScene(scene);
        //catch IOException
        }catch (IOException ex){}
    }
    @FXML
    public void handlesbtnApptRecurrence(ActionEvent event){
        //if the user does not put a request for the name
        if (txtRequestName.getText().equals(""))
        {
            //The requestName label will turn red if the user doesn't enter a name
           lblRequestName.setTextFill(Paint.valueOf("#FF0000"));
            //sets the focus to the name text field
            txtRequestName.requestFocus(); 
            //using return so that the program won't continue until this is done.
            return;
        }
        //otherwise if the user does enter something
        else{
            //The requestName label will turn black if the user does enter a name
            lblRequestName.setTextFill(Paint.valueOf("#000000"));
            //setting the name in the model
            model.setRequestName(txtRequestName.getText());
        }
        //if the user does not put a request for the email
        if (txtRequestEmail.getText().equals("")){
            //The requestEmail label will turn red if the user doesn't enter a email
             lblRequestEmail.setTextFill(Paint.valueOf("#FF0000"));
             //the email text field is focused
             txtRequestEmail.requestFocus(); 
             //using return so that the program won't continue until this is done.
             return;
        } 
        //otherwise if the user does enter something
        else{
            //The requestEmail label will turn black if the user does enter a email
            lblRequestEmail.setTextFill(Paint.valueOf("#000000"));
            //if the email text field contains a @
            if (txtRequestEmail.getText().contains("@")){
                //also if the email text field contains a period
                if (txtRequestEmail.getText().contains(".")){
                    //also if the email text field contains spaces
                    if (!txtRequestEmail.getText().contains(" ")){
                        //setting the email in the model
                        model.setRequestEmail(txtRequestEmail.getText());
                    }
                    //otherwise if the user puts spaces in the email
                    else{
                        //The requestEmail label will turn red if the user enters spaces
                        lblRequestEmail.setTextFill(Paint.valueOf("#FF0000"));
                        //the email text field will get focused
                        txtRequestEmail.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                }
                //otherwise if the user doesn't enter a period
                else{
                    //The requestEmail label will turn red if the user doesn't enter a period 
                    //with their email
                    lblRequestEmail.setTextFill(Paint.valueOf("#FF0000"));
                    //the email text field will get focus
                    txtRequestEmail.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }
            }
            //otherwise if the user doesn't enter a @ sign
            else{
                //The requestEmail label will turn red if the user doesn't enter an @ sign in their email
                lblRequestEmail.setTextFill(Paint.valueOf("#FF0000"));
                //the email text field will get focus
                txtRequestEmail.requestFocus();
                //using return so that the program won't continue until this is done.
                return;
            }
            //The requestEmail label will turn black if all is good
            lblRequestEmail.setTextFill(Paint.valueOf("#000000"));
        }
        //if the user does not put a request for the event title
        if (txtEvent.getText().equals(""))
        {
            //The eventTitle label will turn red if the user doesn't enter the event title
             lblEventTitle.setTextFill(Paint.valueOf("#FF0000"));
             //the event title text field will get focus
             txtEvent.requestFocus();   
             //using return so that the program won't continue until this is done.
             return;
        }
        //otherwise if the user does enter something
        else{
            //The eventTitle label will turn black if the user does enter a event title
            lblEventTitle.setTextFill(Paint.valueOf("#000000"));
            //setting the event model
            model.setEventTitle(txtEvent.getText());
            
        }
        //if the user does not put a request for the number of participants  
        if (txtNumPart.getText().equals("")){
            ///The numPart label will turn red if the user doesn't enter the number
            //of participants
             lblNumPart.setTextFill(Paint.valueOf("#FF0000"));
             //the participants text field will get focus
             txtNumPart.requestFocus();
             //using return so that the program won't continue until this is done.
             return;
        }
        //otherwise if the user does enter something
        else{
            //The numPart label will turn black if the user does enter the number
            //of participants
            lblNumPart.setTextFill(Paint.valueOf("#000000"));
            //using a try catch block to catch if the user enters anything other than a number
            try{
                //getting the number from the text field
               participants = Integer.parseInt(txtNumPart.getText());
                //if the participants is less than one
                if (participants < 1){
                    //The numPart label will turn red to say for the user to enter 
                    //less than a 
                    //one for the participants text field
                    lblNumPart.setTextFill(Paint.valueOf("#FF0000"));
                    //the text field will focus for the user to enter again
                    txtNumPart.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }
                //setting the participants into the model
                model.setParticipants(participants);
            }
            //the catch 
            //if the user does enter something other than an integer 
            catch(NumberFormatException ex){
                //the catch will catch and make sure that an error doesn't pop up
                //but the numPart label will turn red to tell the user that
                //they need to enter a number for the participants
                lblNumPart.setTextFill(Paint.valueOf("#FF0000"));
                //the participants text field gets focus
                txtNumPart.requestFocus();
                //using return so that the program won't continue until this is done.
                return;
            }
            //The numpart label will turn black if all is good.
            lblNumPart.setTextFill(Paint.valueOf("#000000"));
            
        }
        //a try catch block to communicate between the 2 forms
        try{
            //getting the anchor pane
            AnchorPane labScene = (AnchorPane)FXMLLoader.load(FXMLApptRecurrenceController.class.getResource("FXMLApptRecurrence.fxml"));
            //making a new scene
            Scene scene = new Scene(labScene);
            //making a temperary button
            Button tmp = (Button)event.getSource();
            //getting the stage
            Stage theStage = (Stage)tmp.getScene().getWindow();
            //setting the scene
            theStage.setScene(scene);
        //catch IOException
        }catch (IOException ex){}
    }
    @FXML
    private void handlesbtnSubmit(ActionEvent event) {
       //if the user does not put a request for the name
        if (txtRequestName.getText().equals(""))
        {
            //The requestName label will turn red if the user doesn't enter a name
           lblRequestName.setTextFill(Paint.valueOf("#FF0000"));
            //sets the focus to the name text field
            txtRequestName.requestFocus(); 
            //using return so that the program won't continue until this is done.
            return;
        }
        //otherwise if the user does enter something
        else{
            //The requestName label will turn black if the user does enter a name
            lblRequestName.setTextFill(Paint.valueOf("#000000"));
            //setting the name model
            model.setRequestName(txtRequestName.getText());
        }
        //if the user does not put a request for the email
        if (txtRequestEmail.getText().equals("")){
            //The requestEmail label will turn red if the user doesn't enter a email
             lblRequestEmail.setTextFill(Paint.valueOf("#FF0000"));
             //the email text field is focused
             txtRequestEmail.requestFocus(); 
             //using return so that the program won't continue until this is done.
             return;
        } 
        //otherwise if the user does enter something
        else{
            //The requestEmail label will turn black if the user does enter a email
            lblRequestEmail.setTextFill(Paint.valueOf("#000000"));
            //if the email text field contains a @
            if (txtRequestEmail.getText().contains("@")){
                //also if the email text field contains a period
                if (txtRequestEmail.getText().contains(".")){
                    //also if the email text field contains spaces
                    if (!txtRequestEmail.getText().contains(" ")){
                        //setting the email model
                        model.setRequestEmail(txtRequestEmail.getText());
                    }
                    //otherwise if the user puts spaces in the email
                    else{
                        //The requestEmail label will turn red if the user enters spaces
                        lblRequestEmail.setTextFill(Paint.valueOf("#FF0000"));
                        //the email text field will get focused
                        txtRequestEmail.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                }
                //otherwise if the user doesn't enter a period
                else{
                    //The requestEmail label will turn red if the user doesn't enter a period 
                    //with their email
                    lblRequestEmail.setTextFill(Paint.valueOf("#FF0000"));
                    //the email text field will get focus
                    txtRequestEmail.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }
            }
            //otherwise if the user doesn't enter a @ sign
            else{
                //The requestEmail label will turn red if the user doesn't enter an @ sign in their email
                lblRequestEmail.setTextFill(Paint.valueOf("#FF0000"));
                //the email text field will get focus
                txtRequestEmail.requestFocus();
                //using return so that the program won't continue until this is done.
                return;
            }
            //The requestEmail label will turn black if all is good
            lblRequestEmail.setTextFill(Paint.valueOf("#000000"));
        }
        //if the user does not put a request for the event title
        if (txtEvent.getText().equals(""))
        {
            //The eventTitle label will turn red if the user doesn't enter the event title
             lblEventTitle.setTextFill(Paint.valueOf("#FF0000"));
             //the event title text field will get focus
             txtEvent.requestFocus();   
             //using return so that the program won't continue until this is done.
             return;
        }
        //otherwise if the user does enter something
        else{
            //The eventTitle label will turn black if the user does enter a event title
            lblEventTitle.setTextFill(Paint.valueOf("#000000"));
            //setting the event model
            model.setEventTitle(txtEvent.getText());
        }
        //if the user does not put a request for the number of participants  
        if (txtNumPart.getText().equals("")){
            ///The numPart label will turn red if the user doesn't enter the number
            //of participants
             lblNumPart.setTextFill(Paint.valueOf("#FF0000"));
             //the participants text field will get focus
             txtNumPart.requestFocus();
             //using return so that the program won't continue until this is done.
             return;
        }
        //otherwise if the user does enter something
        else{
            //The numPart label will turn black if the user does enter the number
            //of participants
            lblNumPart.setTextFill(Paint.valueOf("#000000"));
            //using a try catch block to catch if the user enters anything other than a number
            try{
                //getting the number from the text field
               participants =Integer.parseInt(txtNumPart.getText());
                //if the participants is less than one
                if (participants < 1){
                    //The numPart label will turn red to say for the user to enter 
                    //less than a 
                    //one for the participants text field
                    lblNumPart.setTextFill(Paint.valueOf("#FF0000"));
                    //the text field will focus for the user to enter again
                    txtNumPart.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }
                //setting the participants model
                model.setParticipants(participants);
            }
            //the catch 
            //if the user does enter something other than an integer 
            catch(NumberFormatException ex){
                //the catch will catch and make sure that an error doesn't pop up
                //but the numPart label will turn red to tell the user that
                //they need to enter a number for the participants
                lblNumPart.setTextFill(Paint.valueOf("#FF0000"));
                //the participants text field gets focus
                txtNumPart.requestFocus();
                //using return so that the program won't continue until this is done.
                return;
            }
            //The numpart label will turn black if all is good.
            lblNumPart.setTextFill(Paint.valueOf("#000000"));
            
        }
        //if the eventdate textfield is visible
        if (txtEventDate.isVisible()){
            //getting the text from the event date text field
            eventDate = txtEventDate.getText();
            //setting the begindate model
            model.setBeginDate(eventDate);         
            //if the user doesn't enter anything
            if (eventDate.equals(""))
            {      
                //The eventDate label will turn red saying to please enter the format MM/DD/YYYY
                lblEventDate.setTextFill(Paint.valueOf("#FF0000"));
                //the event text field will get focus
                txtEventDate.requestFocus();  
                //using return so that the program won't continue until this is done.
                return;
            }
            //otherwise if the user does enter something
            else 
            {
                //The eventDate label will turn black if all is good.
                lblEventDate.setTextFill(Paint.valueOf("#000000"));
                //using a try catch block to make sure that the program will
                //catch the error the user inputs
                try{
                    //if the event contains a /
                    if (eventDate.contains("/")){
                        //getting the index of the string to get
                        //where there is a / in the string
                        indexBegin = eventDate.indexOf("/");
                        //getting the index of the string after the first /
                        //and making sure that the index before is one over
                        //so that no error will pop up
                        indexEnd= eventDate.indexOf("/",indexBegin+1);
                        //if the user enters something less than one or greater than
                        //12 for the month or enters something less than 1 or greater than
                        //31 for the day or enters something less than 0 or greater than 2014
                        if(
                            (Integer.parseInt(eventDate.substring(0,indexBegin)) < 1 
                            || 
                            Integer.parseInt(eventDate.substring(0,indexBegin)) > 12)
                            ||
                            (Integer.parseInt(eventDate.substring(indexBegin+1,indexEnd)) < 1
                            ||
                            Integer.parseInt(eventDate.substring(indexBegin+1,indexEnd)) > 31)
                            ||
                            (Integer.parseInt(eventDate.substring(indexEnd+1)) < 1000 
                            ||
                            Integer.parseInt(eventDate.substring(indexEnd+1)) > 2014))

                        {                           
                            //The eventDate label will turn red if the entry is wrong.
                            lblEventDate.setTextFill(Paint.valueOf("#FF0000"));
                            //the event date text field will get focus
                            txtEventDate.requestFocus();   
                            //using return so that the program won't continue until this is done.
                            return;
                            
                        }
                        //otherwise if the user enters everything correct
                        else
                        {  
                            //The eventDate label will turn black if all is good.
                            lblEventDate.setTextFill(Paint.valueOf("#000000"));
                            //the string eventDate will get the text form the text field
                            eventDate = txtEventDate.getText();                           
                            //setting the begindate model
                            model.setBeginDate(eventDate);

                        }
                    }
                    //otherwise if the user doesn't enter a /
                    else{
                        //The eventDate label will turn red if the entry is wrong
                        lblEventDate.setTextFill(Paint.valueOf("#FF0000"));
                        //the event date text field gets focus
                        txtEventDate.requestFocus();
                        //using return so that the program won't continue until this is done.
                        return;
                    }
                }
                //if the user enters something other than a number
                catch(NumberFormatException ex){
                    //The eventdate label will turn red if the entry is wrong.
                    lblEventDate.setTextFill(Paint.valueOf("#FF0000"));
                    //the event date text field will get focus
                    txtEventDate.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }
                //The eventdate label will turn black if all is good.
                lblEventDate.setTextFill(Paint.valueOf("#000000"));
            }
        }
        
        //if the start time text field is visible
        if (txtStartTime.isVisible()){
            //getting the text from the txtStartTime text field
            startTime = txtStartTime.getText();
            //setting the starttime model
            model.setStartTime(startTime);
            //setting the startAMPM model
            model.setStartAMPM(!btnTogStart.isSelected());
            //if the user doesn't enter anything
            if (startTime.equals("")){
                //The starttime label will turn red if the entry is empty
                 lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                 //the start time text field will get focus
                 txtStartTime.requestFocus();      
                 //using return so that the program won't continue until this is done.
                 return;
            }
            //otherwise if the user does enter something
            else{
                //The starttime label will turn black if all is good.
                lblStartTime.setTextFill(Paint.valueOf("#000000"));
                //a try catch block is being used so that if the user
                //enters something other than a number
                try{
                    //if start time doesn't contain a :
                    if (!startTime.contains(":")){
                        //if the user enters a number less than 1 or greater than 12
                        //for the hour
                        if ((Integer.parseInt(startTime) < 1 || 
                            Integer.parseInt(startTime) > 12)){
                            //The startTime label will turn red if the entry is wrong
                            lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                            //the start time text field will get focus
                            txtStartTime.requestFocus();
                            //using return so that the program won't continue until this is done.
                            return; 
                        }
                        //otherwise
                        else{
                            //setting startTime to the text in txtStartTime 
                            startTime = txtStartTime.getText();
                            //adding :00 to the string
                            startTime += ":00";
                            //setting the startTime model
                            model.setStartTime(startTime);
                            //setting the startAMPM model
                            model.setStartAMPM(!btnTogStart.isSelected());
                        }
                    }
                    //if the text the user entered contained a :
                    else if (startTime.contains(":")){
                        //getting the index to where the colon is
                        indexBegin = startTime.indexOf(":");                       
                        //if the user enters a number less than 1 or greater than 12
                        //for the hour
                        //or the user enters a number less than 0 or greater than 59
                        if ((Integer.parseInt(startTime.substring(0,indexBegin)) < 1 || 
                            Integer.parseInt(startTime.substring(0,indexBegin)) > 12)
                            || (Integer.parseInt(startTime.substring(indexBegin+1)) < 0 ||
                            Integer.parseInt(startTime.substring(indexBegin+1)) > 59))
                        {
                            //The startTime label will turn red if the entry is wrong
                            lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                            //the start time text field will get focus
                            txtStartTime.requestFocus();
                            //using return so that the program won't continue until this is done.
                            return;
                        }
                        //otherwise if the user enters the correct format
                        else{                           
                            //The starttime label will turn black if all is good.
                            lblStartTime.setTextFill(Paint.valueOf("#000000"));
                            //setting the startTime with the text in txtStartTime
                            startTime = txtStartTime.getText();
                            //setting the startTime model
                            model.setStartTime(startTime);
                            //setting the startAMPM model
                            model.setStartAMPM(!btnTogStart.isSelected());
                        }
                        //if there is less than 2 digits in the minute
                        if (startTime.substring(indexBegin+1).length() < 2){
                            //The startTime label will turn red if the entry is wrong
                                lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                                //the start time text field will get focus
                                txtStartTime.requestFocus();
                                //using return so that the program won't continue until this is done.
                                return;
                        }                       
                    }                    
                }
                //catching if the user enters something other than a number
                catch (NumberFormatException ex){
                    //The starttime label will turn red if the entry is wrong.
                    lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                    //the start time text field will get focused
                    txtStartTime.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                } 
                //The starttime label will turn black if all is good.
                lblStartTime.setTextFill(Paint.valueOf("#000000"));
            }
        }
        //if the endtime text field is visible
        if (txtEndTime.isVisible()){
            //getting the text from the endtime text field
            endTime = txtEndTime.getText();
            //setting the endtime model
            model.setEndTime(endTime);
            //setting the endAMPM model
            model.setEndAMPM(!btnTogEnd.isSelected());
            //if the user doesn't enter anything
            if (txtEndTime.getText().equals("")){
                //The endTime label will turn red if the entry is empty
                 lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                 //the end time text field will get focused
                 txtEndTime.requestFocus();   
                 //using return so that the program won't continue until this is done.
                 return;
            }
            //otherwise if the user does enter something
            else{
                //The endtime label will turn black if all is good.
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
                            //setting the text from the txtEndTime textfield to endTime
                            endTime = txtEndTime.getText();
                            //adding :00 to endTime
                            endTime += ":00";
                            //setting the endtime model
                            model.setEndTime(endTime);
                            //setting the endAMPM model
                            model.setEndAMPM(!btnTogEnd.isSelected());
                        }
                    }
                    //if the string contains a :
                    else if (endTime.contains(":")){
                        //getting the index of where the : is in the string
                        indexBegin = endTime.indexOf(":");
                        //if the user enters a number less than 1 or greater than 12
                        //for the hour
                        //or the user enters a number less than 0 or greater than 59
                       if ((Integer.parseInt(endTime.substring(0,indexBegin)) < 1 || 
                            Integer.parseInt(endTime.substring(0,indexBegin)) >12)
                            || Integer.parseInt(endTime.substring(indexBegin+1)) < 0 ||
                            Integer.parseInt(endTime.substring(indexBegin+1)) > 59)
                        {
                            //The endtime label will turn red if the entry is wrong.
                            lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                            //the end time text field is focused
                            txtEndTime.requestFocus();
                            //using return so that the program won't continue until this is done.
                            return;
                        }
                       //otherwise if the user enters everthing correctly
                       else{
                           //The endtime label will turn black if all is good.
                           lblEndTime.setTextFill(Paint.valueOf("#000000"));
                           //setting the endTime to the text in txtEndTime textfield
                           endTime = txtEndTime.getText();
                           //setting the endTime model
                           model.setEndTime(endTime);
                           //setting the endAMPM model
                           model.setEndAMPM(!btnTogEnd.isSelected());
                       }
                       //if there are less than 2 digits in the minutes
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
                    //The endtime label will turn red if the entry is wrong
                    lblEndTime.setTextFill(Paint.valueOf("#FF0000"));
                    //the end time text field is focused
                    txtEndTime.requestFocus();
                     //using return so that the program won't continue until this is done.
                    return;
                }
                //setting startDate with eventDate, startTime, and togStart
                //for the myCalendar method
                startDate = eventDate + " " + startTime + " " + togStart;
                //setting startDate with eventDate, endTime, and togEnd
                //for the myCalendar method
                endDate = eventDate + " " + endTime + " " + togEnd;
       
                //if startDate is less than endDate
                if (MyCalendar(startDate).compareTo(MyCalendar(endDate)) >= 0){
                    //The startTime label will turn red if the entry is wrong
                    lblStartTime.setTextFill(Paint.valueOf("#FF0000"));
                    //the start time text field will get focus
                    txtStartTime.requestFocus();
                    //using return so that the program won't continue until this is done.
                    return;
                }  
                //The endtime label will turn black if all is good.
                lblEndTime.setTextFill(Paint.valueOf("#000000"));
                //The starttime label will turn black if all is good.
                lblStartTime.setTextFill(Paint.valueOf("#000000"));
            }       
        }       
        //this is a method that I created which will 
        //check to see if the user checked the checkbox or not
        printerCheck();
        //if the yes and no radiobuttons are not selected
       if (!radYes.isSelected() && !radNo.isSelected()){
           //The specialsoft label will turn red if neither of the 
           //radiobuttons are selected
           lblSpecialSoft.setTextFill(Paint.valueOf("#FF0000"));
           //using return so that the program won't continue until this is done.
           return;
       }
        // if the yes radio button is selected
       if (radYes.isSelected()){
            //the special request variable will get the text from the 
            //special request text field
            specialRequest = txtSpecialSoft.getText();
            //setting the special request model
            model.setSpecialRequest(specialRequest);
        }
        //otherwise if the no radio button is selected
        else if (radNo.isSelected()){
            //the special request variable will say no requests
            specialRequest = "No Requests";
            //setting the special request model
            model.setSpecialRequest(specialRequest);
        }
        
       //if at least on of the day checkboxes are selected
       if (!model.getDay().isEmpty()){
           //everything will be put into one variable
        summary = "Name: " + model.getRequestName() + "\n" + "Email: " + model.getRequestEmail()+ "\n"
                + "Event: " + model.getEventTitle() + "\n" + "Number of Participants: " + 
                model.getParticipants() + "\n" + "Recurrence: Occurs every " + model.getDay() + "\neffective " 
                + model.getBeginDate()
                + " until "+ model.getEndDate() + "\nfrom " 
                + model.getStartTime() + (model.getStartAMPM()?"PM":"AM") + " to "+ model.getEndTime() +
                (model.getEndAMPM()?"PM":"AM") +"\n"
                + "Printer: " + printRequest
                + "\n" + "Software/Special Instructions: " + model.getSpecialRequest() +"\n";
       }
       //otherwise if there are no days selected
       else{
           //everything will be put into one variable
           summary = "Name: " + model.getRequestName() + "\n" + "Email: " + model.getRequestEmail()+ "\n"
                + "Event: " + model.getEventTitle() + "\n" + "Number of Participants: " + 
                model.getParticipants() + "\nDate: " 
                + model.getBeginDate()
                 + "\nStart time: "
                + model.getStartTime() + (model.getStartAMPM()?"PM":"AM") + "\nEnd time: "+ model.getEndTime() 
                   + (model.getEndAMPM()? "PM": "AM")+"\n"
                + "Printer: " + printRequest
                + "\n" + "Software/Special Instructions: " + model.getSpecialRequest() +"\n";
       }       
        //a message box will pop up with the summary of everything
        MessageBox(summary);    
    }
    
     @FXML
     //this is the close button action event
    private void handlesbtnClose(ActionEvent event) {   
        //making a stage to connect the close button to the scene
        Stage stage = (Stage) btnClose.getScene().getWindow();
        //and closing it.
        stage.close(); 
    }
     @FXML
     //this is the clear button action event
    private void handlesbtnClear(ActionEvent event) {
        //clearing and reseting everything
        txtRequestName.clear();
        txtRequestEmail.clear();
        txtEventDate.clear();
        txtNumPart.clear();
        txtEvent.clear();
        txtStartTime.clear();
        txtEndTime.clear();
        txtSpecialSoft.clear();
        txtSpecialSoft.setEditable(false);
        radYes.setSelected(false);
        radNo.setSelected(false);
        chkPrint.setSelected(false);
        txtRequestName.requestFocus();
        lblSpecialSoft.setTextFill(Paint.valueOf("#000000"));
        lblRequestName.setTextFill(Paint.valueOf("#000000"));
        lblRequestEmail.setTextFill(Paint.valueOf("#000000"));
        lblEventTitle.setTextFill(Paint.valueOf("#000000"));
        lblNumPart.setTextFill(Paint.valueOf("#000000"));
        lblEventDate.setTextFill(Paint.valueOf("#000000"));
        lblStartTime.setTextFill(Paint.valueOf("#000000"));
        lblEndTime.setTextFill(Paint.valueOf("#000000"));
        lblAppt.setVisible(false);
        lblAppt.setText("");
        lblEventDate.setVisible(true);
        lblStartTime.setVisible(true);
        lblEndTime.setVisible(true);
        txtStartTime.setVisible(true);
        txtEventDate.setVisible(true);
        txtEndTime.setVisible(true);
        btnTogStart.setVisible(true);
        btnTogEnd.setVisible(true);
        btnTogStart.setText("AM");
        btnTogEnd.setText("AM");
        btnTogStart.setSelected(true);
        btnTogEnd.setSelected(true);
        
    }
    
     @FXML
     //this is the start time's toggle button action event
    private void handlesbtnTogStart(ActionEvent event) {
        //if the start time toggle button is selected
        if (btnTogStart.isSelected()){
            //the text of the start time toggle button will be set to AM
            btnTogStart.setText("AM");
            //also the variable togStart will be set to am
            togStart = "am";
        }
        //otherwise if the start time toggle button is not selected
        else{
            //the start time toggle button's text will be set to PM
            btnTogStart.setText("PM");
            //and the variable togStart will be set to pm
            togStart = "pm";
        }
        //set the labmodel startampm to pm
        model.setStartAMPM(!btnTogStart.isSelected());
        //set the eventmodel startampm to pm
        eventModel.setStartAMPM(!btnTogStart.isSelected());
    }
     @FXML
     //this is the end time's toggle button action event
    private void handlesbtnTogEnd(ActionEvent event) {
        //if the end time toggle button is selected
        if (btnTogEnd.isSelected()){
            //the end time toggle button's text will be set to AM
            btnTogEnd.setText("AM");
            //the variable togEnd will be set to am
            togEnd = "am"; 
        }
        //otherwise if the end time toggle button is not selected
        else{
            //the end time toggle button's text will be set to PM
            btnTogEnd.setText("PM");
            //the variable togEnd will be set to pm
            togEnd = "pm"; 
        } 
        //set the labmodel endampm to pm
        model.setEndAMPM(!btnTogEnd.isSelected());
        //set the eventmodel endampm to pm
        eventModel.setEndAMPM(!btnTogEnd.isSelected());
    }
     @FXML
     //this is the yes radio button's action event
    private void handlesradYes(ActionEvent event) {
        //if the yes radio button is selected
        if (radYes.isSelected()){
            //the special request text area will be editable
            txtSpecialSoft.setEditable(true);
            //the special request text area will be in focus
            txtSpecialSoft.requestFocus(); 
            //the lblspecialsoft will turn black when all is good.
            lblSpecialSoft.setTextFill(Paint.valueOf("#000000"));
        }
        //in case the no radio button is selected 
        //the no radio button will be not selected
        radNo.setSelected(false);        
    }
     @FXML
     //this is the no radio button's action event
    private void handlesradNo(ActionEvent event) {
        //if the no radio button is selected
        if (radNo.isSelected()){
            //the special request text area will not be editable
            txtSpecialSoft.setEditable(false);
            //the special request area will be cleared
            txtSpecialSoft.clear();
             //the lblspecialsoft will turn black when all is good.
            lblSpecialSoft.setTextFill(Paint.valueOf("#000000"));
            //setting the special request to nothing
            model.setSpecialRequest("");
        }
        //in case the yes radio button is selected 
        //it yes radio button will be not selected
         radYes.setSelected(false);
    }
    
    //this message box is for the summary of the whole form
    private void MessageBox(String message){
        //making a new stage
        Stage dialogStage = new Stage(); 
        //having an ok button
        Button okBtn = new Button("OK");
        //having a cancel button
        Button cancelBtn = new Button("Cancel");
        //having a text variable with a string passed in
        Text text = new Text(message);
        //making sure that the stage is an application stage
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        //setting the scene
        dialogStage.setScene(new Scene(VBoxBuilder.create().
            children(text, okBtn, cancelBtn).
            alignment(Pos.CENTER).padding(new Insets(5)).build()));
        //showing the dialog box
        dialogStage.show();     
        //this is the ok button's on action event
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //the stage closes
                dialogStage.close();
                //everything is cleared and reset
                txtRequestName.clear();
                txtRequestEmail.clear();
                txtEventDate.clear();
                txtNumPart.clear();
                txtEvent.clear();
                txtStartTime.clear();
                txtEndTime.clear();
                txtSpecialSoft.clear();
                radYes.setSelected(false);
                radNo.setSelected(false);
                chkPrint.setSelected(false);
                txtRequestName.requestFocus();
                lblEventDate.setVisible(true);
                lblStartTime.setVisible(true);
                lblEndTime.setVisible(true);
                txtStartTime.setVisible(true);
                txtEventDate.setVisible(true);
                txtEndTime.setVisible(true);
                btnTogStart.setVisible(true);
                btnTogEnd.setVisible(true);
                model.setSpecialRequest("");
                txtSpecialSoft.setEditable(false);
                model.setDay("");
                lblAppt.setVisible(false);
                btnCheck.setVisible(true);
                btnTogStart.setSelected(true);
                btnTogEnd.setSelected(true);
                btnTogStart.setText("AM");
                btnTogEnd.setText("AM");
            }
        });
        //this is the cancel button event handler
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if the user presses cancel then the 
                //stage closes
                dialogStage.close();
            }
        });
        
    }
   
    //this is a printer check method I made
    private void printerCheck(){
        //so if the printer check box is checked
        if (chkPrint.isSelected()){
            //the variable printRequest will say 
            //Printer requested
            printRequest = "Printer Requested";
        }
        //otherwise if it is not checked
        else
        {
            //then the variable printRequest will say
            //Printer Not Requested
            printRequest = "Printer Not Requested";
        }
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
