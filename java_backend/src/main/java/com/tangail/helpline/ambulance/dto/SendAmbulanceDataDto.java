package com.tangail.helpline.ambulance.dto;

import java.util.List;

import com.tangail.helpline.ambulance.entity.Ambulance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class SendAmbulanceDataDto {
    private List<Ambulance> listResponse;

}
