package com.tangail.helpline.ambulance.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EditAmbulanceDto {
    private String name;
    private String contact;
    private String address;
}
