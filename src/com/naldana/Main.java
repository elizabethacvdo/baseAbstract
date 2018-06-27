package com.naldana;

import com.naldana.data.dao.UsuariosDao;
import com.naldana.data.entidades.Usuario;
import com.naldana.scenes.AdminEstudiante;
import com.naldana.scenes.LoginScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");



        // Creando formulario de loginScene
        LoginScene loginConent = new LoginScene() {
            @Override
            public void onLogin(String user, String password) {
                Usuario usuario = new Usuario();
                usuario.setUser(user);
                usuario.setPassword(password);
                UsuariosDao controller = new UsuariosDao();

                try {
                    controller.login(usuario);
                    if (usuario.getId() != 0) {
                        AdminEstudiante adminEstudianteContent = new AdminEstudiante();
                        Scene adimEstudianteScene = new Scene(adminEstudianteContent.content());
                        primaryStage.setScene(adimEstudianteScene);
                    }
                } catch (Exception e) {
                    System.out.println("El usuario no o contraseña no coin...");
                }
            }
        };
        Scene loginScene = new Scene(loginConent.content(), 300, 300);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
