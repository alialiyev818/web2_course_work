package com.app.crud.entity;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Entity class representing a student.
 */
@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private String phone;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "student_instructors",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private Set<InstructorEntity> instructors;

    /**
     * Gets the id of the student.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the student.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the student.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstname;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    /**
     * Gets the last name of the student.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    /**
     * Gets the email of the student.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the student.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the age of the student.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the student.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the phone number of the student.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the student.
     *
     * @param phone the phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the set of instructors associated with this student.
     *
     * @return the set of instructors
     */
    public Set<InstructorEntity> getInstructors() {
        return instructors;
    }

    /**
     * Sets the set of instructors associated with this student.
     *
     * @param instructors the set of instructors
     */
    public void setInstructors(Set<InstructorEntity> instructors) {
        this.instructors = instructors;
    }
}
