public class Concurso {
    String Orgao;
    String edital;
    String codigo;

    public Concurso() {
    }

    public Concurso(String orgao, String edital, String codigo) {
        this.Orgao = orgao;
        this.edital = edital;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Orgao=" + Orgao +
               "\nEdital=" + edital +
               "\nCodigo=" + codigo+"\n\n";
    }
}
