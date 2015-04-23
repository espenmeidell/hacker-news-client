package hncruncher;

import com.sun.javafx.application.HostServicesDelegate;
import hncruncher.data.FavouriteManager;
import hncruncher.data.HNLinkParser;
import hncruncher.uicomponents.EntryListView;
import hncruncher.uicomponents.EntryPanel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    public static HostServicesDelegate hostServicesDelegate;
    public BorderPane root;
    public EntryListView listView = new EntryListView();

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        this.root = root;
        root.getStyleClass().add("baseBG");

        hostServicesDelegate = HostServicesDelegate.getInstance(this);

        primaryStage.setTitle("Hacker News Cruncher 0.3");


        root.setCenter(listView);

        primaryStage.setScene(new Scene(root, Color.valueOf("#eceff1")));
        primaryStage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, stageShownEvent);
        primaryStage.show();
    }

    EventHandler<WindowEvent> stageShownEvent = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {

            try {
                String[] ids = HNLinkParser.getIDsFromLink("https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty");

                for (int i = 0; i < 10; i++) {
                    listView.addEntryPanel(new EntryPanel(HNLinkParser.getEntryFromID(ids[i])));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        }
    };


    public static void main(String[] args) {
        launch(args);
    }
}
