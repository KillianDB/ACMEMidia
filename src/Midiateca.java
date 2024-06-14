import java.util.ArrayList;

public class Midiateca implements Iterator{
    // Referência para elemento inicial da lista.
    private Midia primeiro;
    // Referência para elemento final da lista.
    private Midia ultimo;
    // Referência para a posição atual na lista.
    private Midia atual;
    //Contador
    private int contador;
    private ArrayList<Midia> midias;

    Midiateca() {
        this.midias = new ArrayList<>();
        this.contador = 0;
    }

    public ArrayList<Midia> getMidias() {
        return midias;
    }

    public boolean cadastraMidia(Midia midia) {
        if (midias.contains(midia)) {
        return false;
    }
        if (midia instanceof Musica) {

            Musica m = (Musica) midia;
            this.midias.add(m);
            if(midias.contains(m)) {
                this.contador++;
                return true;
            }
        }

        if (midia instanceof Video) {
            Video v = (Video) midia;
            this.midias.add(v);
            if(midias.contains(v)) {
                this.contador++;
                return true;
            }
        }

        return false;
    }

    public Midia consultaPorCodigo(int codigo) {
            reset();

            while(contador < midias.size()) {
                Midia midia = midias.get(contador);

                if (midia instanceof Musica) {
                    Musica musica = (Musica) midia;
                    atual = musica;
                    if (musica.getCodigo() == codigo) {
                        return musica;
                    }
                }
                if (midia instanceof Video) {
                    Video video = (Video) midia;
                    atual = video;
                    if (video.getCodigo() == codigo) {
                        return video;
                    }
                }
                contador++;
            }
        return null;
    }

    public ArrayList<Midia> consultaPorCategoria(Categoria categoria) {
        ArrayList<Midia> base = new ArrayList<>();
        reset();

            while(contador < midias.size()) {
                Midia midia = midias.get(contador);
                if (midia instanceof Musica) {
                    Musica musica = (Musica) midia;
                    atual = musica;
                    if (musica.getCategoria().equals(categoria)) {
                        base.add(musica);
                    }
                }
                if (midia instanceof Video) {
                    Video video = (Video) midia;
                    atual = video;
                    if (video.getCategoria().equals(categoria)) {
                    base.add(video);
                    }
                }
                contador++;
        }
        if(base.isEmpty()){
            return null;
        }
        return base;
    }

    public ArrayList<Video> consultaPorQualidade(int qualidade) {
        ArrayList<Video> base = new ArrayList<>();
        reset();

        while(contador < midias.size()) {
            Midia midia = midias.get(contador);
            atual = midia;
            if (midia instanceof Video) {
                Video video = (Video) midia;
                if (video.getQualidade() == qualidade) {
                    base.add(video);
                }
            }
            contador++;
        }
        if(base.isEmpty()){
            return null;
        }
        return base;
    }

    public Musica consultaPorDuracao() {
        Musica musica = null;
        reset();

        while(contador < midias.size()) {
            Midia midia = midias.get(contador);
            atual = midia;
            if (midia instanceof Musica) {
                Musica m = (Musica) midia;
                if(musica != null) {
                    if (musica.getDuracao() < m.getDuracao()) {
                        musica = m;
                    }
                }else{
                    musica = m;
                }
            }
            contador++;
        }
        return musica;
    }

    public double somaLocacoes() {
        double soma = 0;
        reset();

        while(contador < midias.size()) {
            Midia midia = midias.get(contador);
            if(midia instanceof Musica){
                Musica m = (Musica) midia;
                atual = m;
                soma += m.calculaLocacao();
            }else if(midia instanceof Video){
                Video v = (Video) midia;
                atual = v;
                soma += v.calculaLocacao();
            }
            contador++;
        }
        return soma;
    }

    public boolean removeMidia(int codigo) {
        reset();

        while(contador < midias.size()) {
            Midia midia = midias.get(contador);
            atual = midia;
            if (midia.codigo == codigo) {
                midias.remove(midia);
                return true;
            }
            contador++;
        }
        return false;
    }

    //reinicia a iteração na coleção
    @Override
    public void reset() {
        contador = 0;
    }

    //retorna true se ainda há elementos para a iteração, ou false em caso
    //contrário
    @Override
    public boolean hasNext() {
        if (contador > midias.size()) {
            return true;
        }
        return false;
    }

    //retorna o próximo elemento da iteração
    @Override
    public Object next() {
        if(atual != ultimo) {
            int i = midias.indexOf(atual);
            atual = midias.get(i+1);
            return atual;
        }
        reset();
        return primeiro;
    }
}
