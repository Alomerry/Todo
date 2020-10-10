package model.user.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public class User implements Serializable {
    @Id
    private String id;
    private String name;
    private String nickName;
    private String passwd;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted;

    public User(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }
}
