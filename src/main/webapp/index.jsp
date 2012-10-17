<%@ include file="_header.jsp" %>
<div class="container" style="padding: 0px 0px 10px 10px;">
	<div style="clear: right;float: right; font-size: large; font-weight: bold; padding: 10px 20px 0px 0px; color: #999;">dbBLB</div>
	<img src="lib/img/db.png" width="120" height="25" />
	
</div>

    <div class="container">
    
    	<div class="navbar">
		 <div class="navbar-inner">
		  <div class="container">
		
		    </div>
		  </div>
		</div>
		

      <!-- Example row of columns -->
      <div class="row">

        <div style="margin: 0 auto; width: 500px; padding-top: 20px;">
        
			<form class="form-horizontal" id="loginForm" method="post">
			  <div class="control-group">
			    <label class="control-label" for="inputUsername">Username</label>
			    <div class="controls">
			      <input type="text" id="inputUsername" name="username" placeholder="Username">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="inputPassword">Password</label>
			    <div class="controls">
			      <input type="password" id="inputPassword" name="password" placeholder="Password">
			    </div>
			  </div>
			  <div class="control-group">
			    <div class="controls">
			      <button id="btn_signin" type="submit" class="btn">Sign in</button>
			    </div>
			  </div>
			</form>
			<div id="loginOutcome" style="color: #F00; text-align: center;"></div>
        </div>
        
      </div>

      <hr>

      <footer>
        <p>&copy; Company 2012</p>
      </footer>

    </div> <!-- /container -->

<div class="container">
<div style="position: relative; width: 400px; margin: 0 auto;">

</div>

</div>
   <script src="lib/js/jquery.js"></script>
   <script src="lib/js/bootstrap.js"></script>
   
<script>

$(document).ready(function() {
	$("#loginForm").submit(function(event) {

		
		$.post("UserAuthentication", 
				{ username: $("#inputUsername").val(), password: $("#inputPassword").val() },
				
				function(data) {
					if (data == "success") {
						window.location.href = "market.jsp";
					}	
					else
						$("#loginOutcome").html("Incorrect username/password. Try again!");
				});
		
		// prevent default form submittion
		 event.preventDefault(); 
		
	});

});


</script>
<%@ include file="_footer.jsp" %>