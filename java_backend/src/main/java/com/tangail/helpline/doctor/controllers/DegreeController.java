package com.tangail.helpline.doctor.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangail.helpline.doctor.dto.SendDegreeDataDto;
import com.tangail.helpline.doctor.entity.Degree;
import com.tangail.helpline.doctor.services.DegreeService;

@RestController
@RequestMapping(path = "api/v1/degree")
public class DegreeController {
    private final DegreeService service;

    // @Autowired
    public DegreeController(DegreeService service) {
        this.service = service;
    }

    @GetMapping
    public SendDegreeDataDto getDegree() {
        SendDegreeDataDto department = new SendDegreeDataDto(service.getDegree());
        return department;
    }

    @PostMapping
    public Map<String, Object> addNewDegree(@RequestBody Degree degree) {
        Map<String, Object> result = service.addNewDegree(degree);
        return result;

    }

    @DeleteMapping(path = "{degreeId}")
    public void deleteDegree(@PathVariable("degreeId") Long id) {
        service.deleteDegree(id);
    }

    @PostMapping(path = "{degreeId}")
    public void updateDegree(@PathVariable("degreeId") Long id, @RequestBody Degree degree) {
        service.updateDegree(id, degree);
    }
}
