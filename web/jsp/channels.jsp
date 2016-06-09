<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Tags</title>

  <jsp:include page="common-top.jsp"/>

  <script>
    function listChannel(result){

      var table = "Channels = " + result.length + '<table>';
      $.each(result, function(k, v) {

        var row = "<tr><td>" +  result[k].name + "</td></tr>";
        table += row;
      });

      table += "</table>";

      $("#listChannelId").html(table);
    }

    $( document ).ready(function() {
      readFromServer(listChannel,"ChannelListAllAction");
    });

    function reloadChannels(){
      readFromServer(listChannel,"ChannelReloadAction");
    }

  </script>

</head>
<body>

<jsp:include page="common-left.jsp"/>

  <div class="centerDiv" style="position: relative;top: 50px">

  <a href="<%=request.getContextPath()%>/jsp/items.jsp" target="statisticsFrame" %>
    <button onclick="reloadChannels();return false;" formtarget="hiddenFrame"
            type="button" class="btn btn-info btn-lg">
      <span class="glyphicon glyphicon-th"></span>
      Reload Channels
    </button>
  </a>
</div>

<div class="centerDiv" id="listChannelId">Lista edit all Channels + reload button</div>

</body>
</html>
