package com.app.crud.repository;

import com.app.crud.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing student entities.
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    /**
     * Retrieves all students using native SQL query.
     *
     * @return list of all students
     */
    @Query(value = "SELECT * FROM students", nativeQuery = true)
    List<StudentEntity> findAllStudentsNative();

    /**
     * Retrieves all students with pagination using native SQL query.
     *
     * @param pageable pagination information
     * @return page of student entities
     */
    @Query(value = "SELECT * FROM students", nativeQuery = true)
    Page<StudentEntity> findAllStudentsPagedNative(Pageable pageable);

    /**
     * Filters students based on various criteria.
     *
     * @param firstname first name to filter by
     * @param lastname  last name to filter by
     * @param email     email to filter by
     * @param age       age to filter by
     * @param phone     phone number to filter by
     * @param pageable  pagination information
     * @return page of filtered student entities
     */
    @Query(value = "SELECT * FROM students WHERE (:firstname IS NULL OR firstname LIKE %:firstname%) " +
            "AND (:lastname IS NULL OR lastname LIKE %:lastname%) " +
            "AND (:email IS NULL OR email LIKE %:email%) " +
            "AND (:phone IS NULL OR phone LIKE %:phone%) " +
            "AND (:age IS NULL OR age = :age)",
            nativeQuery = true)
    Page<StudentEntity> filterStudents(String firstname, String lastname, String email, Integer age, String phone, Pageable pageable);

    /**
     * Retrieves students by instructor ID.
     *
     * @param instructorId the ID of the instructor
     * @return list of students associated with the instructor
     */
    @Query("SELECT s FROM StudentEntity s JOIN s.instructors c WHERE c.id = :instructorId")
    List<StudentEntity> findStudentsByInstructorIdCustom(Long instructorId);
}
