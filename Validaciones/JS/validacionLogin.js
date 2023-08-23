document.addEventListener("DOMContentLoaded", function() {
    const correoI = document.getElementById("usuario");
    const contrasenaI = document.getElementById("contraseña");
  
    correoI.addEventListener("input", function(event) {
        if (!validarCorreo(correoI.value)) {
            event.preventDefault();
        }
    });
  
    contrasenaI.addEventListener("input", function(event) {
        if (!validarContrasena(contrasenaI.value)) {
            event.preventDefault();
        }
    });
  
    function validarCorreo(correo) {
      const correoE = /^[a-zA-Z0-9._-]+@(gmail|hotmail|outlook)+\.(com|co|es)$/;
      if (!correoE.test(correo)) {
        correoI.setCustomValidity("Ingresa un correo electrónico válido");
      } else {
        correoI.setCustomValidity("");
      }
    }
  
    function validarContrasena(contrasena) {
      const contrasenaE = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[@#$%^&\-_+=]).{8,16}$/;
      if (!contrasenaE.test(contrasena)) {
        contrasenaI.setCustomValidity("La contraseña debe cumplir los requisitos de seguridad");
      } else {
        contrasenaI.setCustomValidity("");
      }
    }
});