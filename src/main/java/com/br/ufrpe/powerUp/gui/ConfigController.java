package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.gui.helpers.Constantes;
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

public class ConfigController {
    private ControladorUsuario userController;

    @FXML
    public Button buttonVoltar;

    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
    }

    public void btnVoltar(ActionEvent event) throws IOException {
        // Carregar o FXML usando FXMLLoader para obter o controlador
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Perfil.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela de perfil
        PerfilController perfilController = fxmlLoader.getController();
        perfilController.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, Constantes.PERFILWIDTH, Constantes.PERFILHEIGHT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void btnSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/telaLogin.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
