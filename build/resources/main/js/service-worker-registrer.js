navigator.serviceWorker.addEventListener('message', function(event) {
    if (event.data.type === 'initial-measure') {
        coreConfig = {speed: event.data.speed, time: event.data.time}
        showCoreConfigurations(event.data.speed);
    } else if (event.data.type === 'measure') {
        showCpuUsageCharts([event.data.dataset, coreConfig.time]);
    }
});

navigator.serviceWorker.register("performance.js")
        .then(function(registration) {
            console.log(registration.controller);
            console.log("Service worker registered, scope: " + registration.scope);
            navigator.serviceWorker.controller.postMessage({type:'initial-measure'});
            setInterval(function() {
                navigator.serviceWorker.controller.postMessage({type:'measure'})
            }, 3000);
        })
        .catch(function(error) {
            console.log("Service worker registration failed: " + error.message);
        });
