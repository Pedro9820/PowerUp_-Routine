package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.gui.helpers.BasicController;
import com.br.ufrpe.powerUp.gui.helpers.Constantes;
import com.br.ufrpe.powerUp.gui.helpers.ControladorUsuarioInterface;
import com.br.ufrpe.powerUp.negocio.beans.AtividadeExecutada;
import com.br.ufrpe.powerUp.negocio.beans.TipoAtributo;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PerfilController extends BasicController implements ControladorUsuarioInterface {
    private ControladorUsuario userController;
    private ObservableList<AtividadeExecutada> historico = FXCollections.observableArrayList();

    @FXML
    public Pane pane;

    @FXML
    public Button buttonVoltar;

    @FXML
    public Label labelNome;
    @FXML
    public Label labelForca;
    @FXML
    public Label labelEstamina;
    @FXML
    public Label labelIntelecto;
    @FXML
    public Label labelCriatividade;

    @FXML
    private TableView<AtividadeExecutada> historicoTableView;
    @FXML
    private TableColumn<AtividadeExecutada, String> nomeHistorico;
    @FXML
    private TableColumn<AtividadeExecutada, TipoAtributo> tipoHistorico;
    @FXML
    private TableColumn<AtividadeExecutada, Integer> intensidadeHistorico;
    @FXML
    private TableColumn<AtividadeExecutada, String> inicioHistorico;
    @FXML
    private TableColumn<AtividadeExecutada, String> fimHistorico;

    @Override
    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
        // atributos
        int forca = userController.getUsuarioForca();
        int estamina = userController.getUsuarioStamina();
        int intelecto = userController.getUsuarioIntelecto();
        int criatividade= userController.getUsuarioCriatividade();

        labelNome.setText(userController.getUsuarioName());
        labelForca.setText(String.valueOf(forca));
        labelEstamina.setText(String.valueOf(estamina));
        labelIntelecto.setText(String.valueOf(intelecto));
        labelCriatividade.setText(String.valueOf(criatividade));

        historico.addAll(userController.getAtividadesExecutadas());
        historicoTableView.setItems(historico);
    }

    @Override
    public ControladorUsuario getUserController() {
        return userController;
    }

    public void initialize() {
        // configurar colunas do tableView Historico
        nomeHistorico.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tipoHistorico.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        intensidadeHistorico.setCellValueFactory(new PropertyValueFactory<>("Intensidade"));
        inicioHistorico.setCellValueFactory(new PropertyValueFactory<>("Atinicio"));
        fimHistorico.setCellValueFactory(new PropertyValueFactory<>("Atfim"));

    }

    public void btnConfig(ActionEvent event) throws IOException {
        BasicController.criarCena(event, "/config.fxml", this,
                Constantes.CONFIGHWIDTH,
                Constantes.CONFIGHEIGHT);

    }

    public void btnVoltar(ActionEvent event) throws IOException {
        BasicController.criarCena(event, "/telaPrincipal.fxml", this,
                Constantes.PRINCIPALWIDTH,
                Constantes.PRINCIPALHEIGHT);

    }

    public void buttonMouseEntered() {
        playSound("/sounds/buttonSFX.wav");
    }

    public void buttonMousePressed() {
        playSound("/sounds/buttonCilckSFX.mp3");
    }

}
