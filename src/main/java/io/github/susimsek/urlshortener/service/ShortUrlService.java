package io.github.susimsek.urlshortener.service;

import io.github.susimsek.urlshortener.exception.ShortUrlNotFoundException;
import io.github.susimsek.urlshortener.model.ShortUrl;
import io.github.susimsek.urlshortener.repository.ShortUrlRepository;
import io.github.susimsek.urlshortener.util.RandomStringGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class ShortUrlService {

    final ShortUrlRepository shortUrlRepository;
    final RandomStringGenerator randomStringGenerator;

    public List<ShortUrl> getAllShortUrl() {
        return shortUrlRepository.findAll();
    }

    public ShortUrl getUrlByCode(String code) {
        return shortUrlRepository.findAllByCode(code).orElseThrow(
                () -> new ShortUrlNotFoundException(String.format("Url with code: %s not found", code)));
    }

    public ShortUrl create(ShortUrl shortUrl) {
        boolean codeIsNullOrEmpty = Optional.ofNullable(shortUrl.getCode())
                .map(String::isBlank).orElse(true);
        if (codeIsNullOrEmpty) {
            shortUrl.setCode(generateCode());
        } else if (shortUrlRepository.existsAllByCode(shortUrl.getCode())) {
            throw new RuntimeException(String.format("%s code is already exists", (shortUrl.getCode())));
        }
        shortUrl.setCode(shortUrl.getCode().toUpperCase());
        return shortUrlRepository.save(shortUrl);
    }

    // kod varsa tekrar oluştur.
    private String generateCode() {
        String code;
        // kod var olduğu sürece tekrar oluştur.
        do {
            code = randomStringGenerator.generateRandomString();
        } while (shortUrlRepository.existsAllByCode(code));
        return code;
    }
}
