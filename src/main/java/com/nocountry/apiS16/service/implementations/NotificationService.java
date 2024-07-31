package com.nocountry.apiS16.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nocountry.apiS16.dto.NotificationDTO;
import com.nocountry.apiS16.model.Notification;
import com.nocountry.apiS16.repository.INotificationRepository;
import com.nocountry.apiS16.service.interfaces.INotificationService;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public Notification createNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setRequesterId(notificationDTO.getId_requester());
        notification.setSellerId(notificationDTO.getId_seller());
        notification.setMessage(notificationDTO.getMessage());
        notification.setRead(false);

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findBySellerId(userId);
    }

}
