package com.adeppar.assignment;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
class AssignmentApplicationTests {

    AssignmentApplicationTests() throws IOException {
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void whenResourceAsStream_thenReadSuccessful() throws IOException {

        InputStream resource = new ClassPathResource("data/a.txt").getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));

        Set<String> dataSet = reader.lines()
                .collect(Collectors.toSet());

        assertThat(dataSet).contains("the pen is mightier");
    }

    @Test
    public void whenFilesInDirectory_thenSizeIs1() throws IOException {

        InputStream resource = new ClassPathResource(AssignmentApplication.DATA).getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));

        Set<String> files = reader.lines()
                .collect(Collectors.toSet());

        assertThat(files).hasSize(1);
    }


}
