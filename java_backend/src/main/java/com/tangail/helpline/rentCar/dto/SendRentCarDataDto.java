package com.tangail.helpline.rentCar.dto;

import java.util.List;

import com.tangail.helpline.rentCar.entity.RentCar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class SendRentCarDataDto {
    private List<RentCar> listResponse;

}
