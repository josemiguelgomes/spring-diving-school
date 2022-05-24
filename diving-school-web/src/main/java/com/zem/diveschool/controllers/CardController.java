package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CardConverter;
import com.zem.diveschool.converters.impl.simple.StudentConverter;
import com.zem.diveschool.data.CardExtendedService;
import com.zem.diveschool.data.StudentExtendedService;
import com.zem.diveschool.dto.CardDto;

import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Student;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/students/{studentId}")
public class CardController {

    private static final String VIEWS_STUDENTS_CARDS_LIST = "students/cards/list";
    private static final String VIEWS_STUDENTS_CARDS_CARDFORM = "students/cards/cardform";
    private static final String VIEWS_STUDENTS_CARDS_CARDFORMUPDATE = "students/cards/cardformupdate";
    private static final String VIEWS_STUDENTS_CARDS_SHOW = "students/cards/show";

    private static final String REDIRECT_STUDENTS = "redirect:/students";

    private final CardExtendedService service;
    private final StudentExtendedService studentService;

    private final CardConverter converter;
    private final StudentConverter studentConverter;

    public CardController(CardExtendedService service,
                          StudentExtendedService studentService,
                          CardConverter converter,
                          StudentConverter studentConverter) {
        super();
        this.service = service;
        this.studentService = studentService;
        this.converter = converter;
        this.studentConverter = studentConverter;
    }

    @ModelAttribute("studentDto")
    public StudentDto setStudent(@PathVariable("studentId") Long studentId) {
        Optional<Student> studentOptional = studentService.findById(studentId);
        if(studentOptional.isEmpty()){
            return null; // TODO find a better solution for this
        }
        Student student = studentOptional.get();
        StudentDto studentDto = studentConverter.convertFromEntity(student);
        studentDto.setCards(converter.convertFromEntities(student.getCards()));
        return studentDto;
    }
/*
    @ModelAttribute("card")
    public void setCardValidator(WebDataBinder dataBinder) {
        dataBinder.setValidator(new CardValidator());
  }
*/

//    @InitBinder("student")
//    public void setAllowedFields(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
//    }

    /* ---- */
    @GetMapping("cards")
    public String listStudentCards(StudentDto studentDto, Model model){
        log.debug("Getting cards list for student id: " + studentDto.getId());

        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_CARDS_LIST;
    }

    @GetMapping("cards/new")
    public String initNewStudentCard(StudentDto studentDto, Model model) {
        log.debug("Getting student id " + studentDto.getId());

        CardDto cardDto = CardDto.builder().build();

        model.addAttribute("student", studentDto);
        model.addAttribute("card", cardDto);
        return VIEWS_STUDENTS_CARDS_CARDFORM;
    }

    @PostMapping("cards/new")
    public String processNewStudentCard(@PathVariable("studentId") Long studentId,
                                        StudentDto studentDto,
                                        @Valid CardDto cardDto,
                                        BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(cardDto.getCourse())
                && cardDto.isNew()
                && studentDto.getCards().stream().anyMatch(card -> card.getCourse().equals(cardDto.getCourse()))) {
            result.rejectValue("course", "duplicate", "already exists");
        }
        if (result.hasErrors()) {
            model.put("card", cardDto);
            return VIEWS_STUDENTS_CARDS_CARDFORM;
        } else {
            Card card = converter.convertFromDto(cardDto);
            Student student = studentService.findById(studentId).get();

            // Link the Entities
            card.setStudent(student);
            student.getCards().add(card);

            service.save(card);

            return REDIRECT_STUDENTS + "/" + student.getId() + "/show";
        }
    }

    @GetMapping("cards/{cardId}/delete")
    public String deleteStudentCard(@PathVariable Long studentId,
                                    @PathVariable Long cardId,
                                    StudentDto studentDto,
                                    Model model) {
        log.debug("Getting student id " + studentId + " and card id " + cardId);

        studentService.deleteByStudentIdAndCardId(studentId, cardId);

        model.addAttribute("student", studentDto);
        return REDIRECT_STUDENTS + "/" + studentDto.getId() + "/cards";
    }

    @GetMapping("cards/{cardId}/show")
    public String showStudentCard(@PathVariable Long studentId,
                                  @PathVariable Long cardId,
                                  StudentDto studentDto,
                                  Model model){
        log.debug("Getting card id " + cardId + " for student id: " + studentId);

        Optional<Card> cardOptional = studentService.findByStudentIdAndCardId(studentId, cardId);
        CardDto cardDto = converter.convertFromEntity(cardOptional.get());

        model.addAttribute("student", studentDto);
        model.addAttribute("card", cardDto);
        return VIEWS_STUDENTS_CARDS_SHOW;
    }

    @GetMapping("cards/{cardId}/update")
    public String initUpdateStudentCard(StudentDto studentDto,
                                        @PathVariable Long cardId,
                                        Model model) {
        log.debug("Getting student id " + studentDto.getId());

        Optional<Card> cardOptional = service.findById(cardId);
        CardDto cardDto = converter.convertFromEntity(cardOptional.get());
        cardDto.setStudent(studentDto);

        model.addAttribute("student", studentDto);
        model.addAttribute("card", cardDto);
        return VIEWS_STUDENTS_CARDS_CARDFORMUPDATE;
    }

    @PostMapping("cards/{cardId}/update")
    public String processUpdateStudentCard(@PathVariable("studentId") Long studentId,
                                           @Valid CardDto cardDto,
                                           BindingResult result,
                                           ModelMap model) {
/* TODO refactor this !!!!
        if (StringUtils.hasLength(cardDto.getCourse())
                && cardDto.isNew()
                && studentDto.getCards().stream().anyMatch(card -> card.getCourse().equals(cardDto.getCourse()))) {
            result.rejectValue("course", "duplicate", "already exists");
        }
 */
        if (result.hasErrors()) {
            model.addAttribute("card", cardDto);
            return VIEWS_STUDENTS_CARDS_CARDFORMUPDATE;
        } else {
            Card card = converter.convertFromDto(cardDto);
            Student student = studentService.findById(studentId).get();

            student.getCards().add(card);
            card.setStudent(student);

            service.save(card);

            return REDIRECT_STUDENTS + "/" + student.getId() + "/show";
        }
    }

}
