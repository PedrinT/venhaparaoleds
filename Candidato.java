public class Candidato {
    String nome;
    String cpf;
    String data_nacimento;

    public Candidato() {
    }

    public Candidato(String nome, String cpf, String data_nacimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.data_nacimento = data_nacimento;
    }

    @Override
    public String toString() {
        return "Nome=" + nome +
                "\nCpf=" + cpf +
                "\nData de Nascimento=" + data_nacimento+"\n\n";
    }
}
