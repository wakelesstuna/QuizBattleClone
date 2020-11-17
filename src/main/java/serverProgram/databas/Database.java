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
    private final Path questionPath = Paths.get("src/main/java/serverProgram/databas/questions");
    private static List<Category> categories = new ArrayList<>();

    public Database() {
        try (DirectoryStream<Path> files = Files.newDirectoryStream(questionPath)) {
            //Läser in alla kategori filer i questions mappen
            for (Path file : files) {
                String categoryName = removeFileExtention(file);
                Category category = new Category(categoryName);

                //Lägga alla kategorier i en lista
                categories.add(category);

                //Läser in frågor
                List<Question> questions = DatabaseHandler.readDataFromFile(file);
                //Lägga Question objekt i category class
                category.setQuestions(questions);
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
    public static String removeFileExtention(Path file) {
        String name = file.getFileName().toString();
        return name.substring(0, name.lastIndexOf('.'));
    }

    //Hämta ut index av vald kategori
    public static int getCategoryIndex(String categoryName) {
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getCategoryName().equalsIgnoreCase(categoryName))
                return i;
        }
        return 0;
    }

    //Hämta ut frågor från vald kategori
    public static List<Question> getQuestions(int categoryIndex, int numberOfQuestions) {
        List<Question> tempQuestion = new ArrayList<>();

        //Hämta ut vald kategori objekt
        Category tempCat = categories.get(categoryIndex);

        //Hämta ut ett specifikt antal slumpa frågor
        for (int i = 0; i < numberOfQuestions; i++) {
            Random random = new Random();
            int randomNum = random.nextInt(tempCat.getQuestions().size());
            tempQuestion.add(tempCat.getQuestions().get(randomNum));
            tempCat.getQuestions().remove(randomNum);
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
