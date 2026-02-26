package com.example.Service.ContectUs;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.SupportMessage.ContactRequest;
import com.example.Model.SupportMessage.ContactResponse;
import com.example.Model.SupportMessage.ContectUs;
import com.example.Service.ForgotPassword.ForgotPasswordService;
import com.example.enums.StatusOfContect;
import com.example.repo.ContectUs.ContectUsrepo;

@Service
public class ContectUsService{
	
	@Autowired
	private ContectUsrepo contectUsRepo;
	
	@Autowired
	private ForgotPasswordService service;
	
	public ContactResponse saveContectUs(ContactRequest request) {
		ContectUs body=new ContectUs();
		body.setEmail(request.getEmail());
		body.setMessage(request.getMessage());
		body.setName(request.getName());
		body.setSubject(request.getSubject());
		body.setStatus(StatusOfContect.PENDING);
		body.setCreatedAt(LocalDateTime.now());
		body=contectUsRepo.save(body);
		return bodyToMap(body);
		
		
	}
	
	private ContactResponse bodyToMap(ContectUs body) {

	    ContactResponse response = new ContactResponse();

	    response.setId(body.getId());
	    response.setName(body.getName());
	    response.setEmail(body.getEmail());
	    response.setSubject(body.getSubject());
	    response.setMessage(body.getMessage());
	    response.setStatus(body.getStatus().name());
	    response.setCreatedAt(body.getCreatedAt());

	    return response;
	}

	public List<ContactResponse> getMsg(String search) {

	    List<ContectUs> messages;

	    // If search is provided → search by name, email or subject
	    if (search != null && !search.trim().isEmpty()) {
	        messages = contectUsRepo
	                .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrSubjectContainingIgnoreCase(
	                        search, search, search).orElseThrow(()-> new RuntimeException("No Produt Found"));
	    } else {
	        messages = contectUsRepo.findAll();
	    }

	    // Convert Entity List → Response DTO List
	    return messages.stream().map(this::bodyToMap).toList();
	    
	}
	
	public ContactResponse updateStatus(Long id, String status) {

	    ContectUs message = contectUsRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Message not found"));

	    // Convert String → Enum
	    StatusOfContect newStatus = StatusOfContect.valueOf(status.toUpperCase());
	    
	    
	    message.setStatus(newStatus);
	    
	    
	    ContectUs updated = contectUsRepo.save(message);
	    service.sendSupportStatusUpdateEmail(message.getEmail(), message.getName(),message.getId(), status);
	    return bodyToMap(updated);
	}
	
}