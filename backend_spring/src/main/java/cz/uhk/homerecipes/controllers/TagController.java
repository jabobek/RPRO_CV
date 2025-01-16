package cz.uhk.homerecipes.controllers;

import cz.uhk.homerecipes.dao.TagDAO;
import cz.uhk.homerecipes.models.Tag;
import cz.uhk.homerecipes.payload.request.TagCreateRequest;
import cz.uhk.homerecipes.repository.UserRepository;
import cz.uhk.homerecipes.utils.UserUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagDAO dao;
    private final UserRepository userRepository;

    @Autowired
    public TagController(TagDAO dao, UserRepository userRepository) {
        this.dao = dao;
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public ResponseEntity<?> findAll() {

        List<Tag> tags = dao.findAllByUserId(UserUtils.getCurrentUser(userRepository).getId());
        return ResponseEntity.ok(tags);
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody TagCreateRequest tagCreateRequest) {
        try {
            Tag tag = new Tag();
            tag.setName(tagCreateRequest.getName());
            tag.setUser(UserUtils.getCurrentUser(userRepository));

            dao.create(tag);
            return ResponseEntity.ok(tag);
        } catch (
                DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Same tag already exists.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred. Please try again later.");
        }
    }
}
