<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>WebsSockets</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css" media="screen,projection"/>
</head>
<body>
<div id="content">
    <nav  class="nav-extended">
        <div class="nav-wrapper">
            <ul id="nav-mobile" class="left">
                <li><a href="/">Tasks</a></li>
                <li><a href="/admin">Admin page</a></li>
            </ul>
        </div>
        <div class="nav-content">
            <ul class="tabs tabs-transparent">
                <li class="tab"><a href="#list">List</a></li>
                <li class="tab"><a href="#create">Create</a></li>
            </ul>
        </div>
    </nav>
    <div id="list">
        <table class="bordered centered">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Function</th>
            </tr>
            </thead>
            <tbody>
            <#list attrs as item>
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.function.executionFunction}</td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <div style="padding: 10px 10px 10px 10px" id="create">
        <form class="col s6" action="/admin" method="post">
            <div class="row">
                <div class="input-field col s12">
                    <input placeholder="Placeholder" name="task_name" id="first_name" type="text" class="validate">
                    <label for="first_name">Task Name</label>
                </div>
                <div class="input-field col s12">
                    <textarea id="textarea1_list" name="inputdata" class="materialize-textarea"></textarea>
                    <label for="textarea1_list">; separated list of values</label>
                </div>
                <div class="input-field col s12">
                    <textarea id="textarea1" name="func" class="materialize-textarea"></textarea>
                    <label for="textarea1">Execution function</label>
                </div>
            </div>
            <input class="btn waves-effect waves-light" type="submit" name="action">
            </input>
        </form>
    </div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
</body>
</html>