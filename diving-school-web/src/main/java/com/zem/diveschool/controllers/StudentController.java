package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CardConverter;
import com.zem.diveschool.converters.impl.simple.LocationConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.converters.impl.simple.StudentConverter;
import com.zem.diveschool.data.StudentExtendedService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class StudentController {

    private static final String VIEWS_STUDENTS_INDEX = "students/index";
    private static final String VIEWS_STUDENTS_SHOW = "students/show";
    private static final String VIEWS_STUDENTS_STUDENTFORM = "students/studentform";
    private static final String VIEWS_STUDENTS_IMAGEUPLOADFORM = "students/imageuploadform";
    private static final String VIEWS_STUDENTS_FIND = "students/find";

    private static final String VIEWS_STUDENTS_CARDS_LIST = "students/cards/list";
    private static final String VIEWS_STUDENTS_CARDS_CARDFORM = "students/cards/cardform";
    private static final String VIEWS_STUDENTS_CARDS_SHOW = "students/cards/show";

    private static final String VIEWS_STUDENTS_LOCATIONS_LIST = "students/locations/list";
    private static final String VIEWS_STUDENTS_LOCATIONS_LOCATIONFORM = "students/locations/locationform";
    private static final String VIEWS_STUDENTS_LOCATIONS_SHOW = "students/locations/show";

    private static final String VIEWS_STUDENTS_SLOTS_LIST = "students/slots/list";
    private static final String VIEWS_STUDENTS_SLOTS_SLOTFORM = "students/slots/slotform";
    private static final String VIEWS_STUDENTS_SLOTS_SHOW = "students/slots/show";

    private static final String REDIRECT_STUDENTS = "redirect:/students";
    private static final String REDIRECT_STUDENTS_CARDS = "redirect:/students/cards";
    private static final String REDIRECT_STUDENTS_LOCATIONS = "redirect:/students/locations";
    private static final String REDIRECT_STUDENTS_SLOTS = "redirect:/students/slots";

    private final StudentExtendedService service;
    private final StudentConverter converter;
    private final SlotConverter slotConverter;
    private final LocationConverter locationConverter;
    private final CardConverter cardConverter;

    public StudentController(StudentExtendedService service,
                             StudentConverter converter,
                             SlotConverter slotConverter,
                             LocationConverter locationConverter,
                             CardConverter cardConverter) {
        this.service = service;
        this.converter = converter;
        this.slotConverter = slotConverter;
        this.locationConverter = locationConverter;
        this.cardConverter = cardConverter;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/students/index", "/students/index.html"})
    public String listStudents(@NotNull Model model){
        Set<Student> students = service.findAll();
        Set<StudentDto> studentsDto = converter.convertFromEntities(students);
        model.addAttribute("students", studentsDto);
        return VIEWS_STUDENTS_INDEX;
    }

    @GetMapping({"/students/{id}/show"})
    public String showById(@PathVariable String id, @NotNull Model model){
        Optional<Student> studentOptional = service.findById(Long.valueOf(id));
        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());

        studentDto.setHomeAddress(locationConverter.convertFromEntity(studentOptional.get().getHomeAddress()));
        studentDto.setCards(cardConverter.convertFromEntities(studentOptional.get().getCards()));
        studentDto.setSlots(slotConverter.convertFromEntities(studentOptional.get().getSlots()));

        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_SHOW;
    }

    @GetMapping("students/new")
    public String newStudent(@NotNull Model model){
        model.addAttribute("student", StudentDto.builder().build());
        return VIEWS_STUDENTS_STUDENTFORM;
    }

    @GetMapping("students/{id}/update")
    public String updateStudent(@PathVariable String id, @NotNull Model model){
        Optional<Student> studentOptional = service.findById(Long.valueOf(id));
        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());

        studentDto.setHomeAddress(locationConverter.convertFromEntity(studentOptional.get().getHomeAddress()));

        model.addAttribute("student", studentDto);
        return  VIEWS_STUDENTS_STUDENTFORM;
    }

    @PostMapping("students")
    public String saveOrUpdate(@ModelAttribute StudentDto studentDto){
        Student student = converter.convertFromDto(studentDto);
        Student savedStudent = service.save(student);
        StudentDto savedStudentDto = converter.convertFromEntity(savedStudent);
        return REDIRECT_STUDENTS + "/" + savedStudentDto.getId() + "/show";
    }

    @GetMapping("students/{id}/delete")
    public String deleteById(@PathVariable String id){
        service.deleteById(Long.valueOf(id));
        return REDIRECT_STUDENTS;
    }

    /* --- */

    @GetMapping("/students/{id}/photo")
    public String showUploadForm(@PathVariable String id, @NotNull Model model){
        Optional<Student> studentOptional = service.findById(Long.valueOf(id));
        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());
        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_IMAGEUPLOADFORM;
    }

    @PostMapping("/students/{id}/photo")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){
        service.saveImageFile(Long.valueOf(id), file);
        return REDIRECT_STUDENTS + "/" + id + "/show";
    }

    /* --- */

    @GetMapping("/students/find")
    public String findStudents(Model model) {
        model.addAttribute("student", StudentDto.builder().build());
        return VIEWS_STUDENTS_FIND;
    }

    @GetMapping("/students")
    public String processFindForm(StudentDto studentDto, BindingResult result, Model model) {
        // allow parameterless GET request for /students to return all records
        if (studentDto.getLastName() == null) {
            studentDto.setLastName(""); // empty string signifies a more abroad possible search
        }

        // find students by last name
        Student student = converter.convertFromDto(studentDto);
        Set<Student> students = service.findAllByLastNameLike("%" + student.getLastName() + "%");
        Set<StudentDto> studentsDto = converter.convertFromEntities(students);

        if (studentsDto.isEmpty()) {
            // no students found
            result.rejectValue("lastName", "notFound", "not found");
            return VIEWS_STUDENTS_FIND;
        } else if (studentsDto.size() == 1) {
            // 1 student found
            studentDto = studentsDto.stream().findFirst().get();
            return REDIRECT_STUDENTS + "/" + studentDto.getId() + "/show";
        } else {
            // multiple students found
            model.addAttribute("students", studentsDto);
            return "students/index";
        }
    }

    /* --- */

    @GetMapping("/students/{studentId}/cards")
    public String listStudentCards(@PathVariable String studentId, @NotNull Model model){
        log.debug("Getting cards list for student id: " + studentId);

        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));
        Set<Card> cards = service.findCardsByStudentId(Long.valueOf(studentId));

        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());
        Set<CardDto> cardsDto = cardConverter.convertFromEntities(cards);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("cards", cardsDto);
        model.addAttribute("student", studentDto);

        return VIEWS_STUDENTS_CARDS_LIST;
    }

    @GetMapping("/students/{studentId}/cards/new")
    public String newStudentCard(@PathVariable String studentId, @NotNull Model model) {
        log.debug("Getting student id " + studentId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));

        // need to return back parent id for hidden form property
        Card card = new Card();
        card.setStudent(studentOptional.get());

        CardDto cardDto = cardConverter.convertFromEntity(card);

        model.addAttribute("card", cardDto);
        return VIEWS_STUDENTS_CARDS_CARDFORM;
    }

    @GetMapping("/students/{studentId}/cards/{cardId}/delete")
    public String deleteStudentCard(@PathVariable String studentId, @PathVariable String cardId, @NotNull Model model) {
        log.debug("Getting student id " + studentId + " and card id " + cardId);

        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));
        service.deleteByStudentIdAndCardId(Long.valueOf(studentId), Long.valueOf(cardId));

        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());

        model.addAttribute("student", studentDto);
        return REDIRECT_STUDENTS_CARDS;
    }

    @GetMapping("/students/{studentId}/cards/{cardId}/show")
    public String showStudentCard(@PathVariable String studentId, @PathVariable String cardId, @NotNull Model model){
        log.debug("Getting card id " + cardId + " for student id: " + studentId);

        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));
        Optional<Card> cardOptional = service.findByStudentIdAndCardId(Long.valueOf(studentId),
                Long.valueOf(cardId));

        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());
        CardDto cardDto = cardConverter.convertFromEntity(cardOptional.get());

        model.addAttribute("card", cardDto);
        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_CARDS_SHOW;
    }

    @GetMapping("/students/{studentId}/locations")
    public String listStudentLocations(@PathVariable String studentId, @NotNull Model model){
        log.debug("Getting location list for student id: " + studentId);

        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));
        Set<Location> locations = service.findLocationsByStudentId(Long.valueOf(studentId));

        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());
        Set<LocationDto> locationsDto = locationConverter.convertFromEntities(locations);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("locations", locationsDto);
        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_LOCATIONS_LIST;
    }

    @GetMapping("/students/{studentId}/locations/new")
    public String newStudentLocation(@PathVariable String studentId, @NotNull Model model) {
        log.debug("Getting student id " + studentId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));

        // need to return back parent id for hidden form property
        Location location = new Location();
