package model.user.vo;

import lombok.*;

import java.io.Serializable;

@Data
public class LoginResponse implements Serializable {
    private String id;
    private String name;
    private String accessToken;

    public LoginResponse(String id, String name, String accessToken) {
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
    }
}
