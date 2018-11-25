'use strict'

/**
 * Validacion de largo de cadenas.
 * @param {element} input - Elemento HTML input.
 * @returns {Boolean} - True si es mayor a 0 y menor a 100 de largo, false de lo contrario.
 */
function validar_texto(input){
    try{
        var value = input.value;
        if (value.length <= 0 || value.length >= 100) {
            return false;
        }
        return true;
    }catch(ex){
        return false;
    }
}

/**
 * Valida el largo del input contraseña.
 * @param {type} input
 * @returns {Boolean} - True si la contraseña cumple con las condiciones.
 */
function validar_texto_contrasena(input){
    try{
        var value = input.value;
        
        if (value.length === 0) {
            return true;
        }
        
        if (value.length < 6 || value.length > 50) {
            return false;
        }
        return true;
    }catch(ex){
        return false;
    }
}

/**
 * Valida si las contraseñas ingresadas coinciden.
 * @param {string} pass 
 * @param {string} pass2
 * @returns {Boolean} - True si las contraseñas coniciden
 */
function valida_contrasena_coincide(pass, pass2){
    try {
        var pw1 = pass.value;
        var pw2 = pass2.value;
        
        if (pw1 === pw2) {
            return true;
        }
        return false;
    } catch(ex) {
        return false;
    }
}

function ValidateEmail(input) 
{
    try {
        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        var email = input.value;
        if (email.match(mailformat))
        {
            return true;
        }
        return false;
    } catch(ex) {
        return false;
    }
}

/**
 * Validacion para RUT Chileno. Ignora puntos y guiones.
 * @param {string} Objeto 
 * @returns {String|RutValidation.rutCompleto|Boolean|Number}
 */
function RutValidation (Objeto) {
  if (Objeto != null) {
    var tmpstr = '';
    var intlargo = Objeto;
    if (intlargo.length > 0) {
      var crut = Objeto;
      var largo = crut.length;
      if (largo < 2) {
        return false;
      }
      for (var i = 0; i < crut.length; i++) {
        if (crut.charAt(i) !== ' ' && crut.charAt(i) !== '.' && crut.charAt(i) !== '-') {
          tmpstr = tmpstr + crut.charAt(i)
        }
      }
      var rut = tmpstr;
      crut = tmpstr;
      largo = crut.length;
      if (largo > 2) {
        rut = crut.substring(0, largo - 1);
      } else {
        rut = crut.charAt(0);
      }
      var dv = crut.charAt(largo - 1);
      if (rut === null || dv === null) {
        return false;
      }
      var dvr = '0';
      var suma = 0;
      var mul = 2;
      for (i = rut.length - 1; i >= 0; i--) {
        suma = suma + rut.charAt(i) * mul;
        if (mul === 7) {
          mul = 2;
        } else {
          mul++;
        }
      }
      var res = suma % 11;
      if (res === 1) {
        dvr = 'k';
      } else {
        if (res === 0) {
          dvr = '0';
        } else {
          var dvi = 11 - res;
          dvr = dvi + '';
        }
      }
      var rutCompleto = rut + '-' + dv.toLowerCase();
      if (dvr !== dv.toLowerCase() || rutCompleto.length < 9) {
        return false;
      }
      return true;
    }
    return false;
  } else {
    return false;
  }
}

/**
 * Valida si una cadena es una fecha.
 * @param {string} value - fecha en forma de cadena.
 * @returns {Boolean} true si es fecha, false de lo contrario.
 */
function isDate (value) {
    if (isNaN(Date.parse(value))) {
        return false;
    } else {
        return true;
    }
}

/**
 * Valida si una fecha corresponde a una antiguedad de menos de 18 años.
 * @param {string} date - fecha en forma de cadena
 * @returns {Boolean} true si es menor a 18. False de lo contrario.
 */
function isUnderAge (date) {
    if (isDate(date)) {
        var fechaActual = new Date();
        var fecha = new Date(date.replace(/-/g, '\/'));
        if (fechaActual.getFullYear() - fecha.getFullYear() < 18) {
            return true;
        } else if (fechaActual.getFullYear() - fecha.getFullYear() === 18 && fechaActual.getMonth() < fecha.getMonth()) {
            return true;
        } else if (fechaActual.getFullYear() - fecha.getFullYear() === 18 && fechaActual.getMonth() === fecha.getMonth() && fechaActual.getDate() < fecha.getDate()) {
            return true;
        } else {
            return false;
        }
    } else {
        return true;
    }
}

function validacion_fecha_inicio(value) {
    try {
        var hoy = new Date();
        var y = String(hoy.getUTCFullYear());
        var m = String(hoy.getUTCMonth() + 1);
        var d = String(hoy.getUTCDate());
        hoy = new Date(y + '/' + m + '/' + d);
        
        if (isDate(value)) {
            var inicio = new Date(value.replace(/-/g, '\/'));

            if (hoy.getTime() >= inicio.getTime()) {
                return false;
            }
            return true;
        }
        return false;
    } catch (ex) {
        return false;
    }
}

function validacion_fecha_termino(inicio, termino) {
    try {
        var fecha_inicio = new Date(inicio.replace(/-/g, '\/'));
        var fecha_termino = new Date(termino.replace(/-/g, '\/'));
        
        if (isDate(fecha_inicio) && isDate(fecha_termino)) {
            if (fecha_inicio.getTime() >= fecha_termino.getTime()) {
                return false;
            }
            return true;
        }
        return false;
    } catch (ex) {
        return false;
    }
}

