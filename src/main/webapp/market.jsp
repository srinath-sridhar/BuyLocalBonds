<%@ include file="_header.jsp" %>
<div class="container">
<%@include file="_menu.jsp" %>

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

<%@ include file="_modal_search.jsp" %>
    
   <!-- Le javascript
   ================================================== -->
   <!-- Placed at the end of the document so the pages load faster -->
   <script src="lib/js/jquery.js"></script>
   <script src="lib/js/bootstrap.js"></script>
   
<script>
$(document).ready(function() {
	
	refreshData("");
	
	$('#buyModal').modal({
		backdrop : true,
		show : false
	});
	
	$('#searchModal').modal({
		backdrop : true,
		show : false
	});
	
	
	$('#thcusip').tooltip({ placement : "bottom",  title : "Uniform Security Identification Number" });
	$('#thcoupon').tooltip({ placement : "bottom", title: "Coupon"});
	$('#thytm').tooltip({ placement : "bottom", title: "Yield to Maturity"});
	$('#thquantity').tooltip({ placement : "bottom", title: "Available Quantity"});
});


$(document).on("click", ".buybutton", function () {

	$('#buy_cusip').html($(this).data('id'));
	
	$.getJSON("Market?cusip="+$(this).data('id'), function(data) {
		
		marketData = data.bonds[0];
		
		$('#buy_cusip').html(marketData.cusip);
		$('#buy_rating').html(marketData.rating);
		$('#buy_currentYield').html(marketData.currentYield);
		$('#buy_yieldToMaturity').html(marketData.yieldToMaturity);
		$('#buy_maturityDate').html(marketData.maturityDate);
		$('#buy_quantityAvailable').html(marketData.quantityAvailable);
		$('#buy_parValue').html(marketData.parValue);
		$('#buy_price').html(marketData.price);
		
	}).error(function() {
		window.location = "index.jsp";
	});
	

});

$("#buy_quantity").keyup(function() {
	if (jQuery.isNumeric($(this).val())) {
		$("#buy_purchaseAmount").html("$" + ($(this).val() * $("#buy_price").html()).toFixed(2));
	}
});

$("#searchform").submit(function(event) {
	refreshData($("#searchform").serialize());
	$("#btnCloseSearch").click();
	
	// prevent default form submittion
	 event.preventDefault();
	
});


function refreshData(postinfo) {
	
	$('#bond_market_data > tbody:last').html("");
	
	$.post("Market", postinfo, function(marketData) {
		
		if (marketData.errorCode != 200) {
			alert(marketData.responseMessage);
			return false;
		}
		
		$.each(marketData.bonds, function(key, value) {
			$('#bond_market_data > tbody:last').append('<tr id="row_' + key + '"></tr>');
			$('#row_'+key).append('<td class="action_buttons"><a class="buybutton" href="#buyModal" role="button" data-toggle="modal" data-id="'+value.cusip+'">BUY</a></td>');
			$('#row_'+key).append('<td>'+value.cusip+'</td>');
			$('#row_'+key).append('<td class="hidden-800">'+value.rating+'</td>');
			$('#row_'+key).append('<td>'+value.coupon.toFixed(2)+'%</td>');
			$('#row_'+key).append('<td>###%</td>');
			$('#row_'+key).append('<td class="hidden-800">###%</td>');
			$('#row_'+key).append('<td>'+value.maturityDate.substring(0,12)+'</td>');
			$('#row_'+key).append('<td style="text-align: right;">$'+value.price.toFixed(4)+'</td>');
			$('#row_'+key).append('<td style="text-align: right;">'+value.quantityAvailable+'</td>');
			
		});
	}).error(function() {
		window.location = "index.jsp";
	});
	
}

</script>

<%@ include file="_footer.jsp" %>