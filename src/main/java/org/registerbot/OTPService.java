package org.registerbot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OTPService {

    private final OTPRepository otpRepository;

    public OTP generateOTP(Long userID){
        OTP otp = new OTP();
        otp.setUserId(userID);
        otp.setCode(generateRandomOTP());
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        return otpRepository.save(otp);
    }

    private Long generateRandomOTP(){
        Random random = new Random();
        long leftLimit = 1_000_000L;
        long rightLimit = 1_000_000_000L;
        return leftLimit + Math.abs(random.nextLong(leftLimit, rightLimit));
    }
}
