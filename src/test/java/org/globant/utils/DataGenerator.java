package org.globant.utils;

import org.globant.models.User;

public class DataGenerator {

    public static User generateRandomUser() {
        long timestamp = System.currentTimeMillis();
        return User.builder()
                .id(timestamp)
                .username("user_" + timestamp)
                .firstName("Perf")
                .lastName("Dog")
                .email("test_" + timestamp + "@perfdog.com")
                .password("Pass1234!")
                .phone("123456789")
                .userStatus(1)
                .build();
    }
}
