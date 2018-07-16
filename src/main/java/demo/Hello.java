package demo;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static javafx.collections.FXCollections.observableArrayList;

public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");
        HBox root = new HBox() {{
            getChildren().add(createTable1());
            getChildren().add(createTable2());
            getChildren().add(createTable3());
        }};
        primaryStage.setScene(new Scene(root, 600, 250));
        primaryStage.show();
    }

    private final ObservableList<String> data = observableArrayList("AAA", "BBB", "CCC");

    private TableView<String> createTable1() {
        TableView<String> table = new TableView<String>() {
            @Override
            public void resize(double width, double height) {
                super.resize(width, height);
                Pane header = (Pane) lookup("TableHeaderRow");
                header.setMinHeight(0);
                header.setPrefHeight(0);
                header.setMaxHeight(0);
                header.setVisible(false);
            }
        };

        table.getColumns().add(new TableColumn<String, String>("Name") {{
            setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));
        }});
        table.setItems(data);
        return table;
    }

    private TableView<String> createTable2() {
        return new TableView<String>() {{
            skinProperty().addListener((a, b, newSkin) -> {
                TableHeaderRow header = ((TableViewSkinBase) newSkin).getTableHeaderRow();
                header.setMinHeight(0);
                header.setPrefHeight(0);
                header.setMaxHeight(0);
                header.setVisible(false);
            });

            this.getColumns().add(new TableColumn<String, String>("Name") {{
                setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));
            }});
            this.setItems(data);
        }};
    }

    private TableView<String> createTable3() {
        return new TableView<String>() {{
            getStylesheets().add("hello.css");
            getStyleClass().add("no-header");

            this.getColumns().add(new TableColumn<String, String>("Name") {{
                setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));
            }});
            this.setItems(data);
        }};
    }
}
