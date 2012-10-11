<%@ include file="_header.jsp" %>

    <div class="container">
		<div class="page-header">
		  <h1>Buy Local Bonds <small class="hidden-800">Designed in the UK</small></h1>
		</div>
      <!-- Example row of columns -->
      <div class="row">

        <div class="span5" style="padding-top: 20px;">
        
			<form class="form-horizontal" action="UserAuthentication" method="POST">
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
			      <button type="submit" class="btn">Sign in</button>
			    </div>
			  </div>
			</form>

        </div>
        
       <div class="span7 hidden-800">
          <h2>Heading</h2>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <p><a class="btn" href="#">View details &raquo;</a></p>
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

<%@ include file="_footer.jsp" %>