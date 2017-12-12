package graphics;

import app.CarsApplication;
import jade.core.AID;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import wsd.Agent2;
import wsd.Sign;

import java.util.ArrayList;

public class GUIApp extends Application {
    private static Group rootView = new Group();

    private static ArrayList<VehicleSymbol> vehicleSymbols = new ArrayList<>();
    private static VehicleSymbol findSymbolBy(AID aid) {
        return vehicleSymbols.stream().filter(vs -> vs.getAid().equals(aid)).findFirst().orElse(null);
    }

    public static void onSetup(AID aid, Long x) {
        VehicleSymbol symbol = new VehicleSymbol(aid, x);
        vehicleSymbols.add(symbol);
        Platform.runLater(() -> {
            rootView.getChildren().add(symbol.getLine());
            symbol.getLine().toFront();
        });
    }

    public static void onUpdateParameters(AID aid, Long x, Long y) {
        VehicleSymbol symbol = findSymbolBy(aid);
        if (symbol != null) {
            Platform.runLater(() -> symbol.translate(x, y));
        }
    }

    public static void onDelete(AID aid) {
        VehicleSymbol symbol = findSymbolBy(aid);
        if (symbol != null) {
            Platform.runLater(() -> rootView.getChildren().remove(symbol.getLine()));
        }
    }

    static final Long ROAD_START_Y = 550L;
    static final Long ROAD_END_Y = 50L;
    static final double LANE_LEFT_X = 50.0;
    static final double LANE_RIGHT_X = 160.0;
    static final double ROAD_WIDTH = 210.0;
    static final double MIDDLE_LINE_WIDTH = 10.0;

    private void drawRoad() {
        Rectangle roadRect = new Rectangle();
        roadRect.setX(LANE_LEFT_X);
        roadRect.setY(ROAD_END_Y);
        roadRect.setHeight(ROAD_START_Y - ROAD_END_Y);
        roadRect.setWidth(ROAD_WIDTH);
        roadRect.setFill(Color.BLACK);
        roadRect.setArcWidth(2.0);
        roadRect.setArcHeight(2.0);
        roadRect.toBack();
        rootView.getChildren().add(roadRect);
    }

    private void drawMiddleLine() {
        Rectangle lineRect = new Rectangle();
        lineRect.setX(LANE_RIGHT_X - MIDDLE_LINE_WIDTH);
        lineRect.setY(ROAD_END_Y);
        lineRect.setHeight(ROAD_START_Y - ROAD_END_Y);
        lineRect.setWidth(MIDDLE_LINE_WIDTH);
        lineRect.setFill(Color.WHITE);
        lineRect.toFront();
        rootView.getChildren().add(lineRect);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //
        System.out.print("test");
        //

        Scene scene = new Scene(rootView,800, 600);

        VBox newVehicleBox = new VBox(10);
        newVehicleBox.setLayoutX(300);
        newVehicleBox.setLayoutY(50);

        TextField newVehicleNameField = new TextField();
        newVehicleNameField.setPromptText("Name");
        newVehicleNameField.setFocusTraversable(false);

        TextField newVehicleSpeedField = new TextField();
        newVehicleSpeedField.setPromptText("Speed");
        newVehicleSpeedField.setFocusTraversable(false);

        TextField newVehicleMaxSpeedField = new TextField();
        newVehicleMaxSpeedField.setPromptText("MaxSpeed");
        newVehicleMaxSpeedField.setFocusTraversable(false);

        Button btnAddVehicle = new Button("New vehicle");
        String temp = "temp";
        //CarsApplication.createAgent(temp, Agent2.class.getName(), args9);

        btnAddVehicle.setOnMouseClicked(event -> {
            String speedStr = newVehicleSpeedField.getText();
            String name = newVehicleNameField.getText();
            String maxSpeed = newVehicleMaxSpeedField.getText();
            if (speedStr != null && !speedStr.isEmpty() && speedStr.matches("[0-9]+")
                    && name != null && !name.isEmpty()) {
                String[] args = {"speed:" + speedStr, "maxSpeed:"+maxSpeed};
                CarsApplication.createAgent(name, Agent2.class.getName(), args);
                //CarsApplication.createAgent("qwerty", Sign.class.getName(), args9);

            }
        });

        newVehicleBox.getChildren().addAll(newVehicleNameField, newVehicleSpeedField, newVehicleMaxSpeedField,  btnAddVehicle);

        rootView.getChildren().add(newVehicleBox);
        scene.setFill(Color.LIGHTGREY);
        drawRoad();
        drawMiddleLine();

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        //"y_begin: "+ y_begin+ "y_end: "+ y_end +", Limit_MaxSpeed: "
        String[] args9={"y_begin:" + "100", "y_end:" + "200", "maxSpeed:"+"70"};
        for(String i : args9) {
            System.out.println(i);
        }
        CarsApplication.createAgent("NNNZNAK", Sign.class.getName(), args9);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
