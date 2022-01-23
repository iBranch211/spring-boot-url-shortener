package io.github.susimsek.urlshortener.util;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class RandomStringGenerator {

    @Value("${code-length}")
    int codeLength;

    public String generateRandomString() {
        SecureRandom random = new SecureRandom();

        var letters = "abcdefghijklmprstuvyzqw123456789"
                .toUpperCase()
                .chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.toList());

        // karakter yerlerini karıştır.
        Collections.shuffle(letters);

        return IntStream.range(0, codeLength)
                .mapToObj(i -> letters.get(random.nextInt(letters.size())))
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
