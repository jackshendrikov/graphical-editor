package ua.jackshen.composite;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Jack Shendrikov
 */
public class Main extends Application {

    private GraphicsContext g;
    private File file;
    private Canvas area;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8;
    private ColorPicker fillColor, strokeColor;
    private FileChooser saveImageFileChooser;
    private TextField xPosField, yPosField, zPosField, widthField, heightField, strokeField;

    private final static int CANVAS_WIDTH = 900;
    private final static int CANVAS_HEIGHT = 850;

    private LinkedList<GraphicShape> shapeList = new LinkedList<>();

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // =================================================== MENU BAR ====================================================
        MenuBar menuBar = new MenuBar();
        SeparatorMenuItem divider = new SeparatorMenuItem();

        // Create menus
        Menu fileMenu = new Menu("File");

        // Create FileMenu items
        MenuItem newCanvasItem = new MenuItem("New Canvas");
        MenuItem saveImageItem = new MenuItem("Save Image");
        MenuItem exitItem = new MenuItem("Exit");


        // Add menu items to Menus
        fileMenu.getItems().addAll(newCanvasItem, saveImageItem, divider, exitItem);

        menuBar.getMenus().addAll(fileMenu);

        // Functions
        newCanvasItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        newCanvasItem.setOnAction(e -> {
            g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
            shapeList.clear();
            xPosField.clear();
            yPosField.clear();
            zPosField.clear();
            widthField.clear();
            heightField.clear();
            strokeField.clear();
            strokeColor.setValue(Color.BLACK);
            fillColor.setValue(Color.TRANSPARENT);
        });

        saveImageItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveImageItem.setOnAction(e -> {
            saveImageFileChooser = new FileChooser();

            // set extension filter
            FileChooser.ExtensionFilter pngExtFilter = new FileChooser.ExtensionFilter("PNG Files / *.png", "*.png");
            saveImageFileChooser.getExtensionFilters().add(pngExtFilter);

            // show save file dialog
            file = saveImageFileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                try {
                    WritableImage writableImage = new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
                    area.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        exitItem.setOnAction(e -> {
            AlertBox.confirm("Exit Graphic Editor", "Exit Graphic Editor", "Are you sure you want to quit Graphic Editor?");
            System.exit(0);
        });

        // ================================================= END MENU BAR ==================================================
        // ==================================================== PANELS =====================================================
        GridPane shapeChooser = new GridPane();
        shapeChooser.setPadding(new Insets(30, 0, 0, 20));
        shapeChooser.setPrefWidth(180);

        VBox shapeButtons = new VBox();
        ToggleGroup shapesRadioButtons = new ToggleGroup();

        rb1 = new RadioButton("Rectangle");
        rb2 = new RadioButton("Oval");
        rb3 = new RadioButton("Line");
        rb4 = new RadioButton("Triangle");
        rb5 = new RadioButton("Circle");
        rb6 = new RadioButton("Ornament");
        rb7 = new RadioButton("Brush");
        rb8 = new RadioButton("Pencil");
        rb1.setToggleGroup(shapesRadioButtons);
        rb2.setToggleGroup(shapesRadioButtons);
        rb3.setToggleGroup(shapesRadioButtons);
        rb4.setToggleGroup(shapesRadioButtons);
        rb5.setToggleGroup(shapesRadioButtons);
        rb6.setToggleGroup(shapesRadioButtons);
        rb7.setToggleGroup(shapesRadioButtons);
        rb8.setToggleGroup(shapesRadioButtons);

        shapeButtons.setSpacing(15);
        shapeButtons.getChildren().addAll(rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8);
        GridPane.setConstraints(shapeButtons, 0, 0);

        shapeChooser.getChildren().addAll(shapeButtons);

        // Panel for shape properties ======================================================================================
        GridPane shapeProperties = new GridPane();
        shapeProperties.setPadding(new Insets(30, 20, 10, 20));
        shapeProperties.setVgap(20);
        shapeProperties.setPrefWidth(200);

        VBox posProperties = new VBox();
        posProperties.setSpacing(5);

        Label xPosFieldLabel = new Label("Enter X Position:");
        xPosField = new TextField();

        Label yPosFieldLabel = new Label("Enter Y Position:");
        yPosField = new TextField();

        Label zPosFieldLabel = new Label("Enter Z Position:");
        zPosField = new TextField();

        posProperties.getChildren().addAll(xPosFieldLabel, xPosField, yPosFieldLabel, yPosField, zPosFieldLabel, zPosField);
        GridPane.setConstraints(posProperties, 0, 0);

        VBox sizeProperties = new VBox();
        sizeProperties.setSpacing(5);

        Label widthFieldLabel = new Label("Enter Width:");
        widthField = new TextField();

        Label heightFieldLabel = new Label("Enter Height:");
        heightField = new TextField();

        Label strokeFieldLabel = new Label("Enter Stroke width:");
        strokeField = new TextField();

        sizeProperties.getChildren().addAll(widthFieldLabel, widthField, heightFieldLabel, heightField, strokeFieldLabel, strokeField);
        GridPane.setConstraints(sizeProperties, 0, 1);

        VBox colorProperties = new VBox();

        Label fillColorLabel = new Label("Choose fill color:");
        fillColor = new ColorPicker(Color.TRANSPARENT);
        fillColor.setPrefWidth(200);

        Label strokeColorLabel = new Label("Choose stroke color:");
        strokeColor = new ColorPicker(Color.BLACK);
        strokeColor.setPrefWidth(200);

        // Color picker functionality
        fillColor.setOnAction(e -> g.setFill(fillColor.getValue()));
        strokeColor.setOnAction(e -> g.setStroke(strokeColor.getValue()));

        colorProperties.setSpacing(5);
        colorProperties.getChildren().addAll(fillColorLabel, fillColor, strokeColorLabel, strokeColor);
        GridPane.setConstraints(colorProperties, 0, 2);

        // Button to draw the shape
        Button drawButton = new Button("Draw Shape");
        GridPane.setConstraints(drawButton, 0, 3);
        drawButton.setPrefWidth(200);

        // Button to print the shape list to console
        Button printListButton = new Button("Print List");
        GridPane.setConstraints(printListButton, 0, 4);
        printListButton.setPrefWidth(200);

        shapesRadioButtons.selectedToggleProperty().addListener((ob, o, n) -> {
            // ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle
            if (rb1.isSelected() || rb2.isSelected() || rb3.isSelected() || rb4.isSelected() || rb5.isSelected()
                    || rb6.isSelected() || rb7.isSelected() || rb8.isSelected()) {
                shapeList.clear();
                xPosField.clear();
                yPosField.clear();
                zPosField.clear();
                widthField.clear();
                heightField.clear();
                strokeField.clear();
            }

            if (rb1.isSelected() || rb2.isSelected()) {
                shapeList.clear();
                xPosField.clear();
                yPosField.clear();
                zPosField.clear();
                widthField.clear();
                heightField.clear();
                strokeField.clear();
                zPosField.setText("0");
            } else if (rb3.isSelected()) {
                zPosField.setText("0");
                heightField.setText("0");
            } else if (rb4.isSelected()) {
                widthField.setText("0");
                heightField.setText("0");
            } else if (rb5.isSelected()) {
                zPosField.setText("0");
                heightField.setText("0");
            } else if (rb6.isSelected()) {
                xPosField.setText("0");
                yPosField.setText("0");
                zPosField.setText("0");
                widthField.setText("0");
                heightField.setText("0");
                strokeField.setText("0");
            } else if (rb7.isSelected()) {
                xPosField.setText("0");
                yPosField.setText("0");
                zPosField.setText("0");
                widthField.setText("0");
                heightField.setText("0");
                strokeField.setText("20");
                fillColor.setValue(Color.TRANSPARENT);
            } else if (rb8.isSelected()) {
                xPosField.setText("0");
                yPosField.setText("0");
                zPosField.setText("0");
                widthField.setText("0");
                heightField.setText("0");
                strokeField.setText("15");
                fillColor.setValue(Color.TRANSPARENT);
            }
        });

        drawButton.setOnAction(e -> {
            int x, y, z, w, h, stroke;
            try {
                x = Integer.parseInt(xPosField.getText());
                y = Integer.parseInt(yPosField.getText());
                z = Integer.parseInt(zPosField.getText());
                w = Integer.parseInt(widthField.getText());
                h = Integer.parseInt(heightField.getText());
                stroke = Integer.parseInt(strokeField.getText());

                if (x >= g.getCanvas().getWidth() || y >= g.getCanvas().getHeight() || w >= g.getCanvas().getWidth() || h >= g.getCanvas().getHeight()) {
                    AlertBox.warning("Too Big Properties", "Too Big Properties", "Please enter more smaller properties for the shape!");
                } else {
                    if (rb1.isSelected()) {
                        shapeList.add(new Rectangle(x, y, w, h, stroke, fillColor.getValue(), strokeColor.getValue()));
                    } else if (rb2.isSelected()) {
                        shapeList.add(new Oval(x, y, w, h, stroke, fillColor.getValue(), strokeColor.getValue()));
                    } else if (rb3.isSelected()) {
                        shapeList.add(new Line(x, y, w, stroke, strokeColor.getValue()));
                    } else if (rb4.isSelected()) {
                        shapeList.add(new Triangle(x, y, z, stroke, fillColor.getValue(), strokeColor.getValue()));
                    } else if (rb5.isSelected()) {
                        shapeList.add(new Circle(x, y, w, stroke, fillColor.getValue(), strokeColor.getValue()));
                    } else if (rb6.isSelected()) {
                        shapeList.add(new Ornament());
                    } else if (rb7.isSelected()) {
                        shapeList.add(new Brush(area, stroke, strokeColor.getValue()));
                    } else if (rb8.isSelected()) {
                        shapeList.add(new Pencil(area, stroke, strokeColor.getValue()));
                    }
                }
            } catch (Exception exc) {
                AlertBox.warning("Empty Fields", "Empty Fields", "To continue please fill Position and Size fields!");
            }

            drawShape(g);
        });

        printListButton.setOnAction(e -> {
            if (shapeList.size() == 0) {
                System.out.print("Empty list!");
            }
            for (GraphicShape aShapeList : shapeList) {
                System.out.println(aShapeList.getClass().toString());
            }
        });

        // Add all elements to panel
        shapeProperties.getChildren().addAll(posProperties, sizeProperties, colorProperties, drawButton, printListButton);

        // Area for drawing the shapes =====================================================================================
        StackPane drawingArea = new StackPane();
        drawingArea.setStyle("-fx-background-color: #eee");
        area = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        g = area.getGraphicsContext2D();
        drawingArea.getChildren().add(area);


        // ================================================== END PANELS ===================================================
        // ================================================ Layout Settings ================================================
        root.setTop(menuBar);
        root.setLeft(shapeChooser);
        root.setRight(shapeProperties);
        root.setCenter(drawingArea);

        Scene scene = new Scene(root, 1300, 850);
        scene.getStylesheets().addAll(this.getClass().getResource("Main.css").toExternalForm());
        primaryStage.getIcons().add(new Image("https://i.imgur.com/C4GX4OS.png"));
        primaryStage.setTitle("Graphical Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawShape(GraphicsContext g) {
        for (GraphicShape aShapeList : shapeList) {
            aShapeList.drawShape(g);
        }
    }

    public static void main(String args[]) {
        launch(args);
    }
}
