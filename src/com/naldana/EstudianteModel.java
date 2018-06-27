package com.naldana;


import com.naldana.data.entidades.Estudiante;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class EstudianteModel {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty apellido;

    public static List<EstudianteModel> toModel(List<Estudiante> estudiantes){
        List<EstudianteModel> estudianteModels = new ArrayList<>();

        for (Estudiante e : estudiantes) {
            estudianteModels.add(new EstudianteModel(e));
        }

        return estudianteModels;
    }
    public EstudianteModel(int id, String nombre, String apellido) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
    }

    public EstudianteModel(Estudiante e) {
        this.id = new SimpleIntegerProperty(e.getId());
        this.nombre = new SimpleStringProperty(e.getNombre());
        this.apellido = new SimpleStringProperty(e.getApellido());
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public SimpleStringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }
}
