package work.myapp.book.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.myapp.book.springboot.domain.posts.Posts;
import work.myapp.book.springboot.domain.posts.PostsRepository;
import work.myapp.book.springboot.web.dto.PostsResponseDto;
import work.myapp.book.springboot.web.dto.PostsSaveRequestDto;
import work.myapp.book.springboot.web.dto.PostsUpdateRequestDto;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no matching user. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no matching user. id="+ id));

        return new PostsResponseDto(entity);
    }
}
