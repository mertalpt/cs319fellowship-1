package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreditsController {
    @FXML Button mainMenuBtn;

    @FXML
    public void backToMainMenu() throws IOException{
        Stage current = (Stage) mainMenuBtn.getScene().getWindow();

        BorderPane root = FXMLLoader.load(getClass().getResource("/view/MainMenuStage.fxml"));

        current.getScene().setRoot(root);
    }
}
