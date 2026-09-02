package LabDevSoft.CadastroAluno;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciamentoArquivo {
    private static final String CAMINHO_ARQUIVO = "alunos.txt";

    public static void SalvarAlunos(List<Alunos> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Alunos a : alunos) {
                writer.write(a.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public static List<Alunos> carregarAlunos() {
        List<Alunos> alunos = new ArrayList<>();
        File file = new File(CAMINHO_ARQUIVO);
        if (!file.exists()) {
            return alunos;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while((linha = reader.readLine()) != null) {
                Alunos alunos2 = Alunos.fromCSV(linha);
                if(alunos2 != null) alunos.add(alunos2);
            }   
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivos: " + e.getMessage());
        }
        return alunos;
    }
}
