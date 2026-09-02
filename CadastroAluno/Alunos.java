package LabDevSoft.CadastroAluno;

public class Alunos {
    private String nome;
    private String dataNascimento;
    private String sexo;
    private String matric;
    private String curso;
    private String cpf;
    private Endereco endereco;
    private String estado;
    private String telefone;

    public Alunos(String nome, String dataNascimento, String sexo, String matric, String curso, String cpf, Endereco endereco, String estado, String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.matric = matric;
        this.curso=curso;
        this.endereco = endereco;
        this.cpf = cpf;
        this.estado = estado;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getMatric() {
        return matric;
    }

    public String getCurso() {
        return curso;
    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String toCSV() {
        return matric + ";" + nome + ";" + dataNascimento + ";" + sexo + ";" + curso + ";" + cpf + ";" + estado + ";" + telefone + ";" + endereco.toCSV();
    }

    public static Alunos fromCSV(String linha) {
        String[] partes = linha.split(";");
        if(partes.length < 13) return null;
        
        Endereco end = Endereco.fromCSV(partes, 8);
        return new Alunos(partes[1], partes[2], partes[3], partes[0], partes[4], partes[5], end, partes[6], partes[7]);
    }
}
