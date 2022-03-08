package tranduongkyoto.redditbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tranduongkyoto.redditbackend.dto.VoteDto;
import tranduongkyoto.redditbackend.exceptions.PostNotFoundException;
import tranduongkyoto.redditbackend.exceptions.SpringRedditException;
import tranduongkyoto.redditbackend.model.Post;
import tranduongkyoto.redditbackend.model.Vote;
import tranduongkyoto.redditbackend.model.VoteType;
import tranduongkyoto.redditbackend.repository.PostRepository;
import tranduongkyoto.redditbackend.repository.VoteRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
