document.addEventListener("DOMContentLoaded", () => {
    const toggle = document.querySelector(".toggle");
    const menuDashboard = document.querySelector(".menu-dashboard");
    const iconoMenu = document.querySelector(".toggle i"); 
    const enlacesMenu = document.querySelectorAll(".enlace"); 
    const cerrarSe = document.querySelector(".CS");

    toggle.addEventListener("click", () => { 
        menuDashboard.classList.toggle("open");

        if (iconoMenu.classList.contains("bi-list")) {
            iconoMenu.classList.replace("bi-list", "bi-x");
        } else {
            iconoMenu.classList.replace("bi-x", "bi-list");
        }
    });

    enlacesMenu.forEach(enlace => {
        enlace.addEventListener("click", () => {
            menuDashboard.classList.add("open");
            iconoMenu.classList.replace("bi-list", "bi-x");
        });
    });

    cerrarSe.addEventListener("click", () => {
        menuDashboard.classList.add("open");
        iconoMenu.classList.replace("bi-list", "bi-x");
    });
});