//      location.setStudent(studentOptional.get());

        LocationDto locationDto = locationConverter.convertFromEntity(location);

        model.addAttribute("location", locationDto);
        return VIEWS_STUDENTS_LOCATIONS_LOCATIONFORM;
    }

    @GetMapping("/students/{studentId}/locations/{locationId}/delete")
    public String deleteStudentLocation(@PathVariable String studentId, @PathVariable String locationId, @NotNull Model model) {
        log.debug("Getting student id " + studentId + " and location id " + locationId);

        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));
        service.deleteByStudentIdAndLocationId(Long.valueOf(studentId), Long.valueOf(locationId));

        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());

        model.addAttribute("student", studentDto);
        return REDIRECT_STUDENTS_LOCATIONS;
    }

    @GetMapping("/students/{studentId}/locations/{locationId}/show")
    public String showStudentLocation(@PathVariable String studentId, @PathVariable String locationId, @NotNull Model model){
        log.debug("Getting location id " + locationId + " for student id: " + studentId);

        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));
        Optional<Location> locationOptional =
                service.findByStudentIdAndLocationId(Long.valueOf(studentId), Long.valueOf(locationId));

        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());
        LocationDto locationDto = locationConverter.convertFromEntity(locationOptional.get());

        model.addAttribute("location", locationDto);
        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_LOCATIONS_SHOW;
    }

    @GetMapping("/students/{studentId}/slots")
    public String listStudentSlots(@PathVariable String studentId, @NotNull Model model){
        log.debug("Getting slots list for student id: " + studentId);

        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));
        Set<Slot> slots = service.findSlotsByStudentId(Long.valueOf(studentId));

        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());
        Set<SlotDto> slotsDto = slotConverter.convertFromEntities(slots);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotsDto);
        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_SLOTS_LIST;
    }

    @GetMapping("/students/{studentId}/slots/new")
    public String newStudentSlot(@PathVariable String studentId, @NotNull Model model) {
        log.debug("Getting student id " + studentId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));

        // need to return back parent id for hidden form property
        Slot slot = new Slot();
        slot.getStudents().add(studentOptional.get());

        SlotDto slotDto = slotConverter.convertFromEntity(slot);

        model.addAttribute("slot", slotDto);
        return VIEWS_STUDENTS_SLOTS_SLOTFORM;
    }

    @GetMapping("/students/{studentId}/slots/{slotId}/delete")
    public String deleteStudentSlot(@PathVariable String studentId, @PathVariable String slotId, @NotNull Model model) {
        log.debug("Getting student id " + studentId + " and slot id " + slotId);

        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));
        service.deleteByStudentIdAndSlotId(Long.valueOf(studentId), Long.valueOf(slotId));

        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());

        model.addAttribute("student", studentDto);
        return REDIRECT_STUDENTS_SLOTS;
    }

    @GetMapping("/students/{studentId}/slots/{slotId}/show")
    public String showStudentSlot(@PathVariable String studentId, @PathVariable String slotId, @NotNull Model model){
        log.debug("Getting slot id " + slotId + " for student id: " + studentId);

        Optional<Student> studentOptional = service.findById(Long.valueOf(studentId));
        Optional<Slot> slotOptional =
                service.findByStudentIdAndSlotId(Long.valueOf(studentId), Long.valueOf(slotId));

        StudentDto studentDto = converter.convertFromEntity(studentOptional.get());
        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());

        model.addAttribute("slot", slotDto);
        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_SLOTS_SHOW;
    }
}
