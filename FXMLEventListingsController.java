/*
 * Karna Johnson
 * CSC 240-040
 * Project #3
 * Description: The user is trying to schedule an event.
                If the event is available then they can schedule that event.
                if the event is not available then they can't schedule the event.
 */
package labscheduler;

//all the import statements
import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;

/**
 * FXML Controller class
 *
 * @author Karna Johnson
 */
public class FXMLEventListingsController implements Initializable {

    //the controls
    @FXML
    private TableView<EventModel> tbRequestEvent;
    @FXML
    private TableView<EventModel> tbEvents;
    @FXML
    private Label lblAvailable;
    //models
    private EventModel model;
    private EventCollectionModel collection;
    private LabModel lab;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initializing models
        collection = new EventCollectionModel();
        model = new EventModel(lab.getEventTitle(),lab.getBeginDate(),
                lab.getStartTime(), lab.getStartAMPM(), lab.getEndTime(), lab.getEndAMPM());
        lab = new LabModel();
        //reading from the file
        collection.readFile();
        //setting the tablecolumn
        TableColumn<EventModel, String> eventCol = new TableColumn<EventModel, String>("Event Title");
        TableColumn<EventModel, String> dateCol = new TableColumn<EventModel, String>("Event Date");
        TableColumn<EventModel, String> startCol = new TableColumn<EventModel, String>("Start Time");
        TableColumn<EventModel, String> endCol = new TableColumn<EventModel, String>("End Time");
        //setting seperate table columns 
        TableColumn<EventModel, String> eventCol1 = new TableColumn<EventModel, String>("Event Title");
        TableColumn<EventModel, String> dateCol1 = new TableColumn<EventModel, String>("Event Date");
        TableColumn<EventModel, String> startCol1 = new TableColumn<EventModel, String>("Start Time");
        TableColumn<EventModel, String> endCol1 = new TableColumn<EventModel, String>("End Time");
        //setting the width of the columns
        eventCol.setMinWidth(114);
        dateCol.setMinWidth(114);
        startCol.setMinWidth(114);
        endCol.setMinWidth(114);
        //setting the width of the columns
        eventCol1.setMinWidth(114);
        dateCol1.setMinWidth(114);
        startCol1.setMinWidth(114);
        endCol1.setMinWidth(114);
        //setting the cellvaluefactory for the properties 
        eventCol.setCellValueFactory(cellData -> cellData.getValue().getEventTitleProp());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getEventDateProp());
        startCol.setCellValueFactory(cellData -> cellData.getValue().getStartTimeProp());
        endCol.setCellValueFactory(cellData -> cellData.getValue().getEndTimeProp());
        //setting the cellvaluefactory for the properties 
        eventCol1.setCellValueFactory(cellData -> cellData.getValue().getEventTitleProp());
        dateCol1.setCellValueFactory(cellData -> cellData.getValue().getEventDateProp());
        startCol1.setCellValueFactory(cellData -> cellData.getValue().getStartTimeProp());
        endCol1.setCellValueFactory(cellData -> cellData.getValue().getEndTimeProp());
        //adding the columns
        tbRequestEvent.getColumns().addAll(eventCol, dateCol, startCol, endCol);
        //making an arraylist modelcollection for the data in the tableview
        ArrayList<EventModel> modelCollection = new ArrayList<EventModel>();
        //adding the eventmodel
        modelCollection.add(model);
        //making a observable list with eventmodel
        ObservableList<EventModel> modelData = FXCollections.observableArrayList(modelCollection);
       //setting the items for the requesting event
        tbRequestEvent.setItems(modelData); 
        //making an observable list for the events from the collection
        ObservableList<EventModel> data = FXCollections.observableArrayList(collection.getItems());
        //setting the columns
        tbEvents.getColumns().addAll(eventCol1, dateCol1, startCol1, endCol1);
        //setting the data
        tbEvents.setItems(data);       
    }    
    @FXML
    public void handlesBtnEvent(ActionEvent event){
        //making a boolean for is the event that the user is trying to schedule
        //available or not
        boolean isItConflicting= false;
        //making a local variable i
        int i = 0;
        //a for loop going through the collection
        for (i = 0; i < collection.getItems().size(); i++){ 
            //getting the true or false if the collection and the requested event
            //conflict
            isItConflicting = model.isInConflict(collection.getItems().get(i)); 
            //if they do conflict then break
            if (isItConflicting) break;        
        }
        //if the events are conflicting
        if (isItConflicting){
            //the lblAvailable label will say lab not available on the date that
            //they requested
            lblAvailable.setText("Lab Not Available on " +lab.getBeginDate());
            //making a selectionmodel to be able to select the event that conflicts
            TableViewSelectionModel selectionModel = tbEvents.getSelectionModel();
            //selecting the event that does conflict
            selectionModel.select(i);
        }//otherwise
        else{
            //the lblAvailable label will say that the lab is available on 
            //the requested date
            lblAvailable.setText("Lab Available on " +lab.getBeginDate());
            //adding the model to the collection
            collection.addItems(model);
            //sorting the collection
            collection.sort();
            //writing to the file
            collection.writeFile();
        } 
    }
    @FXML
    public void handlesBtnCloseEvent(ActionEvent event){       
        //this is to switch to the other form 
        try{
            //loading the other form
            AnchorPane labScene = (AnchorPane)FXMLLoader.load
        (FXMLEventListingsController.class.getResource("FXMLLabDoc.fxml"));
            //getting the scene
            Scene scene = new Scene(labScene);
            //making a temperary button
            Button tmp = (Button)event.getSource();
            //setting the stage
            Stage theStage = (Stage)tmp.getScene().getWindow();
            //setting the scene
            theStage.setScene(scene);
        //catching any ioexception
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
