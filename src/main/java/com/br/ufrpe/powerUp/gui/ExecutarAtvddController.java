package com.br.ufrpe.powerUp.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class ExecutarAtvddController {
    @FXML
    public Button buttonTerminar;
    @FXML
    private Label labelAtividade;

    private LocalDateTime inicioAtividade;
    private LocalDateTime fimAtividade;

   public void setAtividade(String atividade) {
       labelAtividade.setText(atividade);
       this.inicioAtividade = LocalDateTime.now();
    }

    public void btnTerminar() {
       this.fimAtividade = LocalDateTime.now();

        Stage stage = (Stage) buttonTerminar.getScene().getWindow();
        stage.close();
    }

    public LocalDateTime getInicioAtividade() {
        return inicioAtividade;
    }

    public LocalDateTime getFimAtividade() {
        return fimAtividade;
    }

}
