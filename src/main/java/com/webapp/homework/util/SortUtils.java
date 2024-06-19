package com.webapp.homework.util;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@Component
public class SortUtils {

    public <T> void sortOutputOrderByFieldsAndSortPattern(String columnHeader, String sortPattern, List<T> outputList, Function<T, String> entityField) {
        Comparator<T> comparator = Comparator.comparing((T data) -> {
            String displayValue = entityField.apply(data).toLowerCase();
            if (columnHeader.equals("username")) {
                return displayValue;
            } else if (columnHeader.equals("model")) {
                return displayValue;
            } else if (columnHeader.equals("make")) {
                return displayValue;
            } else if (columnHeader.equals("numberplate")) {
                return displayValue;
            }
            return displayValue;
        });
        outputList.sort(sortPattern.equals("asc") ? comparator : comparator.reversed());
    }
}
