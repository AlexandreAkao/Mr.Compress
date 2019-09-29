package mr_Compress;

public class No {

    private No proximo;
    private No esquerda;
    private No direita;
    private char caractere;
    private int quantidade;
    private static String arvoreBin;
    private static ListaTabelaChar lista;

    public No(char caractere, int quantidade) {
        this.caractere = caractere;
        this.quantidade = quantidade;
        this.proximo = null;
        this.esquerda = null;
        this.direita = null;
        No.arvoreBin = "";
        No.lista = new ListaTabelaChar();
    }

    public No(int quantidade) {
        this.quantidade = quantidade;
        this.proximo = null;
        this.esquerda = null;
        this.direita = null;
        No.arvoreBin = "";
        No.lista = new ListaTabelaChar();
    }

    public No(char caractere) {
        this.caractere = caractere;
        this.proximo = null;
        this.esquerda = null;
        this.direita = null;
        No.arvoreBin = "";
        No.lista = null;
    }

    public No() {
        this.proximo = null;
        this.esquerda = null;
        this.direita = null;
        No.arvoreBin = "";
        No.lista = new ListaTabelaChar();
    }

    /**
     * Gera a arvore binaria
     */
    public void gerarArvoreBin() {
        if (esquerda == null && direita == null) {
            String aux = "";
            while (Integer.toBinaryString((int) this.caractere).length() + aux.length() < 8) {
                aux += "0";

            }
            arvoreBin += "1" + aux + Integer.toBinaryString((int) this.caractere);
        } else {
            arvoreBin += "0";

        }

        if (esquerda != null) {

            esquerda.gerarArvoreBin();
        }

        if (direita != null) {

            direita.gerarArvoreBin();
        }
    }

    /**
     *
     * Busca a folha na arvore e cria lista das coordenadas
     *
     * @param coord coordenada
     */
    public void buscarFolha(String coord) {
        String coordenada = coord;

        if (esquerda == null && esquerda == null) {

            lista.inserirFinal(coordenada, Character.toString(this.caractere));
        }

        if (esquerda != null) {
            coordenada += "0";
            esquerda.buscarFolha(coordenada);
            coordenada = coordenada.substring(0, coordenada.length() - 1);
        }

        if (direita != null) {
            coordenada += "1";
            direita.buscarFolha(coordenada);
            coordenada = coordenada.substring(0, coordenada.length() - 1);
        }
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public char getCaractere() {
        return caractere;
    }

    public void setCaractere(char caractere) {
        this.caractere = caractere;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getArvoreBin() {
        return arvoreBin;
    }

    public static ListaTabelaChar getLista() {
        return lista;
    }
}
