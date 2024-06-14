public interface Iterator {
    //reinicia a iteração na coleção
    public void reset();
    //retorna true se ainda há elementos para a iteração, ou false em caso
    //contrário
    public boolean hasNext();
    //retorna o próximo elemento da iteração
    public Object next();
}
