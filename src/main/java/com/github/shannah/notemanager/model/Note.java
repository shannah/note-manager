package com.github.shannah.notemanager.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Note {
    private final String id;
    private String title;
    private String content;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Note(String title, String content) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = this.createdAt;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
        this.modifiedAt = LocalDateTime.now();
    }
    public String getContent() { return content; }
    public void setContent(String content) {
        this.content = content;
        this.modifiedAt = LocalDateTime.now();
    }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getModifiedAt() { return modifiedAt; }
}
