package ClientProgram.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerUtil {

    public void changeScene(String fxml, Node node){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
