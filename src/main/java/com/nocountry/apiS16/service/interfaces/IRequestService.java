package com.nocountry.apiS16.service.interfaces;

import com.nocountry.apiS16.dto.RequestDTO;
import com.nocountry.apiS16.model.Request;

import java.util.List;

public interface IRequestService {

    Request saveRequest(Long productId, Long userId);
    List<Request> findByUserId(Long user_id);
    Request findByid(Long request_id);
    Boolean deleteRequest(Long id_user);
    void confirmRequest(Long id_request);
}
