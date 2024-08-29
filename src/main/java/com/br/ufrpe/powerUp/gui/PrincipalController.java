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

public class PrincipalController {
    private ControladorUsuario userController;
    private ObservableList<Objetivo> objetivoData = FXCollections.observableArrayList();

    @FXML
    public Label labelForca;
    @FXML
    public Label labelEstamina;
    @FXML
    public Label labelIntelecto;
    @FXML
    public Label labelCriatividade;

    @FXML
    private Label labelNome;
    @FXML
    private Label labelAtividade;
    @FXML
    private Label labelQuota;
    @FXML
    private Label labelDataMax;
    @FXML
    private Label labelDescricao;

    @FXML
    private Button buttonRemover;

    @FXML
    private TableView<Objetivo> objetivoTableView;
    @FXML
    private TableColumn<Objetivo, String> nomeColumn;
    @FXML
    private TableColumn<Objetivo, String> atividadeColumn;
    @FXML
    private TableColumn<Objetivo, LocalDate> dataMaxColumn;

    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;

        // ---------------------------Perfil---------------------------
        int forca = userController.getUsuarioForca();
        int estamina = userController.getUsuarioStamina();
        int intelecto = userController.getUsuarioResistencia();
        int criatividade= userController.getUsuarioVelocidade();

        labelForca.setText(String.valueOf(forca));
        labelEstamina.setText(String.valueOf(estamina));
        labelIntelecto.setText(String.valueOf(intelecto));
        labelCriatividade.setText(String.valueOf(criatividade));

        //---------------------Gerenciar Objetivos---------------------
        objetivoData.addAll(userController.getObjetivos());
        objetivoTableView.setItems(objetivoData);
    }

    @FXML
    private void initialize() {
        // Configurar colunas do TableView
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        atividadeColumn.setCellValueFactory(new PropertyValueFactory<>("Atividade"));
        dataMaxColumn.setCellValueFactory(new PropertyValueFactory<>("DataMaxima"));
        objetivoTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhesObjetivo(newValue));
    }

    private void mostrarDetalhesObjetivo(Objetivo objetivo) {
        if (objetivo != null) {
            labelNome.setText(objetivo.getNome());
            labelAtividade.setText(objetivo.getAtividade());
            labelQuota.setText(String.valueOf(objetivo.getQuota()));
            //labelDataMax.setText(String.valueOf(objetivo.getDataMax()));
            labelDescricao.setText(objetivo.getDescricao());
        } else {
            labelNome.setText("");
            labelAtividade.setText("");
            labelQuota.setText("");
            labelDataMax.setText("");
            labelDescricao.setText("");
        }
    }

    public void handleDeleteObjetivo() {
        int indexSelecionado = objetivoTableView.getSelectionModel().getSelectedIndex();
        objetivoTableView.getItems().remove(indexSelecionado);
    }

    public void btnCriarActionPerformed(){
        try {
            // Carrega o FXML para a tela de cadastro
            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/criarObjetivo.fxml"));
            Scene criarScene = new Scene(fxmlLoader.load(), 272, 328);

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

    public void updateObjetivos() {
        objetivoData.clear();
        objetivoData.addAll(userController.getObjetivos());
    }


}
