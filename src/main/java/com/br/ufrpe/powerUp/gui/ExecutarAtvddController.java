package com.br.ufrpe.powerUp.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class ExecutarAtvddController {
    @FXML
    public Button buttonTerminar;
    @FXML
    private Text textAtividade;

    private LocalDateTime inicioAtividade;
    private LocalDateTime fimAtividade;

   public void setAtividade(String atividade) {
       textAtividade.setText(atividade);
    }

    public void btnIniciar() {
        this.inicioAtividade = LocalDateTime.now();
    }

    public void btnTerminar() {
       if (inicioAtividade != null) {
           this.fimAtividade = LocalDateTime.now();

           Stage stage = (Stage) buttonTerminar.getScene().getWindow();
           stage.close();
       }
    }

    public LocalDateTime getInicioAtividade() {
        return inicioAtividade;
    }

    public LocalDateTime getFimAtividade() {
        return fimAtividade;
    }

    public void btnVoltar() {
        Stage stage = (Stage) buttonTerminar.getScene().getWindow();
        stage.close();
    }

}
