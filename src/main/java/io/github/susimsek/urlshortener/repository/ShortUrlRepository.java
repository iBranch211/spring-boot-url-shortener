package io.github.susimsek.urlshortener.repository;

import io.github.susimsek.urlshortener.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    Optional<ShortUrl> findAllByCode(String code);

    boolean existsAllByCode(String code);
}
