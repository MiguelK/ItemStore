<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta itemTagTree *must* come first in the head; any other head content must come *after* these itemTagTree -->
<meta name="description" content="">
<meta name="author" content="">

<!--<link rel="icon" href="../../../../Downloads/bootstrap-3.3.5/docs/favicon.ico"> -->
<title>Dashboard TemplateA for Bootstrap</title>
<!-- Bootstrap core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/dashboard.css" rel="stylesheet">
<link href="../css/my-css.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<title></title>


<script>
    function readFromServer(callBack, action) {
        var jqxhr = $.ajax("<%=request.getContextPath()%>/stat?action=" + action)
                .done(function (result) {
                    //   alert("success" + result.itemsCount);
                    callBack(result);
                })
                .fail(function (result) {
                    alert("error" + result.itemsCount);
                })
                .always(function () {
                    //   alert("complete");
                });
    }

    function postToServer(idForm, callBack, action) {

        var url = "<%=request.getContextPath()%>/stat?action=" + action;

        $.ajax({
            type: "POST",
            url: url,
            data: $("#" + idForm).serialize(), // serializes the form's elements.
            success: function (result) {
                callBack(result);
            },
            failure: function (x) {
                alert(x);
            }
        });


    }

</script>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Version 0.12 w48 ItemCollector  <%=request.getContextPath()%> </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Settings</a></li>
                <li><a href="#">Profile</a></li>
                <li><a href="#">Help</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<iframe style="visibility:hidden;display:none" name="hiddenFrame"></iframe>

