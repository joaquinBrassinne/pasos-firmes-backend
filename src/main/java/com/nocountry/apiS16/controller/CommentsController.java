package com.nocountry.apiS16.controller;

import com.nocountry.apiS16.dto.CommentsDTO;
import com.nocountry.apiS16.model.Comments;
import com.nocountry.apiS16.service.interfaces.ICommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentsController {

    private final ICommentsService commentsService;

    @PostMapping("/add")
    public ResponseEntity<Comments> saveComments(@RequestBody CommentsDTO commentsDTO) {
        return new ResponseEntity<>(this.commentsService.saveComments(commentsDTO.getId_user(),commentsDTO.getId_product()
                , commentsDTO.getDescription())
                , HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Comments>> getComments() {
        List<Comments> commentsList = this.commentsService.getComments();

        if (!commentsList.isEmpty()) {
            return new ResponseEntity<>(commentsList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get/{id_comments}")
    public ResponseEntity<Comments> findComments(@PathVariable Long id_comments) {
        Comments comments = this.commentsService.findComments(id_comments);

        if (comments != null) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id_comments}")
    public ResponseEntity<String> deleteComments(@PathVariable Long id_comments) {
        Boolean commentDeleted = this.commentsService.deleteComments(id_comments);

        if (commentDeleted) {
            return new ResponseEntity<>("Comment deleted succesfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comment with that doesnt found", HttpStatus.NOT_FOUND);
        }
    }
}
