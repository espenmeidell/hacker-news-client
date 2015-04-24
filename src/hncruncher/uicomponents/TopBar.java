package hncruncher.uicomponents;

import hncruncher.Main;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * This will be the top part of the application
 * Created by Espen Meidell on 23.04.15.
 */
public class TopBar extends HBox {

    private ToggleGroup group = new ToggleGroup();
    private ToggleButton newButton = new ToggleButton("New Articles");
    private ToggleButton topButton = new ToggleButton("Top Articles");
    private ToggleButton favButton = new ToggleButton("Favourites");

    private Region sep1 = new Region();

    public TopBar() {
        setSpacing(0);
        setPadding(new Insets(5, 30, 5, 30));
        newButton.setToggleGroup(group);
        newButton.getStyleClass().setAll("my-toggle-button");
        newButton.setOnMouseClicked(Main.main::loadData);
        topButton.setToggleGroup(group);
        topButton.getStyleClass().setAll("my-toggle-button");
        topButton.setOnMouseClicked(Main.main::loadData);
        favButton.setToggleGroup(group);
        favButton.getStyleClass().setAll("my-toggle-button");
        favButton.setOnMouseClicked(Main.main::loadData);


        sep1.setMinWidth(10);

        getChildren().addAll(newButton, topButton, favButton, sep1);

    }

    public String getSelectedCategory(){
        if (group.getSelectedToggle() == newButton){
            return "new";
        } else if (group.getSelectedToggle() == topButton) {
            return "top";
        } else if (group.getSelectedToggle() == favButton) {
            return "fav";
        }
        return null;
    }

}
