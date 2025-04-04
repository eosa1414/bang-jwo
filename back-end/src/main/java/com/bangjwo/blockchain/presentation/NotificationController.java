package com.bangjwo.blockchain.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.bangjwo.blockchain.application.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/subscribe")
	public SseEmitter subscribe() {
		return notificationService.subscribe();
	}
}
