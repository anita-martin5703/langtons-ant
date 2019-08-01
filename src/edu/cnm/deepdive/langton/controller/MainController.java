package edu.cnm.deepdive.langton.controller;

import edu.cnm.deepdive.langton.model.Terrain;
import edu.cnm.deepdive.langton.view.TerrainView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;

import java.util.Random;

public class MainController {

    @FXML   private Slider populationSize;
    @FXML   private Button resetButton;
    @FXML   private Slider speedSlider;
    @FXML   private TerrainView terrainView;
    @FXML   private ToggleButton runToggle;

    private boolean running;
    private Terrain terrain;
    private AnimationTimer timer;

    @FXML
    private void initialize() {
        terrain = new Terrain(1, new Random());
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                terrainView.draw(terrain.getPatches());
            }
        };
    }

    @FXML
    private void toggleRun(ActionEvent actionEvent) {
        if (runToggle.isSelected()) {
            start();
        } else {
            stop();
        }
    }

    private void start() {
        running = true;
        resetButton.setDisable(false);
        timer.start();
        new Runner().start();
    }

    public void stop() {
        runToggle.setDisable(true);
        running = false;
        timer.stop();
    }

    @FXML
    public void reset() {
        int newPopulation = (int) populationSize.getValue();
        terrain = new Terrain(newPopulation, new Random());
        terrainView.draw(terrain.getPatches());
    }

    private class Runner extends Thread {

        @Override
        public void run() {
            while (running) {
                int speedSelection = (int) speedSlider.getValue() * -1;
                for (int i = 0; i < 10; i++) {
                    terrain.tick();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // DO NOTHING! GET ON WITH YOUR LIFE!
                }
            }
            runToggle.setDisable(false);
            resetButton.setDisable(false);
        }
    }
}
