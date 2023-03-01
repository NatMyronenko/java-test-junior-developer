package com.example.java.test.junior.developer;

import com.example.java.test.junior.developer.controllers.PagesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class JavaTestJuniorDeveloperApplicationTests {
    @Autowired
    private PagesController myController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(myController).isNotNull();
    }

}