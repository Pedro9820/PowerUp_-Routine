package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.negocio.beans.Objetivo;
import com.br.ufrpe.powerUp.negocio.beans.TipoAtributo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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

    public void criarCena(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Objetivos.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela de perfil
        ObjetivosController controller = fxmlLoader.getController();
        controller.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void criarObjetivo(ActionEvent event) {
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
                criarCena(event);

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Entrada inválida");
                alert.setContentText("Por favor, insira um número válido para a quota.");
                alert.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Dados faltando");
            alert.setContentText("Por favor, preencha todos os campos e selecione um atributo.");
            alert.showAndWait();
        }
    }

    public void btnVoltar(ActionEvent event) throws IOException {
        criarCena(event);
    }

}
