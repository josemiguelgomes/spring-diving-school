package com.zem.diveschool.persistence.services;

import com.zem.diveschool.persistence.model.Slot;

import java.util.Set;

public interface SlotService extends CrudService<Slot, Long> {
    //
    //
    Set<Slot> findByStudentID(Long id);
}