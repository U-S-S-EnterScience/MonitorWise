package com.example.monitorwise.screen.courses;

import com.example.monitorwise.model.domain.course.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Lucas Mosca on 09/11/2021.
 */
public class CoursesRepository implements CoursesContract.Repository {
    @Override
    public List<Course> fetchCourses() {
        return populateItems();
    }

    @Override
    public List<Course> populateItems() {
        List<Course> fatecCourses = new ArrayList<>();

        fatecCourses.add(new Course("Análise e Desenvolvimento de Sistemas"));
        fatecCourses.add(new Course("Comércio Exterior"));
        fatecCourses.add(new Course("Gestão de Serviços"));
        fatecCourses.add(new Course("Gestão Empresarioal"));
        fatecCourses.add(new Course("Logística Aeroportuária"));
        fatecCourses.add(new Course("Redes de Computadores"));

        return fatecCourses;
    }
}
