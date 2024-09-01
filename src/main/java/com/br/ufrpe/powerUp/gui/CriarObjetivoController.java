package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.negocio.beans.Objetivo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CriarObjetivoController {
    private ControladorUsuario userController;

    @FXML
    public TextField txtFieldNome;
    @FXML
    public TextField txtFieldQuota;
    @FXML
    public TextArea txtAreaDescricao;
    @FXML
    public Button buttonCriar;

    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
    }

    public void criarObjetivo() {
        LocalDate data = LocalDate.now();
        Objetivo objetivo = new Objetivo(data, txtFieldNome.getText(), txtAreaDescricao.getText(), 10);
        userController.adicionarObjetivoUsuario(objetivo);

        Stage stage = (Stage) buttonCriar.getScene().getWindow();
        stage.close();
    }
}
