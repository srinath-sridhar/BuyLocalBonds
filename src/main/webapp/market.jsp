<%@ include file="_header.jsp" %>
<div class="container">
 <div class="navbar">
  <div class="navbar-inner">
   <div class="container">
 
      <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
      <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
 
      <!-- Be sure to leave the brand out there if you want it shown -->
      <a class="brand" href="#">Buy Local Bonds</a>
 
      <!-- Everything you want hidden at 940px or less, place within here -->
      <div class="nav-collapse">
         <ul class="nav">
           <li><a href="#">Home</a></li>
           <li class="active"><a href="#">Buy</a></li>
           <li><a href="#">Sell</a></li>
           <li><a href="#">Holding</a></li>
         </ul>
         <form class="navbar-search pull-right" action="">
           <input type="text" class="search-query span2" placeholder="Search">
         </form>
         <ul class="nav pull-right">
           <li class="dropdown">
             <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
             <ul class="dropdown-menu">
               <li><a href="#">Action</a></li>
               <li><a href="#">Another action</a></li>
               <li><a href="#">Something else here</a></li>
               <li class="divider"></li>
               <li><a href="#">Separated link</a></li>
             </ul>
           </li>
           <li class="divider-vertical"></li>
         </ul>
    	</div>
 
    </div>
  </div>
</div>


<div class="row">
  <div class="span10">
	  <table class="table table-striped" id="bond_market_data">
		<thead>
		<tr>
			<th id="thaction">ACTION</th>
			<th id="thcusip">CUSIP</th>
			<th id="thrating" class="hidden-800">RATING</th>
			<th id="thcoupon">COUP</th>
			<th id="thyield">YIELD</th>
			<th id="thytm" class="hidden-800">YTM</th>
			<th id="thmaturity">MATURITY</th>
			<th id="thprice" style="text-align: right;">PRICE</th>
			<th id="thquantity" style="text-align: right;">QTY</th>
		</tr>
		</thead>
		<tbody>
		</tbody>
		</table>
	</div>
  	<div class="span2 hidden-800">
  	<h4>Having trouble?</h4>
  	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas lorem quam, 
  	faucibus quis tincidunt ac, vestibulum eu nisi. Maecenas vel velit lacus. 
  	Proin venenatis, dui elementum sollicitudin elementum, ipsum nunc dignissim diam, 
  	quis molestie eros sem at neque. </p>
  	<p><a href="#">Contact Support</a></p>
  	</div>
</div>
    </div> <!-- /container -->
    
<%@ include file="_modal_buy.jsp" %>
    
   <!-- Le javascript
   ================================================== -->
   <!-- Placed at the end of the document so the pages load faster -->
   <script src="lib/js/jquery.js"></script>
   <script src="lib/js/bootstrap.js"></script>
   
<script>
$(function() {
	
	$.getJSON("/BuyLocalBonds/Market", function(marketData) {
		$.each(marketData, function(key) {
			$('#bond_market_data > tbody:last').append('<tr id="row_' + key + '"></tr>');
			$('#row_'+key).append('<td><a class="btn btn-mini"  href="#buyModal" role="button" data-toggle="modal">BUY</a></td>');
			
			$('#row_'+key).append('<td>'+marketData[key].cusip+'</td>');
			$('#row_'+key).append('<td class="hidden-800">'+marketData[key].rating+'</td>');
			$('#row_'+key).append('<td>'+marketData[key].coupon.toFixed(2)+'%</td>');
			$('#row_'+key).append('<td>###%</td>');
			$('#row_'+key).append('<td class="hidden-800">###%</td>');
			$('#row_'+key).append('<td>'+marketData[key].maturityDate.substring(0,12)+'</td>');
			$('#row_'+key).append('<td style="text-align: right;">$'+marketData[key].price.toFixed(2)+'</td>');
			$('#row_'+key).append('<td style="text-align: right;">'+marketData[key].quantityAvailable+'</td>');
			
		});
	});

	$('#buyModal').modal({
		backdrop : true,
		show : false
	});
	
	$('#thcusip').tooltip({ placement : "bottom",  title : "Uniform Security Identification Number" });
	$('#thcoupon').tooltip({ placement : "bottom", title: "Coupon"});
	$('#thytm').tooltip({ placement : "bottom", title: "Yield to Maturity"});
	$('#thquantity').tooltip({ placement : "bottom", title: "Available Quantity"});
});

</script>

<%@ include file="_footer.jsp" %>