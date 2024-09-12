package com.br.ufrpe.powerUp.gui;

import animatefx.animation.Pulse;
import com.br.ufrpe.powerUp.dados.exceptions.AJRException;
import com.br.ufrpe.powerUp.dados.exceptions.ANexception;
import com.br.ufrpe.powerUp.gui.helpers.BasicController;
import com.br.ufrpe.powerUp.gui.helpers.Constantes;
import com.br.ufrpe.powerUp.gui.helpers.ControladorUsuarioInterface;
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

public class AtividadeController extends BasicController implements ControladorUsuarioInterface {
    private ControladorUsuario userController;

    @FXML
    public Button buttonVoltar;
    @FXML
    public Text txtDica;
    @FXML
    public Text txtDica2;

    @Override
    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
    }

    @Override
    public ControladorUsuario getUserController() {
        return this.userController;
    }

    private void executarAtividade(Atividade atividade, String acao, String dica) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/IniciarAtividade.fxml"));
            Scene criarScene = new Scene(fxmlLoader.load(), Constantes.INICIARATIVIDADEWIDTH, Constantes.INICIARATIVIDADEHEIGH);

            ExecutarAtvddController executarAtvddController = fxmlLoader.getController();
            executarAtvddController.setAtividade(acao, dica);

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
                        if (atividade.getNome().equals("calistenia")) {
                            userController.atualizarAtributoUsuario(TipoAtributo.STAMINA, 2);

                        } else if (atividade.getNome().equals("meditar")) {
                            userController.atualizarAtributoUsuario(TipoAtributo.INTELECTO,2);
                        }
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
        executarAtividade(atividade, "Malhando...", "");
    }

    public void malhacaoMouseEntered() {
        txtDica.setText("Os exercícios físicos podem trazer muitos benefícios para a saúde, como:");
        txtDica2.setText("* Redução de estresse e ansiedade.\n\n" +
                "* Melhora da qualidade de sono.\n\n" +
                "* Fortalecimento do sistema imunológico.");
    }

    public void btnCardio() {
        Atividade atividade = new Atividade("0", "cardio", TipoAtributo.STAMINA, 5);
        executarAtividade(atividade, "Cardio", "Aquecimento protege a musculatura e o coração");
    }

    public void cardioMouseEntered() {
        txtDica.setText("Os exercícios de cardio oferecem diversos benefícios para a saúde, tais como:");
        txtDica2.setText("* Aumento da resistência física.\n\n" +
                "* Melhora na saúde cardiovascular.\n\n" +
                "* Auxílio na perda de peso e queima de gordura.");
    }

    public void btnEstudar() {
        Atividade atividade = new Atividade("0", "estudar", TipoAtributo.INTELECTO, 5);
        executarAtividade(atividade, "Estudando", "Mantenha-se distante de possíveis distrações");
    }

    public void estudarMouseEntered() {
        txtDica.setText("Dedicar-se aos estudos pode proporcionar muitos benefícios, como:");
        txtDica2.setText("* Desenvolvimento do raciocínio lógico e crítico.\n\n" +
                "* Aumento do conhecimento e habilidades cognitivas.\n\n" +
                "* Melhoria da capacidade de concentração e memória.");
    }

    public void btnLer() {
        Atividade atividade = new Atividade("0", "ler", TipoAtributo.CRIATIVIDADE, 5);
        executarAtividade(atividade, "Lendo", "Leia o que lhe desperta interesse");
    }

    public void lerMouseEntered() {
        txtDica.setText("A leitura regular oferece diversos benefícios, tais como:");
        txtDica2.setText("* Expansão do vocabulário e aprimoramento da linguagem.\n\n" +
                "* Estímulo à imaginação e à criatividade.\n\n" +
                "* Aumento do conhecimento e desenvolvimento intelectual.");
    }

    public void btnCalistenia() {
        Atividade atividade = new Atividade("0", "calistenia", TipoAtributo.FORCA, 3);
        executarAtividade(atividade, "calistenia", "");
    }

    public void calisteniaMouseEntered() {
        txtDica.setText("A calistenia é um tipo de exercício de peso corporal que oferece os seguintes benefícios:");
        txtDica2.setText("* Fortalecimento muscular sem necessidade de equipamentos.\n\n" +
                "* Melhora na flexibilidade e coordenação motora.\n\n" +
                "* Aumento da resistência física e controle corporal.");

    }

    public void btnMeditar() {
        Atividade atividade = new Atividade("0", "meditar", TipoAtributo.CRIATIVIDADE, 3);
        executarAtividade(atividade, "Meditação", "Foco na respiração");
    }

    public void meditarMouseEntered() {
        txtDica.setText("A prática da meditação oferece inúmeros benefícios para a mente e o corpo, incluindo:");
        txtDica2.setText("* Redução do estresse e da ansiedade.\n\n" +
                "* Melhora na concentração e no foco.\n\n" +
                "* Aumento do autoconhecimento e da sensação de calma.");

    }

    public void btnVoltar(ActionEvent event) throws IOException {
        BasicController.criarCena(event, "/telaPrincipal.fxml", this,
                Constantes.PRINCIPALWIDTH,
                Constantes.PRINCIPALHEIGHT);
    }

    public void buttonMouseEntered(javafx.scene.input.MouseEvent event) {
        Button button = (Button) event.getSource();
        playSound("/sounds/buttonSFX.wav", userController.getVolume());
        new Pulse(button).play();
    }

    public void buttonMousePressed() {
        playSound("/sounds/buttonCilckSFX.mp3", userController.getVolume());
    }

}
