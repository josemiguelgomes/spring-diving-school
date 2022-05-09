package com.zem.diveschool.controllers;

import com.zem.diveschool.data.StudentDtoService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.StudentDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    @Mock
    StudentDtoService studentDtoService;

    StudentController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new StudentController(studentDtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/index"));
//                .andExpect(model().size(1)); ???? why
    }

    @Test
    public void test_listStudents() throws Exception {
        //given
        Set<StudentDto> studentsDto = new HashSet<>();

        //when
        when(studentDtoService.findAll()).thenReturn(studentsDto);

        //then
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/index"))
                .andExpect(model().attributeExists("students"))
                .andExpect(model().size(1));

        verify(studentDtoService, times(1)).findAll();
    }

    @Test
    public void test_showById() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/show"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

        verify(studentDtoService, times(1)).findById(anyLong());
    }

    @Test
    public void test_newStudent() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();

        //when

        //then
        mockMvc.perform(get("/students/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/studentform"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

    }

    @Test
    public void test_updateStudent() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(2L);

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/studentform"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

        verify(studentDtoService, times(1)).findById(anyLong());
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(2L);

        //when
        when(studentDtoService.save(any())).thenReturn(studentDto);

        //then
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("firstName", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/students/2/show"))
                .andExpect(model().size(2));    // TODO ?????

        verify(studentDtoService, times(1)).save(any());
    }

    @Test
    public void test_deleteById() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/students/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/students"))
                .andExpect(model().size(0));

        verify(studentDtoService, times(1)).deleteById(anyLong());
    }

    /* --- */

    @Test
    public void test_findStudents() throws Exception {
        //given
        Set<StudentDto> studentsDto = new HashSet<StudentDto>();

        //when
        when(studentDtoService.findAll()).thenReturn(studentsDto);

        //then
        mockMvc.perform(get("/students/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/find"))
                .andExpect(model().attributeExists("students"))
                .andExpect(model().size(1));

        verify(studentDtoService, times(1)).findAll();
    }

    /* --- */

    @Test
    public void test_listStudentCards() throws Exception {
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Set<CardDto> cardsDto = new HashSet<CardDto>();

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentDtoService.findCardsByStudentId(anyLong())).thenReturn(cardsDto);

        //then
        mockMvc.perform(get("/students/1/cards"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/cards/list"))
                .andExpect(model().attributeExists("cards"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentDtoService, times(1)).findById(anyLong());
        verify(studentDtoService, times(1)).findCardsByStudentId(anyLong());
    }

    @Test
    public void test_newStudentCard() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/cards/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/cards/cardform"))
                .andExpect(model().attributeExists("card"))
                .andExpect(model().size(1));

        verify(studentDtoService, times(1)).findById(anyLong());
    }

    @Test
    public void test_showStudentCard() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        CardDto cardDto = new CardDto();
        cardDto.setId(2L);

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(Optional.of(studentDto));
        when(studentDtoService.findByStudentIdAndCardId(anyLong(), anyLong())).thenReturn(Optional.of(cardDto));

        //then
        mockMvc.perform(get("/students/1/cards/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/cards/show"))
                .andExpect(model().attributeExists("card"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentDtoService, times(1)).findById(anyLong());
        verify(studentDtoService, times(1)).findByStudentIdAndCardId(anyLong(), anyLong());
    }

    @Test
    public void test_listStudentLocations() throws Exception {
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Set<LocationDto> locationsDto = new HashSet<LocationDto>();

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentDtoService.findLocationsByStudentId(anyLong())).thenReturn(locationsDto);

        //then
        mockMvc.perform(get("/students/1/locations"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/locations/list"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentDtoService, times(1)).findById(anyLong());
        verify(studentDtoService, times(1)).findLocationsByStudentId(anyLong());
    }

    @Test
    public void test_newStudentLocation() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/locations/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/locations/locationform"))
                .andExpect(model().attributeExists("location"))
                .andExpect(model().size(1));

        verify(studentDtoService, times(1)).findById(anyLong());
    }

    @Test
    public void test_showStudentLocation() throws Exception {
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Optional<LocationDto> locationDtoOptional = Optional.of(new LocationDto());

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentDtoService.findByStudentIdAndLocationId(anyLong(),anyLong())).thenReturn(locationDtoOptional);

        //then
        mockMvc.perform(get("/students/1/locations/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/locations/show"))
                .andExpect(model().attributeExists("location"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentDtoService, times(1)).findById(anyLong());
        verify(studentDtoService, times(1)).findByStudentIdAndLocationId(anyLong(),anyLong());
    }

    @Test
    public void test_listStudentSlots() throws Exception {
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Set<SlotDto> slotsDto = new HashSet<SlotDto>();

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentDtoService.findSlotsByStudentId(anyLong())).thenReturn(slotsDto);

        //then
        mockMvc.perform(get("/students/1/slots"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/slots/list"))
                .andExpect(model().attributeExists("slots"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentDtoService, times(1)).findById(anyLong());
        verify(studentDtoService, times(1)).findSlotsByStudentId(anyLong());
    }

    @Test
    public void test_newStudentSlot() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/slots/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/slots/slotform"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(1));

        verify(studentDtoService, times(1)).findById(anyLong());
    }

    @Test
    public void test_showStudentSlot() throws Exception {
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentDtoService.findByStudentIdAndSlotId(anyLong(),anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/students/1/slots/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/slots/show"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentDtoService, times(1)).findById(anyLong());
        verify(studentDtoService, times(1)).findByStudentIdAndSlotId(anyLong(),anyLong());
    }
}
