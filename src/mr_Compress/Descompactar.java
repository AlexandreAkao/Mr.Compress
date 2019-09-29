package mr_Compress;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javafx.scene.control.ProgressBar;

public class Descompactar {

    private No raiz;
    private StringBuilder mensagemBin;
    private StringBuilder mensagem;

    public Descompactar() {
        this.raiz = null;
        this.mensagemBin = new StringBuilder();
        this.mensagemBin.setLength(0);
        this.mensagem = new StringBuilder();
        this.mensagem.setLength(0);
    }

    /**
     *
     * Descompacta o arquivo
     *
     * @param arquivo Arquivo para descompactar
     * @param caminho Caminho do arquivo
     * @param progresso Barra de progresso
     * @param pasta Boolean se ir se gravado na pasta no arquivo ou na selecionado
     * @param caminhoP Caminho da pasta selecionado
     * @throws IOException
     */
    public void descompartarArquivo(BufferedReader arquivo, String caminho, ProgressBar progresso, boolean pasta, String caminhoP) throws IOException {
        this.mensagemBin.append(arquivo.readLine());
        this.raiz = this.gerarArvore();
        progresso.setProgress(0.5);
        this.gerarMensagem();
        progresso.setProgress(0.9);
        this.gerarArquivo(caminhoP, pasta);
        progresso.setProgress(1);
        this.limpar();
    }

    /**
     *
     * Gera arvore apartir de uma sequencia de bits e retorna a raiz
     *
     * @return No, a raiz da arvore
     */
    public No gerarArvore() {

        if (mensagemBin.charAt(0) == '0') {
            No novo = new No();
            mensagemBin.replace(0, mensagemBin.length(), mensagemBin.substring(1));
            novo.setEsquerda(this.gerarArvore());
            novo.setDireita(this.gerarArvore());
            return novo;
        } else {
            No novo = new No((char) Integer.parseInt(mensagemBin.substring(1, 9), 2));
            mensagemBin.replace(0, mensagemBin.length(), mensagemBin.substring(9));
            return novo;
        }
    }

    /**
     * Gera a mensagem apos criar a arvore
     *
     * @deprecated
     */
    public void gerarMensagemAntigo() {
        No aux = raiz;
        if (mensagemBin.length() == 0) {
            mensagem.append(Character.toString(aux.getCaractere()));
        } else {

            while (mensagemBin.length() != 0) {

                if (mensagemBin.charAt(0) == '0') {
                    aux = aux.getEsquerda();
                } else {
                    aux = aux.getDireita();
                }

                if (aux.getEsquerda() == null && aux.getDireita() == null) {
                    mensagem.append(Character.toString(aux.getCaractere()));
                    aux = raiz;
                }
                mensagemBin.replace(0, mensagemBin.length(), mensagemBin.substring(1));
            }
        }
    }

    /**
     * Gera a mensagem apos criar a arvore
     */
    public void gerarMensagem() {
        No aux;
        int i = 0;

        while (i < mensagemBin.length()) {
            aux = raiz;
            while (!(aux.getEsquerda() == null && aux.getDireita() == null)) {

                if (mensagemBin.charAt(i) == '0') {
                    aux = aux.getEsquerda();
                } else {
                    aux = aux.getDireita();
                }
                i++;
            }
            mensagem.append(Character.toString(aux.getCaractere()));

        }
    }

    /**
     *
     * Gera o arquivo descompactado
     *
     * @param caminho Caminho do arquivo
     * @param pasta Boolean se ir se gravado na pasta no arquivo ou na selecionado
     * @throws IOException
     */
    public void gerarArquivo(String caminho, boolean pasta) throws IOException {
        File arquivo;

        if (pasta) {
            arquivo = new File(caminho + "txt");
        } else {

            arquivo = new File(caminho.substring(0, caminho.length() - 3) + "txt");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "Cp1252"));

        bw.write(this.mensagem.toString());
        bw.close();
    }

    /**
     * Limpa todos os dados das variaveis
     */
    public void limpar() {
        this.raiz = null;
        this.mensagemBin.setLength(0);
        this.mensagem.setLength(0);
    }
}
