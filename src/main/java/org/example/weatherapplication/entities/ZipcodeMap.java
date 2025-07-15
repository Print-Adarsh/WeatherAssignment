package org.example.weatherapplication.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ZipcodeMap {
    @Id
    private String pincode;
    private LocalDateTime updatedAt;
    private double lat;
    private double lon;

    public void setPincode(String pincode) {
    }

    public void setUpdatedAt(LocalDateTime now) {
    }

    public double getLon() {
    }
}
