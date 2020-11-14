// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

function cargarGraficaVisitasPorHora(visitasNoramalesAceptadas, visitaNormalesDenegadas, visitasPrioritariaAceptada, visitasPrioritariaDenegada) {
    var ctx = document.getElementById("chartN");
    var ctxPrioritario = document.getElementById("chartP");

    let maximoVisitasNormales = 0;
    let maximoVisitasPrioritaria = 0
    let maximo = 0;
    if (Math.max.apply(null, visitasNoramalesAceptadas) > Math.max.apply(null, visitaNormalesDenegadas)) {
        maximoVisitasNormales = Math.max.apply(null, visitasNoramalesAceptadas);
    } else {
        maximoVisitasNormales = Math.max.apply(null, visitaNormalesDenegadas);
    }

    if (Math.max.apply(null, visitasPrioritariaAceptada) > Math.max.apply(null, visitasPrioritariaDenegada)) {
        maximoVisitasPrioritaria = Math.max.apply(null, visitasPrioritariaAceptada);
    } else {
        maximoVisitasPrioritaria = Math.max.apply(null, visitasPrioritariaAceptada);
    }
    if (maximoVisitasNormales > maximoVisitasPrioritaria) {
        maximo = maximoVisitasNormales;
    } else {
        maximo = maximoVisitasPrioritaria
    }

    let inicio = 5;
    let fin = 23;
    let vNA = [];
    let vND = [];
    let vPA = [];
    let vPD = [];
    let contador=0;
    for (let i = inicio; i <= fin; i++) {
        vNA[contador] = visitasNoramalesAceptadas[i];
        vND[contador] = visitaNormalesDenegadas[i];
        vPA[contador] = visitasPrioritariaAceptada[i];
        vPD[contador] = visitasPrioritariaDenegada[i];
        contador=contador+1;
    }
    var VisitasNormalesRealizadas = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"],
            datasets: [{
                label: "Aceptadas",
                lineTension: 0.3,
                backgroundColor: "#99FF99",
                borderColor: "#28a745",
                pointRadius: 5,
                pointBackgroundColor: "#28a745",
                pointBorderColor: "rgba(255,255,255,0.8)",
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "#28a745",
                pointHitRadius: 50,
                pointBorderWidth: 2,
                data: vNA,
            },
                {
                    label: "Denegadas",
                    lineTension: 0.3,
                    backgroundColor: "#FF9966",
                    borderColor: "#dc3545",
                    pointRadius: 5,
                    pointBackgroundColor: "#dc3545",
                    pointBorderColor: "rgba(255,255,255,0.8)",
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: "#dc3545",
                    pointHitRadius: 50,
                    pointBorderWidth: 2,
                    data: vND,
                }
            ],
        },
        options: {
            scales: {
                xAxes: [{
                    time: {
                        unit: 'date'
                    },
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        maxTicksLimit: 7
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        max: maximo,
                        maxTicksLimit: 5
                    },
                    gridLines: {
                        color: "rgba(0, 0, 0, .125)",
                    }
                }],
            },
            legend: {
                display: true
            }
        }
    });
    var VisitasConPrioridadRealizadas = new Chart(ctxPrioritario, {
        type: 'line',
        data: {
            labels: ["5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"],
            datasets: [{
                label: "Aceptadas",
                lineTension: 0.3,
                backgroundColor: "rgba(2,117,216,0.2)",
                borderColor: "rgba(2,117,216,1)",
                pointRadius: 5,
                pointBackgroundColor: "rgba(2,117,216,1)",
                pointBorderColor: "rgba(255,255,255,0.8)",
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "rgba(2,117,216,1)",
                pointHitRadius: 50,
                pointBorderWidth: 2,
                data: vPA,
            }, {
                label: "Denegadas",
                lineTension: 0.3,
                backgroundColor: "#FFFF99",
                borderColor: "#FFCC33",
                pointRadius: 5,
                pointBackgroundColor: "#FFCC33",
                pointBorderColor: "rgba(255,255,255,0.8)",
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "#FFCC33",
                pointHitRadius: 50,
                pointBorderWidth: 2,
                data: vPD,

            }
            ],
        },
        options: {
            scales: {
                xAxes: [{
                    time: {
                        unit: 'date'
                    },
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        maxTicksLimit: 7
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        max: maximo,
                        maxTicksLimit: 5
                    },
                    gridLines: {
                        color: "rgba(0, 0, 0, .125)",
                    }
                }],
            },
            legend: {
                display: true
            }
        }
    });

}

function cargarTotalesDeVisitas(a, b, c, d) {
    let totalPermitidos = document.getElementById("TotalPermitidos");
    let totalDenegadas = document.getElementById("totalDenegadas");
    let TotalPrioridadPermitidos = document.getElementById("TotalPrioridadPermitidos");
    let TotalPrioridadDenegados = document.getElementById("TotalPrioridadDenegados");
    totalPermitidos.innerText = a;
    totalDenegadas.innerText = b;
    TotalPrioridadPermitidos.innerText = c;
    TotalPrioridadDenegados.innerText = d;

}

function cargarDatosDesdeAPI() {
    let estadistica = "";
    let idSucursal = "1";
    let endpoint = "visitasPorHora"
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:7000/api-Rest/' + endpoint + "/" + idSucursal;
    Http.open("GET", url);
    Http.send();
    Http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            estadistica = JSON.parse(Http.responseText);
            cargarTotalesDeVisitas(estadistica.visitasNormalesAceptadas.Total, estadistica.visitasNormalesDenegadas.Total, estadistica.visitasPrioritariaAceptada.Total, estadistica.visitasPrioritariaDenegada.Total)
            cargarGraficaVisitasPorHora(estadistica.visitasNormalesAceptadas.PorHora, estadistica.visitasNormalesDenegadas.PorHora, estadistica.visitasPrioritariaAceptada.PorHora, estadistica.visitasPrioritariaDenegada.PorHora);
        }

    }
}

$( document ).ready(function() {
    console.log( "ready!" );
    cargarDatosDesdeAPI();
});
function actualizarDatos(){
    cargarDatosDesdeAPI();
}
