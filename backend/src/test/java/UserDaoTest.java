import app.WebApp;
import dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Value("${logging.level.root}")
    private String level;

    @Test
    public void testInsert() {
        System.out.println(level);
//        User user = new User("alomerry", "120211");
//        userDao.save(user);
    }
}
