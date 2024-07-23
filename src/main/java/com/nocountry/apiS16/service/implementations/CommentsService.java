package com.nocountry.apiS16.service.implementations;

import com.nocountry.apiS16.model.Comments;
import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.repository.ICommentsRepository;
import com.nocountry.apiS16.repository.IProductRepository;
import com.nocountry.apiS16.repository.IUserRepository;
import com.nocountry.apiS16.service.interfaces.ICommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentsService implements ICommentsService {

    private final ICommentsRepository commentsRepository;

    private final IProductRepository productRepository;

    private final IUserRepository userRepository;

    @Override
    public List<Comments> getComments() {
        return this.commentsRepository.findAll();
    }

    @Override
    public Comments findComments(Long id_comments) {
        return this.commentsRepository.findById(id_comments)
                .orElseThrow(()-> new RuntimeException("Comments with that id doesnt exist"));
    }

    @Override
    public Comments saveComments(Long idUser,Long idProduct, String description) {
        Optional<Users> users = this.userRepository.findById(idUser);

        Optional<Product> products = this.productRepository.findById(idProduct);

        if (users.isPresent() && products.isPresent()){
            Comments comments = Comments.builder()
                    .description(description)
                    .creationDate(LocalDate.now())
                    .user(users.get())
                    .product(products.get())
                    .build();
            return this.commentsRepository.save(comments);

        }else {
            throw  new RuntimeException("User or Product doesnt found");
        }
    }

    @Override
    public Boolean deleteComments(Long id_comments)  {
        Comments commentDeleted = this.findComments(id_comments);

        if (commentDeleted != null){
            this.commentsRepository.delete(commentDeleted);
            return true;
        }
        else {
            throw new RuntimeException("Comment with that id doesnt exist");
        }
    }
}
