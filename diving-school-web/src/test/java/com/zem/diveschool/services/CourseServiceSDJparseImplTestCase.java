package com.zem.diveschool.services;

import com.zem.diveschool.converters.ConverterDtoEntityService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.repositories.CourseRepository;
import com.zem.diveschool.persistence.services.impl.springdatajpa.CourseServiceSDJpaImpl;
//import org.aspectj.lang.annotation.Before;
//import org.junit.Before;
import org.mockito.Mock;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;

public class CourseServiceSDJparseImplTestCase {

    CourseServiceSDJpaImpl courseService;

    @Mock
    CourseRepository courseRepository;

    @Mock
    ConverterDtoEntityService<Course, CourseDto> convertCourseToDto;

    @Mock
    ConverterDtoEntityService<CourseDto, Course> convertCourseToEntity;

    /*
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        courseService = new CourseServiceSDJpaImpl(courseRepository);
    }
     */
// TODO

}
