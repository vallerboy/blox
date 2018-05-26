package pl.oskarpolak.blox.models.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.oskarpolak.blox.models.CommentEntity;
import pl.oskarpolak.blox.models.PostEntity;
import pl.oskarpolak.blox.models.repositories.PostRepository;

@Service
public class CommentService {

    @Autowired
    PostRepository postRepository;

    //@Async  - nie mieszac z ładowaniem typu leazy
    public void addComment(String author, String comment, int postId){
        PostEntity postEntity = postRepository.findById(postId).get();
        CommentEntity newComment = createNewCommentEntity(author, comment, postEntity);
        postEntity.getComments().add(newComment);

        postRepository.save(postEntity);
    }

    private CommentEntity createNewCommentEntity(String author, String comment, PostEntity postEntity) {
        CommentEntity newComment = new CommentEntity();
        newComment.setContent(comment);
        newComment.setAuthor(author);
        newComment.setPost(postEntity);
        return newComment;
    }

}
