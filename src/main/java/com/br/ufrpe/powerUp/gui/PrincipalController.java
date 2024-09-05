package com.br.ufrpe.powerUp.gui;

import com.br.ufrpe.powerUp.dados.exceptions.AJRException;
import com.br.ufrpe.powerUp.dados.exceptions.ANexception;
import com.br.ufrpe.powerUp.negocio.AtividadeExecutada;
import com.br.ufrpe.powerUp.negocio.beans.Atividade;
import com.br.ufrpe.powerUp.negocio.beans.Objetivo;
import com.br.ufrpe.powerUp.negocio.beans.TipoAtributo;
import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
public class PrincipalController {
    private ControladorUsuario userController;
    private ObservableList<Objetivo> objetivoData = FXCollections.observableArrayList();
    private ObservableList<AtividadeExecutada> historico = FXCollections.observableArrayList();

    @FXML
    public TabPane tabPane;
    @FXML
    public Tab tabPerfil;
    @FXML
    public Tab tabObjetivo;

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
    private TableView<Objetivo> objetivoTableView;
    @FXML
    private TableColumn<Objetivo, String> nomeColumn;
    @FXML
    private TableColumn<Objetivo, String> atividadeColumn;
    @FXML
    private TableColumn<Objetivo, LocalDate> dataMaxColumn;

    public void setUserController(ControladorUsuario userController) {
        this.userController = userController;

        // Perfil
        int forca = userController.getUsuarioForca();
        int estamina = userController.getUsuarioStamina();
        int intelecto = userController.getUsuarioResistencia();
        int criatividade= userController.getUsuarioVelocidade();

        labelForca.setText(String.valueOf(forca));
        labelEstamina.setText(String.valueOf(estamina));
        labelIntelecto.setText(String.valueOf(intelecto));
        labelCriatividade.setText(String.valueOf(criatividade));

        historico.addAll(userController.getAtividadesExecutadas());
        historicoTableView.setItems(historico);

        //Gerencia objetivos
        objetivoData.addAll(userController.getObjetivos());
        objetivoTableView.setItems(objetivoData);
    }

    public void initialize() {
        System.out.println("Método initialize chamado");
        // configurar colunas do tableView Historico
        nomeHistorico.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tipoHistorico.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        intensidadeHistorico.setCellValueFactory(new PropertyValueFactory<>("Intensidade"));
        inicioHistorico.setCellValueFactory(new PropertyValueFactory<>("Atinicio"));
        fimHistorico.setCellValueFactory(new PropertyValueFactory<>("Atfim"));
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab == tabPerfil) {
                System.out.println("Aba de perfil selecionada");
                updateHistorico();
            }
        });


        // Configurar colunas do TableView objetivos
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        atividadeColumn.setCellValueFactory(new PropertyValueFactory<>("Atividade"));
        dataMaxColumn.setCellValueFactory(new PropertyValueFactory<>("DataMaxima"));
        objetivoTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhesObjetivo(newValue));
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab == tabObjetivo) {
                updateObjetivos();
            }
        });
    }

    //----------------------Perfil----------------------


    //-----------------Executar atividade-----------------

    private void executarAtividade(int num, Atividade atividade) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("/executarAtividade.fxml"));
            Scene criarScene = new Scene(fxmlLoader.load(), 277, 268);

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
                    userController.adicionarAtividadeExecutada(atividade, inicio, fim);
                } catch (ANexception e) {
                    throw new RuntimeException(e);
                } catch (AJRException e) {
                    throw new RuntimeException(e);
                }

            });
            criarStage.show();

            userController.atualizarAtributoUsuario(atividade.getTipo(),atividade.getIntensidade());

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnMalhacao() {
        Atividade atividade = new Atividade("0", "malhação", TipoAtributo.FORCA, 5);
        executarAtividade(1, atividade);
    }

    public void btnCardio() {
        Atividade atividade = new Atividade("0", "cardio", TipoAtributo.STAMINA, 5);
        executarAtividade(2, atividade);
    }

    public void btnEstudar() {
        Atividade atividade = new Atividade("0", "estudar", TipoAtributo.INTELECTO, 5);
        executarAtividade(3, atividade);
    }

    public void btnLer() {
        Atividade atividade = new Atividade("0", "ler", TipoAtributo.CRIATIVIDADE, 5);
        executarAtividade(4, atividade);
    }


    //----------------gerencia de objetivos----------------

    private void mostrarDetalhesObjetivo(Objetivo objetivo) {
        String data = objetivo.getDataMaxima().toString();

        if (objetivo != null) {
            labelNome.setText(objetivo.getNome());
            labelAtividade.setText(objetivo.getAtividade().name());
            labelQuota.setText(String.valueOf(objetivo.getQuota()));
            labelDataMax.setText(String.valueOf(data));
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

    public void updateHistorico() {
        int forca = userController.getUsuarioForca();
        int estamina = userController.getUsuarioStamina();
        int intelecto = userController.getUsuarioIntelecto();
        int criatividade= userController.getUsuarioCriatividade();

        historico.clear();
        historico.addAll(userController.getAtividadesExecutadas());
        labelForca.setText(String.valueOf(forca));
        labelEstamina.setText(String.valueOf(estamina));
        labelIntelecto.setText(String.valueOf(intelecto));
        labelCriatividade.setText(String.valueOf(criatividade));
    }


}
