import java.util.Scanner;


public class Menu {
    public static void main(String[] args){

        while(true){
            System.out.println("Informe oque deseja realizar:\n\n" +
                    "1 - Busca de Cursos a partir do cpf do candidato;\n" +
                    "2 - Busca de Candidatos a partir do numero do concurso;");

            Scanner ler = new Scanner(System.in);

            int op = ler.nextInt();


            switch (op){
                case 1:
                    EncontrarConcursos.buscarCursos();
                    break;
                case 2:
                    EncontrarCandidatos.buscarCandidatos();
                    break;
                default:
                    System.out.println("Opção invalida");
            }

            System.out.println("Deseja continuar fazendo buscas?\n"+
                    "1 - Sim\n"+
                    "2 - Não\n");

            op = ler.nextInt();

            if(op == 2){
                break;
            }
        }
    }

}
