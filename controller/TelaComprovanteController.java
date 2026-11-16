package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Cadeira;
import model.Compra;
import model.Produtos;
import model.Sessao;
import java.io.IOException;

public class TelaComprovanteController {

    @FXML private TextArea areaComprovante;

    public void setDadosComprovante(Compra compra, String tipoIngresso, double valorTotal) {
        Sessao sessao = compra.getSessao();
        StringBuilder sb = new StringBuilder();

        sb.append("=========================================\n");
        sb.append("         CAPIVARA RECORDS CINEMA         \n");
        sb.append("=========================================\n");
        sb.append("Cliente CPF: ").append(compra.getCliente().getCpf()).append("\n");
        sb.append("Filme: ").append(sessao.getFilme().getNome()).append("\n");
        sb.append("Sala: ").append(sessao.getNumeroSala()).append(" | Horário: ").append(sessao.getHorario()).append("\n");
        sb.append("Modalidade: ").append(tipoIngresso).append("\n\n");

        sb.append("--- BILHETES EMITIDOS ---\n");
        int numBilhete = 1;
        for (Cadeira c : compra.getCadeiras()) {
            sb.append(String.format("Bilhete #%d | Assento: %s\n", numBilhete++, c.getFileiraENumero()));
        }

        sb.append("\n--- BOMBONIERE / LANCHONETE ---\n");
        if (compra.getItensLanchonete().isEmpty()) {
            sb.append("Nenhum item adquirido.\n");
        } else {
            for (Produtos p : compra.getItensLanchonete()) {
                if (p != null) {
                    sb.append(String.format("- %s (R$ %.2f)\n", p.getNome(), p.getValor()));
                }
            }
        }

        sb.append("\n=========================================\n");
        sb.append(String.format("VALOR TOTAL PAGO: R$ %.2f\n", valorTotal));
        sb.append("=========================================\n");

        areaComprovante.setText(sb.toString());
    }

    @FXML
    public void voltarInicio(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicial.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) areaComprovante.getScene().getWindow();
            stage.setTitle("Capivara records");
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            System.err.println("Erro ao voltar para a tela inicial: " + e.getMessage());
            e.printStackTrace();
        }
    }
}