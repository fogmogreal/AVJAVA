import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Aluno;

public class App {

    public static void main(String[] args) {
        String arquivoPath = "C:\\Users\\autologon\\Desktop\\_ws\\JAVA\\AvaliacaoJAVA\\avjava\\alunos.csv";
        String resultadoPath = "C:\\Users\\autologon\\Desktop\\_ws\\JAVA\\AvaliacaoJAVA\\avjava\\resumo.csv";

        List<Aluno> alunos = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoPath))) {
            String line;

            leitor.readLine();

            while ((line = leitor.readLine()) != null) {
                String[] dados = line.split(";");
                String nome = dados[1].trim();
                int matricula = Integer.parseInt(dados[0].trim());
                double nota = Double.parseDouble(dados[2].trim().replace(",", "."));

                Aluno aluno = new Aluno(matricula, nome, nota);
                alunos.add(aluno);
            }
        } catch (IOException e) {
            System.out.println("Error archwrt: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Error archtype: " + e.getMessage());
            return;
        }

        int totalAlunos = alunos.size();
        int aprovados = 0;
        int reprovados = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;
        double somaNotas = 0.0;

        for (Aluno aluno : alunos) {
            double nota = aluno.getNota();
            somaNotas += nota;

            if (nota >= 6.0) {
                aprovados++;
            } else {
                reprovados++;
            }

            if (nota < menorNota) {
                menorNota = nota;
            }

            if (nota > maiorNota) {
                maiorNota = nota;
            }
        }

        double mediaGeral = somaNotas / totalAlunos;

        // try catch method
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultadoPath))) {
            writer.write("Total de alunos: " + totalAlunos);
            writer.newLine();
            writer.write("Alunos aprovados: " + aprovados);
            writer.newLine();
            writer.write("Alunos reprovados: " + reprovados);
            writer.newLine();
            writer.write("Maior nota: " + maiorNota);
            writer.newLine();
            writer.write("Menor nota: " + menorNota);
            writer.newLine();
            writer.write("Média geral: " + mediaGeral);
        } catch (IOException e) {
            System.out.println("Error archwrt: " + e.getMessage());
        }

        System.out.println("Success: " + resultadoPath);
    }
}
