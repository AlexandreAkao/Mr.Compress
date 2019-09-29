package mr_Compress;

public class NoTabela<E> {

    private E coordenada;
    private E caractere;
    private NoTabela proximo;

    public NoTabela(E coordenada, E caractere) {
        this.coordenada = coordenada;
        this.caractere = caractere;
        this.proximo = null;
    }

    public NoTabela getProximo() {
        return proximo;
    }

    public void setProximo(NoTabela proximo) {
        this.proximo = proximo;
    }

    public E getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(E coordenada) {
        this.coordenada = coordenada;
    }

    public E getCaractere() {
        return caractere;
    }

    public void setCaractere(E caractere) {
        this.caractere = caractere;
    }

}
