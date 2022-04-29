package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.services.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LocationControllerTest {

    @Mock
    LocationService locationService;

    @Mock
    ConvertObjectToObject<Location, LocationDto> convertToDto;
    @Mock
    ConvertObjectToObject<LocationDto, Location> convertToEntity;


    @Mock
    Model model;

    LocationController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new LocationController(locationService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(view().name("locations/index"));
    }

    @Test
    public void requestListLocations() throws Exception {
        //given
        Set<LocationDto> locationsDto = new HashSet<>();

        LocationDto locationDto1 = new LocationDto();
        locationDto1.setId(1L);
        locationsDto.add(locationDto1);

        LocationDto locationDto2 = new LocationDto();
        locationDto2.setId(2L);
        locationsDto.add(locationDto2);


        when(convertToDto.convert(locationService.findAll())).thenReturn(locationsDto);

        ArgumentCaptor<Set<LocationDto>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.listLocations(model);

        //then
        assertEquals("locations/index", viewName);
        verify(model, times(1)).addAttribute(eq("locations"), argumentCaptor.capture());
        Set<LocationDto> setInController = argumentCaptor.getValue();
        assertEquals(locationsDto.size(), setInController.size());
    }

    @Test
    public void requestShowById() throws Exception {
        //given
        LocationDto locationDto = new LocationDto();
        locationDto.setId(1L);

        when(convertToDto.convert(locationService.findById(1L))).thenReturn(locationDto);

        ArgumentCaptor<LocationDto> argumentCaptor = ArgumentCaptor.forClass(LocationDto.class);

        //when
        String id = Long.valueOf(1L).toString();
        String viewName = controller.showById(id, model);

        //then
        assertEquals("locations/show", viewName);
        verify(model, times(1)).addAttribute(eq("location"), argumentCaptor.capture());
        LocationDto inController = argumentCaptor.getValue();
        assertEquals(Optional.of(1L), Optional.ofNullable(inController.getId()));
    }


    @Test
    public void getNewLocation() throws Exception {
        ///////// TODO
        //given

        //when

        //then
        assertEquals(1,0);
    }

    @Test
    public void getUpdateLocation() throws Exception {
        ///////// TODO
        //given

        //when

        //then
        assertEquals(1,0);

    }

    @Test
    public void postSaveOrUpdate() throws Exception {
        ///////// TODO
        //given

        //when

        //then
        assertEquals(1,0);

    }

    @Test
    public void getDeleteById() throws Exception {
        ///////// TODO
        //given

        //when

        //then
        assertEquals(1,0);
    }

}
