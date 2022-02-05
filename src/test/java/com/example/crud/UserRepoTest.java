package com.example.crud;

import com.example.crud.dbaccess.User;
import com.example.crud.dbaccess.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void beforeAll() {

        userRepository.save(new User("Bob", "bob@domain.com"));
    }

    @Test
    void checkIfAddingUserWorks() {

        List<User> users = (List<User>) userRepository.findAll();
        assertEquals(1, users.size());
    }

    @Test
    void getUserOk() {

        List<User> users = (List<User>) userRepository.findAll();
        var bob = users.get(0);
        assertAll("bob",
                () -> assertEquals("Bob", bob.getName()),
                () -> assertEquals("bob@domain.com", bob.getEmail())
        );
    }
}
