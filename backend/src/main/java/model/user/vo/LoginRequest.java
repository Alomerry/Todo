package model.user.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    private String name;
    private String passwd;
}
