package example.app;

import org.hibernate.validator.constraints.NotEmpty;

public class SearchForm {

    @NotEmpty
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
