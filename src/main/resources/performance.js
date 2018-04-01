function calculateBasicCPUspeed() {
    const d = new Date();
    const amount = 150000000;
    for (let i = amount; i > 0; i--) {}
    var newDate = new Date();
    var accnewd = Number(String(newDate.getSeconds()) + "." + String(newDate.getMilliseconds()));
    var accd = Number(String(d.getSeconds())+"."+String(d.getMilliseconds()));
    var di = accnewd-accd;
    if (d.getMinutes() !== newDate.getMinutes()) {
        di = (60*(newDate.getMinutes()-d.getMinutes()))+di}
    const time = Math.round(di*1000)/1000;
    var _speedconstant = 1.15600e-8;
    const rawSpeed = ((((_speedconstant*amount)/di) / navigator.hardwareConcurrency));
    const speed = Math.abs(Math.round(rawSpeed * 100) / 100);
    return {time, speed};
}

self.addEventListener('install', function(event) {
    event.waitUntil(self.skipWaiting()); // Activate worker immediately
});

self.addEventListener('activate', function(event) {
    event.waitUntil(self.clients.claim()); // Become available to all pages
});

self.addEventListener('message', function(event) {
    const eventDataType = event.data.type;
    if (eventDataType === 'initial-measure') {
        self.clients.matchAll().then(all => all.forEach(client => {
            let coreConfigurations = null;
            for (let i = 0; i < 10; i++) {
                coreConfigurations = calculateBasicCPUspeed();
            }
            client.postMessage({
                type: 'initial-measure',
                ...coreConfigurations
            });
        }));
    } else if (eventDataType === 'measure') {
        self.clients.matchAll().then(all => all.forEach(client => {
            client.postMessage({
                type: 'measure',
                dataset: calculateCPUspeed()
            })
        }));
    }
});


function calculateCPUspeed() {
    const d = new Date();
    const amount = 150000000;
    for (let i = amount; i > 0; i--) {}
    var newDate = new Date();
    var accnewd = Number(String(newDate.getSeconds()) + "." + String(newDate.getMilliseconds()));
    var accd = Number(String(d.getSeconds())+"."+String(d.getMilliseconds()));
    var di = accnewd-accd;
    if (d.getMinutes() !== newDate.getMinutes()) {
        di = (60*(newDate.getMinutes()-d.getMinutes()))+di}
    return Math.round(di * 1000) / 1000;

}