package com.br.ufrpe.powerUp.gui.helpers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
}
