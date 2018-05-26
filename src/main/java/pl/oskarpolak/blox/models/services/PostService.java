package pl.oskarpolak.blox.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.oskarpolak.blox.models.CategoryEntity;
import pl.oskarpolak.blox.models.PostEntity;
import pl.oskarpolak.blox.models.forms.PostForm;
import pl.oskarpolak.blox.models.repositories.CategoryRepository;
import pl.oskarpolak.blox.models.repositories.PostRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    //@Async // zadanie asynchroniczne - przyspiesza zwrot strony, nie gwarantuje wykonania przed wyslaniem response do usera
    @Transactional //Powoduje, że metoda wykona wszystko albo nic.
    public void addPost(PostForm postForm, String userIp) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(postForm.getCategory());

        PostEntity postEntity = new PostEntity();
        postEntity.setCategory(categoryEntity.orElseThrow(IllegalStateException::new));
        postEntity.setAuthor(postForm.getAuthor());
        postEntity.setArticle(postForm.getArticle());
        postEntity.setTitle(postForm.getTitle());
        postEntity.setComments(Collections.emptyList());
        postEntity.setUserIp(userIp);

        postRepository.save(postEntity);
    }

    public PostRepository getRepository() {
        return postRepository;
    }
}
