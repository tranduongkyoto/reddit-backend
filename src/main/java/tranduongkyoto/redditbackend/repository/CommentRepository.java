package tranduongkyoto.redditbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tranduongkyoto.redditbackend.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
