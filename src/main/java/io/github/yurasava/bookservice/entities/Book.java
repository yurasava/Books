package io.github.yurasava.bookservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private String description;
}
