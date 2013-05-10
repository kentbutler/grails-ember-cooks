

<script type="text/html" id="index-template">

<div class="user-section">
  
  <div class="row">

    <div class="span6">
        <div class="well">
            <img src="images/food/IMG_1701.jpg">
        </div>
        <div class="span4">
            <h3>Traditional Hungarian Lecho</h3>
            <p>Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
         </div>

    </div> <!-- /panel -->
    
    <div data-bind="template:'_rightUserMenu'"></div>

  </div> <!-- /row -->
</div>
</script>

<script type="text/html" id="_rightUserMenu">

    <div class="span4">
      <div class="well" style="padding: 8px 0;">
        <ul class="nav nav-list">
          <!--
          <li class="active"><a href="#home">Home</a></li>
          -->
          <li class="nav-header">Get Things Done</li>
          <li><a href="#">Search for Recipes</a></li>
          <li>{{#linkTo recipes.new}}Add New Recipe{{/linkTo}}</li>
          <li class="nav-header">Most Popular</li>
          <li><a href="#">Breakfast</a></li>
          <li><a href="#">Lunch</a></li>
          <li><a href="#">Dinner</a></li>
        </ul>
      </div>
    </div>
</script>
    
<script type="text/html" id="header-template">
     <div class="navbar navbar-fixed-top">
   <div class="navbar-inner">
     <div class="container">
       <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
       </a>
       <a class="brand" href="../">Cooking at Home</a>
       <div class="nav-collapse collapse" id="main-menu">
        <ul class="nav" id="main-menu-left">
          <li><a href="#home">Home</a></li>
          <li><a id="swatch-link" href="../#gallery">Gallery</a></li>
          <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin <b class="caret"></b></a>
            <ul class="dropdown-menu" id="admin-menu">
              <li><a href="#home">Home</a></li>
              <li class="divider"></li>
              <li><a href="#categories">Categories</a></li>
              <li><a href="#foodGroups">FoodGroups</a></li>
              <li><a href="#rawIngredients">Raw Ingredients</a></li>
              <li><a href="#units">Units</a></li>
            </ul>
          </li>
        </ul>
        <ul class="nav pull-right" id="main-menu-right">
          <li><a rel="tooltip" target="_blank" href="http://builtwithbootstrap.com/" title="Showcase of Bootstrap sites &amp; apps" onclick="_gaq.push(['_trackEvent', 'click', 'outbound', 'builtwithbootstrap']);">Built With Bootstrap <i class="icon-share-alt"></i></a></li>
          <li><a rel="tooltip" target="_blank" href="https://wrapbootstrap.com/?ref=bsw" title="Marketplace for premium Bootstrap templates" onclick="_gaq.push(['_trackEvent', 'click', 'outbound', 'wrapbootstrap']);">WrapBootstrap <i class="icon-share-alt"></i></a></li>
          <li> <a href="../userProfile">Welcome, <span data-bind="text: username"/></a>  </li>
        </ul>
       </div>
     </div>
   </div>
 </div>
</script>