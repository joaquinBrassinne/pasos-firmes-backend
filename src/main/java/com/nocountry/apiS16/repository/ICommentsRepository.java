package com.nocountry.apiS16.repository;

import com.nocountry.apiS16.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentsRepository extends JpaRepository<Comments, Long> {
}
