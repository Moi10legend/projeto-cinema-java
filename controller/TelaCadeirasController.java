package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Cadeira;
import model.Sessao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaCadeirasController {

    @FXML private GridPane gridCadeiras;
    @FXML private TextField campoQuantidade;
    
    private Sessao sessaoAtual;
    private List<Cadeira> assentosSelecionados = new ArrayList<>();

    // Matriz para guardar os botões e podermos alterar as cores facilmente
    private Button[][] botoesCadeira = new Button[10][15];

    public void setSessao(Sessao sessao, List<Cadeira> cadeirasJaEscolhidas) {
        this.sessaoAtual = sessao;
        if (cadeirasJaEscolhidas != null) {
            this.assentosSelecionados = cadeirasJaEscolhidas;
        }
        desenharMatrizDeCadeiras();
    }

    private void desenharMatrizDeCadeiras() {
        Cadeira[][] matriz = sessaoAtual.getCadeiras();
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                Cadeira cadeira = matriz[i][j];
                Button btnCadeira = new Button(cadeira.getFileiraENumero());
                btnCadeira.setPrefSize(45, 35);
                
                // Guarda o botão na nossa matriz de controle visual
                botoesCadeira[i][j] = btnCadeira;
                
                atualizarCorDoBotao(btnCadeira, cadeira);
                
                // Evento de clique na cadeira
                btnCadeira.setOnAction(event -> {
                    if (cadeira.getEstaOcupada()) return;

                    if (!assentosSelecionados.contains(cadeira)) {
                        assentosSelecionados.add(cadeira);
                    } else {
                        assentosSelecionados.remove(cadeira);
                    }
                    atualizarCorDoBotao(btnCadeira, cadeira);
                });
                
                gridCadeiras.add(btnCadeira, j, i);
            }
        }
    }

    private void atualizarCorDoBotao(Button btn, Cadeira cadeira) {
        if (cadeira.getEstaOcupada()) {
            btn.setStyle("-fx-background-color: #ff4c4c; -fx-text-fill: white;"); // Vermelho
            btn.setDisable(true);
        } else if (assentosSelecionados.contains(cadeira)) {
            btn.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white;"); // Azul (Selecionada)
        } else {
            btn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white;"); // Verde (Livre)
        }
    }

    @FXML
    public void sugerirCadeiras(ActionEvent event) {
        // Limpa as cores de sugestões antigas voltando ao normal
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                atualizarCorDoBotao(botoesCadeira[i][j], sessaoAtual.getCadeiras()[i][j]);
            }
        }

        try {
            int qtd = Integer.parseInt(campoQuantidade.getText());
            if (qtd < 2 || qtd > 9) {
                mostrarAlerta("Quantidade Inválida", "Digite um valor entre 2 e 9 para receber sugestões.");
                return;
            }

            Cadeira[][] matriz = sessaoAtual.getCadeiras();
            int sugestoesEncontradas = 0;

            // Lógica de busca de cadeiras juntas na mesma fileira (linha 'i')
            for (int i = 0; i < 10; i++) {
                int cadeirasContiguasLivres = 0;
                
                for (int j = 0; j < 15; j++) {
                    if (!matriz[i][j].getEstaOcupada() && !assentosSelecionados.contains(matriz[i][j])) {
                        cadeirasContiguasLivres++;
                        
                        if (cadeirasContiguasLivres == qtd) {
                            // Encontrou um bloco! Pinta as cadeiras desse bloco de Amarelo
                            for (int k = 0; k < qtd; k++) {
                                botoesCadeira[i][j - k].setStyle("-fx-background-color: #ffb300; -fx-text-fill: black;");
                            }
                            sugestoesEncontradas++;
                            cadeirasContiguasLivres = 0; // Zera para buscar outra sugestão na mesma fileira
                            
                            if (sugestoesEncontradas == 3) return; // Para ao achar 3 sugestões
                        }
                    } else {
                        cadeirasContiguasLivres = 0; // Quebrou a sequência
                    }
                }
            }

            if (sugestoesEncontradas == 0) {
                mostrarAlerta("Aviso", "Não foi possível encontrar " + qtd + " lugares juntos.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Por favor, digite apenas números no campo de quantidade.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void voltarParaSessoes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaSessoes.fxml"));
            Parent root = loader.load();

            // Devolve o filme atual para a tela de sessões recarregar os horários corretamente
            TelaSessoesController controller = loader.getController();
            controller.setFilme(sessaoAtual.getFilme());

            // Usa o gridCadeiras (ou qualquer outro elemento da tela) para pegar a janela atual
            Stage stage = (Stage) gridCadeiras.getScene().getWindow();
            stage.setTitle("Cinema - Sessões: " + sessaoAtual.getFilme().getNome());
            stage.setScene(new Scene(root, 800, 600));
            
        } catch (IOException e) {
            System.err.println("Erro ao voltar para a tela de sessões: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void avancarLanchonete(ActionEvent event) {
        if(assentosSelecionados.isEmpty()){
            mostrarAlerta("Erro", "Selecione pelo menos um assento!");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaLanchonete.fxml"));
            Parent root = loader.load();

            TelaLanchoneteController controller = loader.getController();
            controller.setDadosCompra(sessaoAtual, assentosSelecionados);

            Stage stage = (Stage) gridCadeiras.getScene().getWindow();
            stage.setTitle("Cinema - Lanchonete");
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}