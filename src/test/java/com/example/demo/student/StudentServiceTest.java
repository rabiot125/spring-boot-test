package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;


    @BeforeEach
    void setUp() {

        studentService = new StudentService(studentRepository);
    }

    @Test
    void getAllStudents() {
        //WHEN
        studentService.getAllStudents();
        //THEN
        verify(studentRepository).findAll();
    }

    @Test
    void addStudent() {
        //Given
        Student student = new Student(
                "Donald", "kipdon563@gmail.com", Gender.MALE
        );
        //When
        studentService.addStudent(student);
        //Then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);

    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //Given
        Student student = new Student(
                "Donald", "kipdon53@gmail.com", Gender.MALE
        );

        given(studentRepository.selectExistsEmail(student.getEmail()))
                .willReturn(true);
        //When
        //Then

        assertThatThrownBy(() -> studentService.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");

        verify(studentRepository, never()).save(any());
    }


    @Test
    void deleteStudent() throws  Exception{
        //given
      final Student student= new Student();
      // when(studentRepository.findById(1L)).thenReturn(null);
       when(studentRepository.existsById(1L)).thenReturn(true);
       doNothing().when(studentRepository).deleteById(1L);

       //when
        StudentService studentService = this.studentService;
        studentService.deleteStudent(1L);

//       StudentService studentService = new StudentService(studentRepository);
//       studentService.deleteStudent(1L);

       //THEN

        verify(studentRepository, times(1))
                .deleteById(1L);
        //assertThat(studentService).isEqualTo(true) ;
        //verify(studentRepository).save(student);
    }

}



