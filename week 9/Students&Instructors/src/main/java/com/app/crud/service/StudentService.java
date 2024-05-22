package com.app.crud.service;

import com.app.crud.entity.InstructorEntity;
import com.app.crud.entity.StudentEntity;
import com.app.crud.repository.InstructorRepository;
import com.app.crud.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service class for managing student entities.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    /**
     * Retrieves a list of all students.
     *
     * @return list of all students
     */
    public List<StudentEntity> showAllStudents() {
        return studentRepository.findAllStudentsNative();
    }

    /**
     * Retrieves all students with pagination.
     *
     * @param pageable pagination information
     * @return a page of student entities
     */
    public Page<StudentEntity> getAllStudents(Pageable pageable) {
        return studentRepository.findAllStudentsPagedNative(pageable);
    }

    /**
     * Adds a new student.
     *
     * @param student the student to add
     */
    public void addStudent(StudentEntity student) {
        studentRepository.save(student);
    }

    /**
     * Deletes a student by ID.
     *
     * @param id the ID of the student to delete
     */
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    /**
     * Retrieves a student by ID.
     *
     * @param id the ID of the student to retrieve
     * @return the student entity, or null if not found
     */
    public StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    /**
     * Filters students based on various criteria.
     *
     * @param pageable  pagination information
     * @param firstName first name to filter by
     * @param lastName  last name to filter by
     * @param email     email to filter by
     * @param age       age to filter by
     * @param phone     phone number to filter by
     * @return a page of filtered student entities
     */
    public Page<StudentEntity> filterStudents(Pageable pageable, String firstName, String lastName, String email, Integer age, String phone) {
        return studentRepository.filterStudents(firstName, lastName, email, age, phone, pageable);
    }

    /**
     * Removes a student from an instructor.
     *
     * @param studentId   the ID of the student to remove
     * @param instructorId the ID of the instructor from whom to remove the student
     * @throws EntityNotFoundException if the student or instructor is not found
     */
    @Transactional
    public void removeStudent(Long studentId, Long instructorId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        InstructorEntity instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found"));

        student.getInstructors().remove(instructor);
        studentRepository.save(student);
    }
}
