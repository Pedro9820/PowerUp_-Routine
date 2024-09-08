package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

@SuppressWarnings("ALL")
public class PrincipalController {
    private ControladorUsuario userController;

    @FXML
    private Button buttonAtividade;

    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
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
