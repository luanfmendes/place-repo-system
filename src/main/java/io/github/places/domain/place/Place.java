package io.github.places.domain.place;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity(name = "places")
@Table(name = "places")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 25, nullable = false, unique = true)
    private String name;
    @Column(length = 25, nullable = false)
    private String city;
    @Column(length = 25, nullable = false)
    private String state;
    private String slug;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateAt;


    public void generateSlug() {
        this.slug = name.toLowerCase() + "-slug";
    }
}
