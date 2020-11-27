function buscarEmpresasDesdeAPI() {
    let repuestaServidor = "";
    let endpoint = "listaEmpresa"
    const Http = new XMLHttpRequest();
    const url = location.protocol + "//" + location.hostname + ':' + location.port + '/api-Rest/' + endpoint;
    Http.open("GET", url);
    Http.send();
    Http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            repuestaServidor = JSON.parse(Http.responseText);
            if (repuestaServidor != null) {
                if (repuestaServidor.length > 0) {
                    cargarEmpresas(repuestaServidor);
                }

            }
        }

    }
}

function cargarEmpresas(data) {
    let selectEmpresa = document.getElementById("empresa");
    let j = 0;
    selectEmpresa.length = data.length + 1;
    for (let i in data) {
        j = parseInt(i) + 1;
        selectEmpresa.options[j].value = data[i].id;
        selectEmpresa.options[j].text = " " + data[i].nombre;
    }

}

function buscarSucursalDadoEmpresa(idEmrpesa) {
    let repuestaServidor = "";
    let endpoint = "sucursalPorEmpresa"
    const Http = new XMLHttpRequest();
    const url = location.protocol + "//" + location.hostname + ':' + location.port + '/api-Rest/' + endpoint + "/" + idEmrpesa;
    Http.open("GET", url);
    Http.send();
    Http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            repuestaServidor = JSON.parse(Http.responseText);
            if (repuestaServidor != null) {
                if (repuestaServidor.length > 0) {
                    cargarSucursales(repuestaServidor);
                }

            }
        }

    }
}

function cargarSucursales(data) {
    let selectEmpresa = document.getElementById("sucursal");
    let j = 0
    selectEmpresa.length = data.length + 1;
    for (let i in data) {
        j = parseInt(i) + 1;
        selectEmpresa.options[j].value = data[i].id;
        selectEmpresa.options[j].text = " " + data[i].ubicacion.dirrecion;
    }
}

function cambiarSucursal() {
    //
    let empresa = document.getElementById('empresa');
    var ind = document.getElementById('empresa').value;
    let sucursal = document.getElementById('sucursal');

    limpiarSucursal();
    if (ind != -1) {
        sucursal.disabled = false;
        buscarSucursalDadoEmpresa(ind);
    }
}

function limpiarSucursal() {
    let sucursal = document.getElementById('sucursal');
    sucursal.disabled = true;
    // si no hay nada selecionada elimino lo otro.
    sucursal.length = 1
    //coloco un guión en la única opción que he dejado
    sucursal.options[0].value = -1;
    sucursal.options[0].text = 'Selecionar Sucursal:';
    let modo = document.getElementById('modo');
    modo.disabled = true;
    modo.options[0].value = -1;
    modo.options[0].text = 'Selecionar Modo';
    let agregar = document.getElementById('agregar');
    agregar.disabled = true;

}

function habiliatarModo() {
    let sucursal = document.getElementById('sucursal');
    var ind = document.getElementById('sucursal').value;
    let modo = document.getElementById('modo');
    let agregar = document.getElementById('agregar');
    modo.disabled = true;
    agregar.disabled = true;
    modo.options[0].value = -1;
    modo.options[0].text = 'Selecionar Modo';
    if (ind != -1) {
        modo.disabled = false;
        return
    }


}

function habilitarBotonAgregar() {
    var ind = document.getElementById('modo').value;
    let agregar = document.getElementById('agregar');
    agregar.disabled = true;
    if (ind != -1) {
        agregar.disabled = false;
    }

}

buscarEmpresasDesdeAPI();