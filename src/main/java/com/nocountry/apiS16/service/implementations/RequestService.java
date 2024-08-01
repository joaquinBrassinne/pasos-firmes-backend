package com.nocountry.apiS16.service.implementations;

import com.nocountry.apiS16.dto.RequestDTO;
import com.nocountry.apiS16.exceptions.ObjectNotFoundException;
import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.model.Request;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.repository.IProductRepository;
import com.nocountry.apiS16.repository.IRequestRepository;
import com.nocountry.apiS16.repository.IUserRepository;
import com.nocountry.apiS16.service.interfaces.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService implements IRequestService {

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Request saveRequest(Long productId, Long userId) throws ObjectNotFoundException {
        Optional<Users> usersOptional = this.userRepository.findById(userId);
        Optional<Product> productOptional = this.productRepository.findById(productId);


        if (!usersOptional.isPresent() || !productOptional.isPresent()) {
            throw new ObjectNotFoundException("User or product dont found");
        }


        if (productOptional.get().getUsers().getId_user().equals(userId)) {
            throw new RuntimeException("User cannot request their own product");
        }


        String completeName = usersOptional.get().getName();

        Request newRequest = Request.builder()
                .requestDay(LocalDate.now())
                .requestCompleted(false)
                .name(completeName)
                .users(usersOptional.get())
                .product(productOptional.get())
                .build();

        return this.requestRepository.save(newRequest);


    }

    @Override
    public List<Request> findByUserId(Long id_user) {
        return this.requestRepository.findByUserId(id_user);
    }

    @Override
    public Request findByid(Long request_id) throws ObjectNotFoundException{

        Request requestOptional = this.requestRepository.findById(request_id).orElse(null);

        if (requestOptional != null){
            return requestOptional;
        }else{
            throw new ObjectNotFoundException("I dont found the request");
        }


    }

    @Override
    public Boolean deleteRequest(Long request_id) {
        Request request = this.findByid(request_id);

        if(request != null){
            this.requestRepository.delete(request);
            return true;
        }else {
            throw new ObjectNotFoundException("Request with the id: " + request_id + " doesnt exist");
        }

    }

    @Override
    public void confirmRequest(Long id_request) {

        Request requestComplete = this.findByid(id_request);

        requestComplete.setRequestCompleted(true);
        this.requestRepository.save(requestComplete);

        Product product = requestComplete.getProduct();
        this.productRepository.delete(product);

    }
}
