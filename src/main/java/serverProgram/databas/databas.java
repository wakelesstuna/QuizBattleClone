package serverProgram.databas;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class databas {

    public List<Question> getQuestionsList(String category) {
        Path filePath = Paths.get("src/main/java/serverProgram/databas/questions/" + category + ".txt");

        return IUtil.readDataFromFile(filePath);
    }

}
