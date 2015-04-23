package hncruncher.uicomponents;

import hncruncher.HNEntry;
import hncruncher.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Panel to display information about HN Entry
 * Created by Espen Meidell on 23.04.15.
 */
public class EntryPanel extends HBox{


    private Label titleLabel = new Label("");
    private Label infoLabel = new Label("");
    private VBox labelBox = new VBox(5, titleLabel, infoLabel);

    private Button goButton = new Button("Open Article");
    private Button favButton = new Button("+");

    public EntryPanel(HNEntry e){
        setSpacing(20);
        setPadding(new Insets(5));
        setAlignment(Pos.CENTER);
        getStyleClass().add("entry");
        titleLabel.getStyleClass().add("entryTitle");
        favButton.getStyleClass().add("favButton");

        labelBox.setMinWidth(700);
        labelBox.setMaxWidth(700);

        titleLabel.setText(e.getTitle());
        infoLabel.setText(Integer.toString(e.getPointCount()) + " point(s), by: " + e.getBy());


        goButton.setOnMouseClicked(e::openInBrowser);
        getChildren().addAll(labelBox, goButton, favButton);
    }


}
