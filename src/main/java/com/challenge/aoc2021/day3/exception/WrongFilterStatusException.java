package com.challenge.aoc2021.day3.exception;

import com.challenge.aoc2021.day3.DiagnosticReportFilter;

public class WrongFilterStatusException extends Exception {
    private String message;

    public WrongFilterStatusException(DiagnosticReportFilter filter) {
        if (filter.getNumActiveLines() > 1) {
            message = String.format("The filter still have %d lines left", filter.getNumActiveLines());
        } else if (filter.getNumActiveLines() == 0) {
            message = "The filter is empty";
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
