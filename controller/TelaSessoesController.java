package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Filme;

public class TelaSessoesController {
    @FXML
    private Label tituloFilmeLabel;

    @FXML
    private Label detalhesFilmeLabel;
    
    @FXML 
    private FlowPane containerSessoes;

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
        containerSessoes.getChildren().clear(); // Limpa a tela por segurança

        // Aqui você chamará o seu model para buscar as sessões reais.
        // Exemplo: List<Sessao> sessoes = administrador.buscarSessoesPorFilme(filmeAtual.getNome());
        
        // Para testar a interface imediatamente, usaremos um array de horários de exemplo:
        String[] horarios = {"14:00", "16:30", "19:00", "21:30"};

        for (String horario : horarios) {
            Button btnSessao = new Button(horario);
            btnSessao.setStyle("-fx-font-size: 16px; -fx-padding: 10 20; -fx-cursor: hand;");
            
            // O que acontece ao clicar no horário:
            btnSessao.setOnAction(event -> {
                System.out.println("O cliente escolheu a sessão das " + horario);
                // Aqui você chamará o método para abrir a tela de escolher as cadeiras
            });
            
            containerSessoes.getChildren().add(btnSessao);
        }
    }

    @FXML
    public void voltarTelaInicial(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicial.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) containerSessoes.getScene().getWindow();
            stage.setTitle("Cinema - Seleção de Filmes");
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
