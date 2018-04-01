<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>WebsSockets</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css" media="screen,projection"/>
    <style>
        .nav-wrapper {
            background-color: #26A69A;
        }
        .centered {
            width: 90%;
            display: table;
            margin-left: 5%;
            margin-top: 25px;
        }
        #js-active-task {
            display: flex;
            justify-content: flex-start;
        }
        canvas {
            display: inline !important;
        }
    </style>
</head>
<body>
<div id="content">
    <nav>
        <div class="nav-wrapper">
            <ul id="nav-mobile" class="left">
                <li><a href="/">Tasks</a></li>
                <li><a href="/admin">Admin page</a></li>
            </ul>
        </div>
    </nav>
    <table class="bordered centered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <#list attrs as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>
                <a onclick="sendTask(${item.id}, '${item.name}')" class="waves-effect waves-light btn">Execute</a>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
    <div style="padding-left: 100px">
        <h4>CPU Usage graph</h4>
        <div id="js-active-task">
            <div id="core-configuration">
                <span id="cores-info-count">Not calculated yet</span>
                <p id="cores-info-power">Not calculated yet</p>
            </div>
            <canvas id="cpu-usage" width="300" height="300"></canvas>
            <div id="choosen-task">
                <p>Selected task information:</p>
                <span id="selected-task-name"></span>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/js/chart.js"></script>
<script type="text/javascript" src="/js/performance-chart.js"></script>
<script type="text/javascript" src="/js/service-worker-registrer.js"></script>
<script type="text/javascript" src="/js/tasks.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
</body>
</html>