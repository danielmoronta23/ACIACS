function generarQR(cedula, com) {
    if (cargarQR(cedula)) {
        document.getElementById("divQR").style.display = "block";
    }
}

function pp() {
    var base64 = $("#codigoQR img").attr('src');
    $("#descargarCodigo").attr('href', base64);
    $("#descargarCodigo").attr('download', "codigoQR");
    $("#descargarCodigo").trigger("click");
}


function cargarQR(cedula) {
    document.getElementById("codigoQR").innerHTML = "";
    let miCodigoQR = new QRCode("codigoQR");
    miCodigoQR.makeCode(cedula);
    return true;
}
