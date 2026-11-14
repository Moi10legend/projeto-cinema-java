package controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.Administrador;
import model.Filme;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TelaInicialController {

    @FXML
    private FlowPane gradeFilmes;
    

    // O método initialize roda automaticamente quando a tela abre
    @FXML
    public void initialize() {
        // Supondo que você criou o método getListaFilmes() no Administrador
        for (Filme filme : Administrador.getInstance().getListaFilmes()) {
            
            // 1. Cria o contêiner do card (VBox empilha itens na vertical)
            VBox card = new VBox(10);
            card.setAlignment(Pos.CENTER);
            card.setStyle("-fx-cursor: hand; -fx-padding: 10; -fx-border-color: #cccccc; -fx-border-radius: 5;");
            
            // 2. Cria a imagem do cartaz carregando da URL da web
            // O "true" no final habilita o carregamento em background para não travar a tela
            Image imagem = new Image(filme.getCartaz(), true);
            ImageView poster = new ImageView(imagem);
            poster.setFitWidth(150); // Largura do cartaz
            poster.setPreserveRatio(true);
            
            // 3. Cria os textos
            Label titulo = new Label(filme.getNome());
            titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            
            Label info = new Label(filme.getDuracao() + " min | R$ " + filme.getValor());
            
            // 4. Adiciona imagem e textos dentro do card
            card.getChildren().addAll(poster, titulo, info);
            
            // 5. Define a ação de clique no card inteiro
            card.setOnMouseClicked(event -> abrirTelaSessoes(filme));
            
            // 6. Adiciona o card pronto na tela
            gradeFilmes.getChildren().add(card);
        }
    }

    private void abrirTelaSessoes(Filme filmeEscolhido) {
    try {
        // 1. Instancia o carregador apontando para o arquivo FXML da próxima tela
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaSessoes.fxml"));
        Parent root = loader.load();

        // 2. (Opcional, mas recomendado) Envia o filme para o próximo Controller
        TelaSessoesController controllerSessoes = loader.getController();
        controllerSessoes.setFilme(filmeEscolhido);

        // 3. Obtém a janela (Stage) atual a partir de qualquer elemento visual (ex: gradeFilmes)
        Stage stage = (Stage) gradeFilmes.getScene().getWindow();

        // 4. Cria a nova cena, altera o título e atualiza a janela
        Scene cenaSessoes = new Scene(root, 800, 600);
        stage.setTitle("Cinema - Sessões: " + filmeEscolhido.getNome());
        stage.setScene(cenaSessoes);
        
    } catch (IOException e) {
        System.err.println("Erro ao carregar a tela de sessões: " + e.getMessage());
        e.printStackTrace();
    }
}
}
