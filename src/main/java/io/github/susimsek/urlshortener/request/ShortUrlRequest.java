package io.github.susimsek.urlshortener.request;

import javax.validation.constraints.NotBlank;

public record ShortUrlRequest(
        @NotBlank
        String url,

        String code
) {
}
