package hncruncher.uicomponents;

import hncruncher.Main;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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

    ProgressIndicator progressIndicator = new ProgressIndicator();
    Label progressLabel = new Label("Loading Data");
    HBox progressBox = new HBox(progressIndicator, progressLabel);

    /**
     * Creates a new Top Bar
     */
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
        topButton.setSelected(true);

        progressBox.setMaxHeight(30);
        progressBox.setAlignment(Pos.CENTER);

        sep1.setMinWidth(10);

        getChildren().addAll(newButton, topButton, favButton, sep1);

    }

    /**
     * Get the selected category
     * @return "new", "top" or "fav"
     */
    public String getSelectedCategory(){
        if (group.getSelectedToggle() == newButton){
            return "new";
        } else if (group.getSelectedToggle() == topButton) {
            return "top";
        } else if (group.getSelectedToggle() == favButton) {
            return "fav";
        }
        return "top";
    }

    /**
     * Shows a loading indicator in the top bar
     */
    public void showLoading(){
        if (getChildren().contains(progressBox)) return;
        getChildren().add(progressBox);
    }

    /**
     * Hides the loading indicator from the top bar
     */
    public void hideLoading(){
        getChildren().remove(progressBox);
    }

}
