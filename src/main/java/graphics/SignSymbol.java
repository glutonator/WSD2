package graphics;

import app.CarsApplication;
import jade.core.AID;
import javafx.scene.shape.Line;

public class SignSymbol {
    private Long y;
    private Long y2;
    private AID aid;
    private Line line;
    private Line line2;
    private static final double SYMBOL_LENGTH = GUIApp.SIGN_LINE_WIDTH;

    public SignSymbol(AID aid, Long y, Long y2) {
        this.y=y;
        this.y2=y2;

        this.aid = aid;
        line = new Line();

        double newY = GUIApp.ROAD_START_Y - (GUIApp.ROAD_START_Y - GUIApp.ROAD_END_Y) * y / CarsApplication.MAX_Y;
        line.setStartY(newY);
        line.setEndY(newY);

        line.setStartX(GUIApp.LANE_SIGN_X);
        line.setEndX(GUIApp.LANE_SIGN_X+SYMBOL_LENGTH);
        line.setStroke(SymbolColor.random().value());
        line.setStrokeWidth(10.0);
        line.toFront();

        line2 = new Line();

        double newY2 = GUIApp.ROAD_START_Y - (GUIApp.ROAD_START_Y - GUIApp.ROAD_END_Y) * y2 / CarsApplication.MAX_Y;
        line2.setStartY(newY2);
        line2.setEndY(newY2);

        line2.setStartX(GUIApp.LANE_SIGN_X);
        line2.setEndX(GUIApp.LANE_SIGN_X+SYMBOL_LENGTH);
        line2.setStroke(SymbolColor.random().value());
        line2.setStrokeWidth(10.0);
        line2.toFront();
    }

    public Line getLine() {
        return line;
    }

    public Line getLine2() {
        return line2;
    }

    public AID getAid() {
        return aid;
    }

//    public void translate(Long x, Long y) {
//        double newY = GUIApp.ROAD_START_Y - (GUIApp.ROAD_START_Y - GUIApp.ROAD_END_Y) * y / CarsApplication.MAX_Y;
//        line.setStartY(newY);
//        line.setEndY(newY);
//        if (!x.equals(this.x)) {
//            if (x == 1) {
//                moveToRightLane();
//            } else if (x == 2) {
//                moveToLeftLane();
//            }
//            this.x = x;
//        }
//
//        line.toFront();
//    }

}
