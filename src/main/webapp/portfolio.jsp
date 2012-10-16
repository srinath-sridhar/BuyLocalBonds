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
<th id="thPDate" class="hidden-800">P. Date</th>
<th id="thPQuantity">P. Qty</th>
<th id="thPPrice">P. Price</th>
<th id="thCPrice">C. Price</th>
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
   
<script>
$(document).ready(function() {
	
	//refreshData("");
	
	$('#buyModal').modal({
		backdrop : true,
		show : false
	});
	
	$('#thcusip').tooltip({ placement : "bottom", title : "Uniform Security Identification Number" });
	$('#thcoupon').tooltip({ placement : "bottom", title: "Coupon"});
	$('#thytm').tooltip({ placement : "bottom", title: "Yield to Maturity"});
	$('#thquantity').tooltip({ placement : "bottom", title: "Available Quantity"});
	
});

// SELL BUTTON FOR BUY MODAL DIALOG
$(document).on("click", ".sellbutton", function () {
	
	$('#buy_cusip').html($(this).data('id'));
	
	$.getJSON("BondSearch", { cusip : $(this).data('id') }, function(data) {
	
		marketData = data.bonds[0];
		
		$('#buy_hidden_cusip').val(marketData.cusip);
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

$("#sellform").submit(function(event) {
	
	//prevent default form submittion
	event.preventDefault();
	$.post("BuyBond", $("#buyform").serialize(), function(data) {
		refreshData($("#searchform").serialize(), $("#buyModal"));
	});

});


function alertHighLowValuesForSearch(typeString, firstString, secondString) {
	if (xorJqueryStringCompare(firstString, secondString))
		alert("Search requires high and low for " + typeString.toUpperCase() + ".");
}


// GETS SEARCH RESULTS AND DISPLAYS IT TO MAIN TABLE
function refreshData(delegate) {

	$('#bond_market_data > tbody:last').html("");
	
	$.getJSON("Portfolio", function(marketData) {

		if (marketData.errorCode != 200) {
			alert(marketData.responseMessage);
			
		}
		else if (delegate != null) {
			$(delegate).modal("hide");
		}
		
		$.each(marketData.holdings, function(key, value) {
			$('#bond_market_data > tbody:last').append('<tr id="row_' + key + '"></tr>');
			$('#row_'+key).append('<td class="action_buttons"><a class="sellbutton" href="#sellModal" role="button" data-toggle="modal" data-id="'+value.cusip+'">SELL</a></td>');
			$('#row_'+key).append('<td>'+value.cusip+'</td>');
			$('#row_'+key).append('<td>'+value.purchaseDate+'</td>');
			$('#row_'+key).append('<td>'+value.purchaseQuantity+'%</td>');
			$('#row_'+key).append('<td>'+value.purchasePrice+'</td>');
			$('#row_'+key).append('<td>'+value.currentPrice+'</td>');
		});
		
		
		if ($('#bond_market_data > tbody:last').html().length == 0) {
			$('#bond_market_data > tbody:last').append('<td colspan="6"><div style="text-align: center; padding: 30px 0px;">Could not find anything to match that criteria! <a href="#searchModal" data-toggle="modal">Search again</a></div></td>');
		}
	
	}).error(function() {
		window.location = "index.jsp";
	});

}
	
	
// XOR FOR JQUERY STRING OBJECT
function xorJqueryStringCompare(a, b) {
	
	return ((aString.length == 0 || bString.length == 0) &&
	!(aString.length == 0 && bString.length == 0));

}
</script>

<%@ include file="_footer.jsp" %>