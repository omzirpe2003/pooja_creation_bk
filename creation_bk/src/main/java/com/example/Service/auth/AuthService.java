
package com.example.Service.auth;
import com.example.ExeptionHandel.*;
import com.example.Model.authmodel.AuthResponseDto;
import com.example.Model.authmodel.UserModel;
import com.example.Model.authmodel.UserRequestDto;
import com.example.Model.authmodel.UserResponseDto;
import com.example.Model.authmodel.logIn.LogInRequestDto;
import com.example.Model.response.ApiResponse;
import com.example.Model.roles.Roles;
import com.example.Service.jwtservcei.JwtTokenService;
import com.example.repo.role.RoleRepository;
import com.example.repo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtTokenService jwtService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private RoleRepository roleRepository;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
    );

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    private static final Pattern INDIAN_MOBILE_PATTERN = Pattern.compile(
            "^(\\+91|0)?[6789]\\d{9}$"
    );

    public AuthResponseDto register(UserRequestDto request) {

        // Password match
        if (!request.getPassword().equals(request.getConfoPassword())) {
            throw new PasswordMismatchException("Passwords do not match. Please check and try again.");
        }

        // Input validation
        if (!EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
            throw new InvalidInputException("Please enter a valid email address (example: name@example.com)");
        }

        if (!PASSWORD_PATTERN.matcher(request.getPassword()).matches()) {
            throw new InvalidInputException(
                    "Password must be at least 8 characters long and include:\n" +
                    "• At least one uppercase letter\n" +
                    "• At least one lowercase letter\n" +
                    "• At least one number\n" +
                    "• At least one special character (@ $ ! % * ? &)"
            );
        }

        if (request.getMobileNo() != null && !request.getMobileNo().isBlank() &&
                !INDIAN_MOBILE_PATTERN.matcher(request.getMobileNo()).matches()) {
            throw new InvalidInputException(
                    "Please enter a valid 10-digit Indian mobile number.\n" +
                    "Examples: 9876543210, +919876543210, 09876543210"
            );
        }

        // Uniqueness checks
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("The username '" + request.getUsername() + "' is already taken. Please choose another.");
        }

        if (userRepository.existsByEmailId(request.getEmail())) {
            throw new UserAlreadyExistsException("This email address is already registered. Please use a different email or login.");
        }

        if (request.getMobileNo() != null && !request.getMobileNo().isBlank() &&
                userRepository.existsByMobileNo(request.getMobileNo())) {
            throw new UserAlreadyExistsException("This mobile number is already in use. Please use a different number.");
        }

        // Fetch role
        Roles userRole = roleRepository.findByRoleType("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Default user role 'USER' not found in the system. Contact support."));

        // Create user
        UserModel user = new UserModel();
        user.setFullName(request.getFullName());
        user.setEmailId(request.getEmail());
        user.setMobileNo(request.getMobileNo());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(userRole);

        user = userRepository.save(user);

        // Generate token & prepare response
        String token = jwtService.generateToken(user.getUsername());
        System.out.println("Strore In database "+user.getEmailId());
        System.out.println("Strore In database "+user.getUsername());
        AuthResponseDto authData = new AuthResponseDto(
                token,
                user.getUsername(),
                user.getRole().getRoleType()   // assuming getRoleType() returns "USER"
        );

        return authData;
    }

    public AuthResponseDto login(LogInRequestDto request) {

        try {
        	System.out.println("In Try 1");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            System.out.println("In Try 2");
            UserModel user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "No account found with username: " + request.getUsername()));
            System.out.println("In Try 3");
            String token = jwtService.generateToken(user.getUsername());
            System.out.println("In Try 4");
            AuthResponseDto authData = new AuthResponseDto(
                    token,
                    user.getUsername(),
                    user.getRole().getRoleType()
            );
            System.out.println("In Try 5");
            return authData;

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Incorrect username or password. Please try again.");
        }
    }

	public UserResponseDto getUserData(Long id) {
		UserModel user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
			
		return mapToDto(user);
	}
	
	private UserResponseDto mapToDto(UserModel user) {
		return new UserResponseDto(
				user.getId(),
	            user.getFullName(),
	            user.getMobileNo(),
	            user.getUsername(),
	            user.getEmailId(),
	            user.getRole().getRoleType(),  // assuming role has getRoleName()
	            user.getCreatedAt(),
	            user.getUpdatedAt()
		);
	}
	
	
}