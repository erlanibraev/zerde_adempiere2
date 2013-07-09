/*
* Returns an new XMLHttpRequest object, or false if the browser
* doesn't support it
*/
var availableSelectList;
var countRow;

function newXMLHttpRequest() {
	
	var xmlreq = false;
	// Create XMLHttpRequest object in non-Microsoft browsers
	if (window.XMLHttpRequest) {
		xmlreq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
				// Try to create XMLHttpRequest in later versions
				// of Internet Explorer
				xmlreq = new ActiveXObject('Msxml2.XMLHTTP');
		} catch (e1) {
			// Failed to create required ActiveXObject
			try {
				// Try version supported by older versions
				// of Internet Explorer
				xmlreq = new ActiveXObject('Microsoft.XMLHTTP');
			} catch (e2) {
				// Unable to create an XMLHttpRequest by any means
				xmlreq = false;
			}
		}
	}
	return xmlreq;
};

/*
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes it XML response to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
function getReadyStateHandler(req, responseXmlHandler) {
	// Return an anonymous function that listens to the XMLHttpRequest instance
	return function () {
		// If the request's status is 'complete'
		if (req.readyState == 4) {
			// Check that we received a successful response from the server
			if (req.status == 200) {
				
				// Pass the XML payload of the response to the handler function.
				responseXmlHandler();
				$.unblockUI();
			} else {
				// An HTTP problem has occurred
				alert('HTTP error '+req.status+': '+req.statusText);
			}
		}
	};
};

function search(searchKey) {
	
	var keyValue = document.getElementById('getCities').value;
	keyValue = keyValue.replace(/^\s*|\s*$/g,'');
	if (keyValue.length > 0)
	{
	availableSelectList = document.getElementById('searchResult');
	var req = newXMLHttpRequest();
	req.onreadystatechange = getReadyStateHandler(req, update);
	req.open('POST','search.action', true);
	req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	var qString = $("#myForm").formSerialize();
	
	req.send(qString);
	}
};

function sendForm(action){
	
	var msg = '<img src="images/ajax-load.gif">';
	
	$.blockUI({ message: msg,
				css: { backgroundColor: '#DAE1EA'}
			});
	availableSelectList = document.getElementById('ResultSave');
	
	var req = newXMLHttpRequest();
	req.onreadystatechange = getReadyStateHandler(req, update);
	req.open('POST',action, true);
	req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	var qString = $("#myForm").formSerialize();
	req.send(qString);
};

function update()
{
	var idx = 1;
	for(var i = 0; i < countRow; i++){
		
		document.getElementById('quantity_IDX_'+idx).className="";
		document.getElementById('quantity_Copy_'+idx).value = document.getElementById('quantity_IDX_'+idx).value; 
		document.getElementById('amountUnit_IDX_'+idx).className="";
		document.getElementById('amountUnit_Copy_'+idx).value = document.getElementById('amountUnit_IDX_'+idx).value;
		
		idx++;
	}
};

function changeValue(comp, currentV, oldV){
	
	var currentValue = currentV.value;
	var oldValue = document.getElementById(oldV).value;
	
	if(currentValue != oldValue)
		document.getElementById(comp).className="changeValue";
	else
		document.getElementById(comp).className="";
};

