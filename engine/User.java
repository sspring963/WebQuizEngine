package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp="[@.]", message = "@ and . is mandatory")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "password is mandatory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 5)
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
