package com.example.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.ForgotPassword.ForgotPasswordRequest;
import com.example.Model.ForgotPassword.ResetPasswordRequest;
import com.example.Model.authmodel.UserPrincipal;
import com.example.Model.orders.CreateOrderRequest;
import com.example.Model.orders.OrderEmailRequest;
import com.example.Model.orders.OrderResponse;
import com.example.Model.response.ApiResponse;
import com.example.Service.ForgotPassword.ForgotPasswordService;
import com.example.Service.Order.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController{
	
	@Autowired
	public OrderService orderService;
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	@PostMapping("create")
	private ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody CreateOrderRequest request){
		OrderResponse response=orderService.createOrder(request);
		ApiResponse<OrderResponse> body=new ApiResponse<OrderResponse>(true,"Order product Succesfullry",response,null);
		return ResponseEntity.ok(body);
	
	}
	
	@PostMapping("/createe")
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody CreateOrderRequest request,
            @AuthenticationPrincipal UserPrincipal authentication) {

        String username = authentication.getUsername();

        OrderResponse response = orderService.createOrder(request, username);

        return ResponseEntity.ok(response);
    }
	
	@PutMapping("/change/{orderId}/status")
	public ResponseEntity<ApiResponse<OrderResponse>> updateOrderStatus(
	        @PathVariable String orderId,
	        @RequestParam String status) {
	    
	    // Call the service to update the status in the database
	    OrderResponse updatedOrder = orderService.updateOrderStatus(orderId, status);
	    
	    ApiResponse<OrderResponse> response = new ApiResponse<>(
	            true, 
	            "Order status updated to " + status, 
	            updatedOrder, 
	            null
	    );
	    
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("getByOrderNumber")
	public ResponseEntity<ApiResponse<OrderResponse>>  getOrderByIdName(@RequestParam(required = true) String orderNumber ){
		OrderResponse response=orderService.getById(orderNumber);
		ApiResponse<OrderResponse> body=new ApiResponse<OrderResponse>(true,"Order product Succesfullry",response,null);
		
		return ResponseEntity.ok(body);
	}
	
	
	
	@GetMapping("/all/getOrders")
	public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders(){
		List<OrderResponse> response = orderService.getAllOrders();
		ApiResponse<List<OrderResponse>> body=new ApiResponse<List<OrderResponse>>(true,"All Orders",response,null);
		return ResponseEntity.ok(body);
		
	}
	
	@GetMapping("/getOrder")
	public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrderByUser(@AuthenticationPrincipal UserPrincipal user){
		List<OrderResponse> response =orderService.getOrderByUserId(user.getId());
		ApiResponse<List<OrderResponse>> body=new ApiResponse<List<OrderResponse>>(true,"All Orders",response,null);
		return ResponseEntity.ok(body);
	}
	
	@PostMapping("/sendMsgOnEmail")
    public ResponseEntity<String> resetPassword(@RequestBody OrderEmailRequest request, @AuthenticationPrincipal UserPrincipal user) {
        forgotPasswordService.orderPlace(user.getUsername(),request.getOrderId());
        return ResponseEntity.ok("Password reset successfully");
    }
	
}