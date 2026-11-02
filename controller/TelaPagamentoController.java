package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import javafx.event.ActionEvent;

public class TelaPagamentoController {
    
    @FXML private TextField campoEmail, campoSenha, campoCupom;
    @FXML private TextField campoCartaoNome, campoCartaoNum;
    @FXML private PasswordField campoCartaoCVV;
    @FXML private Label statusLoginLabel, resumoLabel, totalLabel;
    @FXML private ComboBox<String> comboTipoIngresso;

    private Compra compraAtual;
    private Usuario usuarioLogado;
    private CupomPromocional cupomAplicado = null;

    public void setResumoCompra(Compra compra) {
        this.compraAtual = compra;
        
        // Preenche as opções do ComboBox (Padrão Strategy)
        comboTipoIngresso.getItems().addAll("Inteira", "Meia-Entrada (Estudante)", "Cortesia (Crítico)");
        comboTipoIngresso.getSelectionModel().selectFirst();
        
        atualizarResumo();
    }

    @FXML
    public void validarUsuario(ActionEvent event) {
        if (compraAtual == null) {
            mostrarAlerta("Erro Crítico", "Os dados da compra não foram carregados corretamente da tela anterior.");
            return;
        }

        String email = campoEmail.getText();
        String senha = campoSenha.getText();
        
        Usuario u = Administrador.getInstance().buscarUsuarioPorEmail(email);
        if (u != null && u.getSenha().equals(senha)) {
            this.usuarioLogado = u;
            this.compraAtual.setCliente(u);
            statusLoginLabel.setText("Logado como: " + u.getCpf());
            statusLoginLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } else {
            mostrarAlerta("Erro", "E-mail ou senha incorretos.");
        }
    }

    @FXML
    public void aplicarCupom(ActionEvent event) {
        try {
            // Tenta converter o texto digitado num Enum correspondente
            String textoCupom = campoCupom.getText().toUpperCase();
            this.cupomAplicado = CupomPromocional.valueOf(textoCupom); //[cite: 12]
            mostrarAlerta("Sucesso", "Cupom aplicado: " + cupomAplicado.getPorcentagem() + "% de desconto"); //[cite: 12]
            atualizarResumo();
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Erro", "Cupom inválido.");
            this.cupomAplicado = null;
            atualizarResumo();
        }
    }

    @FXML
    public void atualizarResumo() {
        // 1. Define qual Estratégia usar com base no ComboBox
        EstrategiaPreco estrategia = new PrecoInteira();
        String tipoSelecionado = comboTipoIngresso.getValue();
        
        if (tipoSelecionado != null) {
            if (tipoSelecionado.contains("Estudante")) estrategia = new PrecoEstudante();
            else if (tipoSelecionado.contains("Crítico")) estrategia = new PrecoCritico();
        }

        // 2. Chama a classe Compra para fazer o cálculo pesado
        double total = compraAtual.calcularTotalDaCompra(estrategia, cupomAplicado);
        
        // 3. Atualiza a tela
        resumoLabel.setText(compraAtual.getCadeiras().size() + " Ingressos + Lanchonete");
        totalLabel.setText(String.format("Total: R$ %.2f", total));
    }

    @FXML
    public void finalizarCompra(ActionEvent event) {
        if (usuarioLogado == null) {
            mostrarAlerta("Erro", "Faça o login antes de pagar.");
            return;
        }
        
        // Valida se os dados do cartão batem com o cadastro no usuarios.txt
        if (!campoCartaoNum.getText().equals(usuarioLogado.getnumeroDoCartao()) || 
            !campoCartaoCVV.getText().equals(usuarioLogado.getCodigoSegurancaCartao())) {
            mostrarAlerta("Erro", "Dados do cartão incorretos ou não pertencem a este usuário.");
            return;
        }

        // Sucesso! Aqui você atualiza as cadeiras para ocupadas (true) e gera os objetos Bilhete
        for (Cadeira c : compraAtual.getCadeiras()) {
            c.switchIsOcupada(); 
        }
        
        mostrarAlerta("Sucesso", "Compra finalizada! Bom filme.");
        // Implementar código para voltar para a tela inicial...
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
