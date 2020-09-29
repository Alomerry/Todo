package model.user.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@ToString
public class User{
    @Id
    private ObjectId id;
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
