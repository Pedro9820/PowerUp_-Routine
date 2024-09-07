package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.dados.exceptions.AJRException;
import com.br.ufrpe.powerUp.dados.exceptions.ANexception;
import com.br.ufrpe.powerUp.negocio.beans.Atividade;
import com.br.ufrpe.powerUp.negocio.beans.TipoAtributo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class AtividadeController {
    private ControladorUsuario userController;

    @FXML
    public Button buttonVoltar;

    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
    }

    private void executarAtividade(int num, Atividade atividade) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/IniciarAtividade.fxml"));
            Scene criarScene = new Scene(fxmlLoader.load(), 600, 400);

            ExecutarAtvddController executarAtvddController = fxmlLoader.getController();
            switch (num) {
                case 1:
                    executarAtvddController.setAtividade("Malhando...");
                    break;
                case 2:
                    executarAtvddController.setAtividade("Correndo...");
                    break;
                case 3:
                    executarAtvddController.setAtividade("Estudando...");
                    break;
                case 4:
                    executarAtvddController.setAtividade("Lendo...");
                    break;
                default:
                    executarAtvddController.setAtividade("atividade");
                    break;
            }

            Stage criarStage = new Stage();
            criarStage.setTitle("Executar atividade");
            criarStage.setScene(criarScene);

            criarStage.setOnHidden(event ->{
                LocalDateTime inicio = executarAtvddController.getInicioAtividade();
                LocalDateTime fim = executarAtvddController.getFimAtividade();

                try {
                    if (fim != null) {
                        userController.adicionarAtividadeExecutada(atividade, inicio, fim);
                        userController.atualizarAtributoUsuario(atividade.getTipo(),atividade.getIntensidade());
                    }
                } catch (ANexception e) {
                    throw new RuntimeException(e);
                } catch (AJRException e) {
                    throw new RuntimeException(e);
                }

            });
            criarStage.show();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnMalhacao() {
        Atividade atividade = new Atividade("0", "malhação", TipoAtributo.FORCA, 5);
        executarAtividade(1, atividade);
    }

    public void btnCardio() {
        Atividade atividade = new Atividade("0", "cardio", TipoAtributo.STAMINA, 5);
        executarAtividade(2, atividade);
    }

    public void btnEstudar() {
        Atividade atividade = new Atividade("0", "estudar", TipoAtributo.INTELECTO, 5);
        executarAtividade(3, atividade);
    }

    public void btnLer() {
        Atividade atividade = new Atividade("0", "ler", TipoAtributo.CRIATIVIDADE, 5);
        executarAtividade(4, atividade);
    }

    public void btnVoltar() {
        try {
            // Carregar o FXML e o controlador
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/telaPrincipal.fxml"));
            Scene principalScene = new Scene(fxmlLoader.load(), 600, 400);

            // Obter o controlador da tela de perfil
            PrincipalController controller = fxmlLoader.getController();

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

}
