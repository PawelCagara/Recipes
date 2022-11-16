package recipes.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotEmpty
    @Pattern(regexp = ".+@.+\\..+")
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
