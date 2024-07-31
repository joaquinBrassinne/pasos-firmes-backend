package com.nocountry.apiS16.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nocountry.apiS16.model.Notification;


public interface INotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findBySellerId(Long sellerId);
}
