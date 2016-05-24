<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>API</title>
    <jsp:include page="common-top.jsp"/>

    <%
        String jsonServiceURL = request.getContextPath() + "/api/";
    %>

    <script>
        function listEvents(result){

            var table = "Events = " + result.length + "<table><tr><td>Name</td><td>Count</td></tr>";
            $.each(result, function(k, v) {

                var row = "<tr><td>" +  result[k].name + "</td><td> " + result[k].count + "</td></tr>";
                table += row;
            });

            table += "</table>";

            $("#listEventsId").html(table);
        }

        $( document ).ready(function() {
            readFromServer(listEvents,"ShowEventsAction");
        });

        function reloadChannels(){
            readFromServer(listEvents,"ShowEventsAction");
        }

    </script>

</head>
<body>

<jsp:include page="common-left.jsp"/>
<div class="centerDiv" id="listEventsId"> **** </div>
</body>

</html>
