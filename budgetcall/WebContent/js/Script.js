
function getChar(event) {
	
	if (event.which == null) {
		if (event.keyCode < 32) return null;
	    return String.fromCharCode(event.keyCode); // IE
	}

	if (event.which!=0 && event.charCode!=0) {
		if (event.which < 32) return null;
		return String.fromCharCode(event.which);   // остальные
	}

	return null; // специальная клавиша
}

function isQuantity(event) {
	  
	e = event;
	if (e.ctrlKey || e.altKey || e.metaKey) return; 
	var chr = getChar(e);
	if (chr == null) return;
	if (chr < '0' || chr > '9') {
		return false;
	}else return true;
	  
}

function isAmountUnit(event) {
	  
	  e = event;
	  if (e.ctrlKey || e.altKey || e.metaKey) return; 
	  var chr = getChar(e);
	  if (chr == null) return;
	  if(chr != '.'){
		  if (chr < '0' || chr > '9') {
			return false;
		  }else return true;
	  }else return true;
	  
}


function do_math(f,edit,edit1,edit2)
{

    var t1 = f.elements[edit1].value;
    var t2 = f.elements[edit2].value;
    
    var res = parseInt(t1)*parseFloat(t2);
    f.elements[edit].value = isNaN(res) ? '' : Math.round(res * 100) / 100;
}

function summa(f, quantityField, amountField, count){
	
	var q = 0;	var s = 0;
	var qS = 0; var sS = 0; 
	var idx = 1;
	for(var i = 0; i < count; i++){
		
			q = isNaN(parseInt(f.elements['quantity_IDX_'+idx].value)) ? 0: parseInt(f.elements['quantity_IDX_'+idx].value);
			qS = qS + q;
			s = isNaN(parseFloat(f.elements['amount_IDX_'+idx].value)) ? 0: parseFloat(f.elements['amount_IDX_'+idx].value);
			sS = sS + s;
		idx++;
	}
	f.elements[quantityField].value = isNaN(qS) ? 0 : qS;
	f.elements[amountField].value = isNaN(sS) ? 0 : sS;
}
