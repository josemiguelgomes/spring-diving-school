package com.zem.diveschool.data;

import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.services.CrudService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

public interface StudentExtendedService extends CrudService<Student, Long> {
    Set<Card> findCardsByStudentId(Long studentId);
    Optional<Card> findByStudentIdAndCardId(Long studentId, Long cardId);
    Set<Location> findLocationsByStudentId(Long studentId);
    Optional<Location> findByStudentIdAndLocationId(Long studentId, Long locationId);
    Set<Slot> findSlotsByStudentId(Long studentId);
    Optional<Slot> findByStudentIdAndSlotId(Long studentId, Long slotId);

    void deleteByStudentIdAndCardId(long studentId, long cardId);

    void deleteByStudentIdAndLocationId(long studentId, long locationId);

    void deleteByStudentIdAndSlotId(long studentId, long slotId);

    void saveImageFile(Long studentId, MultipartFile file);

    Set<Student> findAllByLastNameLike(String lastName);
}
