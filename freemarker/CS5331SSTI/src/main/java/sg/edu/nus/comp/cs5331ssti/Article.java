package sg.edu.nus.comp.cs5331ssti;

import org.springframework.stereotype.Repository;

@Repository
public class Article {
    protected String text = "";
    public Article() {
        super();
        this.text = "nothing...";
    }
    public Article(String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.getText();
    }
}
