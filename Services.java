import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Services {

    public static void listarLista(ArrayList lista) {
        for(Object elemento: lista){
            System.out.println(elemento);
        }
    }

    public static void salvarListaConcursos(ArrayList<Concurso> lista, String candidato){

        String nome_arquivo = "Concursos_para_"+candidato+"txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nome_arquivo))) {
            for (Object item : lista) {
                writer.write(item.toString());
                writer.newLine();
            }
            System.out.println("Lista salva com sucesso no arquivo " + nome_arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista no arquivo: " + e.getMessage());
        }
    }

    public static void salvarListaCandidatos(ArrayList<Candidato> lista, String concurso){

        String nome_arquivo = "Caandidatos_para_o_concurso_"+concurso+"txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nome_arquivo))) {
            for (Object item : lista) {
                writer.write(item.toString());
                writer.newLine();
            }
            System.out.println("Lista salva com sucesso no arquivo " + nome_arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista no arquivo: " + e.getMessage());
        }
    }


}
