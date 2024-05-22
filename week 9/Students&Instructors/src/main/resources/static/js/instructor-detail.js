   const addButton = document.getElementById('addButton');
    const coursesContainer = document.getElementById('coursesContainer');
    const addModal = document.getElementById('addModal');
    const addCourseForm = document.getElementById('addCourseForm');

    addButton.addEventListener('click', () => {
        openModal(addModal);
    });



    function createCourseElement(courseName) {
        const courseDiv = document.createElement('div');
        courseDiv.classList.add('course');
        courseDiv.innerHTML = `
            <span>${courseName}</span>
            <button class="delete-button">Delete</button>
        `;
        return courseDiv;
    }

    function openModal(modal) {
        modal.style.display = 'block';
    }

    function closeModals() {
        const modals = document.querySelectorAll('.modal');
        modals.forEach(modal => {
            modal.style.display = 'none';
        });
    }

    window.addEventListener('click', (e) => {
        if (e.target.classList.contains('modal')) {
            closeModals();
        }
    });

    document.querySelectorAll('.close').forEach(closeButton => {
        closeButton.addEventListener('click', () => {
            closeModals();
        });
    });