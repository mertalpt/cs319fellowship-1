package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Mert Duman
 * @version 17.11.2018
 */
public class GameUIController {
    @FXML Button cubeBtn1;
    @FXML Button cubeBtn2;
    @FXML Button cubeBtn3;
    @FXML Button cubeBtn4;
    @FXML Button cubeBtn5;
    @FXML Button cubeBtn6;
    @FXML GridPane boardPane;
    GameOptionsController goc;
    int difficulty;

    public GameUIController(GameOptionsController goc) {
        this.goc = goc;
        difficulty = goc.getDifficulty();
    }

    public void initialize() {
        loadImagesToCubes(null);
        bindDragToCubes();
        loadBoardAndBindDrag();
    }

    @FXML
    public void backToMainMenu() throws IOException {
        Stage current = (Stage) cubeBtn1.getScene().getWindow();
        BorderPane root = FXMLLoader.load(getClass().getResource("../view/MainMenuStage.fxml"));
        Scene scene = new Scene(root, 800, 600);

        current.setScene(scene);
    }

    private void loadImagesToCubes(Image[] imgs) {
        cubeBtn1.setBackground(new Background(new BackgroundImage(new Image("/patterns/cube1/pattern1_1.jpeg"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, false, false, false, false))));
        cubeBtn2.setBackground(new Background(new BackgroundImage(new Image("/patterns/cube1/pattern1_2.jpeg"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, false, false, false, false))));
        cubeBtn3.setBackground(new Background(new BackgroundImage(new Image("/patterns/cube1/pattern1_3.jpeg"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, false, false, false, false))));
        cubeBtn4.setBackground(new Background(new BackgroundImage(new Image("/patterns/cube1/pattern1_4.jpeg"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, false, false, false, false))));
        cubeBtn5.setBackground(new Background(new BackgroundImage(new Image("/patterns/cube1/pattern1_5.jpeg"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, false, false, false, false))));
        cubeBtn6.setBackground(new Background(new BackgroundImage(new Image("/patterns/cube1/pattern1_6.jpeg"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, false, false, false, false))));
    }

    private void bindDragToCubes() {
        Button[] buttons = {cubeBtn1, cubeBtn2, cubeBtn3, cubeBtn4, cubeBtn5, cubeBtn6};
        for (int i = 0; i < buttons.length; i++) {
            Button btn = buttons[i];
            String str = "/patterns/cube1/pattern1_" + (i+1) + ".jpeg";
            System.out.println(str);
            Image img = new Image(str);
            System.out.println(img);
            btn.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dragboard db = btn.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(img);
                    db.setDragView(img);
                    db.setContent(content);

                    event.consume();
                }
            });
        }
    }

    private void loadBoardAndBindDrag() {
        for(int i = 0; i < difficulty; i++) {
            for(int j = 0; j < difficulty; j++) {
                Pane pane = new Pane();
                pane.setPrefSize(100, 100);

                pane.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        event.acceptTransferModes(TransferMode.ANY);
                        event.consume();
                    }
                });
                pane.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        Dragboard db = event.getDragboard();
                        pane.setBackground(new Background(new BackgroundImage(db.getImage(), BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(120,120,false,false,false,false))));
                        event.setDropCompleted(true);
                        event.consume();
                    }
                });
                boardPane.add(pane, i, j);
            }
        }
    }

    public int getDifficulty() {
        return difficulty;
    }
}