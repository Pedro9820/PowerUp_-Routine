package com.br.ufrpe.powerUp.gui.helpers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    protected ImageView animarSprite(Image spriteSheet, int frameWidth, int frameHeight) {
        int numFramesPerRow = (int) (spriteSheet.getWidth() / frameWidth);
        int numFramesTotal = (int) ((spriteSheet.getWidth() / frameWidth) * (spriteSheet.getHeight() / frameHeight));

        ImageView imageView = new ImageView(spriteSheet);
        imageView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));

        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), e -> {
            Rectangle2D viewport = imageView.getViewport();
            int currentFrameX = (int) viewport.getMinX();
            int currentFrameY = (int) viewport.getMinY();

            // Calcula o próximo frame (coluna e linha)
            int proxFrame = ((currentFrameX / frameWidth) + 1) % numFramesPerRow;
            int proxRow = currentFrameY / frameHeight;

            if (proxFrame == 0) {
                proxRow = (int) ((proxRow + 1) % (spriteSheet.getHeight() / frameHeight));
            }

            imageView.setViewport(new Rectangle2D(proxFrame * frameWidth,
                    proxRow * frameHeight,
                    frameWidth, frameHeight));
        });

        Timeline animation = new Timeline(keyFrame);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        return imageView;
    }
}
