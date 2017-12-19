<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>API</title>
    <jsp:include page="common-top.jsp"/>

    <%
        String jsonServiceURL = request.getContextPath() + "/api/";
    %>

</head>
<body>

<jsp:include page="common-left.jsp"/>

<div class="centerDiv">

    <table>
        <tr>
            <td>Tags</td>
            <td><a href="<%=jsonServiceURL%>tags"> getAllTags() ALL </a>
            </td>
        </tr>
    </table>
    <br>


    <table>
        <tr>
            <td>ItemGroup</td>
            <td><a href="<%=jsonServiceURL%>itemGroups"> getItemGroups() ALL </a>
            </td>
        </tr>
    </table>
    <br>


    <table>
        <tr>
            <td>Download JSON ZIP</td>
            <td><a href="<%=jsonServiceURL%>ItemGroupsZipServlet/download/zip"> ItemGroups JSON (ZIP) </a>
            </td>
        </tr>
    </table>
    <br>


    <table border="1px">
        <tr>
            <td>Get TagTree
            <td>
            <td><a href="<%=jsonServiceURL%>tag"> getTagTree() </a>
            </td>
        </tr>
    </table>

    <br>

    <form id="formId" action="<%=jsonServiceURL%>user" method="post" target="_self">
        .
        <table>
            <tr>
                <td>UserId</td>
                <td><input type="text" placeholder="userId" name="userId"></td>
            </tr>

            <tr>
                <td>FirstName</td>
                <td><input type="text" placeholder="firstName" name="firstName"></td>
            </tr>


            <tr>
                <td>Received Items</td>
                <td><input type="text" placeholder="Received Items" name="receivedItems"></td>
            </tr>

            <tr>
                <td>Favorite Tags</td>
                <td><input type="text" placeholder="Favorite Tags" name="favoriteTags"></td>
            </tr>
            <tr>
                <td>Exclude Tags</td>
                <td><input type="text" placeholder="Exclude Tags" name="excludeTags"></td>
            </tr>
            <tr>
                <td>
                <td><input type="submit" value="Update"></td>
            </tr>
        </table>
    </form>
</div>
</body>


</html>
