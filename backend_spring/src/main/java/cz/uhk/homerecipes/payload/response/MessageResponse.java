package cz.uhk.homerecipes.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponse {
  private String message;

  public void setMessage(String message) {
    this.message = message;
  }
}
