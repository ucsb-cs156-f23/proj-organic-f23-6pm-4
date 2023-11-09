package edu.ucsb.cs156.organic.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserEmailTests {
    @Test
    void test_getGithubId() {
        User user = User.builder().githubId(12345).githubLogin("cgaucho").build();
        UserEmail userEmail = UserEmail.builder().email("cgaucho@ucsb.edu").user(user).build();
        assertEquals(12345, userEmail.getGithubId()); 
    }
}
