package hncruncher;

import com.sun.javafx.application.HostServicesDelegate;
import hncruncher.data.EntryPointComparator;
import hncruncher.data.FavouriteManager;
import hncruncher.data.HNLinkParser;
import hncruncher.uicomponents.EntryListView;
import hncruncher.uicomponents.EntryPanel;
import hncruncher.uicomponents.TopBar;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public DataControl dataControl = new DataControl();         //Controls data for list views
    public static HostServicesDelegate hostServicesDelegate;       //To open browser
    public static Main main;                                    //Main instance
    public BorderPane root;                                     //Application Root
    public EntryListView listView = new EntryListView();        //List view to add entries to
    public TopBar topBar;                                       //Top bar (contains controls etc.)

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
        loadData(null);

    }

    /**
     * Load data based on the selected button in the top panel
     * @param mouseEvent Mouse Event that starts this method. Use null if not applicable
     */
    public void loadData(MouseEvent mouseEvent){
        topBar.showLoading();
        listView.removeAllEntryPanels();    //Remove existing panels (if any)
        dataControl.clearEntries();         //Remove HNEntries from data control
        String selectedCategory = topBar.getSelectedCategory();
        String link;
        switch (selectedCategory){
            case "top":
                System.out.println("Loading top stories");
                link = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
                break;
            case "new":
                System.out.println("Loading new stories");
                link = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
                break;
            case "fav":
                //TODO: Implement favourite thing
                link = "FAVOURITES"; //To prevent nullpointer
                break;
            default:
                link = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
                break;
        }


        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    String[] ids;
                    if (link.equals("FAVOURITES")) {
                        ids = FavouriteManager.getFavourites();
                    } else {
                        ids = HNLinkParser.getIDsFromLink(link);
                    }
                    for (int i = 0; i < 15; i++) {
                        HNEntry entry = HNLinkParser.getEntryFromID(ids[i].trim());
                        dataControl.addEntry(entry);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                dataControl.sortEntries(new EntryPointComparator());
                dataControl.getEntries().forEach(e -> listView.addEntryPanel(new EntryPanel(e)));
                topBar.hideLoading();
            }
        });
        new Thread(task).start();   //Load data from HN in a seperate thread, so that UI doesn't freeze

    }



    public static void main(String[] args) {
        launch(args);
    }
}
