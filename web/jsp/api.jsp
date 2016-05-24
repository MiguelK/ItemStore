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
            <td>User with empty result</td>
            <td><a href="<%=jsonServiceURL%>item/9999"> getItemComponentsForUser/9999 </a></td>
        </tr>

        <tr>
            <td>User not found</td>
            <td><a href="<%=jsonServiceURL%>item/9111999"> getItemComponentsForUser/9111999 </a></td>
        </tr>

        <tr>
            <td>User 1</td>
            <td><a href="<%=jsonServiceURL%>item/1"> getItemComponentsForUser/1 </a>
            </td>
        </tr>
        <tr>
            <td>User 2 (TV Old Stockholm)</td>
            <td><a href="<%=jsonServiceURL%>item/2"> getItemComponentsForUser/2 </a>
            </td>
        </tr>

        <tr>
            <td>Toplist</td>
            <td><a href="<%=jsonServiceURL%>item/toplist/1"> getTopNewsByLocation </a>
            </td>
        </tr>
    </table>

    <table border="1px">
        <tr>
            <td>List all users
            <td>
            <td><a href="<%=jsonServiceURL%>user"> getUsers </a>
            </td>
        </tr>
    </table>

    <table border="1px">
        <tr>
            <td>Get TagTree
            <td>
            <td><a href="<%=jsonServiceURL%>tag"> getTagTree() </a>
            </td>
        </tr>
    </table>


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
