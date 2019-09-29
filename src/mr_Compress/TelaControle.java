package mr_Compress;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaControle implements Initializable {

    private ProgressBar barra;
    private Button sair;
    private Label ms;

    private FileChooser escolherArquivo;
    private DirectoryChooser caminhoArquivo;
    private Compactador compactador;
    private Descompactar descompactador;
    private File arquivo;
    private File arquivoC;
    private BufferedReader br;

    public TelaControle() {
        barra = new ProgressBar();
        sair = new Button();
        ms = new Label();
        escolherArquivo = new FileChooser();
        caminhoArquivo = new DirectoryChooser();
        compactador = new Compactador();
        descompactador = new Descompactar();
        escolherArquivo.setTitle("Escolha o arquivo");
        caminhoArquivo.setTitle("Escolher caminho para o arquivo");
    }

    /**
     *
     * Botao para compactar arquivo na mesma pasta do arquivo
     *
     * @throws IOException
     */
    @FXML
    private void compactar(ActionEvent event) throws IOException {
        //escolherArquivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo TXT (*.txt)", "*.txt"));

        arquivo = escolherArquivo.showOpenDialog(CompacDescompac.stage);
        escolherArquivo.getExtensionFilters().clear();

        if (arquivo == null) {
            return;
        }

        this.popupProgresso();

        long tempoInicio = System.currentTimeMillis();

        String caminho = arquivo.getCanonicalPath();
        br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho), "ISO_8859_1"));
        compactador.LerEGeraArvore(br, caminho, barra, false, caminho);
        br.close();

        ms.setText((System.currentTimeMillis() - tempoInicio) + " ms");

        sair.setDisable(false);
    }

    /**
     *
     * Botao para compactar arquivo na pasta selecionada
     *
     * @throws IOException
     */
    @FXML
    private void compactarPara(ActionEvent event) throws IOException {
        escolherArquivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo TXT (*.txt)", "*.txt"));

        arquivo = escolherArquivo.showOpenDialog(CompacDescompac.stage);
        escolherArquivo.getExtensionFilters().clear();

        if (arquivo == null) {
            return;
        }

        arquivoC = caminhoArquivo.showDialog(CompacDescompac.stage);

        if (arquivoC == null) {
            return;
        }

        this.popupProgresso();

        long tempoInicio = System.currentTimeMillis();

        String caminho = arquivo.getCanonicalPath();
        String caminhoC = arquivoC.getCanonicalPath() + "\\" + arquivo.getName().substring(0, arquivo.getName().length() - 3);

        br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho), "ISO_8859_1"));
        compactador.LerEGeraArvore(br, caminho, barra, true, caminhoC);
        br.close();

        ms.setText((System.currentTimeMillis() - tempoInicio) + " ms");

        sair.setDisable(false);
    }

    /**
     *
     * Botao para descompactar arquivo na mesma pasta do arquivo
     *
     * @throws IOException
     */
    @FXML
    private void descompactar(ActionEvent event) throws IOException {
        escolherArquivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo BIN (*.bin)", "*.bin"));

        arquivo = escolherArquivo.showOpenDialog(CompacDescompac.stage);
        escolherArquivo.getExtensionFilters().clear();

        if (arquivo == null) {
            return;
        }

        this.popupProgresso();

        long tempoInicio = System.currentTimeMillis();

        String caminho = arquivo.getCanonicalPath();
        br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho), "ISO_8859_1"));
        descompactador.descompartarArquivo(br, caminho, barra, false, caminho);
        br.close();

        ms.setText((System.currentTimeMillis() - tempoInicio) + " ms");

        sair.setDisable(false);

    }

    /**
     *
     * Botao para descompactar arquivo na pasta selecionada
     *
     * @throws IOException
     */
    @FXML
    private void descompactarPara(ActionEvent event) throws IOException {
        escolherArquivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo BIN (*.bin)", "*.bin"));

        arquivo = escolherArquivo.showOpenDialog(CompacDescompac.stage);
        escolherArquivo.getExtensionFilters().clear();

        if (arquivo == null) {
            return;
        }

        arquivoC = caminhoArquivo.showDialog(CompacDescompac.stage);

        if (arquivoC == null) {
            return;
        }

        this.popupProgresso();

        long tempoInicio = System.currentTimeMillis();

        String caminho = arquivo.getCanonicalPath();
        String caminhoC = arquivoC.getCanonicalPath() + "\\" + arquivo.getName().substring(0, arquivo.getName().length() - 3);

        br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho), "ISO_8859_1"));
        descompactador.descompartarArquivo(br, caminho, barra, true, caminhoC);
        br.close();

        ms.setText((System.currentTimeMillis() - tempoInicio) + " ms");

        sair.setDisable(false);

    }

    /**
     * Sait da aplicacao
     */
    @FXML
    private void sair(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Metodo para abrir popup
     */
    @FXML
    private void popupProgresso() {
        final Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(CompacDescompac.stage);

        AnchorPane box = new AnchorPane();
        box.setPrefWidth(300);
        box.setPrefHeight(90);

        barra.setLayoutX(50);
        barra.setLayoutY(14);
        barra.setPrefWidth(200);
        barra.setProgress(0);
        box.getChildren().add(barra);

        ms.setFont(Font.font("Verdana", 20));
        ms.setLayoutX(181);
        ms.setLayoutY(49);
        box.getChildren().add(ms);

        sair.setDisable(true);
        sair.setText("Completo");
        sair.setLayoutX(81);
        sair.setLayoutY(45);
        sair.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                barra.setProgress(0);
                popup.close();
            }
        });
        box.getChildren().add(sair);

        popup.getIcons().add(new Image("/icone/winrar-logo.png"));

        Scene scene = new Scene(box);
        popup.setScene(scene);
        popup.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
