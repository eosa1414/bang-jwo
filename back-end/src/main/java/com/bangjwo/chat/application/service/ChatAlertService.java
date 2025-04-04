package com.bangjwo.chat.application.service;

import java.util.List;

import com.bangjwo.chat.domain.entity.ChatAlert;

public interface ChatAlertService {

	void saveAlert(ChatAlert dto);

	void markAlertAsRead(Long chatRoomId, Long userId);

	List<ChatAlert> getAlerts(Long userId);
}
