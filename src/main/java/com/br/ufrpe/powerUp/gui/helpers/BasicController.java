package com.br.ufrpe.powerUp.gui.helpers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class BasicController {

    protected void playSound(String path) {
        String soundPath = getResourcePath(path);
        if (soundPath != null) {
            Media sound = new Media(soundPath);
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
    }

    private String getResourcePath(String resource) {
        // verifica se o recurso existe
        var resourceURL = BasicController.class.getResource(resource);
        return resourceURL != null ? resourceURL.toString() : null;
    }

    protected static <C> void criarCena(ActionEvent event, String fxmlFile, C controller, double width, double height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BasicController.class.getResource(fxmlFile));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela
        C loadedController = fxmlLoader.getController();
        if (controller != null) {
            if (controller instanceof ControladorUsuarioInterface) {
                // 'controller' é o atual e o 'loadedController' é o próximo
                ((ControladorUsuarioInterface) loadedController).setUserController(((ControladorUsuarioInterface) controller).getUserController());
            }
        }

        // Configurar a cena e o palco
        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
