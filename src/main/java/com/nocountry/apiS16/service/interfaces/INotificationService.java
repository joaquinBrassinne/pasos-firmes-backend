package com.nocountry.apiS16.service.interfaces;

import java.util.List;

import com.nocountry.apiS16.dto.NotificationDTO;
import com.nocountry.apiS16.model.Notification;

public interface INotificationService {
    public Notification createNotification(NotificationDTO notificationDTO);
    public List<Notification> getUserNotifications(Long userId);

}
