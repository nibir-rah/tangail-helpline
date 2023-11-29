package com.tangail.helpline.doctor.dto;

import java.util.List;

import com.tangail.helpline.doctor.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class SendDeptDataDto {
    private List<Department> data;

}
