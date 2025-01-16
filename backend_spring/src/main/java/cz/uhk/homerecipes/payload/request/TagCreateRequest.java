package cz.uhk.homerecipes.payload.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class TagCreateRequest {
    @NotBlank
    @Size(min = 3, max = 16)
    private String name;
}

