<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
  <div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
      <ul class="nav nav-sidebar">

       <!-- <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li> -->

        <!--      <li><a href="#">Reports</a></li> -->
     <!-- <li><a href="<%=request.getContextPath()%>/stat" target="statisticsFrame" %>Statistics</a></li> -->
        <li><a href="overview.jsp" >Overview</a></li>

        <li><a href="items.jsp" >Items</a></li>
        <li><a href="itemTagTree.jsp" >Tags</a></li>
        <li><a href="channels.jsp" >Channels</a></li>
        <li><a href="api.jsp" >API</a></li>
        <li><a href="events.jsp" >Events</a></li>

      </ul>
    </div>

  <!--  <iframe name="statisticsFrame" width="1200" height="800"></iframe> -->
  </div>
</div>

