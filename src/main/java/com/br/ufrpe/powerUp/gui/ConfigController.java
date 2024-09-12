package com.br.ufrpe.powerUp.gui;

import animatefx.animation.Pulse;
import com.br.ufrpe.powerUp.gui.helpers.BasicController;
import com.br.ufrpe.powerUp.gui.helpers.Constantes;
import com.br.ufrpe.powerUp.gui.helpers.ControladorUsuarioInterface;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import com.sshtools.twoslices.Toast;
import com.sshtools.twoslices.ToastType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ConfigController extends BasicController implements ControladorUsuarioInterface {
    private ControladorUsuario userController;

    @FXML
    public Button buttonVoltar;
    @FXML
    private ScrollBar scrollBarVolume;
    @FXML
    private CheckBox checkBoxNotificacao;
    @FXML
    private TextField txtFieldDias;
    @FXML
    private TextField txtFieldNome;
    @FXML
    private TextField txtFieldSenha;
    @FXML
    private TextField txtFieldAltura;

    @Override
    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
        scrollBarVolume.setValue(userController.getVolume());
        checkBoxNotificacao.setSelected(userController.isbNotificacao());
        String dias = Integer.toString(userController.getNotificacoDias());
        txtFieldDias.setText(dias);
        txtFieldNome.setText(userController.getUsuarioName());
        txtFieldSenha.setText(userController.getUsuarioSenha());
        txtFieldAltura.setText(Float.toString(userController.getUsuarioAltura()));
    }

    @Override
    public ControladorUsuario getUserController() {
        return userController;
    }

    @FXML
    public void initialize() {
        scrollBarVolume.valueProperty().addListener((observable, oldValue, newValue) -> {
            double novoVolume = newValue.doubleValue();
            userController.setVolume(novoVolume);
        });
    }

    public void mudarEstadoNotificacao() {
        boolean notificacao = userController.isbNotificacao();
        userController.setbNotificacao(!notificacao);
    }

    public void mudarDiasNotificacao() {
        try {
            userController.setNotificacoDias(Integer.parseInt(txtFieldDias.getText()));
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dia inválido");
            alert.setHeaderText("Digite Outr");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void btnSalvar() {
        try {
            String nome = txtFieldNome.getText();
            String senha = txtFieldSenha.getText();
            float altura = Float.parseFloat(txtFieldAltura.getText());
            userController.setNomeUsuario(nome);
            userController.setSenha(senha);
            userController.setAlturaUsuario(altura);
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Altura inválida");
            alert.setHeaderText("Digite uma altura válida");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void btnVoltar(ActionEvent event) throws IOException {
        BasicController.criarCena(event, "/Perfil.fxml", this,
                Constantes.PERFILWIDTH,
                Constantes.PERFILHEIGHT);
    }


    public void btnSair(ActionEvent event) throws IOException {
        userController.salvarRepositorio();
        Parent root = FXMLLoader.load(getClass().getResource("/telaLogin.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void btnTestarNotificacao() {
        Toast.toast(ToastType.INFO, "test", "Olá mundo!");
    }

    public void buttonMouseEntered(javafx.scene.input.MouseEvent event) {
        Button button = (Button) event.getSource();
        playSound("/sounds/buttonSFX.wav", userController.getVolume());
        new Pulse(button).play();
    }

    public void buttonMousePressed() {
        playSound("/sounds/buttonCilckSFX.mp3", userController.getVolume());
    }
}
