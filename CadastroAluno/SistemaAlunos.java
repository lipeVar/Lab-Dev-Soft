package LabDevSoft.CadastroAluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaAlunos extends JFrame{
    
    private List<Alunos> listaAlunos = new ArrayList<>();
    private DefaultTableModel tableModel;

    // Campos do formulário
    private JTextField txtNome = new JTextField(15);
    private JTextField txtDataNasc = new JTextField(8);
    private JTextField txtSexo = new JTextField(8);
    private JTextField txtMatricula = new JTextField(8);
    private JTextField txtCurso = new JTextField(10);
    private JTextField txtCpf = new JTextField(10);
    private JTextField txtTelefone = new JTextField(10);
    
    // Endereço
    private JTextField txtRua = new JTextField(12);
    private JTextField txtNum = new JTextField(4);
    private JTextField txtBairro = new JTextField(8);
    private JTextField txtCidade = new JTextField(8);
    private JTextField txtCep = new JTextField(8);
    private JComboBox<String> cbEstado;

    public SistemaAlunos() {
        setTitle("Cadastro de Alunos");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel Formulário
        JPanel panelForm = new JPanel(new GridLayout(4, 6, 5, 5));
        
        String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        cbEstado = new JComboBox<>(estados);

        panelForm.add(new JLabel("Matrícula:")); panelForm.add(txtMatricula);
        panelForm.add(new JLabel("Nome:")); panelForm.add(txtNome);
        panelForm.add(new JLabel("Nascimento (DD/MM/AAAA):")); panelForm.add(txtDataNasc);
        panelForm.add(new JLabel("Sexo:")); panelForm.add(txtSexo);
        panelForm.add(new JLabel("CPF:")); panelForm.add(txtCpf);
        panelForm.add(new JLabel("Curso:")); panelForm.add(txtCurso);
        panelForm.add(new JLabel("Telefone:")); panelForm.add(txtTelefone);
        panelForm.add(new JLabel("Rua:")); panelForm.add(txtRua);
        panelForm.add(new JLabel("Número:")); panelForm.add(txtNum);
        panelForm.add(new JLabel("Bairro:")); panelForm.add(txtBairro);
        panelForm.add(new JLabel("Cidade:")); panelForm.add(txtCidade);
        panelForm.add(new JLabel("CEP:")); panelForm.add(txtCep);
        panelForm.add(new JLabel("Estado:")); panelForm.add(cbEstado);

        JButton btnAdicionar = new JButton("Adicionar Aluno");
        panelForm.add(btnAdicionar);

        add(panelForm, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"Matrícula", "Nome", "Nasc.", "Sexo", "CPF", "Curso", "Telefone", "Cidade/UF"};
        tableModel = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(tableModel);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Eventos
        btnAdicionar.addActionListener(e -> adicionarAluno());

        // Carregar dados salvos ao iniciar
        carregarDadosIniciais();
    }

    private void adicionarAluno() {
        Endereco end = new Endereco(txtRua.getText(), txtNum.getText(), txtBairro.getText(), txtCidade.getText(), txtCep.getText());
        Alunos a = new Alunos(
            txtNome.getText(), txtDataNasc.getText(), txtSexo.getText(),
            txtMatricula.getText(), txtCurso.getText(), txtCpf.getText(),
            end, (String) cbEstado.getSelectedItem(), txtTelefone.getText()
        );

        listaAlunos.add(a);
        adicionarLinhaTabela(a);
        GerenciamentoArquivo.SalvarAlunos(listaAlunos);
        limparCampos();
    }

    private void carregarDadosIniciais() {
        listaAlunos = GerenciamentoArquivo.carregarAlunos();
        for (Alunos a : listaAlunos) {
            adicionarLinhaTabela(a);
        }
    }

    private void adicionarLinhaTabela(Alunos a) {
        tableModel.addRow(new Object[]{
            a.getMatric(), a.getNome(), a.getDataNascimento(),
            a.getSexo(), a.getCpf(), a.getCurso(), a.getTelefone(),
            a.getEndereco().getCidade() + "/" + a.getEstado()
        });
    }

    private void limparCampos() {
        txtNome.setText(""); txtDataNasc.setText(""); txtSexo.setText("");
        txtMatricula.setText(""); txtCurso.setText(""); txtCpf.setText("");
        txtTelefone.setText(""); txtRua.setText(""); txtNum.setText("");
        txtBairro.setText(""); txtCidade.setText(""); txtCep.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaAlunos().setVisible(true));
    }
}