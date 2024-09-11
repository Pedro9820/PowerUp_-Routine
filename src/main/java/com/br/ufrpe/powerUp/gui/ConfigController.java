package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.gui.helpers.BasicController;
import com.br.ufrpe.powerUp.gui.helpers.Constantes;
import com.br.ufrpe.powerUp.gui.helpers.ControladorUsuarioInterface;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfigController extends BasicController implements ControladorUsuarioInterface {
    private ControladorUsuario userController;

    @FXML
    public Button buttonVoltar;

    @Override
    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
    }

    @Override
    public ControladorUsuario getUserController() {
        return userController;
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

    public void buttonMouseEntered() {
        playSound("/sounds/buttonSFX.wav");
    }

    public void buttonMousePressed() {
        playSound("/sounds/buttonCilckSFX.mp3");
    }
}
