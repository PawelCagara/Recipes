package recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "recipe")

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @NotBlank

    private String name;
    @NotBlank
    private String category;
    private LocalDateTime date;
    @NotBlank
    private String description;
    @ElementCollection
    @Size(min = 1)
    @NotEmpty
    private List<String> ingredients;
    @ElementCollection
    @Size(min = 1)
    @NotEmpty
    private List<String> directions;

    public Recipe(String name, String category, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        date = LocalDateTime.now();
    }
}