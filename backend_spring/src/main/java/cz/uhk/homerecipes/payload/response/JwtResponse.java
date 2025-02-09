package cz.uhk.homerecipes.payload.response;

import lombok.Getter;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private int id;
  @Getter
  private String username;

  @Getter
  private String email;
  private List<String> roles;

  public JwtResponse(String accessToken, int id, String username, String email, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
