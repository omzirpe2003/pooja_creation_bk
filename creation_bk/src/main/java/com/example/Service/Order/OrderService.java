package com.example.Service.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ExeptionHandel.ProductNotFoundException;
import com.example.Model.authmodel.UserModel;
import com.example.Model.orders.CreateOrderRequest;
import com.example.Model.orders.Order;
import com.example.Model.orders.OrderItem;
import com.example.Model.orders.OrderItemRequest;
import com.example.Model.orders.OrderItemResponse;
import com.example.Model.orders.OrderResponse;
import com.example.Model.products.Products;
import com.example.Service.ForgotPassword.ForgotPasswordService;
import com.example.enums.Orders.OrderStatus;
import com.example.repo.Order.OrderRepository;
import com.example.repo.product.ProductRepo;
import com.example.repo.user.UserRepository;

@Service
public class OrderService {
		
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private ProductRepo productRepository;
    @Autowired
	private OrderRepository orderRepository;
	
    @Autowired
    private ForgotPasswordService forgotPasswordService;
	
	public OrderResponse createOrder(CreateOrderRequest request) {
		Order order=new Order();
		order.setOrderNumber(generateOrderNumber());
		order.setStatus(OrderStatus.CREATED);
		BigDecimal orderTotal = BigDecimal.ZERO;
		
		
		
		 for (OrderItemRequest req : request.getItems()) {

	            Products product = productRepository.findById(req.getProductId())
	                    .orElseThrow(() -> new RuntimeException("Product not found"));

	            if (!product.isAvailable()) {
	                throw new RuntimeException("Insufficient stock for " + product.getProductName());
	            }

	            OrderItem item = new OrderItem();
	            item.setOrder(order);
	            item.setProduct(product);
	            item.setQuantity(req.getQuantity());
	            item.setUnitPrice(product.getPrice());
	            

	            BigDecimal itemTotal =
	                    product.getPrice().multiply(BigDecimal.valueOf(req.getQuantity()));

	            item.setTotalPrice(itemTotal);
	            orderTotal = orderTotal.add(itemTotal);

	            // reduce stock
	            //product.setStock(product.getStock() - req.getQuantity());

	            order.getItems().add(item);
	        }
		 order.setTotalAmount(orderTotal);
		
		 Order savedOrder = orderRepository.save(order);
	     return mapToResponse(savedOrder);
		
	
		
	}
	
	public OrderResponse updateOrderStatus(String orderId, String status) {
	    // 1. Find the existing order
	    Order order = orderRepository.findByOrderNumber(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

	    try {
	        // 2. Convert String to Enum
	        // .toUpperCase() ensures it matches even if the mobile app sends "paid" instead of "PAID"
	        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
	        
	        // 3. Set the Enum to the entity
	        order.setStatus(orderStatus);
	    } catch (IllegalArgumentException e) {
	        throw new RuntimeException("Invalid status: " + status + ". Please use valid OrderStatus values.");
	    }

	    // 4. Save and return
	    Order savedOrder = orderRepository.save(order);
	    forgotPasswordService.sendStatusUpdateEmail(order.getUser().getEmailId(),order.getId(),status);
	    return mapToResponse(savedOrder);
	}
	
	public OrderResponse createOrder(CreateOrderRequest request, String username) {

        // 1️⃣ Get Logged In User
        UserModel user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Create Order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setOrderNumber(UUID.randomUUID().toString());

        BigDecimal totalAmount = BigDecimal.ZERO;

        // 3️⃣ Loop Items
        for (OrderItemRequest itemRequest : request.getItems()) {

            Products product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found :"+itemRequest.getProductId() ));

            BigDecimal unitPrice = product.getPrice();
            BigDecimal totalPrice =unitPrice.multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalAmount = totalAmount.add(totalPrice);

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setUnitPrice(unitPrice);
            orderItem.setTotalPrice(totalPrice);

            // 🔥 IMPORTANT (Bidirectional)
            order.addOrderItem(orderItem);
        }

        // 4️⃣ Set Total
        order.setTotalAmount(totalAmount);

        // 5️⃣ Save Order (Items auto saved because of Cascade)
        order = orderRepository.save(order);

        return mapToResponse(order);
    }
		
	private OrderResponse mapToResponse(Order order) {
		OrderResponse obj=new OrderResponse();
		obj.setTotalAmount(order.getTotalAmount());
		obj.setCreatedAt(order.getCreatedAt());
		obj.setOrderId(order.getId());
		obj.setOrderNumber(order.getOrderNumber());
		obj.setStatus(order.getStatus());
		obj.setUserId(order.getUser().getId());
		obj.setUserName(order.getUser().getUsername());
		obj.setEmailId(order.getUser().getEmailId());
		obj.setMobileNo(order.getUser().getMobileNo());
		List<OrderItemResponse> itemResponses = new ArrayList<>();
		for(OrderItem item : order.getItems()) {
			OrderItemResponse itemResponse =new OrderItemResponse();
			itemResponse.setProductId(item.getProduct().getId());
			itemResponse.setProductName(item.getProduct().getProductName());
			itemResponse.setQuantity(item.getQuantity());
			itemResponse.setTotalPrice(item.getTotalPrice());
			itemResponse.setUnitPrice(item.getUnitPrice());
			itemResponses.add(itemResponse);
			itemResponse.setCategory(item.getProduct().getCategoryName());
			itemResponse.setCategoryId(item.getProduct().getCategoryId());
		}
		obj.setItems(itemResponses);
		
		return obj;
		
		
		
	}
	
	
	 private String generateOrderNumber() {
	        return "ORD-" + System.currentTimeMillis();
	    }
	 public OrderResponse getById(String id) {
		 Order obj=orderRepository.findByOrderNumber(id).orElseThrow(()->new RuntimeException("No Order found on this id: "+id));
		return mapToResponse(obj);
		 
	 }
	 public List<OrderResponse> getAllOrders() {
		 return orderRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	 }
	 public List<OrderResponse> getOrderByUserId(Long id) {
		List<Order> orders=orderRepository.findByUserId(id).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		return orders.stream().map(this::mapToResponse).toList();
		
	 }
	
	
}