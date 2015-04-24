package hncruncher.uicomponents;

import hncruncher.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * This will be the top part of the application
 * Created by Espen Meidell on 23.04.15.
 */
public class TopBar extends HBox {

    private ToggleGroup sourceGroup = new ToggleGroup();
    private ToggleButton newButton = new ToggleButton("New Articles");
    private ToggleButton topButton = new ToggleButton("Top Articles");
    private ToggleButton favButton = new ToggleButton("Favourites");

    private ToggleGroup sortGroup = new ToggleGroup();
    private ToggleButton normalButton = new ToggleButton("Normal \u23F7");
    private ToggleButton pointsButton = new ToggleButton("Points");

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
        newButton.setToggleGroup(sourceGroup);
        newButton.getStyleClass().setAll("my-toggle-button");
        newButton.setOnMouseClicked(Main.main::loadData);
        topButton.setToggleGroup(sourceGroup);
        topButton.getStyleClass().setAll("my-toggle-button");
        topButton.setOnMouseClicked(Main.main::loadData);
        favButton.setToggleGroup(sourceGroup);
        favButton.getStyleClass().setAll("my-toggle-button");
        favButton.setOnMouseClicked(Main.main::loadData);
        topButton.setSelected(true);

        normalButton.setToggleGroup(sortGroup);
        normalButton.getStyleClass().setAll("my-toggle-button");
        normalButton.setOnMouseClicked(this::changeSorting);
        pointsButton.setToggleGroup(sortGroup);
        pointsButton.getStyleClass().setAll("my-toggle-button");
        pointsButton.setOnMouseClicked(this::changeSorting);
        normalButton.setSelected(true);

        progressBox.setMaxHeight(30);
        progressBox.setAlignment(Pos.CENTER);

        sep1.setMinWidth(30);

        getChildren().addAll(newButton, topButton, favButton, sep1, normalButton, pointsButton);

    }

    /**
     * Get the selected category
     * @return "new", "top" or "fav"
     */
    public String getSelectedCategory(){
        if (sourceGroup.getSelectedToggle() == newButton){
            return "new";
        } else if (sourceGroup.getSelectedToggle() == topButton) {
            return "top";
        } else if (sourceGroup.getSelectedToggle() == favButton) {
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

    /**
     * Check if data is to be sorted by points
     * @return  if data is to be sorted by points
     */
    public boolean sortByPoints(){
        return pointsButton.isSelected();
    }


    private void changeSorting(MouseEvent event){
        if (sortByPoints()){
            Main.main.listView.sortEntriesByPoints();
            normalButton.setText("Normal");
            pointsButton.setText("Points \u23F7");
        } else {
            normalButton.setText("Normal \u23F7");
            pointsButton.setText("Points");
            Main.main.loadData(null);
        }
    }

}
