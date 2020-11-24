function consultarDesdeAPI() {
    let cedula = document.getElementById("cedula");
    let repuestaServidor = "";
    let endpoint = "buscarPersona"
    const Http = new XMLHttpRequest();
    const url = location.protocol+location.hostname+':'+location.port+'/api-Rest/' + endpoint + "/" + cedula.value;
    Http.open("GET", url);
    Http.send();
    Http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            repuestaServidor = JSON.parse(Http.responseText);
            cargarDatosPersona(repuestaServidor[0], repuestaServidor[1]);
        }

    }
}

function cargarDatosPersona(estado, datoPersona) {
    let cedula = document.getElementById("cedula");
    let nombre = document.getElementById("nombre");
    let apellido = document.getElementById("apellido");
    let correo = document.getElementById("correoPersona");

    let primerNombre;
    let segundoNombre;
    let primerApellido;
    let SegundoApellido;
    if (estado == false) {
        nombre.disabled = false;
        apellido.disabled = false;
        correo.disabled = false;
        nombre.innerText = "";
        nombre.placeholder = "Nombre...";
        nombre.value = "";
        apellido.innerText = "";
        apellido.placeholder = "Apellido...";
        apellido.value = "";
        correo.innerText = "";
        correo.placeholder = "Correo...";
        correo.value = "";
        // no se encontro la persona, por favor registrar.
    } else {
        // Se cargan los datos de las personas
        cedula.innerText = datoPersona.cedula;
        cedula.value = datoPersona.cedula;
        primerNombre = datoPersona.primerNombre;
        segundoNombre = datoPersona.segundoNombre;
        primerApellido = datoPersona.primerApellido;
        SegundoApellido = datoPersona.segundoApellido;
        nombre.innerText = primerNombre + " " + segundoNombre;
        nombre.placeholder = primerNombre + " " + segundoNombre;
        nombre.value = primerNombre + " " + segundoNombre;
        apellido.innerText = primerApellido + " " + SegundoApellido;
        apellido.placeholder = primerApellido + " " + SegundoApellido;
        apellido.value = primerApellido + " " + SegundoApellido;
        correo.innerText = datoPersona.correo;
        correo.placeholder = datoPersona.correo;
        correo.value = datoPersona.correo;
    }
}

function buscarPersona() {
    console.log("Buscando persona...");
    consultarDesdeAPI();
}
function eliminar(cedula, id){

    let bConfirmacion = document.getElementById("borrarCliente");
    bConfirmacion.innerText = cedula;
    bConfirmacion.value = cedula;
    document.getElementById("cedulaEliminar").value = id;
}
function bloquear(cedula, id){

    let bBloquear = document.getElementById("bloquearP");
    bBloquear.innerText = cedula;
    bBloquear.value = cedula;
    document.getElementById("bloquear").value = id;
}
console.log("Entrando js de registerPriority...");