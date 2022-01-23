package io.github.susimsek.urlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shortUrlGen")
    @SequenceGenerator(name = "shortUrlGen", sequenceName = "shortUrlSeq", initialValue = 1, allocationSize = 1)
    Long id;

    @NotNull
    String url;

    @NotNull
    @Column(unique = true)
    String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        ShortUrl shortUrl = (ShortUrl) o;
        return id != null && Objects.equals(id, shortUrl.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
