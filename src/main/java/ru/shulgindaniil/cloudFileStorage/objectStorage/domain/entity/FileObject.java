package ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.User;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileObject {
    @Id
    private String id;

    @ManyToOne
    private FileObject parent;

    @ManyToOne
    private User owner;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private FileObjectType type;

    @Column
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Timestamp createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Timestamp modifiedAt;

    @Column
    private Long size = 0L;
}
