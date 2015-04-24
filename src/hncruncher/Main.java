package hncruncher;

import com.sun.javafx.application.HostServicesDelegate;
import hncruncher.data.FavouriteManager;
import hncruncher.data.HNLinkParser;
import hncruncher.uicomponents.EntryListView;
import hncruncher.uicomponents.EntryPanel;
import hncruncher.uicomponents.TopBar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    public DataControl dataControl = new DataControl();
    public static HostServicesDelegate hostServicesDelegate;
    public static Main main;
    public BorderPane root;
    public EntryListView listView = new EntryListView();
    public TopBar topBar;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        this.root = root;
        main = this;
        topBar = new TopBar();
        root.getStyleClass().add("baseBG");

        hostServicesDelegate = HostServicesDelegate.getInstance(this);

        primaryStage.setTitle("Hacker News Cruncher 0.3");

        root.setTop(topBar);
        root.setCenter(listView);


        primaryStage.setScene(new Scene(root, Color.valueOf("#eceff1")));
        primaryStage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.show();

    }
    public void loadData(MouseEvent mouseEvent){

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                try {

                    String[] ids = HNLinkParser.getIDsFromLink("https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty");

                    for (int i = 0; i < 15; i++) {
                        HNEntry entry = HNLinkParser.getEntryFromID(ids[i]);
                        dataControl.addEntry(entry);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                dataControl.getEntries().forEach(e -> listView.addEntryPanel(new EntryPanel(e)));
            }
        });
        new Thread(task).start();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
