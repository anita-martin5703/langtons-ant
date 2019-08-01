package edu.cnm.deepdive.langton;

import edu.cnm.deepdive.langton.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application {

    private static final String RESOURCES_DIR = "resources";
    private static final String LAYOUT_RESOURCE = RESOURCES_DIR + "/main.fxml";
    private static final String RESOURCE_BUNDLE_BASE_NAME = RESOURCES_DIR + "/strings";
    private static final String WINDOW_TITLE_KEY = "window_title";
    private MainController controller;
    private static final String ICON_RESOURCE = RESOURCES_DIR + "/ant.png";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource(LAYOUT_RESOURCE), bundle);
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setTitle(bundle.getString(WINDOW_TITLE_KEY));
        stage.getIcons().add(new Image(Objects.requireNonNull(classLoader.getResourceAsStream(ICON_RESOURCE))));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        controller.stop();
        super.stop();
    }
}
