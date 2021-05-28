package com.github.fabriciolfj.domain.entity;

import com.github.fabriciolfj.domain.exceptions.ProductException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.stream.Stream.of;

@Getter
@AllArgsConstructor
public enum TypeOperation {

    OUT("exit"), IN("entrance"), EST("reversal");

    private String type;

    public static TypeOperation toEnum(final String description) {
        return of(TypeOperation.values())
                .filter(t -> t.type.equalsIgnoreCase(description))
                .findFirst()
                .orElseThrow(() -> new ProductException("Type operation not found: " + description));
    }
}
