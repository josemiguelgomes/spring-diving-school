package com.zem.diveschool.persistence.services.impl.map;

import com.zem.diveschool.persistence.services.SlotService;
import com.zem.diveschool.persistence.services.CourseService;
import com.zem.diveschool.persistence.services.SlotLanguageService;
import com.zem.diveschool.persistence.services.StudentService;
import com.zem.diveschool.persistence.services.InstructorService;
import com.zem.diveschool.persistence.model.Slot;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class SlotMapService extends AbstractMapService<Slot, Long>
                            implements SlotService {

    CourseService courseService;
    SlotLanguageService slotLanguageService;
    StudentService studentService;
    InstructorService instructorService;

    public SlotMapService(CourseService courseService, SlotLanguageService slotLanguageService,
                          StudentService studentService, InstructorService instructorService) {
        this.courseService = courseService;
        this.slotLanguageService = slotLanguageService;
        this.studentService = studentService;
        this.instructorService = instructorService;
    }

    @Override
    @Transactional
    public Set<Slot> findAll() {
        return super.findAll();
    }
    @Override
    @Transactional
    public Slot findById(Long id) {
        return  super.findById(id);
    }
    @Override
    @Transactional
    public Slot save(Slot object) {
        if (object == null) {
            return null;
        }

        //
        // Save Course
        //
        if (object.getCourse() == null) {
            throw new RuntimeException("Course is required on saving a Slot");
        }

        if (object.getCourse().getId() == null) {
            object.setCourse(courseService.save(object.getCourse()));
        }

        //
        // Save Slot Languages
        //
        if (object.getLanguages() == null) {
            throw new RuntimeException("Language(s) are required on saving a Slot");
        }
        object.getLanguages().forEach(language -> {
            if (language.getId() == null) {
                language.setId(slotLanguageService.save(language).getId());
            }
        });

        //
        // Save Students
        //
        if (object.getStudents() != null) {
            object.getStudents().forEach(student -> {
                if (student.getId() == null) {
                    student.setId(studentService.save(student).getId());
                }
            });
        }

        //
        // Save Instructors
        //
        if (object.getInstructors() != null) {
            object.getInstructors().forEach(instructor -> {
                if (instructor.getId() == null) {
                    instructor.setId(instructorService.save(instructor).getId());
                }
            });
        }

        return super.save(object);
    }
    @Override
    @Transactional
    public void delete(Slot object) {
        super.delete(object);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }


}
