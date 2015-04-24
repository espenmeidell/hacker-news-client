package hncruncher.uicomponents;

import hncruncher.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Creates a popup notification
 * Created by Espen Meidell on 24.04.15.
 */
public class PopupNotification extends Stage{

    private BorderPane root = new BorderPane();
    private HBox contentBox;
    private VBox textBox;
    private HBox buttonBox;

    private Label titleLabel;
    private Label messageLabel;

    private Button okButton;


    public PopupNotification(String title, String message, PopupType type){
        initOwner(Main.main.mainStage);
        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.WINDOW_MODAL);
        titleLabel = new Label(title);
        messageLabel = new Label(message);
        messageLabel.setWrapText(true);

        contentBox = new HBox(10);
        if (type != PopupType.NO_IMAGE) {
            contentBox.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream(type.getImgFile()))));
        }
        textBox = new VBox(10, titleLabel, messageLabel);
        textBox.setPadding(new Insets(10, 5, 5, 5));

        contentBox.getChildren().add(textBox);

        buttonBox = new HBox();
        buttonBox.setPadding(new Insets(5, 10, 10, 5));
        buttonBox.setAlignment(Pos.BASELINE_RIGHT);
        buttonBox.setMaxWidth(Double.MAX_VALUE);
        okButton = new Button("DISMISS");
        okButton.setOnMouseClicked(this::closePopup);
        buttonBox.getChildren().add(okButton);

        root.setCenter(contentBox);
        root.setBottom(buttonBox);

        setMaxWidth(420);
        setScene(new Scene(root));

    }

    private void closePopup(MouseEvent e){
        close();
    }


}
