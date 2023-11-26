package ru.javarush.island.ogarkov.view.javafx;

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

public class StatisticsView extends StackPane {
    private Rectangle background;
    private Rectangle diagram;
    private ImageView itemIcon;
    private Text aliveCount;
    private Text deadCount;
    private VBox lines;
    private HBox field;
    private final Color diagramMaxColor;
    private final Color diagramMiddleColor;
    private final Color diagramMinColor;
    private final Color statisticsColor;
    private final int statisticsLineHeight;
    private final int statisticsLineSpacing;
    private final int statisticsFieldWidth;
    private final int statisticsFieldHeight;
    private final int statisticsIconSize;

    public StatisticsView() {
        diagramMaxColor = Setting.get().getDiagramMaxColor();
        diagramMiddleColor = Setting.get().getDiagramMiddleColor();
        diagramMinColor = Setting.get().getDiagramMinColor();
        statisticsColor = Setting.get().getStatisticsColor();
        statisticsLineHeight = Setting.get().getStatisticsLineHeight();
        statisticsLineSpacing = Setting.get().getStatisticsLineSpacing();
        statisticsFieldHeight = statisticsLineHeight * 2 + Setting.get().getStatisticsFieldSpacing();
        statisticsFieldWidth = Setting.get().getStatisticsFieldWidth();
        statisticsIconSize = Setting.get().getStatisticsIconSize();
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
            diagram.setFill(diagramMaxColor);
        } else if (percent > 0.33) {
            diagram.setFill(diagramMiddleColor);
        } else diagram.setFill(diagramMinColor);

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
        background = createBackground(statisticsColor);
        diagram = createBackground(diagramMinColor);
        setAlignment(diagram, Pos.CENTER_LEFT);
        createField();
        getChildren().addAll(background, diagram, field);
    }

    private void createField() {
        StackPane itemIconPane = createItemIconPane();
        lines = createLines();
        field = new HBox(itemIconPane, lines);
        field.setSpacing(statisticsLineSpacing);
    }

    private Rectangle createBackground(Color color) {
        Rectangle background = new Rectangle();
        background.setFill(color);
        background.setWidth(statisticsFieldWidth);
        background.setHeight(statisticsFieldHeight);
        return background;
    }

    private void createCounts() {
        aliveCount = createText(FontWeight.BOLD);
        deadCount = createText(FontWeight.NORMAL);
    }

    private VBox createLines() {
        ImageView aliveIcon = createIcon(Setting.get().getAliveIcon());
        ImageView deadIcon = createIcon(Setting.get().getDeadIcon());
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
        ImageView icon = createIcon(statisticsLineHeight);
        icon.setImage(image);
        return icon;
    }

    private HBox createLine(ImageView icon, Text count) {
        HBox line = new HBox(icon, count);
        line.setSpacing(statisticsLineSpacing);
        line.setAlignment(Pos.CENTER_LEFT);
        return line;
    }

    private StackPane createItemIconPane() {
        itemIcon = createIcon(statisticsIconSize);
        StackPane itemIconPane = new StackPane(itemIcon);
        itemIconPane.setAlignment(Pos.CENTER);
        return itemIconPane;
    }

    private Text createText(FontWeight fontWeight) {
        Text text = new Text();
        text.setFont(Font.font("System", fontWeight, statisticsLineHeight));
        return text;
    }
}
