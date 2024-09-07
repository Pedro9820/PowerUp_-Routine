package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.negocio.beans.Objetivo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ObjetivosController {
    private ControladorUsuario userController;
    private ObservableList<Objetivo> objetivoData = FXCollections.observableArrayList();

    @FXML
    private Label labelDescricao;

    @FXML
    public Button buttonVoltar;

    @FXML
    private TableView<Objetivo> objetivoTableView;
    @FXML
    private TableColumn<Objetivo, String> nomeColumn;
    @FXML
    private TableColumn<Objetivo, String> atividadeColumn;
    @FXML
    private TableColumn<Objetivo, LocalDate> dataMaxColumn;
    @FXML
    private TableColumn<Objetivo, Integer> quotaColumn;

    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;

        objetivoData.addAll(userController.getObjetivos());
        objetivoTableView.setItems(objetivoData);
    }

    public void initialize() {

        // Configurar colunas do TableView objetivos
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        atividadeColumn.setCellValueFactory(new PropertyValueFactory<>("Atividade"));
        dataMaxColumn.setCellValueFactory(new PropertyValueFactory<>("DataMaxima"));
        quotaColumn.setCellValueFactory(new PropertyValueFactory<>("Quota"));
        objetivoTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhesObjetivo(newValue));
    }

    private void mostrarDetalhesObjetivo(Objetivo objetivo) {
        String data = objetivo.getDataMaxima().toString();

        if (objetivo != null) {
            labelDescricao.setText(objetivo.getDescricao());
        } else {
            labelDescricao.setText("");
        }
    }

    public void handleDeleteObjetivo() {
        int indexSelecionado = objetivoTableView.getSelectionModel().getSelectedIndex();
        objetivoTableView.getItems().remove(indexSelecionado);
    }

    public void btnCriarActionPerformed(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/CriacaoDeObjetivo.fxml"));
            Scene criarScene = new Scene(fxmlLoader.load(), 600, 400);

            CriarObjetivoController criarObjetivoController = fxmlLoader.getController();
            criarObjetivoController.setUserController(userController);

            Stage criarStage = new Stage();
            criarStage.setTitle("Criar Objetivo");
            criarStage.setScene(criarScene);
            criarStage.show();

            updateObjetivos();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void updateObjetivos() {
        objetivoData.clear();
        objetivoData.addAll(userController.getObjetivos());
    }
}
