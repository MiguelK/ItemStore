<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>


  <jsp:include page="common-top.jsp"/>

  <script>
    function listUsersCallback(result){
    //  alert("success" + result.itemsCount);

      $("div#listUsersId").text(result.itemsCount);

    }

  </script>

</head>
<body>


<jsp:include page="common-left.jsp"/>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
  <h1 class="page-header">Dashboard ItemCollector</h1>

  <div class="row placeholders">
    <div class="col-xs-6 col-sm-3 placeholder">
      <a href="<%=request.getContextPath()%>/items.jsp" target="statisticsFrame" %>
        <button formaction="items.jsp" formtarget="statisticsFrame"
                type="button" class="btn btn-info btn-lg">
          <span class="glyphicon glyphicon-th"></span>
          Search user by tag
        </button>
      </a>
    </div>
    <div class="col-xs-6 col-sm-3 placeholder">
      <a href="#" onclick="readFromServer(listUsersCallback);return false" %>
        <button type="button" class="btn btn-info btn-lg">
          <span class="glyphicon glyphicon-th"></span>
          List users (20)
        </button>
      </a>
    </div>
  </div>

  <div id="listUsersId">



  </div>
</div>

</body>
</html>
