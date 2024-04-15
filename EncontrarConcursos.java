import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class EncontrarConcursos{


    public static void buscarCursos() {
        String candidato = "", cpfCandidato = "";
        String[] linhaSplit = new String[0];

        System.out.println("\n\nInforme o cpf para a busca:\n");
        Scanner lerTeclado = new Scanner(System.in);
        cpfCandidato = lerTeclado.nextLine();

        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader("candidatos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhaSplit = linha.split(" ");

                if (Objects.equals(linhaSplit[3], cpfCandidato)) {
                    candidato = linhaSplit[0]+" "+linhaSplit[1];
                    encontrado = true;
                    break;
                }
            }

            if(!encontrado){
                System.out.println("O candidato \""+candidato+"\" não encontrado no arquivo, tente novamente.\n");
                return;
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        System.out.println("Efetuando busca dos concursos do candidato: "+candidato+", titular do cpf '"+linhaSplit[3]+"'\n");

        // Separação dos cursos do candidato do split
        // para melhorar na comparação dos dados

        String[] profissoesCandidato;
        StringBuilder auxCandidato = new StringBuilder();

        for(int i = 4; i<=linhaSplit.length-1;i++) {
            auxCandidato.append(linhaSplit[i]);
        }

        profissoesCandidato = auxCandidato.toString().split(",");

        int quantProfissaoCandidato = profissoesCandidato.length-1;

        profissoesCandidato[0] = profissoesCandidato[0].substring(1);
        profissoesCandidato[quantProfissaoCandidato] = profissoesCandidato[quantProfissaoCandidato].substring(0, profissoesCandidato[quantProfissaoCandidato].length() - 1);


        // Busca & Separação dos concursos
        ArrayList<Concurso> ConcursosProCandidato = new ArrayList<Concurso>();

        try (BufferedReader br = new BufferedReader(new FileReader("concursos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhaSplit = linha.split(" ");

                String[] profissoesConcurso;
                StringBuilder auxConcursos = new StringBuilder();

                for(int i = 3; i<=linhaSplit.length-1;i++) {
                    auxConcursos.append(linhaSplit[i]);
                }

                profissoesConcurso = auxConcursos.toString().split(",");
                int quantCursos = profissoesConcurso.length-1;

                profissoesConcurso[0] = profissoesConcurso[0].substring(1);
                profissoesConcurso[quantCursos] = profissoesConcurso[quantCursos].substring(0, profissoesConcurso[quantCursos].length() - 1);

                for(int i = 0;i<=profissoesConcurso.length-1;i++ ){
                    for(String curso : profissoesCandidato){
                        if(curso.equals(profissoesConcurso[i])){
                            ConcursosProCandidato.add(new Concurso(linhaSplit[0], linhaSplit[1], linhaSplit[2]));
                        }
                    }
                }
            }

            System.out.println("Você deseja:" +
                    "\n1 - Listar concursos que possam ser realizados na tela" +
                    "\n2 - Salvar um arquivo que contenha os concursos que possam ser realizados");

            int op = lerTeclado.nextInt();

            if(op == 1){
                Services.listarLista(ConcursosProCandidato);
            }else{
                Services.salvarListaConcursos(ConcursosProCandidato, candidato);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }


    }
}