function validar_numero(input){
    try {
        var value = input.value;
        
        if (value == null || isNaN(value)) {
            return false;
        } else {
            var num = Number(value);
            
            if (num < 1 || num >= 100) {
                return false;
            }
            
            return true;
        }
    } catch(ex) {
        return false;
    }
}

function valida_numero_mayor(max, min){
    try {
        var num_max = max.value;
        var num_min = min.value;
        
        if (!validar_numero(max) || !validar_numero(min)) {
            return false;
        } else {
            num_max = Number(num_max);
            num_min = Number(num_min);
            
            return (num_max > num_min);
        }
    } catch (ex) {
        return false;
    }
}

/********* Validaciones de formularios ***************/

/**
 * Validacion Mi Cuenta
 */
function mi_cuenta(){
    $('#usuario').submit(function(event) {
        event.preventDefault();
        var pw1 = document.getElementById('contrasena');
        var pw2 = document.getElementById('pw2');
        
        var campos = {
            nombre : {
                valid: validar_texto(document.getElementById('persona.nombre')),
                message: 'Debe ingresar un nombre de máximo 100 caracteres'
            },
            app_paterno: {
                valid: validar_texto(document.getElementById('persona.app_paterno')),
                message: 'Debe ingresar un appellido paterno de máximo 100 caracteres'
            },
            app_materno: {
                valid: validar_texto(document.getElementById('persona.app_materno')),
                message: 'Debe ingresar un appellido materno de máximo 100 caracteres'
            },
            rut: {
                valid: RutValidation(document.getElementById('persona.rut').value),
                message: 'Debe ingresar un rut válido'
            },
            fecha_nacimiento: {
                valid: !isUnderAge(document.getElementById('persona.fech_nacimiento').value),
                message: 'Debe ser mayor de edad'
            },
            calle: {
                valid: validar_texto(document.getElementById('persona.direccion.calle')),
                message: 'Debe ingresar una calle'
            },
            numeracion: {
                valid: validar_texto(document.getElementById('persona.direccion.numeracion')),
                message: 'Debe ingresar una numeración'
            },
            email: {
                valid: validar_texto(document.getElementById('persona.contacto.desc_contacto')),
                message: 'Debe ingresar un email valido'
            },
            contrasena1: {
                valid: validar_texto_contrasena(pw1),
                message: 'Debe ingresar una contraseña de al menos 6 caracteres y máximo 50'
            },
            contrasena2: {
                valid: valida_contrasena_coincide(pw1, pw2),
                message: 'Las contraseñas deben coincidir'
            }
        };
        
        var valid = true && campos.nombre.valid && campos.app_paterno.valid 
                && campos.app_materno.valid && campos.rut.valid 
                && campos.fecha_nacimiento.valid && campos.calle.valid && campos.numeracion.valid
                && campos.email.valid && campos.contrasena1.valid && campos.contrasena2.valid;
        
        if (valid) {
            this.submit();
        } else {
            set_mensajes(campos);
        }
    });
}

/**
 * Crear o limpia los mensajes de error de los campos de los formularios.
 * @param {type} campos - Objeto con los campos del formulario
 */
function set_mensajes(campos){
    for(var key in campos) {
        if (campos.hasOwnProperty(key)){
            try {
                var elem = document.getElementById(key);
                if (campos[key].valid === false) {
                    // Set the error message
                    elem.innerText = campos[key].message;
                } else {
                    // Clear the error message
                    elem.innerText = '';
                }
            } catch (ex) {
                // Do nothing.
            }
        }
    }
}

/**
 * Validación Formulario "Nuevo Programa de Estudio" y "Editar Programa de Estudio"
 */
function nuevo_programa(){
    $('#programa').submit(function(event) {
        debugger;
        event.preventDefault();
        var inicio = document.getElementById('fech_inicio').value;
        var termino = document.getElementById('fech_termino').value;
        var min = document.getElementById('cant_min_alumnos');
        var max = document.getElementById('cant_max_alumnos');
        
        var campos = {
            nombre: {
                valid: validar_texto(document.getElementById('nomb_programa')),
                message: 'Debe ingresar un nombre de máximo 100 caracteres'
            },
            descripcion: {
                valid: validar_texto(document.getElementById('desc_programa')),
                message: 'Debe ingresar una descripción de máximo 100 caracteres'
            },
            fecha_inicio: {
                valid: validacion_fecha_inicio(inicio),
                message: 'Debe ingresar una fecha mayor a hoy'
            },
            fecha_termino: {
                valid: validacion_fecha_termino(inicio, termino),
                message: 'Debe ingresar una fecha mayor a la fecha de inicio'
            },
            min_alumnos: {
                valid: validar_numero(min),
                message: 'Debe ingresar un valor mayor a 1 y menor a 100'
            },
            max_alumnos: {
                valid: valida_numero_mayor(max, min),
                message: 'Debe ingresar un valor mayor a la cantidad minima de alumnos y menor a 100'
            }
        };
        
        var valid = true && campos.nombre.valid && campos.descripcion.valid && campos.fecha_inicio.valid
                    && campos.fecha_termino.valid && campos.min_alumnos.valid && campos.max_alumnos.valid;
        
        if (valid) {
            this.submit();
        } else {
            set_mensajes(campos);
        }
    });
}

/**
 * Validacion para formularios de cursos
 */
function cursos(){
    $("#curso").submit(function(event){
        event.preventDefault();
        
        var campos = {
            descripcion: {
                valid: validar_texto(document.getElementById('desc_curso')),
                message: 'Debe ingresar una descripción de menos de 100 caracteres'
            },
            cupo: {
                valid: validar_numero(document.getElementById('cupos')),
                message: 'Debe ingresar un número mayor a 1 y menor a 100'
            }
        };
    });
}