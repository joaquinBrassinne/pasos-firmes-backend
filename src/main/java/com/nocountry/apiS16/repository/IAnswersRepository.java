package com.nocountry.apiS16.repository;

import com.nocountry.apiS16.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswersRepository extends JpaRepository<Answers, Long> {
}
