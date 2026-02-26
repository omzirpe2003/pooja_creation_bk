package com.example.Service.ForgotPassword;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.Model.Otp.OtpDetails;

@Service
public class OtpStorageService{
	private final ConcurrentHashMap<String, OtpDetails> otpMap = new ConcurrentHashMap<>();
	
	public void storeOtp(String email, String otp) {
		long expiry=System.currentTimeMillis() + 7 *60*1000;
		otpMap.put(email, new OtpDetails(otp,expiry));
	}
	
	public boolean isValidOtp(String email,String userOtp) {
		OtpDetails details=otpMap.get(email);
		if(details==null || System.currentTimeMillis()>details.getExpiryTime()) {
			otpMap.remove(email);
			return false;
		}		
		return details.getOtp().equals(userOtp);
	}
	
	public void removeOtp(String email) {
        otpMap.remove(email);
    }
	
	

}