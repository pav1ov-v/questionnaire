package com.example.questionnaire.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<TakenQuestionnaireEntity> takenQuestionnaires;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public UserEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
