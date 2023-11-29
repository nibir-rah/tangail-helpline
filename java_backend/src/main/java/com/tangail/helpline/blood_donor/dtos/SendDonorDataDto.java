package com.tangail.helpline.blood_donor.dtos;

import java.util.List;

import com.tangail.helpline.blood_donor.entity.BloodDonor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class SendDonorDataDto {
    private List<BloodDonor> bloodDonors;
}
