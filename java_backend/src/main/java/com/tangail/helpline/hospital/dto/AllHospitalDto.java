package com.tangail.helpline.hospital.dto;

import java.util.List;

import com.tangail.helpline.hospital.entity.Hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class AllHospitalDto {
    private List<Hospital> hospitals;
}
