 package com.example.controller.SupportMessage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.SupportMessage.ContactRequest;
import com.example.Model.SupportMessage.ContactResponse;
import com.example.Model.response.ApiResponse;
import com.example.Service.ContectUs.ContectUsService;
import com.example.repo.ContectUs.ContectUsrepo;

@RestController
@RequestMapping("/api/contectUs/")
 public class ContectUs{
	@Autowired
    private ContectUsService contectusService;
	 
	@PostMapping("/send")
    public ResponseEntity<ApiResponse<ContactResponse>> sendMessage(@RequestBody ContactRequest request) {
		ContactResponse response=contectusService.saveContectUs(request);
		ApiResponse<ContactResponse> body=new ApiResponse<ContactResponse>(true,"ContectUs request Send",response,null);
		return ResponseEntity.ok(body);
	}
	
	@GetMapping("/messages")
    public ResponseEntity<ApiResponse<List<ContactResponse>>> getAllMessages(
            @RequestParam(required = false) String search) {
        List<ContactResponse> response= contectusService.getMsg(search);
        ApiResponse<List<ContactResponse>> body=new ApiResponse<List<ContactResponse>>(true,"All Msg",response,null);
        return ResponseEntity.ok(body);
	}
	
	@PutMapping("/change_status/{id}")
	public ResponseEntity<ApiResponse<ContactResponse>> changeStatus(
	        @PathVariable Long id,
	        @RequestParam String status) {

	    ContactResponse response = contectusService.updateStatus(id, status);

	    ApiResponse<ContactResponse> body =
	            new ApiResponse<>(true, "Status Updated Successfully", response, null);

	    return ResponseEntity.ok(body);
	}
	
 }