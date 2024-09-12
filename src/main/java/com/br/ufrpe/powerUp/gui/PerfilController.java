package com.br.ufrpe.powerUp.gui;

import animatefx.animation.Pulse;
import com.br.ufrpe.powerUp.gui.helpers.BasicController;
import com.br.ufrpe.powerUp.gui.helpers.Constantes;
import com.br.ufrpe.powerUp.gui.helpers.ControladorUsuarioInterface;
import com.br.ufrpe.powerUp.negocio.beans.AtividadeExecutada;
import com.br.ufrpe.powerUp.negocio.beans.Peso;
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
import javafx.scene.control.TextField;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PerfilController extends BasicController implements ControladorUsuarioInterface {
    private ControladorUsuario userController;
    private ObservableList<AtividadeExecutada> historico = FXCollections.observableArrayList();
    private XYChart.Series<String, Number> seriesPeso = new XYChart.Series<>();

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
    public Label labelIMC;
    @FXML
    public Label labelNumberIMC;
    @FXML
    public Label labelIMCStatuts;

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

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private TextField pesoTextField;
    @FXML
    private Button adicionarPesoButton;

    @Override
    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;
        // atributos
        int forca = userController.getUsuarioForca();
        int estamina = userController.getUsuarioStamina();
        int intelecto = userController.getUsuarioIntelecto();
        int criatividade = userController.getUsuarioCriatividade();

        labelNome.setText(userController.getUsuarioName());
        labelForca.setText(String.valueOf(forca));
        labelEstamina.setText(String.valueOf(estamina));
        labelIntelecto.setText(String.valueOf(intelecto));
        labelCriatividade.setText(String.valueOf(criatividade));

        historico.addAll(userController.getAtividadesExecutadas());
        historicoTableView.setItems(historico);

        // Configurar o gráfico
        atualizarGrafico();
        atualizarIMC();
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

    private void atualizarGrafico() {
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Peso ao longo do tempo");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        for (Peso peso : userController.getHistoricoPesos()) {
            // Converter LocalDate para o formato "dia/mês"
            String dataFormatada = peso.getData().format(formatter);
            series.getData().add(new XYChart.Data<>(dataFormatada, peso.getValor()));
        }

        lineChart.getData().add(series);
    }

    @FXML
    private void adicionarPeso(ActionEvent event) {
        String textoPeso = pesoTextField.getText();
        if (!textoPeso.isEmpty()) {
            try {
                float valorPeso = Float.parseFloat(textoPeso);
                Peso novoPeso = new Peso(valorPeso, LocalDate.now());
                userController.adicionarPeso(novoPeso);
                atualizarGrafico();
                atualizarIMC();
                pesoTextField.clear();
            } catch (NumberFormatException e) {
                System.out.println("WIP");
            }
        }
    }

    private void atualizarIMC() {
        float peso = userController.getPesoAtual();
        float altura = userController.getUsuarioAltura();

        float imc = peso / (altura * altura);
        String imcString = String.format("%.2f", imc);
        labelNumberIMC.setText(imcString);

        if (imc < 18.5) {
            labelIMC.setStyle("-fx-text-fill: blue;"); // Abaixo do peso
            labelIMCStatuts.setStyle("-fx-text-fill: blue;");
            labelIMCStatuts.setText("Baixo Peso");
        } else if (imc >= 18.5 && imc < 24.9) {
            labelIMC.setStyle("-fx-text-fill: green;"); // Peso normal
            labelIMCStatuts.setStyle("-fx-text-fill: green;");
            labelIMCStatuts.setText("Peso Normal");
        } else if (imc >= 25 && imc < 29.9) {
            labelIMC.setStyle("-fx-text-fill: orange;"); // Sobrepeso
            labelIMCStatuts.setStyle("-fx-text-fill: orange;");
            labelIMCStatuts.setText("Sobrepeso");
        } else {
            labelIMC.setStyle("-fx-text-fill: red;"); // Obeso
            labelIMCStatuts.setStyle("-fx-text-fill: red;");
            labelIMCStatuts.setText("Obeso");
        }
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

    public void buttonMouseEntered(javafx.scene.input.MouseEvent event) {
        Button button = (Button) event.getSource();
        playSound("/sounds/buttonSFX.wav", userController.getVolume());
        new Pulse(button).play();
    }

    public void buttonMousePressed() {
        playSound("/sounds/buttonCilckSFX.mp3", userController.getVolume());
    }

}
