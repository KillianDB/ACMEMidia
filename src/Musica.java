public class Musica extends Midia{
    //duracao é em minutos
    private double duracao;
    private Categoria categoria;

    Musica(int codigo, String titulo, int ano, double duracao, Categoria categoria) {
        super(codigo, titulo, ano);
        this.duracao = duracao;
        this.categoria = categoria;
    }

    @Override
    public double calculaLocacao() {
        //Ação:R$0,90/min
        if(this.categoria.equals(Categoria.ACA)) {
            double locacao = duracao*0.9;

            return locacao;
        }
        //Drama:R$0,70/min
        if(this.categoria.equals(Categoria.DRA)) {
            double locacao = duracao*0.7;

            return locacao;
        }
        //Ficção:R$0,50/min
        if(this.categoria.equals(Categoria.FIC)) {
            double locacao = duracao*0.5;

            return locacao;
        }
        //Romance:R$0,30/min
        if(categoria.equals(Categoria.ROM)) {
            double locacao = duracao*0.3;

            return locacao;
        }

        return 0;
    }

    public double getDuracao() {
        return this.duracao;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public int getCodigo() {
        return this.codigo;
    }
}
