package model.user.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginResponse {
    private String id;
    private String name;
    private String accessToken;

    public LoginResponse(String id, String name, String accessToken) {
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
    }
}
