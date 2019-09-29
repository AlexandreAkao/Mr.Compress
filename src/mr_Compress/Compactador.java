package mr_Compress;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.ProgressBar;

public class Compactador {

    private No primeiro;
    private No ultimo;
    private int quant;
    private String arvoreBin;
    private StringBuilder mensagemBin;
    private StringBuilder texto;
    private String completa;

    public Compactador() {
        this.primeiro = null;
        this.ultimo = null;
        this.quant = 0;
        this.arvoreBin = "";
        this.mensagemBin = new StringBuilder();
        this.mensagemBin.setLength(0);
        this.texto = new StringBuilder();
        this.texto.setLength(0);
        this.completa = null;
    }

    /**
     *
     * Ler, gera a arvore e cria o arquivo compactado
     *
     * @param arquivo Arquivo pra compactar
     * @param caminho Caminho para o arquivo
     * @param progresso Barra de progresso
     * @param pasta Boolean se ir se gravado na pasta no arquivo ou na selecionado
     * @param caminhoP Caminho da pasta selecionado
     * @throws IOException
     */
    public void LerEGeraArvore(BufferedReader arquivo, String caminho, ProgressBar progresso, boolean pasta, String caminhoP) throws IOException {
        this.lerArquivo(arquivo);
        progresso.setProgress(0.1);
        this.gerarArvore();
        this.primeiro.gerarArvoreBin();
        progresso.setProgress(0.5);
        this.arvoreBin = this.primeiro.getArvoreBin();
        this.primeiro.buscarFolha("");
        progresso.setProgress(0.7);
        this.gerarMsgBin();
        this.completa = arvoreBin + mensagemBin;
        this.gerarArquivo(caminhoP, pasta);
        this.limpar();
        progresso.setProgress(1);
    }

    /**
     *
     * Ler o arquivo e coloca o conteudo do arquivo na variavel texto e executa o metodo {@code ler}
     *
     * @param arquivo Arquivo pra compactar
     */
    private void lerArquivo(BufferedReader arquivo) {
        try {
            while (arquivo.ready()) {
                texto.append(arquivo.readLine());
                if (arquivo.ready()) {
                    texto.append("\n");
                }
            }

            this.ler(texto.toString().toCharArray());
        } catch (IOException ex) {
            System.out.println("ERROR!!");
            System.out.println("Reiniciar aplica��o");
        }
    }

    /**
     *
     * Coloca os caracteres no Histograma baseado na tabela ASCII e executa o metodo {@code criarNo}
     *
     * @param caract array de char do conteudo do arquivo
     */
    private void ler(char[] caract) {
        int[] histograma = new int[256];

        for (char caractere : caract) {
            histograma[(int) caractere]++;
        }
        criarNo(histograma);
    }

    /**
     *
     * Cria os Nos com os caracteres e frequencia
     *
     * @param hist Histograma com as frequencia dos caracteres
     */
    private void criarNo(int[] hist) {
        for (int i = 0; i < hist.length; i++) {
            if (hist[i] != 0) {
                inserirOrdenado((char) i, hist[i]);
                this.quant++;
            }
        }
    }

    /**
     *
     * Inserir o No apartir do char e frequencia
     *
     * @param valor Char
     * @param freq Frequencia do Char
     */
    private void inserirOrdenado(char valor, int freq) {
        No novo = new No(valor, freq);

        if (this.primeiro == null) {
            this.primeiro = novo;
            this.ultimo = novo;
        } else {
            No aux1 = null;
            No aux2 = this.primeiro;

            while (aux2 != null && freq >= aux2.getQuantidade()) {
                if (aux1 == null) {
                    aux1 = this.primeiro;
                } else {

                    aux1 = aux1.getProximo();
                }
                aux2 = aux2.getProximo();

            }

            if (aux1 != null) {

                aux1.setProximo(novo);
            } else {
                this.primeiro = novo;
            }

            novo.setProximo(aux2);

            if (aux1 == this.ultimo) {
                this.ultimo = novo;
            }
        }
    }

    /**
     *
     * Inserir o No apartir do No
     *
     * @param arvore No
     */
    private void inserirOrdenado(No arvore) {
        No novo = arvore;

        if (this.primeiro == null) {
            this.primeiro = novo;
            this.ultimo = novo;
        } else {
            No aux1 = null;
            No aux2 = this.primeiro;

            while (aux2 != null && arvore.getQuantidade() >= aux2.getQuantidade()) {
                if (aux1 == null) {
                    aux1 = this.primeiro;
                } else {

                    aux1 = aux1.getProximo();
                }
                aux2 = aux2.getProximo();

            }

            if (aux1 != null) {

                aux1.setProximo(novo);
            } else {
                this.primeiro = novo;
            }

            novo.setProximo(aux2);

            if (aux1 == this.ultimo) {
                this.ultimo = novo;
            }
        }
    }

    /**
     *
     * Gera a arvore apartir da lista de caracteres
     *
     * @throws IOException
     */
    private void gerarArvore() throws IOException {

        while (this.primeiro.getProximo() != null) {
            No novo = new No(primeiro.getQuantidade() + primeiro.getProximo().getQuantidade());
            novo.setEsquerda(primeiro);
            novo.setDireita(primeiro.getProximo());

            this.inserirOrdenado(novo);

            this.removerIniciais();
        }
    }

    /**
     * Remove o primeiro e o segundo elemento da lista de caracteres
     */
    private void removerIniciais() {
        No aux = primeiro;

        this.primeiro = primeiro.getProximo().getProximo();

        aux.getProximo().setProximo(null);
        aux.setProximo(null);
    }

    /**
     * Gera a mensagem apartir da arvore
     */
    private void gerarMsgBin() {
        NoTabela aux;

        for (int i = 0; i < texto.length(); i++) {
            aux = No.getLista().getPrimeiro();

            for (int x = 0; x < No.getLista().getTamanho(); x++) {

                if (Character.toString(texto.charAt(i)).equals(aux.getCaractere())) {
                    this.mensagemBin.append(aux.getCoordenada());
                    break;
                } else {
                    aux = aux.getProximo();
                }
            }
        }
    }

    /**
     *
     * Gera arquivo
     *
     * @param caminho Caminho do arquivo
     * @param pasta Boolean se ir se gravado na pasta no arquivo ou na selecionado
     * @throws IOException
     */
    public void gerarArquivo(String caminho, boolean pasta) throws IOException {
        File arquivo;

        if (pasta) {
            arquivo = new File(caminho + "bin");
        } else {

            arquivo = new File(caminho.substring(0, caminho.length() - 3) + "bin");
        }

        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(this.completa);
        bw.close();
    }

    /**
     * Limpa todos os dados das variaveis
     */
    public void limpar() {
        this.primeiro = null;
        this.ultimo = null;
        this.quant = 0;
        this.arvoreBin = "";
        this.mensagemBin.setLength(0);
        this.texto.setLength(0);
        this.completa = null;
    }
}
