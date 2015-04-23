package hncruncher;

import hncruncher.hncruncher.uicomponents.EntryPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox(5);

        primaryStage.setTitle("Hello World");

        String[] ids = HNLinkParser.getIDsFromLink("https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty");


        if (ids != null) {
            root.getChildren().add(new EntryPanel(HNLinkParser.getEntryFromID(ids[0])));
        }
        System.out.println(ids.length);
        primaryStage.setScene(new Scene(root, 650, 275));
        primaryStage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
