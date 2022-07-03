package ru.javarush.island.ogarkov.view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ru.javarush.island.ogarkov.settings.Setting;

import static ru.javarush.island.ogarkov.settings.Setting.*;

public class StatisticsView extends StackPane {
    private Rectangle background;
    private Rectangle diagram;
    private ImageView itemIcon;
    private Text aliveCount;
    private Text deadCount;
    private VBox lines;
    private HBox field;

    public StatisticsView() {
        init();
    }

    public void updateView(Image itemIcon, String aliveCount, String deadCount) {
        this.itemIcon.setImage(itemIcon);
        this.aliveCount.setText(aliveCount);
        this.deadCount.setText(deadCount);
    }

    public void updateDiagram(double percent) {
        percent = percent > 1 ?
                1 : percent < 0 ?
                0 : percent;

        if (percent > 0.66) {
            diagram.setFill(DIAGRAM_MAX_COLOR);
        } else if (percent > 0.33) {
            diagram.setFill(DIAGRAM_MIDDLE_COLOR);
        } else diagram.setFill(DIAGRAM_MIN_COLOR);

        if (percent > 0) {
            diagram.setWidth(background.getWidth() * percent);
        } else {
            diagram.setWidth(background.getWidth());
        }
    }

    public double getBackgroundHeight() {
        return background.getHeight();
    }

    private void init() {
        createCounts();
        background = createBackground(STATISTICS_COLOR);
        diagram = createBackground(DIAGRAM_MIN_COLOR);
        setAlignment(diagram, Pos.CENTER_LEFT);
        createField();
        getChildren().addAll(background, diagram, field);
    }

    private void createField() {
        StackPane itemIconPane = createItemIconPane();
        lines = createLines();
        field = new HBox(itemIconPane, lines);
        field.setSpacing(Setting.STATISTICS_LINE_SPACING);
    }

    private Rectangle createBackground(Color color) {
        Rectangle background = new Rectangle();
        background.setFill(color);
        background.setWidth(STATISTICS_FIELD_WIDTH);
        background.setHeight(STATISTICS_FIELD_HEIGHT);
        return background;
    }

    private void createCounts() {
        aliveCount = createText(FontWeight.BOLD);
        deadCount = createText(FontWeight.NORMAL);
    }

    private VBox createLines() {
        ImageView aliveIcon = createIcon(ALIVE_OVERLAY);
        ImageView deadIcon = createIcon(DEAD_OVERLAY);
        HBox aliveLine = createLine(aliveIcon, aliveCount);
        HBox deadLine = createLine(deadIcon, deadCount);
        lines = new VBox(aliveLine, deadLine);
        return lines;
    }

    private ImageView createIcon(int iconSize) {
        ImageView icon = new ImageView();
        icon.setFitWidth(iconSize);
        icon.setFitHeight(iconSize);
        return icon;
    }

    private ImageView createIcon(Image image) {
        ImageView icon = createIcon(Setting.STATISTICS_LINE_HEIGHT);
        icon.setImage(image);
        return icon;
    }

    private HBox createLine(ImageView icon, Text count) {
        HBox line = new HBox(icon, count);
        line.setSpacing(Setting.STATISTICS_LINE_SPACING);
        line.setAlignment(Pos.CENTER_LEFT);
        return line;
    }

    private StackPane createItemIconPane() {
        itemIcon = createIcon(Setting.STATISTICS_ICON_SIZE);
        StackPane itemIconPane = new StackPane(itemIcon);
        itemIconPane.setAlignment(Pos.CENTER);
        return itemIconPane;
    }

    private Text createText(FontWeight fontWeight) {
        Text text = new Text();
        text.setFont(Font.font("System", fontWeight, STATISTICS_LINE_HEIGHT));
        return text;
    }
}
