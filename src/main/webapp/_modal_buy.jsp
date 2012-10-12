<!-- Modal -->
<div class="modal hide" id="buyModal" tabindex="-1" role="dialog" aria-labelledby="buyModalLabel" aria-hidden="true">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
<h3 id="myModalLabel">Purchase Order</h3>
</div>
<div class="modal-body">
	<div class="controls controls-row">
	  <div class="span3">CUSIP: </div>
	  <div class="span2" id="buy_cusip">1000</div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Rating: </div>
	  <div class="span2" id="buy_rating">1000</div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Current Yield (%): </div>
	  <div class="span2" id="buy_currentYield">1000</div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Yield to Maturity (%): </div>
	  <div class="span2" id="buy_yieldToMaturity">1000</div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Maturity Date: </div>
	  <div class="span2" id="buy_maturityDate">1000</div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Quantity Available: </div>
	  <div class="span2" id="buy_quantityAvailable">1000</div>
	</div>
	<div class="controls controls-row">
	  <div class="span3">Par Value: </div>
	  <div class="span2" id="buy_parValue">1000</div>
	</div>
	<hr />
	<div class="controls controls-row">
	  <label class="span3">Quantity to Buy: </label>
	  <input class="span2" type="text" placeholder="Quantity to Buy" id="buy_quantity" name="buy_quantity" />
	</div>
	<div class="controls controls-row" style="padding-bottom: 15px;">
	  <div class="span3">Purchase Amount: </div>
	  <div class="span2" id="buy_purchaseAmount"></div>
	</div>
</div>
<div class="modal-footer">
<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
<button class="btn btn-primary">Submit Purchase Order</button>
</div>
</div>