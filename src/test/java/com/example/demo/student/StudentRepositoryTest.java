package com.example.demo.student;

import lombok.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void selectExistsEmail() {
        //Given
        Student student= new Student(
                "Donald","kipdon53@gmail.com",Gender.MALE
        );
        studentRepository.save(student);
        //When

        boolean exists =studentRepository.selectExistsEmail("kipdon53@gmail.com");

        //Then
        assertThat(exists).isTrue();
    }
    @Test
    void emailDoesNotExist() {
        //Given
        Student student= new Student(
                "Donald","kipdon53@gmail.com",Gender.MALE
        );

        //When

        boolean exists =studentRepository.selectExistsEmail("kipdon53@gmail.com");

        //Then
        assertThat(exists).isFalse();
    }
}