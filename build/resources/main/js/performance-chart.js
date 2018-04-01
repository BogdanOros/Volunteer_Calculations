function showCoreConfigurations(power) {
    var countSpan = document.getElementById('cores-info-count');
    countSpan.replaceChild(document.createTextNode("Count of cores: 4"), countSpan.childNodes[0]);
    var powerSpan = document.getElementById('cores-info-power');
    powerSpan.replaceChild(document.createTextNode("Core power: " + power + "GHz"), powerSpan.childNodes[0]);
}

function createChart() {
    const chartHolder = document.getElementById('cpu-usage');
    return new Chart(chartHolder, {
        type: 'doughnut',
        options: {
            responsive: false
        },
        data: {
            datasets: [{
                data: [1, 1],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.7)',
                    'rgba(54, 162, 235, 0.7)',
                ]
            }],
            labels: ["Under load", "Default"]
        }});
}

function showCpuUsageCharts(dataSet) {
    if (chart !== null || chart !== undefined) {
        chart.data.datasets.forEach(set => set.data = dataSet);
        chart.update();
    }
}
let coreConfig = null;
const chart = createChart();


