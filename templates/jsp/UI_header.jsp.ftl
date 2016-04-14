<div class="header navbar navbar-fixed-top"> 
  <!-- BEGIN TOP NAVIGATION BAR -->
  <div class="header-inner"> 
    <!-- BEGIN LOGO --> 
    <a class="navbar-brand" href="index.html" > <img src="${r"${"}pageContext.request.contextPath}/js/assets/img/logo.png"  alt="logo" class="img-responsive"/> </a> 
    <!-- END LOGO --> 
    <!-- BEGIN RESPONSIVE MENU TOGGLER --> 
    <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="${r"${"}pageContext.request.contextPath}/js/assets/img/menu-toggler.png"  alt=""/> </a> 
    <!-- END RESPONSIVE MENU TOGGLER --> 
    <!-- BEGIN TOP NAVIGATION MENU -->
    <ul class="nav navbar-nav pull-right">
      <!-- BEGIN USER LOGIN DROPDOWN -->
      <li class="dropdown user"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <span class="username"> Bob Nilson </span> <i class="fa fa-angle-down"></i> </a>
        <ul class="dropdown-menu">
          <li> <a href="${r"${"}request.contextPath}/loginController/logout.action"> <i class="fa fa-key"></i>注销</a> </li>
        </ul>
      </li>
      <!-- END USER LOGIN DROPDOWN -->
    </ul>
    <!-- END TOP NAVIGATION MENU --> 
  </div>
  <!-- END TOP NAVIGATION BAR --> 
</div>