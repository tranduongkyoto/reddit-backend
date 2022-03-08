package tranduongkyoto.redditbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tranduongkyoto.redditbackend.model.Comment;
import tranduongkyoto.redditbackend.model.Post;
import tranduongkyoto.redditbackend.model.User;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
