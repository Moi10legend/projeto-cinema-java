package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Critica;
import model.Filme;
import java.io.IOException;

public class TelaCriticasController {

    @FXML private Label tituloFilmeLabel;
    @FXML private Label mediaGeralLabel;
    @FXML private VBox listaCriticasContainer;

    private Filme filmeAtual;

    public void setFilme(Filme filme) {
        this.filmeAtual = filme;
        tituloFilmeLabel.setText("Críticas - " + filme.getNome());

        if (filme.getQuantidade_criticos() > 0) {
            mediaGeralLabel.setText(String.format(
                "Nota Média: %.1f / 10.0  (%d avaliação(ões))",
                filme.getNota(),
                filme.getQuantidade_criticos()
            ));
        } else {
            mediaGeralLabel.setText("Nota Média: N/A (Nenhuma crítica registrada ainda)");
        }

        carregarCriticas();
    }

    private void carregarCriticas() {
        listaCriticasContainer.getChildren().clear();
        Critica[] criticas = filmeAtual.getCriticas();
        boolean possuiCriticas = false;

        for (Critica c : criticas) {
            if (c != null) {
                possuiCriticas = true;

                VBox card = new VBox(6);
                card.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #dddddd; -fx-border-radius: 6; -fx-background-radius: 6; -fx-padding: 12;");

                Label cabecalho = new Label(
                    String.format("%s (%s) — Nota: %.1f/10", c.getNome(), c.getOrigem(), c.getNota())
                );
                cabecalho.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #222222;");

                Label corpo = new Label(c.getMensagem());
                corpo.setWrapText(true);
                corpo.setStyle("-fx-font-size: 13px; -fx-text-fill: #444444;");

                card.getChildren().addAll(cabecalho, corpo);
                listaCriticasContainer.getChildren().add(card);
            }
        }

        if (!possuiCriticas) {
            Label vazio = new Label("Nenhuma crítica cadastrada para este filme até o momento.");
            vazio.setStyle("-fx-text-fill: #777777; -fx-font-style: italic;");
            listaCriticasContainer.getChildren().add(vazio);
        }
    }

    @FXML
    public void voltarParaSessoes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaSessoes.fxml"));
            Parent root = loader.load();

            TelaSessoesController controller = loader.getController();
            controller.setFilme(filmeAtual);

            Stage stage = (Stage) listaCriticasContainer.getScene().getWindow();
            stage.setTitle("Cinema - Sessões: " + filmeAtual.getNome());
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            System.err.println("Erro ao voltar para sessões: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
