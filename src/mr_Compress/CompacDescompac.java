package mr_Compress;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CompacDescompac extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Tela.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Mr.Compress");

        CompacDescompac.stage = stage;

        stage.getIcons().add(new Image("/icone/mr-compress-logo.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
