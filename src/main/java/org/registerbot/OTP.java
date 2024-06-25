package org.registerbot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "otp")
public class OTP {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "otp")
    private Long code;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OTP otp = (OTP) object;
        return Objects.equals(id, otp.id) && Objects.equals(code, otp.code) && Objects.equals(userId, otp.userId) && Objects.equals(name, otp.name) && Objects.equals(expiryTime, otp.expiryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, userId, name, expiryTime);
    }

    @Override
    public String toString() {
        return "OTP{" +
                "code=" + code +
                ", id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", expiryTime=" + expiryTime +
                '}';
    }
}