package model.user.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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
    @CreatedDate
    private long createdAt;
    @LastModifiedDate
    private long updatedAt;
    private boolean isDeleted;

    public User(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }
}
