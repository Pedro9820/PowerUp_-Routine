package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.dados.exceptions.CJEException;
import com.br.ufrpe.powerUp.dados.exceptions.CNException;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroController {
    @FXML
    private TextField txtFieldNome;

    @FXML
    private PasswordField txtFieldSenha;

    @FXML
    private TextField txtFieldAltura;

    @FXML
    private Button buttonCadastrar;

    @FXML
    private void btnCadastrarActionPerformed() throws CJEException, CNException {
        String nome = txtFieldNome.getText();
        String senha = txtFieldSenha.getText();
        String altura = txtFieldAltura.getText();

        try {
            float alturaFloat = Float.parseFloat(altura);
            ControladorUsuario userController = new ControladorUsuario(nome, senha, alturaFloat, false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro Concluído");
            alert.setHeaderText(null);
            alert.setContentText("Conta criada com sucesso!");
            alert.showAndWait(); // Espera o usuário fechar o popup

            Stage stage = (Stage) buttonCadastrar.getScene().getWindow();
            stage.close();

        } catch (CJEException | CNException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no Cadastro");
            alert.setHeaderText("Falha ao criar a conta");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Entrada inválida");
            alert.setContentText("Por favor, insira um número válido para a altura.");
            alert.showAndWait();
        }
    }

}
