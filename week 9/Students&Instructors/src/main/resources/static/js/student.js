const addStudentButton = document.getElementById('addStudentButton');
    const addModal = document.getElementById('myModal');
    const updateModal = document.getElementById('updateModal');
    const updateStudentForm = document.getElementById('updateStudentForm');
    const students = [];

    addStudentButton.addEventListener('click', () => {
        openModal(addModal);
    });

    function openModal(modalElement) {
        modalElement.style.display = 'block';
    }

    function closeModal(modalElement) {
        modalElement.style.display = 'none';
    }


    const modalClose = document.querySelectorAll('.modal .close');
    modalClose.forEach(closeButton => {
        closeButton.addEventListener('click', () => {
            const modalId = closeButton.parentElement.parentElement.parentElement.id;
            closeModal(document.getElementById(modalId));
        });
    });

    window.addEventListener('click', (event) => {
        if (event.target == addModal || event.target == updateModal) {
            closeModal(addModal);
            closeModal(updateModal);
        }
    });

    function displayStudents() {
    }

    function openUpdateModal(id, firstName, lastName, email, age, phone) {
        var updateStudentForm = document.getElementById("updateStudentForm");
        updateStudentForm.setAttribute("action", `/students/${id}/update`);
        updateStudentForm.innerHTML = `
            <div>
                <label for="updateStudentName">Name:</label>
                <input type="text" id="updateStudentName" name="firstName" value="${firstName}" required>
            </div>
            <div>
                <label for="updateStudentSurname">Surname:</label>
                <input type="text" id="updateStudentSurname" name="lastName" value="${lastName}" required>
            </div>
            <div>
                <label for="updateStudentAge">Age:</label>
                <input type="number" id="updateStudentAge" name="age" value="${age}" required>
            </div>
            <div>
                <label for="updateStudentEmail">Email:</label>
                <input type="email" id="updateStudentEmail" name="email" value="${email}" required>
            </div>
            <div>
                <label for="updateStudentPhone">Phone:</label>
                <input type="tel" id="updateStudentPhone" name="phone" value="${phone}" required>
            </div>
            <button type="submit">Update</button>
        `;
        openModal(updateModal);

    }
    function openUpdateModal2(id, name, surname, email, experience, phone) {
        var updateStudentForm = document.getElementById("updateStudentForm");
        updateStudentForm.setAttribute("action", `/instructors/${id}/edit`);
        updateStudentForm.innerHTML = `
            <div>
                <label for="studentName">Name:</label>
                <input type="text" id="studentName" name="name" value="${name}" required>
            </div>
            <div>
                <label for="studentSurname">Surname:</label>
                <input type="text" id="studentSurname" name="surname" value="${surname}" required>
            </div>
            <div>
                <label for="studentEmail">Email:</label>
                <input type="email" id="studentEmail" name="email" value="${email}" required>
            </div>
            <div>
                <label for="studentPhone">Phone:</label>
                <input type="tel" id="studentPhone" name="phone" value="${phone}" required>
            </div>
            <div>
                <label for="studentExperience">Experience:</label>
                <input type="tel" id="studentExperience" name="experience" value="${experience}" required>
            </div>
            <button type="submit">Update</button>
        `;
        openModal(updateModal);

    }
    const closeModalButtons = document.querySelectorAll('.modal .close');

    closeModalButtons.forEach(button => {
        button.addEventListener('click', () => {
            const modal = button.closest('.modal');
            closeModal(modal);
        });
    });

    window.addEventListener('click', (event) => {
        if (event.target.classList.contains('modal')) {
            closeModal(event.target);
        }
    });
    document.getElementById('reset-btn').addEventListener('click', (event) => {
        window.location.href = '/students'
    });


    function reset_btn_instructors(){
        window.location.href = '/instructors'
    }