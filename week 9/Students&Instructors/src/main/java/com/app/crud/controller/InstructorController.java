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
 * InstructorController manages web requests related to instructors.
 */
@Controller
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private StudentService studentService;

    /**
     * Lists all instructors.
     *
     * @param model the model object
     * @param pageable pagination and sorting parameters
     * @param name instructor name
     * @param surname instructor surname
     * @param email instructor email
     * @param experience instructor experience
     * @param phone instructor phone number
     * @param sortBy sorting field
     * @param direction sorting direction
     * @return view of instructor list
     */
    @GetMapping("")
    public String instructorList(Model model,
                                 @PageableDefault(page = 0, size = 9, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String surname,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) Integer experience,
                                 @RequestParam(required = false) String phone,
                                 @RequestParam(defaultValue = "name") String sortBy,
                                 @RequestParam(defaultValue = "asc") String direction
    ) {


        Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.fromString(direction), sortBy));
        Page<InstructorEntity> instructorPage = instructorService.filteredInstructors(pageableWithSort, name, surname);
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);


        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("email", email);
        model.addAttribute("experience", experience);
        model.addAttribute("phone", phone);

        model.addAttribute("instructors", instructorPage.getContent());
        model.addAttribute("totalPages", instructorPage.getTotalPages());
        model.addAttribute("currentPage", instructorPage.getNumber());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "instructor/index";
    }

    /**
     * Adds a new instructor.
     *
     * @param instructor the instructor object to add
     * @return redirect to instructors list
     */
    @PostMapping("")
    public String addInstructor(@ModelAttribute("instructor") InstructorEntity instructor) {
        instructorService.saveInstructor(instructor);
        return "redirect:/instructors";
    }

    /**
     * Edits an existing instructor.
     *
     * @param id the id of the instructor to edit
     * @param instructor the instructor object with updated information
     * @return redirect to instructors list
     */
    @PostMapping("/{id}/edit")
    public String editInstructor(@PathVariable("id") Long id, @ModelAttribute("instructor") InstructorEntity instructor) {
        instructorService.updateInstructor(id, instructor);
        return "redirect:/instructors";
    }

    /**
     * Deletes an instructor.
     *
     * @param id the id of the instructor to delete
     * @return redirect to instructors list
     */
    @GetMapping("/{id}/delete")
    public String deleteInstructor(@PathVariable("id") Long id) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructors";
    }

    /**
     * Displays details of an instructor.
     *
     * @param id the id of the instructor
     * @param model the model object
     * @return view of instructor details
     */
    @GetMapping("/{id}")
    public String instructorDetail(@PathVariable Long id, Model model) {
        InstructorEntity instructor = instructorService.getInstructorById(id);

        if (instructor != null) {
            List<StudentEntity> instructor_students = instructorService.getStudentsByInstructorId(id);
            List<StudentEntity> students = studentService.showAllStudents();
            model.addAttribute("instructor", instructor);
            model.addAttribute("instructor_students", instructor_students);
            model.addAttribute("students", students);
            return "instructor/detail";
        }
        return "redirect:/instructors";
    }

    /**
     * Removes a student from an instructor's list.
     *
     * @param studentId the id of the student to remove
     * @param instructorId the id of the instructor
     * @return redirect to instructor details
     */
    @GetMapping("/{id}/students/{studentId}/remove")
    public String removeStudent(@PathVariable("studentId") Long studentId, @PathVariable("id") Long instructorId) {
        studentService.removeStudent(studentId, instructorId);
        return "redirect:/instructors/{id}";
    }

    /**
     * Adds a student to an instructor's list.
     *
     * @param instructorId the id of the instructor
     * @param studentId the id of the student to add
     * @return redirect to instructor details
     */
    @PostMapping("/{instructorId}/addStudent")
    public String addStudent(@PathVariable Long instructorId, @RequestParam Long studentId, RedirectAttributes redirectAttributes) {
        try {
            instructorService.addStudent(instructorId, studentId);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/instructors/{instructorId}";
        }
        return "redirect:/instructors/{instructorId}";
    }
}
