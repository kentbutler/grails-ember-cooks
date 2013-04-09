<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Cooking at Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Ubuntu orange and unique font.">
    <meta name="author" content="Thomas Park">

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootswatch.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">

  </head>

  <body class="preview" id="top" data-spy="scroll" data-target=".subnav" data-offset="80">

  <div id="main">  </div>



    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--  script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>   -->
    <script src="js/vendor/jquery-1.9.1.min.js"></script>
    <script src="js/jquery.smooth-scroll.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootswatch.js"></script>
    <script src="js/libs/handlebars-1.0.rc.3.js"></script>
    <script src="js/libs/ember.js"></script>
    <script src="js/libs/ember-data.js"></script>
    <script src="js/app.js"></script>
    <script src="js/admin/category.js"></script>
    <script src="js/admin/unit.js"></script>
    <script src="js/admin/group.js"></script>
    <script src="js/admin/rawIngredient.js"></script>

  </body>
</html>



<g:render template="/templates/main" /> 
<g:render template="/templates/category" /> 
<g:render template="/templates/foodGroup" /> 
<g:render template="/templates/rawIngredient" /> 
<g:render template="/templates/unit" /> 
