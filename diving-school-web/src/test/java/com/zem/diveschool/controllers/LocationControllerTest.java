package com.zem.diveschool.controllers;

import com.zem.diveschool.data.LocationDtoService;
import com.zem.diveschool.dto.LocationDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LocationControllerTest {

    @Mock
    LocationDtoService locationDtoService;

    LocationController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new LocationController(locationDtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(view().name("locations/index"));
    }

    @Test
    public void test_listLocations() throws Exception {
        //given
        Set<LocationDto> locationsDto = new HashSet<>();

        //when
        when(locationDtoService.findAll()).thenReturn(locationsDto);

        //then
        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(view().name("locations/index"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(model().size(1));

        verify(locationDtoService, times(1)).findAll();
    }

    @Test
    public void test_showById() throws Exception {
        //given
        LocationDto locationDto = new LocationDto();

        //when
        when(locationDtoService.findById(anyLong())).thenReturn(Optional.of(locationDto));

        //then
        mockMvc.perform(get("/locations/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("locations/show"))
                .andExpect(model().attributeExists("location"))
                .andExpect(model().size(1));

        verify(locationDtoService, times(1)).findById(anyLong());
    }

    @Test
    public void test_newLocation() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/locations/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("locations/locationform"))
                .andExpect(model().attributeExists("location"))
                .andExpect(model().size(1));
    }

    @Test
    public void test_updateLocation() throws Exception {
        //given
        LocationDto locationDto = new LocationDto();

        //when
        when(locationDtoService.findById(anyLong())).thenReturn(Optional.of(locationDto));

        //then
        mockMvc.perform(get("/locations/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("locations/locationform"))
                .andExpect(model().attributeExists("location"))
                .andExpect(model().size(1));

        verify(locationDtoService, times(1)).findById(anyLong());
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        //given
        LocationDto locationDto = new LocationDto();
        locationDto.setId(2L);

        //when
        when(locationDtoService.save(any())).thenReturn(locationDto);

        //then
        mockMvc.perform(post("/locations")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("streetAddress", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/locations/2/show"))
                .andExpect(model().size(1));

        verify(locationDtoService, times(1)).save(any());
    }


    @Test
    public void test_deleteById() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/locations/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/locations"));

        verify(locationDtoService, times(1)).deleteById(anyLong());
    }
 }
