package com.adeppar.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.IIOException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.file.Files.write;

@SpringBootApplication
@Slf4j
public class AssignmentApplication implements CommandLineRunner {

    public static final String DATA = "data";
    private List<Set> fullDataSet = Lists.newArrayList();

    private Set<String> textContents = Sets.newHashSet();
    private List<String> sortedTextContents;


    public static void main(String[] args) {
        SpringApplication.run(
                AssignmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        InputStream resource = new ClassPathResource(DATA).getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));

        Set<String> files = reader.lines()
                .collect(Collectors.toSet());
        log.info("The number of files in the data directory: " + files);

        fullDataSet = files.stream()
                .map(file -> {
                    try {
                        return readFileContents(file);
                    } catch (IOException e) {

                        log.error("Could not read file: " + e.fillInStackTrace());
                        return Sets.newHashSet();
                    }

                }).collect(Collectors.toList());

        fullDataSet.stream()
                .forEach(set -> textContents.addAll(set));

        sortedTextContents = new ArrayList<String>(textContents);
        Collections.sort(sortedTextContents);

        log.info("The full sorted text contents:" + sortedTextContents);
        store(sortedTextContents, "/tmp/out.txt");
    }

    private Set<String> readFileContents(String file) throws IOException {

        InputStream resource = new ClassPathResource(DATA + "/" + file).getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));

        return reader.lines()
                .collect(Collectors.toSet());
    }

    private static void store(List<String> sourceList, String targetFileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        for (String listElement : sourceList) {
            stringBuilder.append(listElement);
            stringBuilder.append(System.lineSeparator());
        }

        String setString = stringBuilder.toString().trim();
        byte[] setBytes = setString.getBytes(StandardCharsets.UTF_8);
        write(Paths.get(targetFileName), setBytes);
    }
}
