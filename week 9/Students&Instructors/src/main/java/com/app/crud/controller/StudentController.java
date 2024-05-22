package com.app.crud.controller;

import com.app.crud.entity.InstructorEntity;
import com.app.crud.entity.StudentEntity;
import com.app.crud.service.InstructorService;
import com.app.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * StudentController handles web requests related to students.
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private StudentService studentService;

    /**
     * Displays a list of students.
     *
     * @param model the model object
     * @param pageable pagination and sorting parameters
     * @param firstName student's first name
     * @param lastName student's last name
     * @param email student's email
     * @param age student's age
     * @param phone student's phone number
     * @param instructorId id of the associated instructor
     * @param sortBy sorting field
     * @param direction sorting direction
     * @return view of student list
     */
    @GetMapping("")
    public String studentList(Model model,
                              @PageableDefault(page = 0, size = 9, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) Integer age,
                              @RequestParam(required = false) String phone,
                              @RequestParam(required = false) Long instructorId,
                              @RequestParam(defaultValue = "id") String sortBy,
                              @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.fromString(direction), sortBy));

        Page<StudentEntity> studentsPage = studentService.filterStudents(pageableWithSort, firstName, lastName, email, age, phone);

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("age", age);
        model.addAttribute("phone", phone);
        model.addAttribute("instructorId", instructorId);

        model.addAttribute("students", studentsPage.getContent());
        model.addAttribute("totalPages", studentsPage.getTotalPages());
        model.addAttribute("currentPage", studentsPage.getNumber());

        List<InstructorEntity> instructors = instructorService.getAllInstructors();
        model.addAttribute("instructors", instructors);

        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "student/index";
    }

    /**
     * Adds a new student.
     *
     * @param student the student object to add
     * @return redirect to the student list page
     */
    @PostMapping("")
    public String addStudent(@ModelAttribute StudentEntity student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

    /**
     * Deletes a student.
     *
     * @param id the id of the student to delete
     * @return redirect to the student list page
     */
    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    /**
     * Updates information of a student.
     *
     * @param id the id of the student to update
     * @param updatedStudent the student object with updated information
     * @param redirectAttributes redirect attributes for flash messages
     * @return redirect to the student list page
     */
    @PostMapping("/{id}/update")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute StudentEntity updatedStudent, RedirectAttributes redirectAttributes) {
        StudentEntity existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setAge(updatedStudent.getAge());
            existingStudent.setPhone(updatedStudent.getPhone());
            studentService.addStudent(existingStudent);
        }
        return "redirect:/students";
    }

    /**
     * Displays details of a student.
     *
     * @param model the model object
     * @param id the id of the student
     * @return view of student details
     */
    @GetMapping("/{id}")
    public String studentDetail(Model model, @PathVariable("id") Long id) {
        StudentEntity student = null;
        List<InstructorEntity> instructors = null;
        try {
            student = studentService.getStudentById(id);
            instructors = instructorService.getAllInstructors();
        } catch (Exception e) {
            return "redirect:/students";
        }
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("instructors", instructors);
        return "student/detail";
    }
}
