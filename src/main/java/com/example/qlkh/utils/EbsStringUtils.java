package com.example.qlkh.utils;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class EbsStringUtils {
    public static String standardize(String text) {
        if (text == null)
            return null;
        return text.trim().replaceAll("\\s+", " ");
    }

    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Chuyển về chuỗi không dấu và không có ký tự đặc biệt
        String cleanedString = removeSpecialCharacters(removeAccent(input));

        // Chuyển thành camel case
        StringBuilder result = new StringBuilder();
        boolean isCapitalize = false;
        for (char c : cleanedString.toCharArray()) {
            if (Character.isWhitespace(c)) {
                isCapitalize = true;
                continue;
            }
            if (isCapitalize) {
                result.append(Character.toUpperCase(c));
                isCapitalize = false;
                continue;
            }
            result.append(Character.toLowerCase(c));
        }

        return result.toString();
    }

    public static String removeAccent(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        }

        // Loại bỏ dấu trong chuỗi tiếng Việt
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);

        // Loại bỏ các ký tự dấu
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedString).replaceAll("");
    }

    public static String removeSpecialCharacters(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        }

        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static String createPath(@NotNull String... values) {
        return createPathWithSeparatorCharacter("/", values);
    }

    public static String createPathWithSeparatorCharacter(@NotNull String separatorCharacter, @NotNull String... values) {
        StringBuilder path = new StringBuilder();
        for (String value : values) {
            path.append(value).append(separatorCharacter);
        }

        path.deleteCharAt(path.length() - 1);

        return path.toString();
    }
}
