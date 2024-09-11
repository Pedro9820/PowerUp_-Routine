package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.gui.helpers.Constantes;
import com.br.ufrpe.powerUp.negocio.beans.Objetivo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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
        atividadeColumn.setCellValueFactory(new PropertyValueFactory<>("Atributo"));
        dataMaxColumn.setCellValueFactory(new PropertyValueFactory<>("DataMaxima"));
        quotaColumn.setCellValueFactory(new PropertyValueFactory<>("Quota"));
        objetivoTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhesObjetivo(newValue));
    }

    private void mostrarDetalhesObjetivo(Objetivo objetivo) {
        if (objetivo != null) {
            labelDescricao.setText(objetivo.getDescricao());
        } else {
            labelDescricao.setText("");
        }
    }


    public void handleDeleteObjetivo() {
        int indexSelecionado = objetivoTableView.getSelectionModel().getSelectedIndex();
        if (indexSelecionado >= 0) {
            Objetivo objetivoSelecionado = objetivoTableView.getItems().get(indexSelecionado);

            // Remover o objetivo do ArrayList no controlador
            userController.removerObjetivo(objetivoSelecionado);

            // Remover o objetivo da TableView
            objetivoTableView.getItems().remove(indexSelecionado);
            objetivoTableView.getSelectionModel().clearSelection();
            mostrarDetalhesObjetivo(null);
        }
    }


    public void btnCriarActionPerformed(ActionEvent event) throws IOException {
        // Carregar o FXML usando FXMLLoader para obter o controlador
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CriacaoDeObjetivo.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela de perfil
        CriarObjetivoController controller = fxmlLoader.getController();
        controller.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, Constantes.CRIAROBJETIVOWIDTH, Constantes.CRIAROBJETIVOHEIGH);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void btnVoltar(ActionEvent event) throws IOException {
        // Carregar o FXML usando FXMLLoader para obter o controlador
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/telaPrincipal.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela de perfil
        PrincipalController controller = fxmlLoader.getController();
        controller.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, Constantes.PRINCIPALWIDTH, Constantes.PRINCIPALHEIGHT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void updateObjetivos() {
        objetivoData.clear();
        objetivoData.addAll(userController.getObjetivos());
    }
}
