package io.github.susimsek.urlshortener.dto.mapper;

import io.github.susimsek.urlshortener.dto.ShortUrlDto;
import io.github.susimsek.urlshortener.model.ShortUrl;
import io.github.susimsek.urlshortener.request.ShortUrlRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShortUrlMapper {

    public ShortUrlDto shortUrlToShortUrlDto(ShortUrl shortUrl) {
        return new ShortUrlDto(shortUrl.getId(), shortUrl.getUrl(), shortUrl.getCode());
    }

    public List<ShortUrlDto> shortUrlsToShortUrlDtos(List<ShortUrl> shortUrls) {
        return shortUrls.stream()
               .map(this::shortUrlToShortUrlDto)
               .collect(Collectors.toList());
    }

    public ShortUrl shortUrlRequestToShortUrl(ShortUrlRequest shortUrlRequest) {
        return ShortUrl.builder()
                .url(shortUrlRequest.url())
                .code(shortUrlRequest.code())
                .build();
    }
}
