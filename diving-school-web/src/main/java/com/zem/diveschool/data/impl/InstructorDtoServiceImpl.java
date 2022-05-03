package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.CourseDtoService;
import com.zem.diveschool.data.InstructorDtoService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.services.CourseService;
import com.zem.diveschool.persistence.services.InstructorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InstructorDtoServiceImpl extends AbstractDtoServiceImpl<InstructorDto, Long, Instructor, InstructorService>
                                implements InstructorDtoService {

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
    public Optional<InstructorDto> findByFirstName(String firstName) {
        return entityToDto.convert(service.findByFirstName(firstName));
    }
}

