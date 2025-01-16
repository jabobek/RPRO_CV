package cz.uhk.homerecipes.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String subtitle;
    @NotBlank
    private String text;

    private String imageBase64;

    @NotNull
    private List<Long> tagIds;
}
