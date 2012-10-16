<!-- Modal -->
<div class="modal hide" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="searchModalLabel" aria-hidden="true">
<form action="" method="post" style="padding: 0px; margin: 0px;" id="searchform" accept-charset="UTF-8">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
<h3 id="myModalLabel">Search for Bond</h3>
</div>
<div class="modal-body">

<div class="controls controls-row">
<label class="span2">Rating: </label>
<select class="span2" name="rating_low">
	<option value="">No Filter</option>
	<option>AAA</option>
	<option>AA+</option>
	<option>AA</option>
	<option>A+</option>
	<option>A</option>
	<option selected>BBB</option>
	<option>BB</option>
	<option>B</option>
	<option>CCC</option>
	<option>CC</option>
	<option>CI</option>
	<option>R</option>
	<option>SD</option>
	<option>D</option>
	<option>NR</option>
</select>
<select class="span2" name="rating_high">
	<option value="">No Filter</option>
	<option selected>AAA</option>
	<option>AA+</option>
	<option>AA</option>
	<option>A+</option>
	<option>A</option>
	<option>BBB</option>
	<option>BB</option>
	<option>B</option>
	<option>CCC</option>
	<option>CC</option>
	<option>CI</option>
	<option>R</option>
	<option>SD</option>
	<option>D</option>
	<option>NR</option>
</select></div>
<div class="controls controls-row">
<label class="span3">Coupon (%): </label>
<input class="span1" type="text" placeholder="Low" name="coupon_low" />
<input class="span1" type="text" placeholder="High" name="coupon_high" />
</div>
<div class="controls controls-row">
<label class="span3">Current Yield (%): </label>
<input class="span1" type="text" placeholder="Low" name="currentYield_low" />
<input class="span1" type="text" placeholder="High" name="currentYield_high" />
</div>
<div class="controls controls-row">
<label class="span3">Yield to Maturity (%): </label>
<input class="span1" type="text" placeholder="Low" name="yieldToMaturity_low" />
<input class="span1" type="text" placeholder="High" name="yieldToMaturity_high" />
</div>
<div class="controls controls-row">
<label class="span3">Maturity Date: </label>
<input class="span1" type="text" placeholder="Low" name="maturityDate_low" />
<input class="span1" type="text" placeholder="High" name="maturityDate_high" />
</div>
<div class="controls controls-row">
<label class="span3">Par Value: </label>
<input class="span1" type="text" placeholder="Low" name="parValue_low" />
<input class="span1" type="text" placeholder="High" name="parValue_high" />
</div>
<div class="controls controls-row">
<label class="span3">Price: </label>
<input class="span1" type="text" placeholder="Low" name="price_low" />
<input class="span1" type="text" placeholder="High" name="price_high" />
</div>
</div>
<div class="modal-footer">
<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
<input type="submit" class="btn btn-primary" value="Search" id="searchbtn" />
</div>
</form>
</div>