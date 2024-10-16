package application.media.media;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


// tell spring this anno is gonna in charge of this class, so Spring has an instance on this class
@Repository
public class MediaRepository {

    private static final Logger log = LoggerFactory.getLogger(MediaRepository.class);
    private final JdbcClient jdbcClient;

    public MediaRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Media> findAll() {
        return jdbcClient.sql("SELECT * FROM Media")
                .query(Media.class)
                .list();
    }

    public Optional<Media> findById(Integer id) {
        return jdbcClient.sql("SELECT media_id, user_id, filename, filesize, media_type, title, description, created_at FROM Media WHERE media_id = :id")
                .param("id", id)
                .query(Media.class)
                .optional();
    }

    public void create (Media media) {
        var updated = jdbcClient.sql("INSERT INTO Media (filename, filesize,  media_type, title, description, user_id) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of( media.filename(), media.filesize(), media.mediaType(), media.title(), media.description(), media.userId()))
                .update();

        Assert.state(updated == 1, "Failed to create media " + media.title());
    }

    public void update (Media media, Integer id) {
        var updated = jdbcClient.sql("UPDATE Media SET filename = ?, filesize = ?,  media_type = ?, title = ?, description = ? WHERE media_id = ?")
                .params(List.of(media.filename(), media.filesize(), media.mediaType(), media.title(), media.description(), id))
                .update();

        Assert.state(updated == 1, "Failed to update media " + media.title());
    }

    public void delete (Integer id) {
        var updated = jdbcClient.sql("DELETE FROM Media WHERE media_id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete media " + id);
    }

    public List<Media> findMediaItemsByUser(Integer userId) {
        return jdbcClient.sql("SELECT media_id, user_id, filename, filesize, media_type, title, description, created_at FROM Media WHERE user_id = :id")
                .param("id", userId)
                .query(Media.class)
                .list();
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM Media").query().listOfRows().size();
    }

    public void saveAll(List<Media> mediaItems) {
        mediaItems.stream().forEach(this::create);
    }





}
