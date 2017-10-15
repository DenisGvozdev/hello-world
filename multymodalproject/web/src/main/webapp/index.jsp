<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.net.URLConnection" %>
<%@ page import="java.io.OutputStream" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <h2>Примеры REST запросов</h2>
    <script src="resources/jquery/jquery-3.2.1.js"></script>
    <script type="text/javascript">
        $(document).ready(
            function () {
                $('#btnOne').click(
                    function () {
                        var userName = $('#nameOne')[0].value;
                        var data = JSON.stringify({'name': userName});
                        $.ajax({
                            dataType: "json",
                            contentType: "application/json; charset=UTF-8",
                            url: "rest/personResource/create",
                            type: "POST",
                            data: data,
                            success: function (result) {
                                $('#nameOneResult')[0].value = result.name;
                            }
                        });
                    });
            }
        );
    </script>
</head>
<body>

<h2>1. Передача параметра из формы</h2>
<form action="rest/personResource/createFromFormParams" method="post">
    Ввведите имя пользователя: <input type="text" name="name"/>
    <input type="submit" value="Создать нового пользователя"/>
</form>


<form action="rest/personResource/create" method="post">
    Ведите имя пользователя: <input id="nameOne" type="text" name="name"/><br/>
    Создан пользователь: <input id="nameOneResult" type="text"/><br/>
    <input id="btnOne" type="button" value="Создать нового пользователя ajax запросом"/>

</form>
</body>
</html>
