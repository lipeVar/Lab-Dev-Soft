package LabDevSoft.CadastroAluno;

public class Endereco {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;

    public Endereco(String rua, String numero, String bairro, String cidade, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    // getters
    public String getRua() {
        return rua;
    }
    public String getNumero() {
        return numero;
    }
    public String getBairro() {
        return bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public String getCep() {
        return cep;
    }

    public String toCSV() {
        return rua + ";" + numero + ";" + bairro + ";" + cidade + ";" + cep;
    }

    public static Endereco fromCSV(String[] data, int startIndex) {
        return new Endereco(data[startIndex], data[startIndex+1], data[startIndex+2], data[startIndex+3], data[startIndex+4]);
    }
}
