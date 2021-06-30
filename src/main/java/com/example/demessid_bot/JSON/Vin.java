package com.example.demessid_bot.JSON;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vin {
    private String vin;

    public Vin() {
    }

    public Vin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return String.format("\n vin: %s", vin);
    }
}
