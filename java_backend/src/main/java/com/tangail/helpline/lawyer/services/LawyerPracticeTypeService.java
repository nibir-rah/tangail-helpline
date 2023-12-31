package com.tangail.helpline.lawyer.services;

import java.util.List;
import java.util.Optional;

import com.tangail.helpline.lawyer.dto.AddPracticeTypeDto;
import com.tangail.helpline.lawyer.dto.EditPracticeTypeDto;
import com.tangail.helpline.lawyer.repositories.LawyerPracticeTypeRepository;
import org.springframework.stereotype.Service;

import com.tangail.helpline.lawyer.entity.LawyerPracticeType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LawyerPracticeTypeService {
    private final LawyerPracticeTypeRepository practiceTypeRepository;

    public List<LawyerPracticeType> getPracticeTypes() {
        return practiceTypeRepository.findAll();
    }

    public boolean addNewPracticeType(AddPracticeTypeDto practiceTypeDto) {
        Optional<LawyerPracticeType> existingType = practiceTypeRepository.findByName(practiceTypeDto.getName());

        if (existingType.isPresent()) {
            return false;
        }
        LawyerPracticeType newType = new LawyerPracticeType();
        newType.setName(practiceTypeDto.getName());
        newType.setBangla_name(practiceTypeDto.getBangla_name());

        practiceTypeRepository.save(newType);
        return true;

    }

    public void deletePracticeType(Long id) {

        if (practiceTypeRepository.existsById(id)) {
            practiceTypeRepository.deleteById(id);
        } else {
            throw new IllegalStateException("PracticeType does not exit");
        }
    }

    @Transactional
    public boolean updatePracticeType(Long id, EditPracticeTypeDto practiceType) {
        LawyerPracticeType newPracticeType = practiceTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("PracticeType does not exit"));

        System.out.println(newPracticeType);

        if (practiceType.getName() != null && practiceType.getName().length() > 0) {
            newPracticeType.setName(practiceType.getName());
        }
        if (practiceType.getBangla_name() != null && practiceType.getBangla_name().length() > 0) {
            newPracticeType.setBangla_name(practiceType.getBangla_name());
        }

        return true;
    }

}
