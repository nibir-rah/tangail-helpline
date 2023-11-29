package com.tangail.helpline.lawyer.dto;

import java.util.List;

import com.tangail.helpline.lawyer.entity.LawyerPracticeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class SendPracticeTypeDataDto {
    private List<LawyerPracticeType> listResponse;

}
