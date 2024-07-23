package com.nocountry.apiS16.service.interfaces;

import com.nocountry.apiS16.model.Comments;

import java.util.List;

public interface ICommentsService {

    public List<Comments> getComments();
    public Comments findComments(Long id_comments);
    public Comments saveComments (Long idUser,Long idProduct, String description);
    public Boolean deleteComments(Long id_comments);
}
