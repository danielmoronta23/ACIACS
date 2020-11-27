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

function habiliatarAgregar(){
    let empresa = document.getElementById('empresa');
    var ind = document.getElementById('empresa').value;
    let agregar = document.getElementById('agregar');

    if (ind != -1) {
        agregar.disabled = false;
        return;
    }
    agregar.disabled=true;
}
buscarEmpresasDesdeAPI();