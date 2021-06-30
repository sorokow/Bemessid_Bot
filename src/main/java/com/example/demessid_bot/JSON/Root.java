package com.example.demessid_bot.JSON;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {
    private String success;
    private String code;
    private Vin data;

//    {"success":true,"code":200,"data":{"vin":"Z9M2130875L001827"}}


    @Override
    public String toString() {
        return String.format("\n success: %s \n code: %s \n data: %s \n", success, code, data);
    }

}
