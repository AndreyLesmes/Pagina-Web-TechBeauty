document.addEventListener("DOMContentLoaded", function () {
  const nombreI = document.getElementById("nombre");
  const apellidoI = document.getElementById("apellido");
  const telefonoI = document.getElementById("telefono");
  const correoI = document.getElementById("correo");
  const contrasenaI = document.getElementById("contraseña");

  nombreI.addEventListener("input", function (event) {
    if (!validarNombre(nombreI.value)) {
      event.preventDefault();
    }
  });

  apellidoI.addEventListener("input", function (event) {
    if (!validarApellido(apellidoI.value)) {
      event.preventDefault();
    }
  });

  telefonoI.addEventListener("input", function (event) {
    if (!validarTelefono(telefonoI.value)) {
      event.preventDefault();
    }
  });

  correoI.addEventListener("input", function (event) {
    if (!validarCorreo(correoI.value)) {
      event.preventDefault();
    }
  });

  contrasenaI.addEventListener("input", function (event) {
    if (!validarContrasena(contrasenaI.value)) {
      event.preventDefault();
    }
  });

  function validarNombre(nombre) {
    const nombreE = /^[A-Za-z]{4,15}$/;
    if (!nombreE.test(nombre)) {
      nombreI.setCustomValidity("Ingresa un nombre válido");
    } else {
      nombreI.setCustomValidity("");
    }
  }

  function validarApellido(apellido) {
    const apellidoE = /^[A-Za-z]{4,15}$/;
    if (!apellidoE.test(apellido)) {
      apellidoI.setCustomValidity("Ingresa un apellido válido");
    } else {
      apellidoI.setCustomValidity("");
    }
  }

  function validarTelefono(telefono) {
    const telefonoE = /^(3)[0-9]{9}$/;
    if (!telefonoE.test(telefono)) {
      telefonoI.setCustomValidity("Ingresa un telefono válido");
    } else {
      telefonoI.setCustomValidity("");
    }
  }

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