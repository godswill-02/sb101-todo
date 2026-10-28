package com.springboot101.todo.tasks.entities;

import com.springboot101.todo.users.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

/**
 * @author Mr Uche.
 *
 */
@Data
@Entity
@Builder
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", length = 300)
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // S'exécute automatiquement avant la création en base
    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now; // Met aussi à jour updated_at à la création pour éviter les valeurs NULL
    }

    // S'exécute automatiquement avant chaque mise à jour en base
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now(); // Rafraîchit uniquement updated_at lors d'un update
    }

}
