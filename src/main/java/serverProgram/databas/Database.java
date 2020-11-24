package serverProgram.databas;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import model.Question;
import model.Category;


public class Database {
    private final Path questionPath = Paths.get("src/main/java/serverProgram/databas/questions");
    private List<Category> categories = new ArrayList<>();

    public Database() {
        //DirectoryStream classen representerar en samling av path som ligger i directory
        try (DirectoryStream<Path> files = Files.newDirectoryStream(questionPath)) {
            //Läser in alla kategori filer path som ligger i questions mappen
            for (Path file : files) {
                String categoryName = removeFileExtention(file);

                //Läser in frågor från text filen
                List<Question> questions = DatabaseHandler.readDataFromFile(file, categoryName);

                //Skapa Category objekt
                Category category = new Category(categoryName, questions);

                //Lägga kategori objekt i en lista
                categories.add(category);
            }
        } catch (IOException e) {
            System.out.println("Det gick inte att läsa in");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Ta bort .txt
     *
     * @param file src/main/java/serverProgram/databas/questions/java.txt
     * @return java
     */
    public String removeFileExtention(Path file) {
        String name = file.getFileName().toString();
        return name.substring(0, name.lastIndexOf('.'));
    }

    //Hämta ut frågor från vald kategori
    public List<Question> getQuestionList(String categoryName){
        List<Question> tempQuestionList = new ArrayList<>();
        for(Category category: categories) {
            if(category.getCategoryName().equalsIgnoreCase(categoryName))
                category.getQuestions().forEach(question -> tempQuestionList.add(question));
        }
        return tempQuestionList;
    }

    //Hämta ut kategorier namn lisr
    public List<Category> getCategories() {
        return categories;
    }

    public static void main(String[] args) {
        Database data = new Database();
        List<Question> questions = data.getQuestionList("java");
        for (Question q : questions){
            System.out.println(q.getCategoryName());
        }

    }


}

