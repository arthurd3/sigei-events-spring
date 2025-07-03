document.addEventListener('DOMContentLoaded', function () {
    const userMenuButton = document.getElementById('userMenuButton');
    const userDropdown = document.getElementById('userDropdown');

    const adminMenuButton = document.getElementById('adminMenuButton');
    const adminDropdown = document.getElementById('adminDropdown');


    if (adminMenuButton) {
        adminMenuButton.addEventListener('click', function (event) {
            event.stopPropagation();
            if (userDropdown) {
                userDropdown.classList.remove('show');
            }
            adminDropdown.classList.toggle('show');
        });
    }

    if (userMenuButton) {
        userMenuButton.addEventListener('click', function (event) {
            event.stopPropagation();
            if (adminDropdown) {
                adminDropdown.classList.remove('show');
            }
            userDropdown.classList.toggle('show');
        });
    }

    window.addEventListener('click', function (event) {
        if (userDropdown && userDropdown.classList.contains('show')) {
            userDropdown.classList.remove('show');
        }
        if (adminDropdown && adminDropdown.classList.contains('show')) {
            adminDropdown.classList.remove('show');
        }
    });
});