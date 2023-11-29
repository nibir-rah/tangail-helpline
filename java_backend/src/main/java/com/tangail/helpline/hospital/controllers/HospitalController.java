package com.tangail.helpline.hospital.controllers;

import com.tangail.helpline.hospital.entity.Hospital;
import com.tangail.helpline.hospital.services.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangail.helpline.helpers.ResponseHandler;
import com.tangail.helpline.helpers.SuccessMessageModel;
import com.tangail.helpline.hospital.dto.AllHospitalDto;
import com.tangail.helpline.hospital.dto.EditHospitalDto;
import com.tangail.helpline.hospital.dto.HospitalDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/hospitals")
public class HospitalController {
    private final HospitalService service;

    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getHospitals() {
        AllHospitalDto department = new AllHospitalDto(service.getHospitals());
        return ResponseHandler.generateResponse(HttpStatus.OK, true,
                "Here are the hospitals",
                department);
    }

    @GetMapping("/{type}")
    public ResponseEntity<Object> getHospitalsByType(@PathVariable("type") String type) {
        AllHospitalDto department = new AllHospitalDto(service.getHospitalsByType(type));
        return ResponseHandler.generateResponse(HttpStatus.OK, true,
                "Here are the hospitals",
                department);
    }

    @PostMapping
    public ResponseEntity<Object> addNewHospital(@RequestBody @Valid HospitalDto hospital) {
        if (service.addNewHospital(hospital)) {
            return ResponseHandler.generateResponse(HttpStatus.CREATED, true,
                    "Hospital added successfully",
                    new SuccessMessageModel("Successfully added an Hospital", true));
        }
        return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false,
                "Hospital exists already",
                new SuccessMessageModel("Hospital exists already", false));
    }

    @DeleteMapping(path = "{hospitalId}")
    public void deleteHospital(@PathVariable("hospitalId") Long id) {
        service.deleteHospital(id);
    }

    @PostMapping(path = "/update/{hospitalId}")
    public ResponseEntity<Object> updateHospital(@PathVariable("hospitalId") Long id,
            @RequestBody EditHospitalDto hospital) {
        Hospital isOkay = service.updateHospital(id, hospital);
        if (isOkay != null) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true,
                    "Hospital updated successfully",
                    isOkay);
        }
        return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false,
                "Hospital already exists",
                null);
    }

}
