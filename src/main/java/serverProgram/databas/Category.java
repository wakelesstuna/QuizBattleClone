package serverProgram.databas;

import java.util.List;

public class Category {
    private String categoryName;
    private List<Question> questions;

    Category(String cateogryName, List<Question> questions){
        this.categoryName = cateogryName;
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
