<%@ include file="_header.jsp" %>
<div class="container">
<%@include file="_menu.jsp" %>

<div class="alert alert-info" id="informationAlert"></div>

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
   
<script>
$(document).ready(function() {
	
	refreshData();
	
	window.setInterval(refreshData, 2000);
	
	$('#buyModal').modal({
		backdrop : true,
		show : false
	});
	
	$('#searchModal').modal({
		backdrop : true,
		show : false
	});

	
	$('#thcusip').tooltip({ placement : "bottom", title : "Uniform Security Identification Number" });
	$('#thcoupon').tooltip({ placement : "bottom", title: "Coupon"});
	$('#thytm').tooltip({ placement : "bottom", title: "Yield to Maturity"});
	$('#thquantity').tooltip({ placement : "bottom", title: "Available Quantity"});
	
});

// BUY BUTTON FOR BUY MODAL DIALOG
$(document).on("click", ".buybutton", function () {
	
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

$("#buyform").submit(function(event) {
	
	//prevent default form submittion
	event.preventDefault();
	

	$.post("BuyBond", $("#buyform").serialize(), function(data) {
		refreshData($("#searchform").serialize(), $("#buyModal"));
	});

});

$("#searchform").submit(function(event) {
	
	//prevent default form submittion
	event.preventDefault();
	
	// alertHighLowValuesForSearch("Rating", "100", null);
	/* alertHighLowValuesForSearch("Coupon", "#coupon_high", "#coupon_low");
	alertHighLowValuesForSearch("Current Yield", "#currentYield_high", "#currentYield_low");
	alertHighLowValuesForSearch("YieldToMaturity", "#yieldToMaturity_high", "#yieldToMaturity_low");
	alertHighLowValuesForSearch("Maturity Date", "#maturityData_high", "#maturityData_low");
	alertHighLowValuesForSearch("Par Value", "#parValue_high", "#parValue_low");
	alertHighLowValuesForSearch("Price", "#price_high", "#price_low");
	*/

	refreshData($("#searchform").serialize(), $("#searchModal"));

});

function alertHighLowValuesForSearch(typeString, firstString, secondString) {
	if (xorJqueryStringCompare(firstString, secondString))
		alert("Search requires high and low for " + typeString.toUpperCase() + ".");
}


// GETS SEARCH RESULTS AND DISPLAYS IT TO MAIN TABLE
function refreshData(postinfo, modalDelegate) {
	
	
	// STORE SEARCH FILTER IN COOKIE OR GET A PREVIOUS SEARCH COOKIE
	if (postinfo == null)
		postinfo = $.cookie("marketPostInfo");
	else
		$.cookie("marketPostInfo", postinfo);
	
	// FILTER INFORMATION IN BLUE INFO BAR
	if ($.cookie('marketPostInfo').length > 0)
		$('#informationAlert').html('<strong>Search Filter(s): </strong> ' + $.cookie('marketPostInfo').replace( /\&/g, ' &nbsp;' ));
	else
		$('#informationAlert').hide();
	
	// REQUEST JSON DATA FROM BONDSEARCH SERVLET
	$.post("BondSearch", postinfo, function(marketData) {
		
		if (marketData.errorCode != 200) {
			
			if (modalDelegate == null)
				alertOnTable(marketData.responseMessage);
			else
				alert(marketData.responseMessage);
		}
		else if (modalDelegate != null) {
			$(modalDelegate).modal("hide");
		}
		
		$('#bond_market_data > tbody:last').html("");
		
		// GO THROUGH EACH ELEMENT IN CONTAINER AND CREATE NEW ROW IN MARKET TABLE
		$.each(marketData.bonds, function(key, value) {
			$('#bond_market_data > tbody:last').append('<tr id="row_' + key + '"></tr>');
			$('#row_'+key).append('<td class="action_buttons"><a class="buybutton" href="#buyModal" role="button" data-toggle="modal" data-id="'+value.cusip+'">BUY</a></td>');
			$('#row_'+key).append('<td>'+value.cusip+'</td>');
			$('#row_'+key).append('<td class="hidden-800">'+value.rating+'</td>');
			$('#row_'+key).append('<td>'+value.coupon.toFixed(2)+'%</td>');
			$('#row_'+key).append('<td>'+value.currentYield+'%</td>');
			$('#row_'+key).append('<td class="hidden-800">'+value.yieldToMaturity+'%</td>');
			$('#row_'+key).append('<td>'+value.maturityDate.substring(0,12)+'</td>');
			$('#row_'+key).append('<td style="text-align: right;">$'+value.price.toFixed(4)+'</td>');
			$('#row_'+key).append('<td style="text-align: right;">'+value.quantityAvailable+'</td>');
		});
	
	// ON FAILURE TO AUTHENTICATE TAKE USER TO LOGIN SCREEN
	}).error(function() {
		window.location = "index.jsp";
	});

}
	
//SHOW ALERTS ON THE MARKET TABLE
function alertOnTable(str) {
	$('#bond_market_data > tbody:last').append('<td colspan="9"><div style="text-align: center; padding: 30px 0px;">' + str + '</div></td>');	
}

// XOR FOR JQUERY STRING OBJECT
function xorJqueryStringCompare(a, b) {
	
	return ((aString.length == 0 || bString.length == 0) &&
	!(aString.length == 0 && bString.length == 0));

}

// REFRESH DATA AFTER CHOOSING A NEW CUSTOMER FROM CUSTOMER LIST IN MENU.JSP
function postCustomerUpdate() {
	refreshData(null);
}
</script>

<%@ include file="_footer.jsp" %>