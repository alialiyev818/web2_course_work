package com.app.crud.entity;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Entity class representing an instructor.
 */
@Entity
@Table(name = "instructors")
public class InstructorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private int experience;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "student_instructors",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<StudentEntity> students;

    /**
     * Gets the set of students associated with this instructor.
     *
     * @return the set of students
     */
    public Set<StudentEntity> getStudents() {
        return students;
    }

    /**
     * Sets the set of students associated with this instructor.
     *
     * @param students the set of students
     */
    public void setStudents(Set<StudentEntity> students) {
        this.students = students;
    }

    /**
     * Gets the id of the instructor.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the instructor.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the instructor.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the instructor.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the surname of the instructor.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the instructor.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the email of the instructor.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the instructor.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the instructor.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the instructor.
     *
     * @param phone the phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the experience of the instructor.
     *
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets the experience of the instructor.
     *
     * @param experience the experience
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }
}
