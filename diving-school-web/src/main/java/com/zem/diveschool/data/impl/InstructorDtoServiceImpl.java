package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.InstructorDtoService;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.services.InstructorService;
import com.zem.diveschool.persistence.services.SlotService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InstructorDtoServiceImpl extends AbstractDtoServiceImpl<InstructorDto, Long, Instructor, InstructorService>
                                implements InstructorDtoService {

    @Autowired // TODO : this will not work !!!!!! create a constructor ????
    SlotService slotService;


    @Override
    public Set<InstructorDto> findAll() {
        return super.findAll();
    }

    @Override
    public InstructorDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public InstructorDto save(InstructorDto object) {
       return super.save(object);
    }

    @Override
    public void delete(InstructorDto object) {
       super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public <S extends InstructorDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
       return super.saveAll(dtos);
    }

    @Override
    @Transactional
    public Optional<InstructorDto> findByFirstName(String firstName) {
        return entityToDto.convert(service.findByFirstName(firstName));
    }

    @Override
    @Transactional
    public Set<InstructorDto> findBySlotId(Long slotId) {
        // TODO replace by a Optional<Slot>
        Slot slot = slotService.findById(slotId);
        if(slot == null) {
            return null;
        }
        return entityToDto.convert(slot.getInstructors());
    }

    @Override
    @Transactional
    public Optional<InstructorDto> findBySlotIdAndInstructorId(Long slotId, Long instructorId) {
        // TODO replace by an Optional
        return Optional.of(entityToDto.convert(service.findById(instructorId)));
    }
}

