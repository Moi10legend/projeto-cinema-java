package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Cadeira;
import model.Compra;
import model.Produtos;
import model.Sessao;
import model.Usuario;
import java.io.IOException;
import java.util.List;

public class TelaLanchoneteController {

    // As listas agora armazenam o objeto Produtos real
    @FXML private ListView<Produtos> listaProdutosDisponiveis;
    @FXML private ListView<Produtos> carrinhoView;

    private Sessao sessaoAtual;
    private List<Cadeira> cadeirasEscolhidas;
    private Compra compra; 

    public void setDadosCompra(Sessao sessao, List<Cadeira> cadeiras) {
        this.sessaoAtual = sessao;
        this.cadeirasEscolhidas = cadeiras;
        
        Usuario usuarioTemp = new Usuario(1, "123", "senha", 20, null, "cliente@teste.com", "Cartao", "123", "123");
        this.compra = new Compra(usuarioTemp); //[cite: 11]
        this.compra.setSessao(sessao);
        this.compra.setCadeiras(cadeiras);
    }

    @FXML
    public void initialize() {
        listaProdutosDisponiveis.getItems().addAll(Produtos.values());
    }

    @FXML
    public void adicionarAoCarrinho(ActionEvent event) {
        Produtos produtoSelecionado = listaProdutosDisponiveis.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            carrinhoView.getItems().add(produtoSelecionado);
            compra.adicionarUmItemDaLanchonete(produtoSelecionado); //[cite: 11]
        }
    }

    @FXML
    public void voltarParaCadeiras(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaCadeiras.fxml"));
            Parent root = loader.load();

            TelaCadeirasController controller = loader.getController();
            // Devolve a sessão e os assentos que o cliente já havia clicado
            controller.setSessao(sessaoAtual, cadeirasEscolhidas);

            Stage stage = (Stage) carrinhoView.getScene().getWindow();
            stage.setTitle("Cinema - Escolha de Assentos");
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void avancarPagamento(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaPagamento.fxml"));
            Parent root = loader.load();

            // Estas linhas são obrigatórias para que a compraAtual não seja nula no próximo Controller
            TelaPagamentoController controller = loader.getController();
            controller.setResumoCompra(this.compra); 

            Stage stage = (Stage) carrinhoView.getScene().getWindow();
            stage.setTitle("Cinema - Pagamento");
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}