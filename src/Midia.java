public abstract class Midia {
    protected int codigo;
    protected String titulo;
    protected int ano;

    Midia(int codigo, String titulo, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
    }

    abstract double calculaLocacao();
}
