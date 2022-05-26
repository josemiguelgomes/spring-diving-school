package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.InstructorConverter;
import com.zem.diveschool.converters.impl.simple.LocationConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.data.InstructorExtendedService;

import com.zem.diveschool.data.SlotExtendedService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class InstructorControllerTest {

    @Mock
    InstructorExtendedService service;

    @Mock
    SlotExtendedService slotService;

    @Mock
    InstructorConverter converter;

    @Mock
    LocationConverter locationConverter;

    @Mock
    SlotConverter slotConverter;

    InstructorController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new InstructorController(service, slotService, converter, locationConverter, slotConverter);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/instructors"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/index"));
    }

    @Test
    public void test_listInstructors() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Set<InstructorDto> instructorsDto = new HashSet<>();

        //when
        when(instructorExtendedService.findAll()).thenReturn(instructorsDto);

        //then
        mockMvc.perform(get("/instructors"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/index"))
                .andExpect(model().attributeExists("instructors"))
                .andExpect(model().size(1));

        verify(instructorExtendedService, times(1)).findAll();
         */
    }

    @Test
    public void test_showById() throws Exception {
        assertEquals(1,0);
        /*
        //given
        InstructorDto instructorDto = new InstructorDto();

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(Optional.of(instructorDto));

        //then
        mockMvc.perform(get("/instructors/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/show"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(1));

        verify(instructorExtendedService, times(1)).findById(anyLong());
         */
    }

    @Test
    public void test_newInstructor() throws Exception {
        assertEquals(1,0);
        /*
        //given

        //when

        //then
        mockMvc.perform(get("/instructors/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/instructorform"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(1));

         */
    }

    @Test
    public void test_updateInstructor() throws Exception {
        assertEquals(1,0);
        /*
        //given
        InstructorDto instructorDto = new InstructorDto();

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(Optional.of(instructorDto));

        //then
        mockMvc.perform(get("/instructors/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/instructorform"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(1));

        verify(instructorExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        assertEquals(1,0);
        /*
        //given
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setId(2L);

        //when
        when(instructorExtendedService.save(any())).thenReturn(instructorDto);

        //then
        mockMvc.perform(post("/instructors")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("firstName", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/instructors/2/show"))
                .andExpect(model().size(1));   // ??????

        verify(instructorExtendedService, times(1)).save(any());

         */
    }

    @Test
    public void test_deleteById() throws Exception {
        assertEquals(1,0);
        /*
        //given

        //when

        //then
        mockMvc.perform(get("/instructors/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/instructors"))
                .andExpect(model().size(0));

        verify(instructorExtendedService, times(1)).deleteById(anyLong());

         */
    }

    /* ---- */

    @Test
    public void test_findInstructors() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Set<InstructorDto> instructorsDto = new HashSet<InstructorDto>();

        //when
        when(instructorExtendedService.findAll()).thenReturn(instructorsDto);

        //then
        mockMvc.perform(get("/instructors/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/find"))
                .andExpect(model().attributeExists("instructors"))
                .andExpect(model().size(1));

        verify(instructorExtendedService, times(1)).findAll();

         */
    }

    /* ---- */

    @Test
    public void test_listInstructorLocations() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<InstructorDto> instructorDtoOptional = Optional.of(new InstructorDto());
        Set<LocationDto> locationsDto = new HashSet<LocationDto>();

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(instructorDtoOptional);
        when(instructorExtendedService.findLocationsByInstructorId(anyLong())).thenReturn(locationsDto);

        //then
        mockMvc.perform(get("/instructors/1/locations"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/locations/list"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(2));

        verify(instructorExtendedService, times(1)).findById(anyLong());
        verify(instructorExtendedService, times(1)).findLocationsByInstructorId(anyLong());

         */
    }

    @Test
    public void test_newInstructorLocation() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<InstructorDto> instructorDtoOptional = Optional.of(new InstructorDto());

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(instructorDtoOptional);

        //then
        mockMvc.perform(get("/instructors/1/locations/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/locations/new"))
                .andExpect(model().attributeExists("location"))
                .andExpect(model().size(1));

        verify(instructorExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_deleteInstructorLocation() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<InstructorDto> instructorDtoOptional = Optional.of(new InstructorDto());

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(instructorDtoOptional);

        //then
        mockMvc.perform(get("/instructors/1/locations/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/instructors/locations"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(1));

        verify(instructorExtendedService, times(1)).findById(anyLong());
        verify(instructorExtendedService, times(1)).deleteByInstructorIdAndLocationId(anyLong(),
                anyLong());

         */
    }

    @Test
    public void test_showInstructorLocation() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<InstructorDto> instructorDtoOptional = Optional.of(new InstructorDto());
        Optional<LocationDto> locationDtoOptional = Optional.of(new LocationDto());

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(instructorDtoOptional);
        when(instructorExtendedService.findByInstructorIdAndLocationId(anyLong(), anyLong())).thenReturn(locationDtoOptional);

        //then
        mockMvc.perform(get("/instructors/1/locations/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/locations/show"))
                .andExpect(model().attributeExists("card"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(2));

        verify(instructorExtendedService, times(1)).findById(anyLong());
        verify(instructorExtendedService, times(1)).findByInstructorIdAndLocationId(anyLong(),
                anyLong());

         */
    }

    @Test
    public void test_listInstructorSlots() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<InstructorDto> instructorDtoOptional = Optional.of(new InstructorDto());
        Set<SlotDto> slotsDto = new HashSet<SlotDto>();

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(instructorDtoOptional);
        when(instructorExtendedService.findSlotsByInstructorId(anyLong())).thenReturn(slotsDto);

        //then
        mockMvc.perform(get("/instructors/1/slots"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/slots/list"))
                .andExpect(model().attributeExists("slots"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(2));

        verify(instructorExtendedService, times(1)).findById(anyLong());
        verify(instructorExtendedService, times(1)).findSlotsByInstructorId(anyLong());

         */
    }

    @Test
    public void test_newInstructorSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<InstructorDto> instructorDtoOptional = Optional.of(new InstructorDto());

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(instructorDtoOptional);

        //then
        mockMvc.perform(get("/instructors/1/slots/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/slots/new"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(1));

        verify(instructorExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_deleteInstructorSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<InstructorDto> instructorDtoOptional = Optional.of(new InstructorDto());

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(instructorDtoOptional);

        //then
        mockMvc.perform(get("/instructors/1/slots/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/instructors/slots"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(1));

        verify(instructorExtendedService, times(1)).findById(anyLong());
        verify(instructorExtendedService, times(1)).deleteByInstructorIdAndSlotId(anyLong(),
                anyLong());

         */
    }

    @Test
    public void test_showInstructorSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<InstructorDto> instructorDtoOptional = Optional.of(new InstructorDto());
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(instructorExtendedService.findById(anyLong())).thenReturn(instructorDtoOptional);
        when(instructorExtendedService.findByInstructorIdAndSlotId(anyLong(), anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/instructors/1/slots/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/slots/show"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(2));

        verify(instructorExtendedService, times(1)).findById(anyLong());
        verify(instructorExtendedService, times(1)).findByInstructorIdAndSlotId(anyLong(), anyLong());

         */
    }

}
