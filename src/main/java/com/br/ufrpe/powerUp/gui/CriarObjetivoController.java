package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.negocio.beans.Objetivo;
import com.br.ufrpe.powerUp.negocio.beans.TipoAtributo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class CriarObjetivoController {
    private ControladorUsuario userController;

    @FXML
    public Button buttonVoltar;

    @FXML
    public TextField txtFieldNome;
    @FXML
    public TextField txtFieldQuota;
    @FXML
    public TextArea txtAreaDescricao;
    @FXML
    public Button buttonCriar;
    @FXML
    public DatePicker dateLimite;
    @FXML
    private MenuButton menuButtonAtributo;

    private TipoAtributo atributoSelecionado;

    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
    }

    @FXML
    public void initialize() {
        // Define o filtro para permitir apenas números no Quota
        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        txtFieldQuota.setTextFormatter(new TextFormatter<>(numberFilter));
    }

    @FXML
    public void handleAtributoSelection(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String text = selectedItem.getText();

        switch (text) {
            case "Força":
                atributoSelecionado = TipoAtributo.FORCA;
                break;
            case "Estamina":
                atributoSelecionado = TipoAtributo.STAMINA;
                break;
            case "Intelecto":
                atributoSelecionado = TipoAtributo.INTELECTO;
                break;
            case "Criatividade":
                atributoSelecionado = TipoAtributo.CRIATIVIDADE;
                break;
            default:
                atributoSelecionado = null;
        }

        menuButtonAtributo.setText(text);
    }


    public void criarObjetivo() {
        String quotaText = txtFieldQuota.getText();

        if (!quotaText.isEmpty() && atributoSelecionado != null) {
            try {
                int quota = Integer.parseInt(quotaText);

                Objetivo objetivo = new Objetivo(
                        dateLimite.getValue(),
                        txtFieldNome.getText(),
                        txtAreaDescricao.getText(),
                        quota,
                        atributoSelecionado
                );
                userController.adicionarObjetivoUsuario(objetivo);

                Stage stage = (Stage) buttonCriar.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Entrada inválida");
                alert.setContentText("Por favor, insira um número válido para a quota.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Dados faltando");
            alert.setContentText("Por favor, preencha todos os campos e selecione um atributo.");
            alert.showAndWait();
        }
    }

    public void btnVoltar() {
        try {
            // Carregar o FXML e o controlador
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/Objetivos.fxml"));
            Scene principalScene = new Scene(fxmlLoader.load(), 600, 400);

            // Obter o controlador da tela de perfil
            ObjetivosController controller = fxmlLoader.getController();

            // Passar o controlador de usuário (userController) para o perfilController
            controller.setUserController(userController);

            // Criar a nova janela
            Stage principalStage = new Stage();
            principalStage.setTitle("Objetivos");
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
