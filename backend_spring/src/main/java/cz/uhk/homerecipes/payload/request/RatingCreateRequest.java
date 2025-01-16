package cz.uhk.homerecipes.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RatingCreateRequest {
    @NotNull
    @Min(1)
    @Max(5)
    private int rating;
}
