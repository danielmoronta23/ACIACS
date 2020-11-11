// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Area Chart Example
var ctx = document.getElementById("myAreaChart");
var myLineChart = new Chart(ctx, {
  type: 'line',
  data: {
    labels: ["Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"],
    datasets: [{
      label: "Prioridad",
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
      data: [10000, 30162, 26263, 11394, 18287, 28682, 31274],
    },{
        label: "Prioridad No aceptados",
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
      data: [1000, 3062, 2623, 1894, 1287, 2682, 3124],

    },{
      label: "Aceptados",
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
      data: [12000, 35062, 27623, 19874, 12879, 2682, 3124],

    },
      {
        label: "Denegados",
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
        data: [12500, 39062, 2723, 19174, 12179, 27282, 4124],

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
          max: 40000,
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
