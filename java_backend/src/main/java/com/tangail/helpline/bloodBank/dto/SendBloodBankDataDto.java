package com.tangail.helpline.bloodBank.dto;

import java.util.List;

import com.tangail.helpline.bloodBank.entity.BloodBank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class SendBloodBankDataDto {
    private List<BloodBank> listResponse;

}
