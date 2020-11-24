package model;


import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    private final String categoryName;
    private List<Question> questions;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryName, List<Question> questions){
        this.categoryName = categoryName;
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
