<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Items</title>
    <%
        String jsonServiceURL = request.getContextPath() + "/api/";
    %>

    <jsp:include page="common-top.jsp"/>

    <script>
        function itemCallback(result) {
            var table = "Added Item: <table>";
            $.each(result, function (k, v) {
                var row = "<tr><td>" + result[k].title + "</td></tr>";
                table += row;
            });

            table += "</table>";

            $("#resultDivId").html(table);
        }

        function itemSearchByTagsCallBack(result) {
            var table = "Search result: " + result.length + "<table width='400px'>";
            $.each(result, function (k, v) {
                var item = result[k];
                var row = "<tr><td>" + item.title + " descr=" + item.description + "<br>" + "</td></tr>";

                table += row;
                table += "<tr><td></td></tr>";
            });

            table += "</table>";

            $("#resultDivId").html(table);
        }

        function itemGetItemGroupsActionCallBack(result) {
            var table = "ItemGroups: " + result.countItemGroups + "<br/> " + result.description;

            $("#resultDivId").html(table);
        }
    </script>

</head>
<body>

<jsp:include page="common-left.jsp"/>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header">Item Collector</h1>

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
                <button formaction="items.jsp" formtarget="statisticsFrame"
                        type="button" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-th"></span>
                    ItemGetByType
                </button>
            </a>
        </div>
        <div class="col-xs-6 col-sm-3 placeholder">
            <a href="<%=request.getContextPath()%>/jsp/items.jsp" target="statisticsFrame" %>
                <button formaction="items.jsp" formtarget="statisticsFrame"
                        type="button" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-th"></span>
                    Channels
                </button>
            </a>
        </div>
        <div class="col-xs-6 col-sm-3 placeholder">
            <a href="<%=request.getContextPath()%>/jsp/items.jsp" target="statisticsFrame" %>
                <button formaction="items.jsp" formtarget="statisticsFrame"
                        type="button" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-th"></span>
                    Tags
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


</body>
</html>
