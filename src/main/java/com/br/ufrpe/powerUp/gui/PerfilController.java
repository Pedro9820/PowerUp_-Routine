package com.br.ufrpe.powerUp.gui;

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

public class PerfilController {
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

    public void setUserController(ControladorUsuario userController) {
        System.out.println("test");
        this.userController = userController;
        // atributos
        int forca = userController.getUsuarioForca();
        int estamina = userController.getUsuarioStamina();
        int intelecto = userController.getUsuarioResistencia();
        int criatividade= userController.getUsuarioVelocidade();

        labelNome.setText(userController.getUsuarioName());
        labelForca.setText(String.valueOf(forca));
        labelEstamina.setText(String.valueOf(estamina));
        labelIntelecto.setText(String.valueOf(intelecto));
        labelCriatividade.setText(String.valueOf(criatividade));

        historico.addAll(userController.getAtividadesExecutadas());
        historicoTableView.setItems(historico);
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
        // Carregar o FXML usando FXMLLoader para obter o controlador
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/config.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador da tela de perfil
        ConfigController controller = fxmlLoader.getController();
        controller.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, Constantes.CONFIGHWIDTH, Constantes.CONFIGHEIGHT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void btnVoltar(ActionEvent event) throws IOException {
        // Carregar o FXML usando FXMLLoader para obter o controlador
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/telaPrincipal.fxml"));
        Parent root = fxmlLoader.load();

        // Obter o controlador
        PrincipalController controller = fxmlLoader.getController();
        controller.setUserController(userController);

        // Configurar a cena e o palco
        Scene scene = new Scene(root, Constantes.PRINCIPALWIDTH, Constantes.PRINCIPALHEIGHT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
