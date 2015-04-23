package hncruncher.hncruncher.uicomponents;

import hncruncher.HNEntry;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by espen on 23.04.15.
 */
public class EntryPanel extends HBox{


    private Label titleLabel = new Label("");
    private Label infoLabel = new Label("");
    private VBox labelBox = new VBox(5, titleLabel, infoLabel);

    private Button goButton = new Button("Open Article");

    public EntryPanel(HNEntry e){
        setSpacing(20);
        setAlignment(Pos.CENTER);
        getStyleClass().add("entry");
        titleLabel.getStyleClass().add("entryTitle");

        titleLabel.setText(e.getTitle());
        infoLabel.setText(Integer.toString(e.getPointCount())+" point(s), by: "+e.getBy());


        goButton.setOnMouseClicked(e::openInBrowser);
        getChildren().addAll(labelBox, goButton);
    }


}
