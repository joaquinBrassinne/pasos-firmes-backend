package com.nocountry.apiS16.service.implementations;

import com.nocountry.apiS16.exceptions.ObjectNotFoundException;
import com.nocountry.apiS16.model.Answers;
import com.nocountry.apiS16.model.Comments;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.repository.IAnswersRepository;
import com.nocountry.apiS16.repository.ICommentsRepository;
import com.nocountry.apiS16.repository.IUserRepository;
import com.nocountry.apiS16.service.interfaces.IAnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswersService implements IAnswersService {

    private final IAnswersRepository iAnswersRepository;

    private final IUserRepository iUserRepository;

    private final ICommentsRepository iCommentsRepository;

    @Override
    public Answers addAnswers(String content, Long id_user, Long id_comments, LocalDate creationDate) throws ObjectNotFoundException {

        Optional<Users> users = this.iUserRepository.findById(id_user);
        Optional<Comments> comments = this.iCommentsRepository.findById(id_comments);

       if(users.isPresent() && comments.isPresent()){

           Users user = users.get();

           String completeName = user.getName() + " " + user.getLastName();

           Answers answers = Answers.builder()
                   .completeName(completeName)
                   .content(content)
                   .creationDate(creationDate)
                   .comments(comments.get())
                   .users(users.get())
                   .build();
           return this.iAnswersRepository.save(answers);
       }else {
           throw new ObjectNotFoundException("User or Comment doesnt found");
       }
    }

    @Override
    public List<Answers> getAnswers() {
        return this.iAnswersRepository.findAll();
    }

    @Override
    public Answers getAnswersById(Long id_answers) throws ObjectNotFoundException{
        Answers answers = this.iAnswersRepository.findById(id_answers).orElse(null);

        if (answers != null){
            return answers;
        }else {
            throw new ObjectNotFoundException("Answer with the id: " + id_answers + " doesnt found");
        }
    }

    @Override
    public Boolean deleteAnswers(Long id_answers) throws ObjectNotFoundException{
        Optional<Answers> answersOptional = this.iAnswersRepository.findById(id_answers);

        if (answersOptional.isPresent()){
            this.iAnswersRepository.deleteById(id_answers);
            return true;
        }else {
            throw new ObjectNotFoundException("Answer doesnt exist with the id: " + id_answers);
        }
    }
}
