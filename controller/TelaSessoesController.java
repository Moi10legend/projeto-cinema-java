package controller;

import java.io.IOException;

import java.time.LocalTime;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Administrador;
import model.Critico;
import model.Filme;
import model.Sala;
import model.Sessao;

public class TelaSessoesController {
    @FXML
    private Label tituloFilmeLabel;

    @FXML
    private Label detalhesFilmeLabel;
    
    @FXML 
    private VBox containerSalas;

    @FXML private TextField campoOrigem;
    @FXML private TextField campoCritica;
    @FXML private Label statusCriticaLabel;

    // Guarda o filme que o cliente escolheu
    private Filme filmeAtual;

    // Método chamado pelo TelaInicialController
    public void setFilme(Filme filme) {
        this.filmeAtual = filme;
        
        // Atualiza a interface gráfica imediatamente após receber o objeto
        tituloFilmeLabel.setText(filmeAtual.getNome());
        detalhesFilmeLabel.setText(
            "Duração: " + filmeAtual.getDuracao() + " min | Valor base: R$ " + filmeAtual.getValor()
        );
        
        // Em seguida, você aciona o carregamento das sessões desse filme
        carregarSessoesDoFilme();
    }

    private void carregarSessoesDoFilme() {
        containerSalas.getChildren().clear();
        LocalTime horaAtual = LocalTime.now();

        // Itera sobre todas as salas instanciadas no Administrador
        for (Sala sala : Administrador.getInstance().getListaSalas()) {
        
            // Contêiner horizontal para os botões de horário desta sala específica
            FlowPane flowHorarios = new FlowPane();
            flowHorarios.setHgap(15.0);
            flowHorarios.setVgap(15.0);
        
            boolean encontrouSessaoNestaSala = false;

            // Itera sobre o array estático de 5 posições da classe Sala
            for (int i = 0; i < 5; i++) {
                Sessao sessao = sala.getSessoes()[i]; //[cite: 6]
            
                // Verifica se a posição não é nula e se o filme confere
                if (sessao != null && sessao.getFilme().getNome().equalsIgnoreCase(filmeAtual.getNome())) {
                    encontrouSessaoNestaSala = true;
                
                    Button btnSessao = new Button(sessao.getHorario());
                    btnSessao.setStyle("-fx-font-size: 14px; -fx-padding: 8 16; -fx-cursor: hand;");
                
                    LocalTime horaDaSessao = LocalTime.parse(sessao.getHorario());
                
                    if (horaDaSessao.isBefore(horaAtual)) {
                        btnSessao.setDisable(true);
                    } else {
                        btnSessao.setOnAction(event -> {
                            System.out.println("Sala escolhida: " + sala.getNumeroSala() + " | Horário: " + sessao.getHorario());
                            abrirTelaCadeiras(sessao);
                        });
                    }
                    flowHorarios.getChildren().add(btnSessao);
                }
            }

            // Se encontrou ao menos um horário para o filme nesta sala, desenha o título e adiciona na tela
            if (encontrouSessaoNestaSala) {
                // Extrai o número da sala e o texto do enum (ex: "Sala 1 - 3D")
                String textoSala = "Sala " + sala.getNumeroSala() + " - " + sala.getTipoSala().getTipo(); //[cite: 6, 7]
                Label labelSala = new Label(textoSala);
                labelSala.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #333333;");
            
                // Cria um sub-contêiner vertical agrupando o título da sala e os botões
                VBox boxSala = new VBox(10);
                boxSala.getChildren().addAll(labelSala, flowHorarios);
            
                containerSalas.getChildren().add(boxSala);
            }
        }
    }

    private void abrirTelaCadeiras(Sessao sessaoEscolhida) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaCadeiras.fxml"));
        Parent root = loader.load();

        TelaCadeirasController controller = loader.getController();
        controller.setSessao(sessaoEscolhida, null);

        Stage stage = (Stage) containerSalas.getScene().getWindow();
        stage.setTitle("Cinema - Escolha de Assentos");
        stage.setScene(new Scene(root, 800, 600));
    } catch (IOException e) {
        System.err.println("Erro ao abrir tela de cadeiras: " + e.getMessage());
        e.printStackTrace();
    }
}

    @FXML
    public void voltarTelaInicial(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicial.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) containerSalas.getScene().getWindow();
            stage.setTitle("Cinema - Seleção de Filmes");
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void enviarCritica(ActionEvent event) {
        String origem = campoOrigem.getText().trim();
        String mensagem = campoCritica.getText().trim();

        if (origem.isEmpty() || mensagem.isEmpty()) {
            statusCriticaLabel.setText("Preencha a origem e a crítica.");
            statusCriticaLabel.setStyle("-fx-text-fill: #ff9800;"); // Laranja para aviso
            return;
        }

        // Busca o crítico pela origem usando o Administrador
        Critico critico = Administrador.getInstance().buscarCriticoPorOrigem(origem);

        if (critico == null) {
            statusCriticaLabel.setText("Crítico não encontrado");
            statusCriticaLabel.setStyle("-fx-text-fill: red;");
        } else {
            // Usa o método que você já criou para instanciar a Critica e salvar no Filme
            critico.atribuirCritica(mensagem, filmeAtual);
            
            statusCriticaLabel.setText("Crítica adicionada com sucesso");
            statusCriticaLabel.setStyle("-fx-text-fill: green;");
            
            // Limpa os campos após o envio
            campoOrigem.clear();
            campoCritica.clear();
            
            // Opcional: imprimir no console para confirmar que entrou no array
            System.out.println("Críticas atuais do filme: " + filmeAtual.getCriticas()[0].getMensagem());
        }
    }
}
