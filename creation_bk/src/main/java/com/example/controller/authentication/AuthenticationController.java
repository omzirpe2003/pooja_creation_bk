package com.example.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.http5.api.Response;
import com.example.Model.ForgotPassword.ForgotPasswordRequest;
import com.example.Model.ForgotPassword.ResetPasswordRequest;
import com.example.Model.ForgotPassword.VerifyOtpRequest;
import com.example.Model.authmodel.AuthResponseDto;
import com.example.Model.authmodel.UserModel;
import com.example.Model.authmodel.UserPrincipal;
import com.example.Model.authmodel.UserRequestDto;
import com.example.Model.authmodel.UserResponseDto;
import com.example.Model.authmodel.logIn.LogInRequestDto;
import com.example.Model.response.ApiResponse;
import com.example.Service.ForgotPassword.ForgotPasswordService;
import com.example.Service.auth.AuthService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user/auth/")
public class AuthenticationController {   // fixed spelling

    private final AuthService authService;
    
    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody UserRequestDto request) {
        System.out.println(request.toString());
    	AuthResponseDto response = authService.register(request);
        ApiResponse body=new ApiResponse(true,"New user Register",response,null);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LogInRequestDto request) {
        AuthResponseDto response = authService.login(request);
        ApiResponse body=new ApiResponse(true,"Log in successfulley",response,null);
        return ResponseEntity.ok(body);
    }
    
    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        forgotPasswordService.sendOtp(request.email());
        return ResponseEntity.ok("OTP sent to your email");
    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequest request) {
        // Standardizing logs
        System.out.println("Verification attempt for: " + request.email());
        
        boolean isValid = forgotPasswordService.verifyOtp(request.otp(), request.email());
        System.out.println("Varity: "+isValid);
        if (isValid) {
            // Return 200 OK - This triggers the 'try' block in Flutter
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            // Return 400 Bad Request - This triggers the 'catch' block or error handling in Flutter
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP");
        }
    }
    
    @PostMapping("/forgot-username")
    public ResponseEntity<String> forgotUsername(@RequestBody ForgotPasswordRequest request) {
    	forgotPasswordService.forgostUsername(request.email());
    	return ResponseEntity.ok("Username sent to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        forgotPasswordService.resetPassword(request.email(), request.otp(), request.newPassword());
        return ResponseEntity.ok("Password reset successfully");
    }
    
    @GetMapping("/getUserData")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@AuthenticationPrincipal UserPrincipal  user){
    	UserResponseDto reponse =authService.getUserData(user.getId());
    	ApiResponse<UserResponseDto> body=new ApiResponse<UserResponseDto>(true,"Get User data",reponse,null);
    	return ResponseEntity.ok(body);
    }
    
    
    
}
