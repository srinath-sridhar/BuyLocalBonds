<!-- Modal -->
<div class="modal hide" id="sellModal" tabindex="-1" role="dialog" aria-labelledby="sellModalLabel" aria-hidden="true">
<form action="" method="post" id="sellform" style="padding: 0px; margin: 0px;">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
<h3 id="myModalLabel">Sell Order</h3>
</div>
<div class="modal-body">
	<div class="controls controls-row">
	  <div class="span3">CUSIP: </div>
	  <div class="span2" id="sell_cusip"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Rating: </div>
	  <div class="span2" id="sell_rating"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Current Yield (%): </div>
	  <div class="span2" id="sell_currentYield"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Yield to Maturity (%): </div>
	  <div class="span2" id="sell_yieldToMaturity"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Maturity Date: </div>
	  <div class="span2" id="sell_maturityDate"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Par Value: </div>
	  <div class="span2" id="sell_parValue"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Quantity Available: </div>
	  <div class="span2" id="sell_quantityAvailable"></div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Price: </div>
	  <div class="span2" id="sell_price"></div>
	</div>
	<hr />
	<div class="controls controls-row" style="padding-bottom: 15px;">
	  <div class="span3">Quantity Available: </div>
	  <div class="span2" id="sell_portfolioQuantity"></div>
	</div>	
	<div class="controls controls-row">
	  <label class="span3">Quantity to Sell: </label>
	  <input class="span2" type="text" placeholder="Quantity to Sell" value="0" id="sell_quantity" name="quantity" />
	</div>
	<div class="controls controls-row" style="padding-bottom: 15px;">
	  <div class="span3">Purchase Amount: </div>
	  <div class="span2" id="sell_purchaseAmount">$0.00</div>
	</div>
</div>
<div class="modal-footer">
<input type="hidden" name="cusip" id="sell_hidden_cusip" value="" />
<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
<input class="btn btn-primary" type="submit" value="Submit Sell Order" />
</div>
</form>
</div>