package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    public void btnVoltar() {
        try {
            // Carregar o FXML e o controlador
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/telaPerfil.fxml"));
            Scene principalScene = new Scene(fxmlLoader.load(), 600, 400);

            // Obter o controlador da tela de perfil
            PerfilController controller = fxmlLoader.getController();

            // Passar o controlador de usuário (userController) para o perfilController
            controller.setUserController(userController);

            // Criar a nova janela
            Stage principalStage = new Stage();
            principalStage.setTitle("PowerUP");
            principalStage.setScene(principalScene);
            principalStage.show();

            // Fechar a janela atual
            Stage stage = (Stage) buttonVoltar.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSair() {
        try {
            // Carregar o FXML e o controlador
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/telaLogin.fxml"));
            Scene principalScene = new Scene(fxmlLoader.load(), 600, 400);

            // Criar a nova janela
            Stage principalStage = new Stage();
            principalStage.setTitle("Login");
            principalStage.setScene(principalScene);
            principalStage.show();

            // Fechar a janela atual
            Stage stage = (Stage) buttonVoltar.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
