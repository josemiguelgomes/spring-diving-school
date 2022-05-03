package com.zem.diveschool.data;

import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Set;

public interface SlotDtoService extends CrudService<SlotDto, Long> {

    Set<SlotDto> findByStudentID(Long id);
}
