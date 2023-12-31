package com.tangail.helpline.doctor.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import com.tangail.helpline.doctor.entity.*;
import com.tangail.helpline.doctor.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tangail.helpline.doctor.dto.DoctorDto;
import com.tangail.helpline.doctor.dto.EditDoctorDto;
import com.tangail.helpline.doctor.entity.*;
import com.tangail.helpline.doctor.repositories.*;
import com.tangail.helpline.helpers.AppConstant;
import com.tangail.helpline.helpers.DebugHelper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final SymptomRepository symptomRepository;
    private final DegreeRepository degreeRepository;
    private final ImageDoctorRepository imageRepository;

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getDoctorsbyDept(Long id) {
        return doctorRepository.findByDeptId(id);
    }

    public List<Doctor> getDoctorsbySymptom(Long id) {
        return doctorRepository.findBySymptomId(id);
    }

    public List<Doctor> getDoctorsbyHomeService() {
        return doctorRepository.findByHomeService(true);
    }

    public DoctorImage updateImageFromFileSystem(MultipartFile file, String id) throws IOException {

        Optional<DoctorImage> savedFile = imageRepository.findById(Long.parseLong(id));

        if (savedFile != null) {
            DebugHelper.printData(savedFile.toString());
            String filePath = savedFile.get().getFilePath();

            DebugHelper.printData(filePath);

            file.transferTo(new File(filePath));
            DoctorImage final_data = imageRepository.save(savedFile.get());
            DebugHelper.printData(final_data.toString());

            return final_data;
        } else {
            return null;
        }
    }

    public DoctorImage uploadImageToFileSystem(MultipartFile file) throws IOException {

        DoctorImage data = DoctorImage.builder()
                .name("DOCTOR" + file.getOriginalFilename())
                .type(file.getContentType())
                .category("doctors")
                .build();
        DoctorImage savedFile = imageRepository.save(data);

        DebugHelper.printData(savedFile.toString());
        String name = "";
        if (file.getOriginalFilename().endsWith(".png") || file.getOriginalFilename().endsWith(".jpg")) {
            name = "DOCTOR" + savedFile.getId().toString() + "_" + file.getOriginalFilename();
        } else {
            name = "DOCTOR" + savedFile.getId().toString() + "_" + file.getOriginalFilename() + ".png";
        }
        String filePath = FOLDER_PATH + name;

        DebugHelper.printData(filePath);

        file.transferTo(new File(filePath));
        savedFile.setFilePath(filePath);
        savedFile.setName(name);

        DoctorImage final_data = imageRepository.save(savedFile);
        DebugHelper.printData(final_data.toString());

        return final_data;

    }

    public Map<String, Object> addNewDoctor(DoctorDto doctorDto) {

        DebugHelper.printData(doctorDto.toString());

        boolean isSymptomOk = true;
        boolean isDegreeOk = true;
        boolean isDeptOk = departmentRepository.existsById(doctorDto.getDeptId());

        DebugHelper.printData(doctorDto.toString());

        for (Long symptomId : doctorDto.getSymptomIds()) {
            if (!symptomRepository.existsById(symptomId)) {
                isSymptomOk = false;
                break;
            }
        }
        for (Long degreeId : doctorDto.getDegreeIds()) {
            if (!degreeRepository.existsById(degreeId)) {
                isDegreeOk = false;
                break;
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        if (isDeptOk && isSymptomOk && isDegreeOk) {
            Doctor doctor = new Doctor();
            if (doctorRepository.existsById(doctorDto.getId())) {
                result.put("id", doctorDto.getId());
                result.put("message", "Doctor already exists");
                result.put("success", false);

                return result;
            }

            doctor.setId(doctorDto.getId());
            BeanUtils.copyProperties(doctorDto, doctor);
            List<Symptom> symptoms = new ArrayList<>();
            List<Degree> degrees = new ArrayList<>();

            for (Long symptomId : doctorDto.getSymptomIds()) {
                symptoms.add(symptomRepository.findById(symptomId)
                        .orElseThrow(() -> new IllegalStateException("symptom does not exit")));
            }
            for (Long degreeId : doctorDto.getDegreeIds()) {
                degrees.add(degreeRepository.findById(degreeId)
                        .orElseThrow(() -> new IllegalStateException("degree does not exit")));
            }
            doctor.setDegree(degrees);
            doctor.setSymptom(symptoms);
            doctor.setDept(departmentRepository.findById(doctorDto.getDeptId())
                    .orElseThrow(() -> new IllegalStateException("department does not exit")));

            Doctor savedDoctor = doctorRepository.save(doctor);
            DebugHelper.printData(savedDoctor.toString());
            result.put("id", savedDoctor.getId());
            result.put("message", "Doctor added successfully");
            result.put("success", true);

            return result;

        } else {
            if (!isDeptOk) {
                result.put("id", null);
                result.put("message", "Department does not exist");
                result.put("success", false);

                return result;
            }
            if (!isSymptomOk) {
                result.put("id", null);
                result.put("message", "Symptom does not exist");
                result.put("success", false);

                return result;
            }
            if (!isDegreeOk) {
                result.put("id", null);
                result.put("message", "Degree does not exist");
                result.put("success", false);

                return result;
            }
        }
        result.put("id", null);
        result.put("message", "Error");
        result.put("success", false);

        return result;
    }

    public void deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Doctor does not exit");
        }
    }

    @Transactional
    // public void updateDoctor(Long id, DoctorDto doctor) {

    public Doctor updateDoctor(EditDoctorDto doctor) {

        Doctor newDoctor = doctorRepository.findById(doctor.getId())
                .orElseThrow(() -> new IllegalStateException("Doctor does not exit"));
        DebugHelper.printData(newDoctor.toString());

        boolean isDeptOk = true;
        boolean isSymptomOk = true;
        boolean isDegreeOk = true;

        List<Symptom> symptoms = new ArrayList<>();
        List<Degree> degrees = new ArrayList<>();

        if (doctor.getSymptomIds() != null) {
            for (Long symptomId : doctor.getSymptomIds()) {

                symptoms.add(symptomRepository.findById(symptomId)
                        .orElseThrow(() -> new IllegalStateException("Symptom does not exit")));
            }
        }
        if (doctor.getDegreeIds() != null) {
            for (Long degreeId : doctor.getDegreeIds()) {
                Degree newDegree = degreeRepository.findById(degreeId)
                        .orElseThrow(() -> new IllegalStateException("Degree does not exit"));
                degrees.add(
                        newDegree);

            }
        }

        if (isDeptOk && isSymptomOk && isDegreeOk) {
            if (doctor.getName() != null && doctor.getName().length() > 0) {
                newDoctor.setName(doctor.getName());
            }
            if (doctor.getDeptId() != null) {
                Department dept = departmentRepository.findById(doctor.getDeptId())
                        .orElseThrow(() -> new IllegalStateException("Department does not exit"));
                newDoctor.setDept(dept);
            }
            if (doctor.getDegreeIds() != null) {
                newDoctor.setDegree(degrees);
            }
            if (doctor.getSymptomIds() != null) {
                newDoctor.setSymptom(symptoms);
            }
            newDoctor.setHomeService(doctor.getHomeService());
            System.out.println(newDoctor.toString());

            return newDoctor;
        }

        return null;

    }

    // private final String FOLDER_PATH =
    // "D:/dev/backend/java/java_backend/src/images/doctors/";
    private final String FOLDER_PATH = AppConstant.folder_path + "/doctors/";

    public byte[] getImage(Long id) throws IOException {

        Optional<DoctorImage> savedFile = imageRepository.findById(id);

        if (savedFile != null) {
            DebugHelper.printData(savedFile.toString());
            String filePath = savedFile.get().getFilePath();

            DebugHelper.printData(filePath);
            byte[] image = Files.readAllBytes(new File(filePath).toPath());
            return image;
        } else {
            return null;
        }
    }
}
