package com.example.qlkh.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class EbsGenerateUtils {

    // pasword khong co dau phay
    @Value("${generate-code.list-char-type: ABCDEFGHIJKLMNOPQRSTUVWXYZ,abcdefghijklmnopqrstuvwxyz}")
    private List<String> listCharType;
    @Value("${generate-code.combined-chars: ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz}")
    private String combinedChars;
    @Value("${generate-code.length: 8}")
    private int length;
    private final Random random = new Random();

    public String code() {
        int numberCharType = listCharType.size();
        if (length < numberCharType)
            length = numberCharType;


        char[] password = new char[length];

        for (int i = 0; i < numberCharType; i++) {
            int location = random.nextInt(length);
            if (password[location] != '\0') {
                i--;
                continue;
            }
            String charType = listCharType.get(i);
            password[location] = charType.charAt(random.nextInt(charType.length()));
        }

        if (length != numberCharType)
            for (int i = 0; i < length; i++) {
                if (password[i] != '\0')
                    continue;
                password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
            }

        return String.valueOf(password);
    }
}
