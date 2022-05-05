package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.SlotDtoService;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.services.SlotService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SlotDtoServiceImpl extends AbstractDtoServiceImpl<SlotDto, Long, Slot, SlotService>
                                implements SlotDtoService {


    @Override
    public Set<SlotDto> findAll() {
        return super.findAll();
    }

    @Override
    public SlotDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public SlotDto save(SlotDto object) {
        return super.save(object);
    }

    @Override
    public void delete(SlotDto object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends SlotDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
        return super.saveAll(dtos);
    }

/*
    @Override
    @Transactional
    public Set<SlotDto> findByStudentID(Long id) {
        return entityToDto.convert(service.findByStudentID(id));
    }
*/
    @Override
    @Transactional
    public Set<SlotDto> findByCourseId(Long courseId) {
        // TODO
        return null;
    }

    @Override
    @Transactional
    public Optional<SlotDto> findByCourseIdAndSlotId(Long courseId, Long slotId) {
        // TODO
        return Optional.empty();
    }

    @Override
    @Transactional
    public Set<SlotDto> findByInstructorId(Long instructorId) {
        // TODO
        return null;
    }

    @Override
    @Transactional
    public Optional<SlotDto> findByInstructorIdAndSlotId(Long instructorID, Long slotId) {
        // TODO
        return Optional.empty();
    }

    @Override
    @Transactional
    public Set<SlotDto> findByStudentId(Long studentId) {
        // TODO
        return null;
    }

    @Override
    @Transactional
    public Optional<SlotDto> findByStudentIdAndSlotId(Long studentId, Long slotId) {
        // TODO
        return Optional.empty();
    }
}

