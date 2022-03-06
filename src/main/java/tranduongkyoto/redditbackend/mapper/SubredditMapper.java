package tranduongkyoto.redditbackend.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tranduongkyoto.redditbackend.dto.SubredditDto;
import tranduongkyoto.redditbackend.model.Post;
import tranduongkyoto.redditbackend.model.Subreddit;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(expression = "java(mapPosts(subreddit.getPosts()))", target = "numberOfPosts")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
}
