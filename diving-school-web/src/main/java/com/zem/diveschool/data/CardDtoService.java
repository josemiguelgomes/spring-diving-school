package com.zem.diveschool.data;


import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface CardDtoService extends CrudService<CardDto, Long> {

      Set<CardDto> findByStudentID(Long id);
      Optional<CardDto> findByStudentIdAndCardId(Long studentId, Long cardId);

}
