package com.zem.diveschool.controllers;

import com.zem.diveschool.data.StudentDtoService;
import com.zem.diveschool.dto.StudentDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
                .andExpect(model().attributeExists("students"));
    }

    @Test
    public void test_showById() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(studentDto);

        //then
        mockMvc.perform(get("/students/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/show"))
                .andExpect(model().attributeExists("student"));
    }


    @Test
    public void test_newStudent() throws Exception {
        StudentDto instructorDto = new StudentDto();

        mockMvc.perform(get("/students/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/studentform"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    public void test_updateStudent() throws Exception {
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
                .andExpect(view().name("redirect:/students/2/show"));
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setId(2L);

        //when
        when(studentDtoService.findById(anyLong())).thenReturn(studentDto);

        //then
        mockMvc.perform(get("/students/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/studentform"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    public void test_deleteById() throws Exception {
        mockMvc.perform(get("/students/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/students"));

        verify(studentDtoService, times(1)).deleteById(anyLong());
    }

    @Test
    public void test_listStudentCards() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_showStudentCard() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_listStudentLocations() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_showStudentLocation() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_listStudentSlots() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_showStudentSlot() throws Exception {
        // TODO
        assertEquals(1, 0);
    }
}
