    function openAddModal() {
        document.getElementById('addModal').style.display = 'block';
    }

    function closeAddModal() {
        document.getElementById('addModal').style.display = 'none';
    }

    function openEditModal(course) {
        const courseName = course.querySelector('span').textContent;
        const courseDescription = course.querySelector('p').textContent;
        const id = course.querySelector('div').textContent;
        document.getElementById('editCourseForm').setAttribute("action", "/courses/" + id + "/edit");
        document.getElementById('editCourseName').value = courseName;
        document.getElementById('editCourseDescription').value = courseDescription;
        document.getElementById('editModal').style.display = 'block';

    }

    function closeEditModal() {
        document.getElementById('editModal').style.display = 'none';
    }

    function deleteCourse(button) {
        button.parentElement.remove();
    }

    function searchCourses() {
        const searchInput = document.getElementById('searchInput').value.toLowerCase();
        const coursesContainer = document.getElementById('coursesContainer');
        const courses = Array.from(coursesContainer.getElementsByClassName('course'));

        courses.forEach(course => {
            const courseName = course.querySelector('span').textContent.toLowerCase();
            if (courseName.includes(searchInput)) {
                course.style.display = 'block';
            } else {
                course.style.display = 'none';
            }
        });
    }