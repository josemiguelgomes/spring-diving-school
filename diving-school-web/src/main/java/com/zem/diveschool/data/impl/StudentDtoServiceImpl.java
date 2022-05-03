package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.InstructorDtoService;
import com.zem.diveschool.data.StudentDtoService;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.services.InstructorService;
import com.zem.diveschool.persistence.services.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentDtoServiceImpl extends AbstractDtoServiceImpl<StudentDto, Long, Student, StudentService>
                                implements StudentDtoService {

    @Override
    public Set<StudentDto> findAll() {
        return super.findAll();
    }

    @Override
    public StudentDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public StudentDto save(StudentDto object) {
       return super.save(object);
    }

    @Override
    public void delete(StudentDto object) {
       super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public <S extends StudentDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
       return super.saveAll(dtos);
    }

    @Override
    @Transactional
    public Optional<StudentDto> findByFirstName(String firstName) {
        return entityToDto.convert(service.findByFirstName(firstName));
    }
}

