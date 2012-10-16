<!-- Modal -->
<div class="modal hide" id="buyModal" tabindex="-1" role="dialog" aria-labelledby="buyModalLabel" aria-hidden="true">
<form action="" method="post" id="buyform" style="padding: 0px; margin: 0px;">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
<h3 id="myModalLabel">Purchase Order</h3>
</div>
<div class="modal-body">
	<div class="controls controls-row">
	  <div class="span3">CUSIP: </div>
	  <div class="span2" id="buy_cusip"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Rating: </div>
	  <div class="span2" id="buy_rating"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Current Yield (%): </div>
	  <div class="span2" id="buy_currentYield"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Yield to Maturity (%): </div>
	  <div class="span2" id="buy_yieldToMaturity"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Maturity Date: </div>
	  <div class="span2" id="buy_maturityDate"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Par Value: </div>
	  <div class="span2" id="buy_parValue"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Quantity Available: </div>
	  <div class="span2" id="buy_quantityAvailable"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Price: </div>
	  <div class="span2" id="buy_price"></div>
	</div>
	<hr />
	<div class="controls controls-row">
	  <label class="span3">Quantity to Buy: </label>
	  <input class="span2" type="text" placeholder="Quantity to Buy" value="0" id="buy_quantity" name="quantity" />
	</div>
	<div class="controls controls-row" style="padding-bottom: 15px;">
	  <div class="span3">Purchase Amount: </div>
	  <div class="span2" id="buy_purchaseAmount">$0.00</div>
	</div>
</div>
<div class="modal-footer">
<input type="hidden" name="cusip" id="buy_hidden_cusip" value="" />
<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
<input class="btn btn-primary" type="submit" value="Submit Purchase Order" />
</div>
</form>
</div>