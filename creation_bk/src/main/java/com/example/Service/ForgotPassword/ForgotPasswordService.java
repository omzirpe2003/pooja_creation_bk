package com.example.Service.ForgotPassword;

import java.math.BigDecimal;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ExeptionHandel.OrderNotFoundException;
import com.example.Model.authmodel.UserModel;
import com.example.Model.orders.Order;
import com.example.repo.Order.OrderRepository;
import com.example.repo.user.UserRepository;

@Service
public class ForgotPasswordService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private OtpStorageService otpStorageService;
    
    @Autowired
    private OrderRepository orderRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private static final SecureRandom random = new SecureRandom();
    
    // Official Business Contact
    private final String OFFICIAL_MOBILE = "8677097777";
    private final String INSTA_URL = "https://www.instagram.com/pooja_creations_official";

    // 1. SEND OTP FOR PASSWORD RESET
    public void sendOtp(String email) {
        UserModel user = userRepo.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("Email Id not Found"));
        
        String otp = String.format("%06d", random.nextInt(1_000_000));
        otpStorageService.storeOtp(user.getEmailId(), otp);
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmailId());
        message.setSubject("🔐 Security: OTP for Pooja Creations");
        message.setText(
            "Hello " + user.getFullName() + ",\n\n" +
            "Your verification code for password reset is: " + otp + "\n\n" +
            "This code expires in 5 minutes. For security, do not share this OTP.\n\n" +
            "Need assistance? Contact us at: " + OFFICIAL_MOBILE + "\n\n" +
            "Regards,\nTeam Pooja Creations"
        );
        mailSender.send(message);
    }
    
    

    // 2. ORDER PLACED NOTIFICATION (TRIGGERED FROM FLUTTER CHECKOUT)
    public void orderPlace(String username, Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        String orderNumber = order.getOrderNumber();
        BigDecimal totalPrice = order.getTotalAmount();

        UserModel user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        sendOrderConfirmationEmail(user.getEmailId(), orderNumber, totalPrice);
    }
    
 // SUPPORT MESSAGE STATUS UPDATE NOTIFICATION
 // SUPPORT MESSAGE STATUS UPDATE NOTIFICATION WITH NAME
    public void sendSupportStatusUpdateEmail(String email,
                                             String customerName,
                                             Long messageId,
                                             String newStatus) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("📩 Support Request Update - Status: " + newStatus.toUpperCase());

        String statusDetail;

        switch (newStatus.toUpperCase()) {

            case "PENDING":
                statusDetail = "🕒 Your support request is currently pending. "
                             + "Our team will contact you shortly.";
                break;

            case "CALL_DONE":
                statusDetail = "📞 Our team has successfully contacted you regarding your request.";
                break;

            case "DONE":
                statusDetail = "✅ Your support request has been resolved successfully.";
                break;

            default:
                statusDetail = "🔄 Your support request status has been updated.";
        }

        String content = "Hello " + customerName + ",\n\n" +
                         "Your support request status has been updated.\n\n" +
                         "Request ID: #" + messageId + "\n" +
                         "New Status: " + newStatus.toUpperCase() + "\n" +
                         "------------------------------------------\n" +
                         statusDetail + "\n" +
                         "------------------------------------------\n\n" +
                         "📞 Contact Us: " + OFFICIAL_MOBILE + "\n\n" +
                         "Regards,\nTeam Pooja Creations";

        message.setText(content);
        mailSender.send(message);
    }
    private void sendOrderConfirmationEmail(String email, String orderNo, BigDecimal price) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("🛍️ Order Confirmed! - Pooja Creations");
        
        String content = "Hello,\n\n" +
                         "Thank you for your order! We are excited to process it.\n\n" +
                         "✨ ORDER SUMMARY ✨\n" +
                         "Order ID: " + orderNo + "\n" +
                         "Total Amount: ₹" + price + "\n" +
                         "------------------------------------------\n\n" +
                         "💳 PAYMENT INFO:\n" +
                         "Our team will contact you shortly on your mobile to provide payment options and confirm your order.\n\n" +
                         "📱 Official Support: " + OFFICIAL_MOBILE + " (Call/WhatsApp)\n\n" +
                         "📸 New Designs: " + INSTA_URL + "\n\n" +
                         "Thank you for shopping with us!";
        
        message.setText(content);
        mailSender.send(message);
    }

    // 3. ORDER STATUS UPDATE NOTIFICATION
    public void sendStatusUpdateEmail(String email, Long orderNo, String newStatus) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("🔔 Order Update: #" + orderNo + " is " + newStatus.toUpperCase());

        String statusDetail = getStatusDescription(newStatus);

        String content = "Dear Customer,\n\n" +
                         "Your order status has been updated.\n\n" +
                         "Order Number: #" + orderNo + "\n" +
                         "New Status: " + newStatus.toUpperCase() + "\n" +
                         "------------------------------------------\n" +
                         statusDetail + "\n" +
                         "------------------------------------------\n\n" +
                         "📞 Contact Us: " + OFFICIAL_MOBILE + " (WhatsApp available)\n\n" +
                         "📸 Follow us: " + INSTA_URL + "\n\n" +
                         "Best Regards,\nTeam Pooja Creations";

        message.setText(content);
        mailSender.send(message);
    }

    private String getStatusDescription(String status) {
        switch (status.toUpperCase()) {
            case "PAID": return "✅ Payment received! We are preparing your items.";
            case "SHIPPED": return "🚚 Your order has been dispatched and is on its way!";
            case "DELIVERED": return "🎉 Order delivered! We hope you love it.";
            case "CANCELLED": return "⚠️ Order cancelled. Contact us at " + OFFICIAL_MOBILE + " for details.";
            default: return "🔄 We are currently processing your order.";
        }
    }

    // 4. FORGOT USERNAME
    public void forgostUsername(String email) {
        UserModel user = userRepo.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("Email Id not Found"));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmailId());
        message.setSubject("🔐 Account Recovery: Your Username");
        message.setText(
            "Hello " + user.getFullName() + ",\n\n" +
            "Your username is: " + user.getUsername() + "\n\n" +
            "Support Contact: " + OFFICIAL_MOBILE + "\n\n" +
            "Regards,\nTeam Pooja Creations"
        );
        mailSender.send(message);
    }

    // 5. OTP VERIFICATION & PASSWORD RESET
    public boolean verifyOtp(String otp, String email) {
        return otpStorageService.isValidOtp(email, otp);
    }

    public void resetPassword(String email, String otp, String newPassword) {
        if(!otpStorageService.isValidOtp(email, otp)) {
            throw new RuntimeException("Invalid or expired OTP");
        }
        UserModel user = userRepo.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setConfirmPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        otpStorageService.removeOtp(email);
    }
}