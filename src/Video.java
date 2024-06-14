public class Video extends Midia{
    private int qualidade;
    private Categoria categoria;

    public Video(int codigo, String titulo,  int ano, Categoria categoria, int qualidade) {
        super(codigo, titulo, ano);
        this.categoria = categoria;
        this.qualidade = qualidade;
    }

    @Override
    public double calculaLocacao() {
        //2024:R$20,00
        if(this.ano == 2024) {
            return 20.00;
        }
        //2000-2023:R$15,00
        if(this.ano < 2024 && this.ano > 2000) {
            return 15.00;
        }
        //antes 2000:R$10,00
        if(this.ano < 2000) {
            return 10.00;
        }
        return 0;
    }

    public Video getAll() {
        return this;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public int getQualidade() {
        return this.qualidade;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public int getAno() {
        return this.ano;
    }
}
