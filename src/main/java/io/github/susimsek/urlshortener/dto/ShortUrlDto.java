package io.github.susimsek.urlshortener.dto;

public record ShortUrlDto(
        Long id,
        String url,
        String code
) {
}
