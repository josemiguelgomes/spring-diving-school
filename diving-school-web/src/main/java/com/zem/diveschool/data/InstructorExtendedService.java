package com.zem.diveschool.data;

import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.services.CrudService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

public interface InstructorExtendedService extends CrudService<Instructor, Long> {
    Set<Slot> findSlotsByInstructorId(Long instructorId);

    Optional<Slot> findByInstructorIdAndSlotId(Long instructorId, Long slotId);

    Set<Location> findLocationsByInstructorId(Long instructorId);
    Optional<Location> findByInstructorIdAndLocationId(Long instructorId, Long locationId);

    void deleteByInstructorIdAndLocationId(Long instructorId, Long locationId);

    void deleteByInstructorIdAndSlotId(Long instructorId, Long slotId);

    void saveImageFile(Long instructorId, MultipartFile file);
}
