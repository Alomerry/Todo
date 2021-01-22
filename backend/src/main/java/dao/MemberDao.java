package dao;

import model.member.po.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberDao extends MongoRepository<Member, ObjectId> {

    Member findByNameAndPasswdAndIsDeleted(String name, String passwd, boolean isDeleted);

    Member findByNameAndIsDeleted(String name, boolean isDeleted);
}
