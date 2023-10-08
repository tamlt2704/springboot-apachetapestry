package jumpstart.entity;

import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false) // this is important for entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Version
    @Column(nullable = false)
    private Integer version;

    @Column(length = 25, nullable = false)
    @NotNull
    @Size(max = 25)
    private String firstName;

    @Column(length = 25, nullable = false)
    @NotNull
    @Size(max = 25)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Regions region;

    @Temporal(TemporalType.DATE)
    @NotNull
    private java.util.Date startDate;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(version, person.version) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && region == person.region && Objects.equals(startDate, person.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, firstName, lastName, region, startDate);
    }

    @PrePersist
    @PreUpdate
    public void validate() throws ValidationException {
    }
}
