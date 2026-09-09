# 🎮 Guia de Lógica e Eventos Swing (Edição Especial)

Este guia ignora a criação manual de interface (já que você usará o recurso de arrastar e soltar do NetBeans) e foca 100% na **lógica de programação**. Para facilitar o seu entendimento na hora da prova, todo o contexto foi adaptado para um **Gerenciador de Biblioteca de Jogos e Animes**, usando exemplos práticos do seu dia a dia.

---

## 📑 Índice
1. [Interagindo com Campos de Texto (Pegando dados)](#1-interagindo-com-campos-de-texto-pegando-dados)
2. [Manipulando JComboBox (Listas de Plataformas)](#2-manipulando-jcombobox-listas-de-plataformas)
3. [Dominando a JTable (Seu Inventário)](#3-dominando-a-jtable-seu-inventário)
4. [Alertas e Confirmações (JOptionPane)](#4-alertas-e-confirmações-joptionpane)
5. [Lógica CRUD Completa (O Gerenciador)](#5-lógica-crud-completa-o-gerenciador)

---

## 1. Interagindo com Campos de Texto (Pegando dados)

No NetBeans, você arrasta um `JTextField` e renomeia a variável (ex: `txtTitulo`). A lógica para usar esses campos nos botões é a seguinte:

### Capturando e Convertendo Dados (Entrada)
Sempre que você pega um texto, ele vem como `String`. Se o campo for numérico (como nível ou horas jogadas), você **precisa** converter.
```java
// Texto comum
String titulo = txtTitulo.getText();

// Números Inteiros (Sempre use try-catch em provas para evitar que o programa feche)
try {
    int nivelAtual = Integer.parseInt(txtNivel.getText());
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "O nível deve ser um número válido!");
}
```

### Preenchendo e Limpando Campos (Saída)
```java
// Colocando um texto via código
txtTitulo.setText("Overwatch 2");

// Limpando o campo (muito usado após um botão "Salvar")
txtTitulo.setText("");

// Devolvendo o foco para o campo (o cursor volta a piscar nele)
txtTitulo.requestFocus();

// Bloqueando um campo para edição (útil para chaves primárias/IDs)
txtId.setEditable(false); 
```

---

## 2. Manipulando JComboBox (Listas de Plataformas)

Se você arrastou um `JComboBox` (ex: `cbPlataforma`), você pode gerenciar as opções dele diretamente pelo código.

### Pegando a opção selecionada
```java
// Retorna exatamente o texto que o usuário escolheu (ex: "PlayStation Store")
String plataformaEscolhida = cbPlataforma.getSelectedItem().toString();

// Retorna a posição (índice) da escolha (0 é o primeiro, 1 o segundo...)
int index = cbPlataforma.getSelectedIndex();
```

### Alterando as opções via Código
```java
// Limpa todas as opções que vieram do modo Design
cbPlataforma.removeAllItems();

// Adiciona novas opções para o seu ecossistema
cbPlataforma.addItem("Selecione a plataforma...");
cbPlataforma.addItem("Steam");
cbPlataforma.addItem("PlayStation");
cbPlataforma.addItem("Crunchyroll");

// Força a seleção de um item específico
cbPlataforma.setSelectedItem("Steam");
```

---

## 3. Dominando a JTable (Seu Inventário)

No NetBeans, a `JTable` é criada automaticamente. Para gerenciar as linhas (adicionar um novo anime ou jogo), você precisa acessar o "Modelo" da tabela.

### Regra de Ouro da Tabela no NetBeans:
Sempre inicie pegando o modelo da sua tabela (supondo que o nome da variável seja `tabelaInventario`).
```java
// Faça um casting (DefaultTableModel) do getModel() da sua tabela visual
DefaultTableModel modelo = (DefaultTableModel) tabelaInventario.getModel();
```

### Adicionando uma Linha (Create)
```java
DefaultTableModel modelo = (DefaultTableModel) tabelaInventario.getModel();
// A ordem dos itens no Object[] DEVE ser exatamente a ordem das colunas da tabela
modelo.addRow(new Object[]{
    txtId.getText(),
    txtTitulo.getText(),
    cbPlataforma.getSelectedItem().toString()
});
```

### Removendo uma Linha Selecionada (Delete)
No NetBeans, clique com o botão direito no botão "Excluir" -> Events -> Action -> actionPerformed.
```java
int linhaSelecionada = tabelaInventario.getSelectedRow();

if (linhaSelecionada >= 0) { // Verifica se alguma linha foi clicada
    DefaultTableModel modelo = (DefaultTableModel) tabelaInventario.getModel();
    modelo.removeRow(linhaSelecionada);
} else {
    JOptionPane.showMessageDialog(null, "Selecione um título no inventário para excluir!");
}
```

### Clicando na Tabela para Preencher o Formulário (Update)
No NetBeans, clique com o botão direito na **JTable** -> Events -> Mouse -> **mouseClicked**.
```java
// O usuário clicou em uma linha, vamos preencher os TextFields para ele editar
int linha = tabelaInventario.getSelectedRow();

// getValueAt(linha, coluna). O toString() é necessário.
txtId.setText(tabelaInventario.getValueAt(linha, 0).toString());
txtTitulo.setText(tabelaInventario.getValueAt(linha, 1).toString());
cbPlataforma.setSelectedItem(tabelaInventario.getValueAt(linha, 2).toString());
```

---

## 4. Alertas e Confirmações (JOptionPane)

O uso do `JOptionPane` é vital para dar feedback ao usuário.

```java
// Mensagem Simples (Aviso/Sucesso)
JOptionPane.showMessageDialog(null, "Adicionado à sua lista com sucesso!");

// Mensagem de Erro (Com ícone vermelho)
JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados!", "Erro Fatal", JOptionPane.ERROR_MESSAGE);

// Pergunta Sim/Não (Muito usado no botão de Excluir)
int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este item da sua biblioteca?", "Confirmação", JOptionPane.YES_NO_OPTION);

if (resposta == JOptionPane.YES_OPTION) {
    // Lógica para confirmar exclusão
}
```

---

## 5. Lógica CRUD Completa (O Gerenciador)

Abaixo, a lógica completa para você usar nos botões gerados pelo NetBeans. O contexto aqui é um sistema onde você cadastra o que está jogando ou assistindo.

### Variáveis Globais (Coloque no topo da classe, embaixo de `public class SuaTela extends javax.swing.JFrame`)
```java
// Simulação de banco de dados em memória
private ArrayList<String[]> listaBiblioteca = new ArrayList<>();
```

### Dentro do Botão "Adicionar" (`btnSalvarActionPerformed`)
```java
String id = txtId.getText();
String titulo = txtTitulo.getText();
String plataforma = cbPlataforma.getSelectedItem().toString();

// Validação de Lógica básica
if (titulo.isEmpty()) {
    JOptionPane.showMessageDialog(this, "O título (nome do jogo/anime) é obrigatório!");
    txtTitulo.requestFocus();
    return; // Para a execução aqui
}

// Adicionando na lista (Banco em memória)
listaBiblioteca.add(new String[]{id, titulo, plataforma});

// Atualizando a tabela visual
DefaultTableModel modelo = (DefaultTableModel) tabelaInventario.getModel();
modelo.addRow(new Object[]{id, titulo, plataforma});

// Limpando campos
txtId.setText("");
txtTitulo.setText("");
txtTitulo.requestFocus();
```

### Dentro do Botão "Atualizar" (`btnAtualizarActionPerformed`)
```java
int linha = tabelaInventario.getSelectedRow();

if (linha >= 0) {
    // Atualiza a interface visual (Tabela)
    tabelaInventario.setValueAt(txtId.getText(), linha, 0);
    tabelaInventario.setValueAt(txtTitulo.getText(), linha, 1);
    tabelaInventario.setValueAt(cbPlataforma.getSelectedItem().toString(), linha, 2);
    
    // Atualiza a lista lógica
    String[] itemModificado = {
        txtId.getText(), 
        txtTitulo.getText(), 
        cbPlataforma.getSelectedItem().toString()
    };
    listaBiblioteca.set(linha, itemModificado);
    
    JOptionPane.showMessageDialog(this, "Inventário atualizado!");
} else {
    JOptionPane.showMessageDialog(this, "Selecione uma linha na tabela para atualizar.");
}
```

### Dentro do Botão "Limpar" (`btnLimparActionPerformed`)
```java
// Lógica para resetar o formulário
txtId.setText("");
txtId.setEditable(true); // Libera o ID caso tenha sido bloqueado
txtTitulo.setText("");
cbPlataforma.setSelectedIndex(0);

// Tira a seleção azul da tabela
tabelaInventario.clearSelection();
```
