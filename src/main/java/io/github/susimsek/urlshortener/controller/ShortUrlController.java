package io.github.susimsek.urlshortener.controller;

import io.github.susimsek.urlshortener.dto.ShortUrlDto;
import io.github.susimsek.urlshortener.dto.mapper.ShortUrlMapper;
import io.github.susimsek.urlshortener.model.ShortUrl;
import io.github.susimsek.urlshortener.request.ShortUrlRequest;
import io.github.susimsek.urlshortener.service.ShortUrlService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class ShortUrlController {

    final ShortUrlMapper shortUrlMapper;
    final ShortUrlService shortUrlService;

    @GetMapping
    public ResponseEntity<List<ShortUrlDto>> getAllUrls() {
        return ResponseEntity.ok(
                shortUrlMapper.shortUrlsToShortUrlDtos(shortUrlService.getAllShortUrl()));
    }

    @GetMapping("/show/{code}")
    public ResponseEntity<ShortUrlDto> getUrlByCode(@Valid @NotBlank @PathVariable String code) {
        return ResponseEntity.ok(
                shortUrlMapper.shortUrlToShortUrlDto(shortUrlService.getUrlByCode(code)));
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@Valid @NotBlank @PathVariable String code) throws URISyntaxException {
        ShortUrl shortUrl = shortUrlService.getUrlByCode(code);
        URI uri = new URI(shortUrl.getUrl());
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(uri).build();
    }

    @PostMapping
    public ResponseEntity<ShortUrlDto> create(@Valid @RequestBody ShortUrlRequest shortUrlRequest) {
        ShortUrl shortUrl = shortUrlMapper.shortUrlRequestToShortUrl(shortUrlRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(shortUrlMapper.shortUrlToShortUrlDto(
                        shortUrlService.create(shortUrl)));
    }


}
