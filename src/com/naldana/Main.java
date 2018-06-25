package com.naldana;

import com.naldana.data.dao.Usuarios;
import com.naldana.data.entidades.Usuario;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");

        // Creando formulario de loginScene

        // User Edit
        Label userLabel = new Label("User:");
        TextField user = new TextField();
        HBox userLayout = new HBox(10);
        userLayout.setPadding(new Insets(0, 10, 0, 0));
        userLayout.setAlignment(Pos.CENTER_RIGHT);
        userLayout.getChildren().addAll(userLabel, user);


        // Password
        Label passwordLabel = new Label("Password:");
        PasswordField password = new PasswordField();
        HBox passwordLayout = new HBox(10);
        passwordLayout.setAlignment(Pos.CENTER_RIGHT);
        passwordLayout.getChildren().addAll(passwordLabel, password);

        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new Insets(10, 0, 0, 10));

        Button loginButton = new Button("Iniciar");

        loginButton.setOnAction(event -> {
            Usuario usuario = new Usuario();
            usuario.setUser(user.getText());
            usuario.setPassword(password.getText());
            Usuarios controller = new Usuarios();


            try {
                controller.login(usuario);
                System.out.println(usuario.getId());
            } catch (Exception e) {
                System.out.println("El usuario no o contraseña no coin...");
            }

        });

        loginLayout.getChildren().addAll(userLayout, passwordLayout, loginButton);
        loginLayout.setAlignment(Pos.CENTER);

        Scene loginScene = new Scene(loginLayout, 300, 300);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
