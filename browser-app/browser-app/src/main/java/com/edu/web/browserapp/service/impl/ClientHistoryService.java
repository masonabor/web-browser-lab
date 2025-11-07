package com.edu.web.browserapp.service.impl;

import com.edu.web.browserapp.service.IHistoryService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHistoryService implements IHistoryService {

    private static final ClientHistoryService INSTANCE = new ClientHistoryService();

    private final HttpClient httpClient;
    private final String API_URL = "http://localhost:8080/browser-service-1.0/api/history"; // Оновіть назву .war

    private ClientHistoryService() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    public static ClientHistoryService getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveToHistory(String url) {
        try {
            String jsonPayload = "{\"url\":\"" + url.replace("\"", "\\\"") + "\"}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(this::handleResponse)
                    .exceptionally(this::handleError);

        } catch (Exception e) {
            System.err.println("[Proxy] Error during request: " + e.getMessage());
        }
    }

    private void handleResponse(HttpResponse<String> response) {
        if (response.statusCode() == 201) {
            System.out.println("[Proxy] History successfully saved");
        } else {
            System.err.println("[Proxy] Server error: " + response.statusCode());
        }
    }

    private Void handleError(Throwable ex) {
        System.err.println("[Proxy] Error during connecting to History Service: " + ex.getMessage());
        return null;
    }

}
