package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


    public void btnAtividade() {
        try {
            // Carregar o FXML e o controlador
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/TelaExecutarAtividades.fxml"));
            Scene principalScene = new Scene(fxmlLoader.load(), 600, 484);

            // Obter o controlador da tela de perfil
            AtividadeController controller = fxmlLoader.getController();

            // Passar o controlador de usuário (userController) para o perfilController
            controller.setUserController(userController);

            // Criar a nova janela
            Stage principalStage = new Stage();
            principalStage.setTitle("Perfil");
            principalStage.setScene(principalScene);
            principalStage.show();

            // Fechar a janela atual
            Stage stage = (Stage) buttonAtividade.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnObjetivos() {
        try {
            // Carregar o FXML e o controlador
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/Objetivos.fxml"));
            Scene principalScene = new Scene(fxmlLoader.load(), 600, 400);

            // Obter o controlador da tela de perfil
            ObjetivosController controller = fxmlLoader.getController();

            // Passar o controlador de usuário (userController) para o perfilController
            controller.setUserController(userController);

            // Criar a nova janela
            Stage principalStage = new Stage();
            principalStage.setTitle("Perfil");
            principalStage.setScene(principalScene);
            principalStage.show();

            // Fechar a janela atual
            Stage stage = (Stage) buttonAtividade.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnPerfil() {
        try {
            // Carregar o FXML e o controlador
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/Perfil.fxml"));
            Scene principalScene = new Scene(fxmlLoader.load(), 600, 400);

            // Obter o controlador da tela de perfil
            PerfilController perfilController = fxmlLoader.getController();

            // Passar o controlador de usuário (userController) para o perfilController
            perfilController.setUserController(userController);

            // Criar a nova janela
            Stage principalStage = new Stage();
            principalStage.setTitle("Perfil");
            principalStage.setScene(principalScene);
            principalStage.show();

            // Fechar a janela atual
            Stage stage = (Stage) buttonAtividade.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
