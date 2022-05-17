package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CardConverter;
import com.zem.diveschool.converters.impl.simple.LocationConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.converters.impl.simple.StudentConverter;
import com.zem.diveschool.data.StudentExtendedService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.StudentDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    @Mock
    StudentExtendedService service;

    @Mock
    StudentConverter converter;

    @Mock
    SlotConverter slotConverter;

    @Mock
    LocationConverter locationConverter;

    @Mock
    CardConverter cardConverter;

    StudentController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new StudentController(service, converter, slotConverter, locationConverter, cardConverter);
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
        assertEquals(1,0);
        /*
        //given
        Set<StudentDto> studentsDto = new HashSet<>();

        //when
        when(studentExtendedService.findAll()).thenReturn(studentsDto);

        //then
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/index"))
                .andExpect(model().attributeExists("students"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findAll();

         */
    }

    @Test
    public void test_showById() throws Exception {
        assertEquals(1,0);
        /*
        //given
        StudentDto studentDto = new StudentDto();

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/show"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_newStudent() throws Exception {
        assertEquals(1,0);
        /*
        //given
        StudentDto studentDto = new StudentDto();

        //when

        //then
        mockMvc.perform(get("/students/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/studentform"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));
*/
    }

    @Test
    public void test_updateStudent() throws Exception {
        assertEquals(1,0);
        /*
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(2L);

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/studentform"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        assertEquals(1,0);
        /*
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(2L);

        //when
        when(studentExtendedService.save(any())).thenReturn(studentDto);

        //then
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("firstName", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/students/2/show"))
                .andExpect(model().size(2));    // TODO ?????

        verify(studentExtendedService, times(1)).save(any());

         */
    }

    @Test
    public void test_deleteById() throws Exception {
        assertEquals(1,0);
        /*
        //given

        //when

        //then
        mockMvc.perform(get("/students/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/students"))
                .andExpect(model().size(0));

        verify(studentExtendedService, times(1)).deleteById(anyLong());

         */
    }

    /* --- */

    @Test
    public void test_showUploadForm() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(studentDtoOptional);

        //then
        mockMvc.perform(get("/students/1/photo"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/photouploadform"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_handleImagePost() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "ZEM Dive School APP".getBytes());

        //when

        //then
        mockMvc.perform(multipart("/students/1/photo").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/students/1/show"));

        verify(studentExtendedService, times(1)).saveImageFile(anyLong(), any());

         */
    }

    /* --- */

    @Test
    public void test_findStudents() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Set<StudentDto> studentsDto = new HashSet<StudentDto>();

        //when
        when(studentExtendedService.findAll()).thenReturn(studentsDto);

        //then
        mockMvc.perform(get("/students/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/find"))
                .andExpect(model().attributeExists("students"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findAll();

         */
    }

    /* --- */

    @Test
    public void test_listStudentCards() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Set<CardDto> cardsDto = new HashSet<CardDto>();

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentExtendedService.findCardsByStudentId(anyLong())).thenReturn(cardsDto);

        //then
        mockMvc.perform(get("/students/1/cards"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/cards/list"))
                .andExpect(model().attributeExists("cards"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentExtendedService, times(1)).findById(anyLong());
        verify(studentExtendedService, times(1)).findCardsByStudentId(anyLong());

         */
    }

    @Test
    public void test_newStudentCard() throws Exception {
        assertEquals(1,0);
        /*
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/cards/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/cards/cardform"))
                .andExpect(model().attributeExists("card"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_deleteStudentCard() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(studentDtoOptional);

        //then
        mockMvc.perform(get("/students/1/cards/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/students/cards"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findById(anyLong());
        verify(studentExtendedService, times(1)).deleteByStudentIdAndCardId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_showStudentCard() throws Exception {
        assertEquals(1,0);
        /*
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        CardDto cardDto = new CardDto();
        cardDto.setId(2L);

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(Optional.of(studentDto));
        when(studentExtendedService.findByStudentIdAndCardId(anyLong(), anyLong())).thenReturn(Optional.of(cardDto));

        //then
        mockMvc.perform(get("/students/1/cards/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/cards/show"))
                .andExpect(model().attributeExists("card"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentExtendedService, times(1)).findById(anyLong());
        verify(studentExtendedService, times(1)).findByStudentIdAndCardId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_listStudentLocations() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Set<LocationDto> locationsDto = new HashSet<LocationDto>();

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentExtendedService.findLocationsByStudentId(anyLong())).thenReturn(locationsDto);

        //then
        mockMvc.perform(get("/students/1/locations"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/locations/list"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentExtendedService, times(1)).findById(anyLong());
        verify(studentExtendedService, times(1)).findLocationsByStudentId(anyLong());

         */
    }

    @Test
    public void test_newStudentLocation() throws Exception {
        assertEquals(1,0);
        /*
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/locations/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/locations/locationform"))
                .andExpect(model().attributeExists("location"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_deleteStudentLocation() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(studentDtoOptional);

        //then
        mockMvc.perform(get("/students/1/locations/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/students/locations"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findById(anyLong());
        verify(studentExtendedService, times(1)).deleteByStudentIdAndLocationId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_showStudentLocation() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Optional<LocationDto> locationDtoOptional = Optional.of(new LocationDto());

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentExtendedService.findByStudentIdAndLocationId(anyLong(),anyLong())).thenReturn(locationDtoOptional);

        //then
        mockMvc.perform(get("/students/1/locations/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/locations/show"))
                .andExpect(model().attributeExists("location"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentExtendedService, times(1)).findById(anyLong());
        verify(studentExtendedService, times(1)).findByStudentIdAndLocationId(anyLong(),anyLong());

         */
    }

    @Test
    public void test_listStudentSlots() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Set<SlotDto> slotsDto = new HashSet<SlotDto>();

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentExtendedService.findSlotsByStudentId(anyLong())).thenReturn(slotsDto);

        //then
        mockMvc.perform(get("/students/1/slots"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/slots/list"))
                .andExpect(model().attributeExists("slots"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentExtendedService, times(1)).findById(anyLong());
        verify(studentExtendedService, times(1)).findSlotsByStudentId(anyLong());

         */
    }

    @Test
    public void test_newStudentSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(Optional.of(studentDto));

        //then
        mockMvc.perform(get("/students/1/slots/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/slots/slotform"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_deleteStudentSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(studentDtoOptional);

        //then
        mockMvc.perform(get("/students/1/slots/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/students/slots"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

        verify(studentExtendedService, times(1)).findById(anyLong());
        verify(studentExtendedService, times(1)).deleteByStudentIdAndSlotId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_showStudentSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(studentExtendedService.findById(anyLong())).thenReturn(studentDtoOptional);
        when(studentExtendedService.findByStudentIdAndSlotId(anyLong(),anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/students/1/slots/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/slots/show"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(2));

        verify(studentExtendedService, times(1)).findById(anyLong());
        verify(studentExtendedService, times(1)).findByStudentIdAndSlotId(anyLong(),anyLong());

         */
    }
}
