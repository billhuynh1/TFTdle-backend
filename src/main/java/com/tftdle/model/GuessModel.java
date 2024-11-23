package com.tftdle.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "guesses")
public class GuessModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String champ;
    private boolean isCorrect;
    private String sessionId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    public GuessModel() {}

    @PrePersist
    public void onPrePersist() {
        if (createdAt == null) {
            createdAt = new Date();
        }
    }

    public String getChamp() {
        return champ;
    }

    public void setChamp(String champ) {
        this.champ = champ;
    }

    public boolean getIsCorrect() {return isCorrect;}

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Getter for sessionId
    public String getSessionId() {
        return sessionId;
    }

    // Setter for sessionId
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
