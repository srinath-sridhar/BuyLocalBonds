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
    
<%@ include file="_modal_sell.jsp" %>
   
<script>

$(document).ready(function() {
	
	refreshData(null);
	
	$('#sellModal').modal({
		backdrop : true,
		show : false
	});
	
	$('#thcusip').tooltip({ placement : "bottom", title : "Uniform Security Identification Number" });
	$('#thPDate').tooltip({ placement : "bottom", title: "Purchase Date"});
	$('#thPQuantity').tooltip({ placement : "bottom", title: "Purchase Quantity"});
	$('#thPPrice').tooltip({ placement : "bottom", title: "Purchase Price"});
	$('#thCPrice').tooltip({ placement : "bottom", title: "Current Price"});
});

// SELL BUTTON FOR SELL MODAL DIALOG
$(document).on("click", ".sellbutton", function () {
	
	$('#sell_cusip').html($(this).data('id'));
	$('#sell_portfolioQuantity').html($(this).data('quantity'));
	
	$.getJSON("BondSearch", { cusip : $(this).data('id') }, function(data) {
		
		marketData = data.bonds[0];
		
		$('#sell_hidden_cusip').val(marketData.cusip);
		$('#sell_cusip').html(marketData.cusip);
		$('#sell_rating').html(marketData.rating);
		$('#sell_currentYield').html(marketData.currentYield);
		$('#sell_yieldToMaturity').html(marketData.yieldToMaturity);
		$('#sell_maturityDate').html(marketData.maturityDate);
		$('#sell_quantityAvailable').html(marketData.quantityAvailable);
		$('#sell_parValue').html(marketData.parValue);
		$('#sell_price').html(marketData.price);
	
	}).error(function() {
		window.location = "index.jsp";
	});


});

$("#sell_quantity").keyup(function() {
	if (jQuery.isNumeric($(this).val())) {
	$("#sell_purchaseAmount").html("$" + ($(this).val() * $("#sell_price").html()).toFixed(2));
	}
});

$("#sellform").submit(function(event) {
	
	//prevent default form submittion
	event.preventDefault();
	
	if ($('#sell_quantity').val() > $('#sell_portfolioQuantity').html() ) {
		alert("Error: Cannot exceed the available quantity in portfolio.");
	}
	else if ($('#sell_quantity').val() < 1) {
		alert("Error: Cannot sell less than 1.");
	}
	else {
		$.post("SellBond", $("#sellform").serialize(), function(data) {
			refreshData($("#searchform").serialize(), $("#sellModal"));
		});
	}
});

// GETS SEARCH RESULTS AND DISPLAYS IT TO MAIN TABLE
function refreshData(modalDelegate) {

	$('#bond_market_data > tbody:last').html("");
	
	$.getJSON("Portfolio", function(marketData) {

		if (marketData.errorCode != 200) {
			alertOnTable(marketData.responseMessage);
			
		}
		else if (modalDelegate != null) {
			$(modalDelegate).modal("hide");
		}
		
		$.each(marketData.holdings, function(key, value) {
			$('#bond_market_data > tbody:last').append('<tr id="row_' + key + '"></tr>');
			$('#row_'+key).append('<td class="action_buttons"><a class="sellbutton" href="#sellModal" role="button" data-toggle="modal" data-id="'+value.cusip+'" data-quantity="' + value.purchaseQuantity + '">SELL</a></td>');
			$('#row_'+key).append('<td>'+value.cusip+'</td>');
			$('#row_'+key).append('<td>'+value.purchaseDate+'</td>');
			$('#row_'+key).append('<td>'+value.purchaseQuantity+'</td>');
			$('#row_'+key).append('<td>$'+value.purchasePrice.toFixed(4)+'</td>');
			$('#row_'+key).append('<td>$'+value.currentPrice.toFixed(4)+'</td>');
		});
		
	
	}).error(function() {
		window.location = "index.jsp";
	});

}
// SHOW ALERTS ON THE PORTFOLIO TABLE
function alertOnTable(str) {
	$('#bond_market_data > tbody:last').append('<td colspan="6"><div style="text-align: center; padding: 30px 0px;">' + str + '</div></td>');	
}

// XOR FOR JQUERY STRING OBJECT
function xorJqueryStringCompare(a, b) {
	
	return ((aString.length == 0 || bString.length == 0) &&
	!(aString.length == 0 && bString.length == 0));

}

function postCustomerUpdate() {
	refreshData(null);
}
</script>

<%@ include file="_footer.jsp" %>