package pl.oskarpolak.blox.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.oskarpolak.blox.models.PostEntity;
import pl.oskarpolak.blox.models.repositories.PostRepository;

import java.util.Optional;

@Controller
//Od hibernate 5.0 JSR310 jest w standardzie
public class MainController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        Optional<PostEntity> postEntity = postRepository.findById(1);
        PostEntity postEntityWithoutOptional = postEntity.get();


        return postEntityWithoutOptional.getTitle() + " " + postEntityWithoutOptional.getComments().toString();
    }

}
