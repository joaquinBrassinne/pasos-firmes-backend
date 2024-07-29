package com.nocountry.apiS16.service.interfaces;

import com.nocountry.apiS16.model.Answers;

import java.time.LocalDate;
import java.util.List;

public interface IAnswersService {
    Answers addAnswers(String content, Long id_user, Long id_comments, LocalDate creationDate);
    List<Answers> getAnswers();
    Answers getAnswersById(Long id_answers);
    Boolean deleteAnswers(Long id_answers);
}
