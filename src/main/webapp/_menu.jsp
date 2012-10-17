<% String reqURI = request.getRequestURI(); %>
<div class="container" style="padding: 0px 0px 10px 10px;">
<div style="float: right; font-size: large; font-weight: bold; padding: 10px 20px 0px 0px; color: #999;">dbBLB</div>
<img src="lib/img/db.png" width="120" height="25" />
</div>
 <div class="navbar">
  <div class="navbar-inner">
   <div class="container">

      <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
      <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <!-- Everything you want hidden at 940px or less, place within here -->
      <div class="nav-collapse">
         <ul class="nav">
           <li <% if (reqURI.contains("home.jsp")) { %>class="active"<% } %>><a href="home.jsp">Home</a></li>
           <li <% if (reqURI.contains("market.jsp")) { %>class="active"<% } %>><a href="market.jsp">Buy</a></li>
           <li <% if (reqURI.contains("portfolio.jsp")) { %>class="active"<% } %>><a href="portfolio.jsp">Portfolio</a></li>
         </ul>
         
         <ul class="nav pull-right">
           <li class="dropdown">
             <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span id="dd-currentCustomer">Current Customer</span> <b class="caret"></b></a>
             <ul class="dropdown-menu" id="dd-customerList">
             </ul>
           </li>
           <% if (reqURI.contains("market.jsp")) { %>
           <li class="divider-vertical"></li>
           <li><a href="#searchModal" data-toggle="modal">Search</a></li>
           <% } %>
         </ul>
    	</div>
 
    </div>
  </div>
</div>

<script>
// PROTOTYPE
postCustomerUpdate = function() {};

$(document).ready(function() {
	
	refreshCustomerList();
	
});

//UPDATES LIST OF CUSTOMERS FOR TRADERS
function refreshCustomerList() {
	
	$.getJSON("CustomerServlet", function(data) {
		
		if (data.errorCode != 200) {
			alert(data.responseMessage);
			return false;
		}
		
		$("#dd-currentCustomer").html(data.activeCustomer.customerName);
		
		$("#dd-customerList").html("");
		
		$.each(data.customers, function(key, value) {
			$("#dd-customerList").append("<li><a href=\"#\" class=\"customerFromCustomerList\" customer-id=\"" + value.customerId + "\">" + value.customerName + "</a></li>");
		});
		
	}).error(function() {
		window.location = "index.jsp";
	});
	
}



$(".customerFromCustomerList").live('click',function() {
	
	customer = this;
	
	$.post("CustomerServlet", { newCurrentCustomer : $(this).attr("customer-id") }, function(data) {
		$("#dd-currentCustomer").html($(customer).html());
		
	}).error(function() {
		window.location = "index.jsp";
	});
	
	postCustomerUpdate();
});



</script>
