package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.dados.RepositorioUsuarios;
import com.br.ufrpe.powerUp.dados.exceptions.CJEException;
import com.br.ufrpe.powerUp.dados.exceptions.CNException;
import com.br.ufrpe.powerUp.negocio.controllers.controladorUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiController {
    @FXML
    private TextField txtFieldNome;

    @FXML
    private PasswordField txtFieldSenha;

    @FXML
    private Button buttonEntrar;

    @FXML
    private Button buttonCadastrar;

    @FXML
    public Label labelStatus;

    private controladorUsuario userController;

    @FXML
    protected void btnEntrarActionPerformed() throws CJEException, CNException {
        String nome = txtFieldNome.getText();
        String senha = txtFieldSenha.getText();

        try {
            userController = new controladorUsuario(nome, senha, true);
            labelStatus.setText("existe!");

        } catch (CNException e) {
            labelStatus.setText("Conta não existe!");

        }

    }

    @FXML
    protected void btnCadastrarActionPerformed(){
        try {
            // Carrega o FXML para a tela de cadastro
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/telaCadastro.fxml"));

            Scene cadastroScene = new Scene(fxmlLoader.load(), 370, 320);
            Stage cadastroStage = new Stage();
            cadastroStage.setTitle("Cadastro de Usuário");
            cadastroStage.setScene(cadastroScene);
            cadastroStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
