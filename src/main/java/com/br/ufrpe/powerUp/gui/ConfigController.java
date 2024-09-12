package com.br.ufrpe.powerUp.gui;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollBar;
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

    @Override
    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
        scrollBarVolume.setValue(userController.getVolume());
        checkBoxNotificacao.setSelected(userController.isbNotificacao());
        String dias = Integer.toString(userController.getNotificacoDias());
        txtFieldDias.setText(dias);
    }

    @Override
    public ControladorUsuario getUserController() {
        return userController;
    }

    public void mudarEstadoNotificacao() {
        boolean notificacao = userController.isbNotificacao();
        userController.setbNotificacao(!notificacao);
    }

    public void mudarDiasNotificacao() {
        try {
            userController.setNotificacoDias(Integer.parseInt(txtFieldDias.getText()));
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void btnVoltar(ActionEvent event) throws IOException {
        BasicController.criarCena(event, "/Perfil.fxml", this,
                Constantes.PERFILWIDTH,
                Constantes.PERFILHEIGHT);
    }


    public void btnSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/telaLogin.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void btnTestarNotificacao() {
        Toast.toast(ToastType.INFO, "test", "Olá mundo!");
    }

    public void buttonMouseEntered() {
        playSound("/sounds/buttonSFX.wav");
    }

    public void buttonMousePressed() {
        playSound("/sounds/buttonCilckSFX.mp3");
    }
}
