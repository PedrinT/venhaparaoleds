import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class EncontrarCandidatos {
    public static void buscarCandidatos() {

        String concurso = "", codConcurso = "";
        String[] linhaSplit = new String[0];

        System.out.println("\n\nInforme o codigo do concurso para a busca:\n");
        Scanner lerTeclado = new Scanner(System.in);
        codConcurso = lerTeclado.nextLine();

        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader("concursos.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                linhaSplit = linha.split(" ");

                if (Objects.equals(linhaSplit[2], codConcurso)) {
                    concurso = linhaSplit[0]+" "+linhaSplit[1];
                    encontrado = true;
                    break;
                }
            }

            if(!encontrado){
                System.out.println("O concurso \""+concurso+"\" não foi encontrado no arquivo, tente novamente.\n");
                return;
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        System.out.println("Efetuando busca dos candidatos aptos ao Concurso de numero: "+codConcurso);

        // Separação dos cursos do concurso
        // para melhorar na comparação dos dados

        String[] cursosDoConcurso;
        StringBuilder CursosConcurso = new StringBuilder();

        for(int i = 3; i<=linhaSplit.length-1;i++) {
            CursosConcurso.append(linhaSplit[i]);
        }

        cursosDoConcurso = CursosConcurso.toString().split(",");

        int quantCursosCandidato = cursosDoConcurso.length-1;

        cursosDoConcurso[0] = cursosDoConcurso[0].substring(1);
        cursosDoConcurso[quantCursosCandidato] = cursosDoConcurso[quantCursosCandidato].substring(0, cursosDoConcurso[quantCursosCandidato].length() - 1);

        // Busca & Separação dos candidatos

        ArrayList<Candidato> CandidatosProConcurso = new ArrayList<Candidato>();

        try (BufferedReader br = new BufferedReader(new FileReader("candidatos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhaSplit = linha.split(" ");

                String[] cursosDoCandidato = new String[0];
                StringBuilder CursosCandidato = new StringBuilder();

                for(int i = 3; i<=linhaSplit.length-1;i++) {
                    CursosCandidato.append(linhaSplit[i]);
                }

                cursosDoCandidato = CursosCandidato.toString().split(",");
                int quantCursos = cursosDoCandidato.length-1;

                cursosDoCandidato[0] = cursosDoCandidato[0].substring(1);
                cursosDoCandidato[quantCursos] = cursosDoCandidato[quantCursos].substring(0, cursosDoCandidato[quantCursos].length() - 1);

                for(int i = 0;i<=cursosDoCandidato.length-1;i++ ){
                    for(String curso : cursosDoCandidato){
                        if(curso.equals(cursosDoCandidato[i])){
                            CandidatosProConcurso.add(new Candidato(linhaSplit[0]+" "+linhaSplit[1], linhaSplit[3], linhaSplit[2]));
                        }
                    }
                }
            }

            System.out.println("Você deseja:" +
                    "\n1 - Listar candidatos que podem realizar o concurso na tela" +
                    "\n2 - Salvar um arquivo que contenha os candidatos que podem realizar o concurso");

            int op = lerTeclado.nextInt();

            if(op == 1){
                Services.listarLista(CandidatosProConcurso);
            }else{
                Services.salvarListaCandidatos(CandidatosProConcurso, concurso);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
