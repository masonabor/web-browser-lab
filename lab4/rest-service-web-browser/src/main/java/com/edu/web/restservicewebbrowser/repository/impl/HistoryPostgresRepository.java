package com.edu.web.restservicewebbrowser.repository.impl;

import com.edu.web.restservicewebbrowser.domain.HistoryItem;
import com.edu.web.restservicewebbrowser.repository.HistoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty; // Імпорт з MicroProfile

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@ApplicationScoped
public class HistoryPostgresRepository implements HistoryRepository {

    @Inject
    @ConfigProperty(name = "DB_URL")
    private String dbUrl;

    @Inject
    @ConfigProperty(name = "DB_USER")
    private String dbUser;

    @Inject
    @ConfigProperty(name = "DB_PASSWORD")
    private String dbPassword;

    public void save(HistoryItem item) throws SQLException {
        String sql = "INSERT INTO HistoryItem (url, visit_time) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.url());
            pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.executeUpdate();
        }
    }
}