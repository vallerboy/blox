package pl.oskarpolak.blox.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.blox.models.CommentEntity;
import pl.oskarpolak.blox.models.PostEntity;
import pl.oskarpolak.blox.models.repositories.CommentRepository;
import pl.oskarpolak.blox.models.repositories.PostRepository;

import java.util.Optional;

@Controller
//Od hibernate 5.0 JSR310 jest w standardzie
public class MainController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "index";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("post", postRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "post";
    }

    @PostMapping("/comment/{id}")
    public String addComment(@RequestParam("comment") String comment,
                             @RequestParam("author")  String author,
                             @PathVariable("id") int id){
        PostEntity postEntity = postRepository.findById(id).get();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(comment);
        commentEntity.setAuthor(author);
        commentEntity.setPost(postEntity);

        postEntity.getComments().add(commentEntity);
        postRepository.save(postEntity);
        return "redirect:/post/" + id;
    }

}
