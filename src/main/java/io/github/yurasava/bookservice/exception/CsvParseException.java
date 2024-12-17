package io.github.yurasava.bookservice.exception;

import java.io.IOException;

public class CsvParseException extends RuntimeException {

    public CsvParseException(String errorReadingCsvFile, IOException e) {
    }
}
