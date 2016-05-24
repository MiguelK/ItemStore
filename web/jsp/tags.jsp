<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Tags</title>

    <%
        String jsonServiceURL = request.getContextPath() + "/api/";
    %>

    <jsp:include page="common-top.jsp"/>

    <script>
        function listAllTags(result) {

            var table = "<table>";
            $.each(result, function (k, v) {
                console.log("result=" + result[k].name);
                var row = "<tr><td>" + result[k].name + "</td></tr>";
                table += row;
            });

            table += "</table>";

            $("#listTags").html(table);
        }

        $(document).ready(function () {
            readFromServer(listAllTags, "TagGettAllAction");
        });


    </script>

</head>
<body>

<jsp:include page="common-left.jsp"/>

<!--
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header">Tags</h1>

    <div class="row placeholders">
        <div class="col-xs-6 col-sm-3 placeholder">
            <a href="items-create-random.jsp" target="_self">
                <button type="button" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-th"></span>
                    Create random
                </button>
            </a>
        </div>

        <div class="col-xs-6 col-sm-3 placeholder">
            <a href="<%=request.getContextPath()%>/jsp/items.jsp" target="statisticsFrame" %>
                <button onclick="readFromServer()" formtarget="statisticsFrame"
                        type="button" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-th"></span>
                    Test (read)
                </button>
            </a>
        </div>
    </div>
</div>
-->

<div class="centerDiv" id="listTags"></div>


</body>
</html>
