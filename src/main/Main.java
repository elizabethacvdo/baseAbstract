package main;

import Dao.Estudiantes;
import entidades.Estudiante;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String... args) {
        Estudiantes estudiantesDao = new Estudiantes();

        ArrayList<Estudiante> estudiantes = estudiantesDao.getByNombre("%a%");
        estudiantes.forEach(e -> {
            System.out.println(e.getId() + "|" + e.getNombre() + "|" + e.getApellido() + "|" + e.getFechaNacimiento()) ;
        });

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("POLLITO");
        estudiante.setApellido("PIO");
        estudiante.setFechaNacimiento(new Date());

       // estudiantesDao.insert(estudiante);
    }
}
