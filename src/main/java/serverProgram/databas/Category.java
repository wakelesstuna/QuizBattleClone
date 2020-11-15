package serverProgram.databas;

import java.util.List;

public class Category {
    private String categoryName;
    private List<Question> questions;

    Category(String cateogryName){
        this.categoryName = cateogryName;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
