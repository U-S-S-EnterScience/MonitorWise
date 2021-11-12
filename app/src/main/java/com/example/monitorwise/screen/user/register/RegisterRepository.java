package com.example.monitorwise.screen.user.register;

import com.example.monitorwise.model.domain.course.Course;

import java.util.ArrayList;

public class RegisterRepository implements RegisterContract.Repository {



    @Override
    public ArrayList<Course> fetchCourse() {
        ArrayList<Course> data = new ArrayList<>();

        data.add (new Course("Análise e Desenvolvimento de Sistemas"));
        data.add (new Course("Comércio Exterior"));
        data.add (new Course("Gestão de Serviços"));
        data.add (new Course("Gestão Empresarial"));
        data.add (new Course("Logística Aeroportuária"));
        data.add (new Course("Rede de Computadores"));

        return data;

    }


}
