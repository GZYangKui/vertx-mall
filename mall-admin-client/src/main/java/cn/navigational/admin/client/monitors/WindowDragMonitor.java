package cn.navigational.admin.client.monitors;

import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

public class WindowDragMonitor {

    private double xOffset = 0;
    private double yOffset = 0;

    private WindowDragMonitor(Window window, Node node) {

        node.addEventHandler(EventType.ROOT, e -> {
            if (!(e instanceof MouseEvent)) {
                return;
            }

            final MouseEvent event = (MouseEvent) e;

            event.consume();

            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {

                xOffset = event.getSceneX();
                yOffset = event.getSceneY();

            } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {

                window.setX(event.getScreenX() - xOffset);

                if (event.getScreenY() - yOffset < 0) {
                    window.setY(0);
                } else {
                    window.setY(event.getScreenY() - yOffset);
                }

            }
        });
    }

    public static void register(Window window, Node node) {
        new WindowDragMonitor(window, node);
    }
}
