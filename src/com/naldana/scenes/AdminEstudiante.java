package com.naldana.scenes;

import com.naldana.EstudianteModel;
import com.naldana.data.dao.EstudiantesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AdminEstudiante {

    public VBox content() {
        EstudiantesDAO estudiantesDAO = new EstudiantesDAO();

        final ObservableList<EstudianteModel> data
                = FXCollections.observableArrayList(
                EstudianteModel.toModel(estudiantesDAO.findAll()));

        Label etiquieta = new Label("Administrar Estudiantes");
        etiquieta.setFont(new Font("Arial", 20));
        TableView table = new TableView();

        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(
                new PropertyValueFactory<EstudianteModel, Integer>("id"));
        TableColumn nombre = new TableColumn("Nombre");
        nombre.setCellValueFactory(
                new PropertyValueFactory<EstudianteModel, String>("nombre"));
        TableColumn apellido = new TableColumn("Apellido");
        apellido.setCellValueFactory(
                new PropertyValueFactory<EstudianteModel, String>("apellido"));

        table.setItems(data);

        table.getColumns().addAll(id, nombre, apellido);

        VBox administrador = new VBox(10);
        administrador.getChildren().addAll(etiquieta, table);
        return  administrador;
    }
}
