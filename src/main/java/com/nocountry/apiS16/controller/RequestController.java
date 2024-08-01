package com.nocountry.apiS16.controller;

import com.nocountry.apiS16.dto.RequestDTO;
import com.nocountry.apiS16.exceptions.ObjectNotFoundException;
import com.nocountry.apiS16.model.Request;
import com.nocountry.apiS16.service.interfaces.IRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/request")
public class RequestController {

    private final IRequestService requestService;

    @PostMapping("/product/{productId}/user/{userId}")
    public ResponseEntity<?> createRequest(@PathVariable Long productId, @PathVariable Long userId) {

        try {
            Request newRequest = requestService.saveRequest(productId, userId);
            return ResponseEntity.ok(newRequest);
        } catch (ObjectNotFoundException  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/confirm-delivery/{requestId}")
    public ResponseEntity<String> confirmDelivery(@PathVariable Long requestId) {
        requestService.confirmRequest(requestId);
        return ResponseEntity.ok("Delivery confirmed and product deleted successfully.");
    }


    @GetMapping("/user/{id_user}")
    public ResponseEntity<List<Request>> requestUsers(@PathVariable Long id_user){
        List<Request> requestList = this.requestService.findByUserId(id_user);

        if (!requestList.isEmpty()){
            return new ResponseEntity<>(requestList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id_request}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id_request){
        Boolean request = this.requestService.deleteRequest(id_request);

        if(request){
            return new ResponseEntity<>("Request deleted!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
