<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <jsp:include page="common-top.jsp"/>

    <%
        String jsonServiceURL = request.getContextPath() + "/api/";
    %>

    <script>
        function itemCallback(result) {

            var table = "<table>";
            $.each(result, function (k, v) {
                var row = "<tr><td>" + result[k].title + "</td></tr>";
                table += row;
            });

            table += "</table>";

            $("#resultDivId").html(table);

        }
    </script>

</head>
<body>

<jsp:include page="common-left.jsp"/>

<h2 align="center">Create Random Items </h2>

<div id="resultDiv" class="centerDiv">

    <form action="#" id="formId" onsubmit="postToServer('formId', itemCallback, 'ItemCreateRandomAction')"
          method="post"
          target="hiddenFrame">
        <table border="1" bgcolor="aqua">
            <tr>
                <td>Title</td>
                <td><input type="text" autofocus name="title" placeholder="Title"  required></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description"  placeholder="Description"></td>
            </tr>
            <tr>
                <td>Count</td>
                <td><input type="number" name="itemsToGenerate" placeholder="Count"></td>
            </tr>
            <tr>
                <td>Tags</td>
                <td><input type="itemTagTree" name="itemTagTree" value="Swe"></td>
            </tr>

        </table>

        <div>
            <input type="submit" value="Generate Item">

        </div>
    </form>


    <div id="resultDivId"></div>

</div>


</body>

</html>
