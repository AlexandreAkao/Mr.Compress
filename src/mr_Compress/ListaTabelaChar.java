package mr_Compress;

public class ListaTabelaChar {
    //Simplismente encadeada

    private NoTabela primeiro;
    private NoTabela ultimo;
    private int tamanho;

    public ListaTabelaChar() {
        primeiro = null;
        ultimo = null;
        tamanho = 0;
    }

    /**
     *
     * Insere o elemento no final
     *
     * @param coordenada Coordenada
     * @param caractere Caractere
     */
    public void inserirFinal(String coordenada, String caractere) {
        NoTabela<String> novo = new NoTabela(coordenada, caractere);

        if (primeiro == null) {

            primeiro = novo;
        } else {

            ultimo.setProximo(novo);
        }

        ultimo = novo;
        tamanho++;
    }

    public int getTamanho() {
        return tamanho;
    }

    public NoTabela getPrimeiro() {
        return primeiro;
    }
}
