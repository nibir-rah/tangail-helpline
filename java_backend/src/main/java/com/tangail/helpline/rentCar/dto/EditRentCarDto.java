package com.tangail.helpline.rentCar.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EditRentCarDto {
    private String name;
    private String contact;
    private String address;
}
