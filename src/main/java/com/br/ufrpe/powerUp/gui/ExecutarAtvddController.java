package com.br.ufrpe.powerUp.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.time.LocalDateTime;

public class ExecutarAtvddController {
    @FXML
    public Button buttonTerminar;
    @FXML
    private Text textAtividade;
    @FXML
    private Label labelCronometro;
    @FXML
    private Label labelDica;

    private LocalDateTime inicioAtividade;
    private LocalDateTime fimAtividade;
    private Timeline cronometroTimeline;

    public void setAtividade(String atividade, String dica) {
       textAtividade.setText(atividade);

        labelDica.setText(dica);
       if (atividade.equals("Malhando...")) {
           DayOfWeek diaDaSemana = LocalDate.now().getDayOfWeek();
           String exercicios;

           switch (diaDaSemana) {
               case MONDAY:
                   exercicios = "Segunda-feira: peitoral e tríceps";
                   break;
               case TUESDAY:
                   exercicios = "Terça-feira: costas e bíceps";
                   break;
               case WEDNESDAY:
                   exercicios = "Quarta-feira: Pernas e ombro";
                   break;
               case THURSDAY:
                   exercicios = "Quinta-feira: Peito e tríceps";
                   break;
               case FRIDAY:
                   exercicios = "Sexta-feira: ombro e abdominais";
                   break;
               case SATURDAY:
                   exercicios = "Sábado: Corrida longa, Caminhada rápida";
                   break;
               case SUNDAY:
                   exercicios = "Domingo: Descanso ativo";
                   break;
               default:
                   exercicios = "Dia não identificado!";
                   break;
           }
           labelDica.setText(exercicios);

       }
    }

    public void btnIniciar() {
        this.inicioAtividade = LocalDateTime.now();
        cronometroTimeline = new Timeline(
          new KeyFrame(Duration.seconds(1), event -> atualizarCronometro())
        );
        cronometroTimeline.setCycleCount(Timeline.INDEFINITE);
        cronometroTimeline.play();
    }

    private void atualizarCronometro() {
        if (inicioAtividade != null) {
            LocalDateTime agora = LocalDateTime.now();

            long segundosTotais = ChronoUnit.SECONDS.between(inicioAtividade, agora);
            long horas = segundosTotais / 3600;
            long minutos = (segundosTotais % 3600) / 60;
            long segundos = segundosTotais % 60;

            labelCronometro.setText(String.format("%02d:%02d:%02d", horas, minutos, segundos));
        }
    }


    public void btnTerminar() {
        if (inicioAtividade != null) {
            this.fimAtividade = LocalDateTime.now();

            // Parar o cronômetro
            if (cronometroTimeline != null) {
                cronometroTimeline.stop();
            }

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
