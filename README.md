# 🎬 Sistema de Venda de Ingressos para Cinema

Projeto acadêmico desenvolvido em **Java** com interface gráfica em **JavaFX**, focando na aplicação prática de conceitos avançados de **Programação Orientada a Objetos (POO)** e Padrões de Projeto.

O sistema simula um terminal de autoatendimento (totem) de cinema, permitindo ao usuário navegar pelo catálogo de filmes, escolher horários de sessões, selecionar assentos através de uma matriz interativa, comprar itens na lanchonete e finalizar o pagamento aplicando descontos e cupons promocionais.

---

## ✨ Funcionalidades Principais

*   **Catálogo Dinâmico:** Exibição de filmes com cartazes carregados via URL, título, duração e valor base.
*   **Gestão de Sessões:** Listagem de salas (Comum, 3D, XD) e horários. O sistema bloqueia automaticamente os botões de horários que já passaram com base no relógio do sistema (`LocalTime`).
*   **Espaço do Crítico:** Usuários cadastrados como "Críticos" podem submeter notas e resenhas diretamente na tela da sessão do filme.
*   **Mapa de Assentos Inteligente:** 
    *   Matriz visual de poltronas divididas por cores (Livre, Ocupada, Selecionada).
    *   **Algoritmo de Sugestão:** O sistema recebe a quantidade de ingressos desejada e sugere automaticamente (em amarelo) as melhores cadeiras contíguas na mesma fileira.
*   **Bomboniere (Lanchonete):** Catálogo de produtos gerido via `Enum`, com adição de itens a um carrinho de compras em tempo real.
*   **Checkout e Pagamentos:** 
    *   Cálculo do valor total unindo ingressos e lanches.
    *   Validação de usuário via leitura de arquivo `.txt` (`usuarios.txt`).
    *   Aplicação de cupons de desconto (`CupomPromocional`).
    *   Cálculo automático de meia-entrada (Estudantes) ou cortesia (Críticos).

---

## 🛠️ Tecnologias e Arquitetura

*   **Linguagem:** Java (JDK)
*   **Interface Gráfica:** JavaFX (FXML + CSS via Scene Builder)
*   **Persistência de Dados:** Arquivos de texto (`.txt`) para leitura de filmes e usuários iniciais.
*   **Arquitetura:** **MVC (Model-View-Controller)**
    *   *Model:* Classes de domínio (Filme, Sessao, Cadeira, Compra, Usuario), Enums e lógica de negócio.
    *   *View:* Arquivos `.fxml` estilizados.
    *   *Controller:* Classes intermediárias gerenciando eventos de clique e transições de tela.

### 📐 Padrões de Projeto Aplicados

1.  **Singleton:** Aplicado na classe central `Administrador`, garantindo que o sistema inteiro acesse a mesma instância e compartilhe as mesmas listas de Filmes, Sessões, Salas e Usuários em memória, evitando duplicação de dados entre as telas.
2.  **Strategy:** Utilizado no cálculo de preços (`EstrategiaPreco`). Em vez de múltiplos `if/else` validando o tipo de cliente, o sistema delega o cálculo para classes específicas (`PrecoInteira`, `PrecoEstudante`, `PrecoCritico`), facilitando a manutenção e adição de novas promoções.

---
