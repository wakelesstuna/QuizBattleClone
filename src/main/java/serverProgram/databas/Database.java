package serverProgram.databas;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Database {
    private String questionPath = "src/main/java/serverProgram/databas/questions";
    private static List<Category> categories = new ArrayList<>();

    public Database() {
        try (DirectoryStream<Path> files = Files.newDirectoryStream(Paths.get(questionPath))) {
            //Läser in alla kategori filer i questions mappen
            for (Path file : files) {
                String categoryName = removeFileExtention(file);
                Category category = new Category(categoryName);

                //Lägga alla kategorier namn i listan
                categories.add(category);

                //Läser in frågor
                List<Question> questions = DatabaseHandler.readDataFromFile(file);
                category.setQuestions(questions);
            }
        } catch (IOException ex) {
            System.out.println("Det gick inte att läsa in");
            ex.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Ta bort .txt
     *
     * @param file src/main/java/serverProgram/databas/questions/java.txt
     * @return java
     */
    public static String removeFileExtention(Path file) {
        String name = file.getFileName().toString();
        return name.substring(0, name.lastIndexOf('.'));
    }

    //Hämta ut frågor från vald kategori
    public static List<Question> getQuestions(String categoryName) {
        List<Question> tempQuestion = new ArrayList<>();
        for(Category category: categories) {
            if(category.getCategoryName().equalsIgnoreCase(categoryName))
                category.getQuestions().forEach(question -> tempQuestion.add(question));
        }
        return tempQuestion;
    }

    //Hämta ut slumpa kategorier namn
    public static List<String> getCategories(int numberOfCategories) {
        Collections.shuffle(categories);
        List<String> tempCat = new ArrayList<>();
        for (int i = 0; i < numberOfCategories; i++) {
            tempCat.add(categories.get(i).getCategoryName());
        }
        return tempCat;
    }

}
