package application.media.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<User> findAll() {
        return jdbcClient.sql("SELECT * FROM User")
                .query(User.class)
                .list();
    }

    public Optional<User> findById(Integer id) {
        return jdbcClient.sql("SELECT user_id, username, email, user_level_id, created_at FROM User WHERE user_id = :id")
                .param("id", id)
                .query(User.class)
                .optional();
    }

    public void create (User user) {
        var updated = jdbcClient.sql("INSERT INTO User (username, password, email, user_level_id) VALUES (?, ?, ?, ?)")
                .params(List.of(user.username(), user.password(), user.email(), 2))
                .update();

        Assert.state(updated == 1, "Failed to create user " + user.username());
    }

    public void update (User user, Integer id) {
        var updated = jdbcClient.sql("UPDATE User SET username = ?, password = ? WHERE user_id = ?")
                .params(List.of(user.username(), user.password(), id))
                .update();

        Assert.state(updated == 1, "Failed to update user " + user.username());
    }

    public void delete (Integer id) {
        var updated = jdbcClient.sql("DELETE FROM User WHERE user_id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete user " + id);
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM User").query().listOfRows().size();
    }

    public void saveAll(List<User> users) {
        users.stream().forEach(this::create);
    }

}
