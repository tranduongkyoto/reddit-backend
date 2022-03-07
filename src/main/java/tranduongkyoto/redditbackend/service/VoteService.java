package tranduongkyoto.redditbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tranduongkyoto.redditbackend.repository.PostRepository;
import tranduongkyoto.redditbackend.repository.VoteRepository;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

}
