package hncruncher.uicomponents;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * This component is the scrollpane in the center of root
 * It contains all Entry Panels
 * Created by Espen Meidell on 23.04.15.
 */
public class EntryListView extends ScrollPane{

    private VBox panelBox = new VBox(5);

    /**
     * Creates a new empty list view
     */
    public EntryListView(){
        getStyleClass().add("baseBG");
        panelBox.getStyleClass().add("baseBG");
        setContent(panelBox);
        setMinWidth(700);
        setMinHeight(300);
        setFitToHeight(true);
        setFitToWidth(true);
    }

    /**
     * Add Entry Panels to view
     * @param panels Panels to add
     */
    public void addEntryPanel(EntryPanel... panels){
        for (EntryPanel panel:panels) {
            panelBox.getChildren().add(panel);
        }
    }

    /**
     * Remove given entry panels from view
     * @param panels Panels to remove
     */
    public void removeEntryPanel(EntryPanel... panels) {
        for (EntryPanel panel:panels) {
            panelBox.getChildren().remove(panel);
        }
    }

    /**
     * Removes all entry panels
     */
    public void removeAllEntryPanels() {
        panelBox.getChildren().removeAll(panelBox.getChildren());
    }




}
