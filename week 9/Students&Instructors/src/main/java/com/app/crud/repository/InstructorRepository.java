package com.app.crud.repository;

import com.app.crud.entity.InstructorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for managing instructor entities.
 */
@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity, Long> {

    /**
     * Fetches all instructors with custom pagination.
     *
     * @param pageable pagination information
     * @return a page of instructor entities
     */
    @Query(value = "SELECT * FROM instructors", nativeQuery = true)
    Page<InstructorEntity> fetchAllFromCustom(Pageable pageable);

    /**
     * Filters instructors by name and surname with custom pagination.
     *
     * @param pageable pagination information
     * @param name     name to filter by
     * @param surname  surname to filter by
     * @return a page of filtered instructor entities
     */
    @Query(value = "SELECT * FROM instructors WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%')) AND LOWER(surname) LIKE LOWER(CONCAT('%', :surname, '%'))", nativeQuery = true)
    Page<InstructorEntity> filterInstructorCustom(Pageable pageable, @Param("name") String name, @Param("surname") String surname);

    /**
     * Retrieves an instructor by ID.
     *
     * @param id the ID of the instructor to retrieve
     * @return the instructor entity, or null if not found
     */
    @Query(value = "SELECT * FROM instructors WHERE id = :id", nativeQuery = true)
    InstructorEntity getInstructorByIdCustom(Long id);

    /**
     * Updates an instructor's information by ID.
     *
     * @param id         the ID of the instructor to update
     * @param name       the updated name
     * @param surname    the updated surname
     * @param email      the updated email
     * @param phone      the updated phone
     * @param experience the updated experience
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE instructors SET name = :name, surname = :surname, email = :email, phone = :phone, experience = :experience WHERE id = :id", nativeQuery = true)
    void updateInstructorById(@Param("id") Long id, @Param("name") String name, @Param("surname") String surname, @Param("email") String email, @Param("phone") String phone, @Param("experience") int experience);

    /**
     * Deletes an instructor by ID.
     *
     * @param instructorId the ID of the instructor to delete
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM instructors WHERE id = :instructorId", nativeQuery = true)
    void deleteInstructorByIdCustom(@Param("instructorId") Long instructorId);

    /**
     * Adds a student to an instructor.
     *
     * @param instructorId the ID of the instructor
     * @param studentId    the ID of the student
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO student_instructors (student_id, instructor_id) VALUES (:studentId, :instructorId)", nativeQuery = true)
    void addStudentToInstructorCustom(@Param("instructorId") Long instructorId, @Param("studentId") Long studentId);
}
