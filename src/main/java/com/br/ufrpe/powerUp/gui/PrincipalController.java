package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.negocio.beans.Objetivo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class PrincipalController {
    private ControladorUsuario userController;

    @FXML
    private Button buttonAtividade;
    @FXML
    private VBox vboxObjetivos;

    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
        atualizarObjetivos();
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

        VBox vbox = new VBox(new Label(mensagem));
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 200, 100);
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TelaExecutarAtividades.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela de perfil
        AtividadeController controller = fxmlLoader.getController();
        controller.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, Constantes.EXECUTARATIVIDADEWIDTH, Constantes.EXECUTARATIVIDADEHEIGHT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void btnObjetivos(ActionEvent event) throws IOException {
        // Carregar o FXML usando FXMLLoader para obter o controlador
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Objetivos.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela de perfil
        ObjetivosController controller = fxmlLoader.getController();
        controller.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, Constantes.OBJETIVOSWIDTH, Constantes.CONFIGHEIGHT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void btnPerfil(ActionEvent event) throws IOException {
        // Carregar o FXML usando FXMLLoader para obter o controlador
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Perfil.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela de perfil
        PerfilController controller = fxmlLoader.getController();
        controller.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, Constantes.PERFILWIDTH, Constantes.PERFILHEIGHT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
