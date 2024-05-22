package com.app.crud.service;

import com.app.crud.entity.InstructorEntity;
import com.app.crud.entity.StudentEntity;
import com.app.crud.repository.InstructorRepository;
import com.app.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing instructor entities.
 */
@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Retrieves all instructors.
     *
     * @return list of all instructors
     */
    public List<InstructorEntity> getAllInstructors() {
        return instructorRepository.findAll();
    }

    /**
     * Retrieves an instructor by ID.
     *
     * @param id the ID of the instructor to retrieve
     * @return the instructor entity, or null if not found
     */
    public InstructorEntity getInstructorById(Long id) {
        return instructorRepository.getInstructorByIdCustom(id);
    }

    /**
     * Filters instructors by name and surname.
     *
     * @param pageable pagination information
     * @param name     name to filter by
     * @param surname  surname to filter by
     * @return a page of filtered instructor entities
     */
    public Page<InstructorEntity> filteredInstructors(Pageable pageable, String name, String surname) {
        return instructorRepository.filterInstructorCustom(pageable, name, surname);
    }

    /**
     * Retrieves all instructors with pagination.
     *
     * @param pageable pagination information
     * @return a page of instructor entities
     */
    public Page<InstructorEntity> instructorList(Pageable pageable) {
        return instructorRepository.fetchAllFromCustom(pageable);
    }

    /**
     * Saves an instructor.
     *
     * @param instructor the instructor to save
     */
    public void saveInstructor(InstructorEntity instructor) {
        instructorRepository.save(instructor);
    }

    /**
     * Updates an instructor's information.
     *
     * @param id             the ID of the instructor to update
     * @param updatedCourse  the updated instructor entity
     */
    public void updateInstructor(Long id, InstructorEntity updatedCourse) {
        instructorRepository.updateInstructorById(id, updatedCourse.getName(), updatedCourse.getSurname(), updatedCourse.getEmail(), updatedCourse.getPhone(), updatedCourse.getExperience());
    }

    /**
     * Deletes an instructor by ID.
     *
     * @param id the ID of the instructor to delete
     */
    public void deleteInstructor(Long id) {
        InstructorEntity instructor = instructorRepository.findById(id).orElse(null);
        if (instructor != null) {
            for (StudentEntity student : instructor.getStudents()) {
                student.getInstructors().remove(instructor);
            }
            instructorRepository.deleteInstructorByIdCustom(id);
        } else {
        }
    }

    /**
     * Retrieves students associated with an instructor by instructor ID.
     *
     * @param instructorId the ID of the instructor
     * @return list of students associated with the instructor
     */
    public List<StudentEntity> getStudentsByInstructorId(Long instructorId) {
        return studentRepository.findStudentsByInstructorIdCustom(instructorId);
    }

    /**
     * Adds a student to an instructor.
     *
     * @param instructorId the ID of the instructor
     * @param studentId    the ID of the student
     */
    public void addStudent(Long instructorId, Long studentId) {
        InstructorEntity instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid instructor ID: " + instructorId));

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + studentId));
        instructorRepository.addStudentToInstructorCustom(instructorId, studentId);
    }
}
