package com.edu.web.browserapp;

import com.edu.web.browserapp.service.IHistoryService;
import com.edu.web.browserapp.service.impl.ClientHistoryService;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class BrowserApp extends Application {

    private WebView webView;
    private TextField addressBar;
    private Label statusLabel;
    private IHistoryService historyService;

    @Override
    public void start(Stage stage) {
        historyService = ClientHistoryService.getInstance();

        webView = new WebView();
        addressBar = new TextField("https://www.google.com");
        statusLabel = new Label("Готовий");

        addressBar.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                loadPage(addressBar.getText());
            }
        });


        webView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            statusLabel.setText(newState.toString());

            if (newState == Worker.State.SUCCEEDED) {
                String loadedUrl = webView.getEngine().getLocation();
                statusLabel.setText("Завантажено: " + loadedUrl);

                historyService.saveToHistory(loadedUrl);
            }
        });

        HBox topBar = new HBox(10, new Label("URL:"), addressBar);
        topBar.setPadding(new Insets(10));
        addressBar.prefWidthProperty().bind(topBar.widthProperty().subtract(50));

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(webView);
        root.setBottom(statusLabel);
        BorderPane.setMargin(statusLabel, new Insets(5));

        loadPage(addressBar.getText());

        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("Web Browser");
        stage.setScene(scene);
        stage.show();
    }

    private void loadPage(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        webView.getEngine().load(url);
    }
}