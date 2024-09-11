package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.gui.helpers.BasicController;
import com.br.ufrpe.powerUp.gui.helpers.Constantes;
import com.br.ufrpe.powerUp.gui.helpers.ControladorUsuarioInterface;
import com.br.ufrpe.powerUp.negocio.beans.Objetivo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.Duration;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class PrincipalController extends BasicController implements ControladorUsuarioInterface {
    private ControladorUsuario userController;

    @FXML
    private Button buttonAtividade;
    @FXML
    private VBox vboxObjetivos;

    @Override
    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
        atualizarObjetivos();
    }

    @Override
    public ControladorUsuario getUserController() {
        return this.userController;
    }

    public void atualizarObjetivos() {
        vboxObjetivos.getChildren().clear();

        ArrayList<Objetivo> listaObjetivos = userController.getObjetivos();

        for (Objetivo objetivo : listaObjetivos) {
            // Criar uma HBox para cada objetivo
            VBox vBox = new VBox(2);
            vBox.setAlignment(Pos.CENTER);

            Label nomeLabel = new Label(objetivo.getNome());

            // Barra de progresso com o percentual concluído
            ProgressBar progressBar = new ProgressBar();
            double progresso = calcularProgresso(objetivo);
            progressBar.setProgress(progresso);
            progressBar.setPrefWidth(200);

            Label progressoLabel = new Label(objetivo.getProgresso() + "/" + objetivo.getQuota());

            if (objetivo.getQuota() <= objetivo.getProgresso()) {
                progressoLabel.setText("Concluido!");
                progressBar.getStyleClass().add("progress-bar-concluido");

                progressBar.setOnMouseClicked(event -> mostrarPopup("Objetivo concluído!", objetivo));
                progressoLabel.setOnMouseClicked(event -> mostrarPopup("Objetivo concluído!", objetivo));
            }

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(progressBar, progressoLabel);
            StackPane.setAlignment(progressoLabel, Pos.CENTER);

            // Adicionar os elementos à HBox
            vBox.getChildren().addAll(nomeLabel, stackPane);
            vboxObjetivos.getChildren().add(vBox);
        }
    }

    private void mostrarPopup(String mensagem, Objetivo objetivo ) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(buttonAtividade.getScene().getWindow());

        Label labelMensagem = new Label(mensagem);

        Image spritesheet = new Image(getClass().getResource("/imgs/fireworksSpriteSheet.png").toString());
        ImageView imageView1 = animarSprite(spritesheet, 192, 192);
        ImageView imageView2 = animarSprite(spritesheet, 192, 192);

        HBox hBox = new HBox(imageView1, imageView2);

        VBox vbox = new VBox(5, hBox, labelMensagem);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 300);
        popupStage.setScene(scene);

        String soundPath = getClass().getResource("/sounds/vitoria.mp3").toString();
        Media sound = new Media(soundPath);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        popupStage.show();

        userController.removerObjetivo(objetivo);
        atualizarObjetivos();
    }


    private double calcularProgresso(Objetivo objetivo) {
        int progressoAtual = objetivo.getProgresso();
        int quota = objetivo.getQuota();
        if (quota == 0) return 0;
        return (double) progressoAtual / quota;
    }

    public void btnAtividade(ActionEvent event) throws IOException {
        BasicController.criarCena(event, "/TelaExecutarAtividades.fxml", this,
                Constantes.EXECUTARATIVIDADEWIDTH,
                Constantes.EXECUTARATIVIDADEHEIGHT);
    }

    public void btnObjetivos(ActionEvent event) throws IOException {
        BasicController.criarCena(event, "/Objetivos.fxml", this,
                Constantes.OBJETIVOSWIDTH,
                Constantes.CONFIGHEIGHT);

    }

    public void btnPerfil(ActionEvent event) throws IOException {
        BasicController.criarCena(event, "/Perfil.fxml", this,
                Constantes.PERFILWIDTH,
                Constantes.PERFILHEIGHT);
    }

    public void buttonMouseEntered() {
        playSound("/sounds/buttonSFX.wav");
    }

    public void buttonMousePressed() {
        playSound("/sounds/buttonCilckSFX.mp3");
    }

}
