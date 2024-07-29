package com.nocountry.apiS16.controller;

import com.nocountry.apiS16.dto.AnswersDTO;
import com.nocountry.apiS16.model.Answers;
import com.nocountry.apiS16.service.interfaces.IAnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/answers")
public class AnswersController {

    private final IAnswersService answersService;

    @PostMapping("/add")
    public ResponseEntity<Answers> addAnswers(@RequestBody AnswersDTO answersDTO){
        return new ResponseEntity<>(this.answersService.addAnswers(answersDTO.getContent(),
                answersDTO.getId_user()
                ,answersDTO.getId_comments()
                ,answersDTO.getCreationDate()), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Answers>> getAnswers(){
        List<Answers> answersList = this.answersService.getAnswers();
        if(!answersList.isEmpty()){
            return new ResponseEntity<>(answersList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get/{id_answers}")
    public ResponseEntity<Answers> getAnswers(@PathVariable Long id_answers){
        Answers answers = this.answersService.getAnswersById(id_answers);
        if(answers!=null){
            return new ResponseEntity<>(answers, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
