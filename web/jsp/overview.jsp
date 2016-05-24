<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Items</title>

    <%
        String jsonServiceURL = request.getContextPath() + "/api/";
    %>
    <jsp:include page="common-top.jsp"/>

    <script>
        function statCallback(result) {
            $("#totalItemsId").text(" " + result.itemsCount);
        }

        function rebuildIndexIdCallBack(result) {
            $("#rebuildIndexId").text(" " + result);

        }

        function rebuildIndexAction() {
            $("#rebuildIndexId").text(" " + "Wainting for result...");

            readFromServer(rebuildIndexIdCallBack, 'ItemRebuildIndexAction')
        }

        $(document).ready(function () {
            readFromServer(statCallback, "ItemListAllAction");
        });


    </script>

</head>
<body>

<jsp:include page="common-left.jsp"/>

<h2>Overview</h2>

<div class="centerDiv" id="statCallbacId">

    <table>
        <th>Items</th>
        <tr>
            <td>Total Items =</td>
            <td>
                <div id="totalItemsId"></div>
            </td>
        </tr>

        <tr>
            <td>
                <button onclick="rebuildIndexAction();return false;"  formtarget="hiddenFrame"
                type="button" class="btn btn-info btn-lg">
                <span class="glyphicon glyphicon-th"></span>
                Rebuild Index
                </button>
            </td>
            <td>
                <div id="rebuildIndexId"></div>
            </td>
        </tr>

    </table>


</div>

</body>
</html>
