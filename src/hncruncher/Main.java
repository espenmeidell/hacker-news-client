package hncruncher;

import com.sun.javafx.application.HostServicesDelegate;
import hncruncher.data.FavouriteManager;
import hncruncher.data.HNLinkParser;
import hncruncher.uicomponents.EntryPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static HostServicesDelegate hostServicesDelegate;

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox();
        hostServicesDelegate = HostServicesDelegate.getInstance(this);

        primaryStage.setTitle("Hacker News Cruncher 0.3");

        String[] ids = HNLinkParser.getIDsFromLink("https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty");


        for (int i = 0; i < 10; i++){
            root.getChildren().add(new EntryPanel(HNLinkParser.getEntryFromID(ids[i])));
        }


        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
