package model.user.vo;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {
    @NotNull(message = "name field can't be null")
    @Size(min = 6, max = 18, message = "name field is to long")
    private String name;
    @NotNull(message = "passwd field can't be null")
    @Size(min = 6, max = 18, message = "name field is to long")
    private String passwd;
}
