let webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/execution");
let activeTaskFunc = null;
let input = null;
let func = null;

function sendTask(taskId, taskName) {
    let payload = {
        action: 'attach',
        payload: taskId
    };
    webSocket.send(JSON.stringify(payload));
    console.log("Task choosen: " + taskId);
    showChoosenTask(taskName);
}

webSocket.onmessage = function (msg) {
    let data = JSON.parse(msg.data);
    if (data.action === 'attach') {
        console.log(data);
        buildFunctionToExecute(data);
        if (activeTaskFunc !== undefined || activeTaskFunc !== null) {
            activeTaskFunc.call();
        }
    }
};

function buildFunctionToExecute(data) {
    input = data.inputData.data;
    const task = data.task;
    func = new Function("str", task.function.executionFunction);
    activeTaskFunc = processActiveTaskFunction(task);
    console.log(activeTaskFunc);

}

webSocket.onclose = function () {
    console.log("Closed");
};

webSocket.onopen = function (msg) {
    console.log("Opened");
};

function showChoosenTask(taskName) {
    const holder = document.getElementById('selected-task-name');
    holder.innerHTML = taskName;
}

function processActiveTaskFunction(task) {
    const result = {};
    let counter = 0;
    let size = input.length;
    return function(){
        while(counter < size) {
            if (counter < size) {
                let str = input[counter];
                result[str.data] = func(str.data);
                counter++;
                console.log(result);
            } else {
                let response = {
                    action: 'result',
                    result: {result: result, task: task.id}
                };
                webSocket.send(JSON.stringify(response));
            }
        }
    };
}