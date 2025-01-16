package cz.uhk.homerecipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;


    @Setter
    @NotNull
    @Getter
    @Size(min = 3, max = 255)
    @Column(name = "name")
    private String name;

    @Setter
    @NotNull
    @Getter
    @Size(min = 3, max = 255)
    @Column(name = "subtitle")
    private String subtitle;

    @Setter
    @NotNull
    @Getter
    @Size(min = 3, max = 1024)
    @Column(name = "text")
    private String text;

    @Setter
    @Getter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recipe_tag_assignment",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @Getter
    @Setter
    private Set<Tag> tags = new HashSet<>();

    @Setter
    @Getter
    @Column(name = "image_base64")
    private String imageBase64;
}
