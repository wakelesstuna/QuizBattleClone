package ClientProgram.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ControllerUtil {

    public AnchorPane loadFMXLFiles(Class<?> tClass,String FMXLFileName){
        try {
            return FXMLLoader.load(tClass.getClassLoader().getResource("view/"
                    + FMXLFileName + ".fxml"));
        }catch (IOException e){
            System.out.println("Couldn't load FMXLfile");
            e.printStackTrace();
        }
        return null;
    }
}
