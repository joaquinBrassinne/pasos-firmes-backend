package com.nocountry.apiS16.service.interfaces;

import com.nocountry.apiS16.dto.RequestDTO;
import com.nocountry.apiS16.model.Request;

import java.util.List;

public interface IRequestService {

    public Request saveRequest(Long productId, Long userId);
    List<Request> findByUserId(Long user_id);
    public Request findByid(Long request_id);
    public Boolean deleteRequest(Long request_id);
    public void confirmRequest(Long id_request);
}
