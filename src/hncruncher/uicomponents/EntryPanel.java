package hncruncher.uicomponents;

import hncruncher.HNEntry;
import hncruncher.Main;
import hncruncher.data.FavouriteManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Panel to display information about HN Entry
 * Created by Espen Meidell on 23.04.15.
 */
public class EntryPanel extends HBox{

    private HNEntry entry;

    private Label titleLabel = new Label("");
    private Label infoLabel = new Label("");
    private VBox labelBox = new VBox(5, titleLabel, infoLabel);

    private Button goButton = new Button("Open Article");
    private Button favButton = new Button();

    private ImageView img1 = new ImageView(new Image(getClass().getResourceAsStream("../res/bookmark_1.png")));
    private ImageView img2 = new ImageView(new Image(getClass().getResourceAsStream("../res/bookmark_2.png")));

    /**
     * Creates a new entry panel
     * @param e Entry to show
     */
    public EntryPanel(HNEntry e){
        entry = e;
        setSpacing(20);
        setPadding(new Insets(5));
        setAlignment(Pos.CENTER);
        getStyleClass().add("entry");
        titleLabel.getStyleClass().add("entryTitle");
        favButton.getStyleClass().setAll("favButton");
        favButton.setTooltip(new Tooltip("Add to favourites"));

        //SET FAVOURITE ICON

        if (FavouriteManager.isFavourite(e.getId())){
            favButton.setGraphic(img2);
        } else {
            favButton.setGraphic(img1);
        }



        labelBox.setMinWidth(700);
        labelBox.setMaxWidth(700);

        titleLabel.setText(e.getTitle());
        infoLabel.setText(Integer.toString(e.getPointCount()) + " point(s), by: " + e.getBy()+ ", ID: "+e.getId());


        goButton.setOnMouseClicked(e::openInBrowser);
        favButton.setOnMouseClicked(this::toggleFavourites);
        getChildren().addAll(labelBox, goButton, favButton);
    }

    private void toggleFavourites(MouseEvent event){
        if (FavouriteManager.isFavourite(entry.getId())) {  //Remove from favourites
            FavouriteManager.removeFromFavourites(entry.getId());
            favButton.setGraphic(img1);
        } else {            //Add to favourites
            FavouriteManager.addToFavourites(entry.getId());
            favButton.setGraphic(img2);
        }
    }


}
