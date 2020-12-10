package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Question {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    private int id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Question text is mandatory")
    private String text;

    @Size(min = 2)
    @NotNull
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer[] answer;


    public Question() {

    }

    public Question(String title, String text, String[] options, Integer[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        if (options.length == 4) {
            this.options = options;
        }
    }

    public Integer[] getAnswer() { return answer; }

    public void setID(int i) {
        this.id = i;
    }

    public int getID() { return id; }
}

