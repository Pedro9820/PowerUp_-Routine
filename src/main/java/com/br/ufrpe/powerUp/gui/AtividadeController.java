package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.dados.exceptions.AJRException;
import com.br.ufrpe.powerUp.dados.exceptions.ANexception;
import com.br.ufrpe.powerUp.negocio.beans.Atividade;
import com.br.ufrpe.powerUp.negocio.beans.TipoAtributo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class AtividadeController {
    private ControladorUsuario userController;

    @FXML
    public Button buttonVoltar;
    @FXML
    public Text txtDica;
    @FXML
    public Text txtDica2;

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

    public void malhacaoMouseEntered() {
        txtDica.setText("Os exercícios físicos podem trazer muitos benefícios para a saúde, como:");
        txtDica2.setText("* Redução de estresse e ansiedade.\n\n" +
                "* Melhora da qualidade de sono.\n\n" +
                "* Fortalecimento do sistema imunológico.");
    }

    public void btnCardio() {
        Atividade atividade = new Atividade("0", "cardio", TipoAtributo.STAMINA, 5);
        executarAtividade(2, atividade);
    }

    public void cardioMouseEntered() {
        txtDica.setText("Os exercícios de cardio oferecem diversos benefícios para a saúde, tais como:");
        txtDica2.setText("* Aumento da resistência física.\n\n" +
                "* Melhora na saúde cardiovascular.\n\n" +
                "* Auxílio na perda de peso e queima de gordura.");
    }

    public void btnEstudar() {
        Atividade atividade = new Atividade("0", "estudar", TipoAtributo.INTELECTO, 5);
        executarAtividade(3, atividade);
    }

    public void estudarMouseEntered() {
        txtDica.setText("Dedicar-se aos estudos pode proporcionar muitos benefícios, como:");
        txtDica2.setText("* Desenvolvimento do raciocínio lógico e crítico.\n\n" +
                "* Aumento do conhecimento e habilidades cognitivas.\n\n" +
                "* Melhoria da capacidade de concentração e memória.");
    }

    public void btnLer() {
        Atividade atividade = new Atividade("0", "ler", TipoAtributo.CRIATIVIDADE, 5);
        executarAtividade(4, atividade);
    }

    public void lerMouseEntered() {
        txtDica.setText("A leitura regular oferece diversos benefícios, tais como:");
        txtDica2.setText("* Expansão do vocabulário e aprimoramento da linguagem.\n\n" +
                "* Estímulo à imaginação e à criatividade.\n\n" +
                "* Aumento do conhecimento e desenvolvimento intelectual.");
    }

    public void btnVoltar(ActionEvent event) throws IOException {
        // Carregar o FXML usando FXMLLoader para obter o controlador
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/telaPrincipal.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela de perfil
        PrincipalController controller = fxmlLoader.getController();
        controller.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
