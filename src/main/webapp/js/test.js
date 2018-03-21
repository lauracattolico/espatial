/*var jsonString = "[{\"firstName\": \"John\"}, {\"firstName\": \"Laura\"}]";

var javascriptObject = eval('(' + jsonString + ')');
alert(javascriptObject);
javascriptObject.forEach(function(item, index) {
	alert("item: " + item["firstName"]);
	alert("index: " + index);
});*/

$(document).ready(function() {	
	// Create the variable with the HTML markup
	var flightTemplate = "<div><div>From: ${from}</div><div>To: ${to}</div><div>Price: ${price}</div></div>";

	// Store it as a named template
	$.template("flightTemplate", flightTemplate);
	
	
	
	
	$("#buttonSearch").click(function(e) {
		var searchForm = $('#searchForm');
		$("#response").html("");
		
		$.getJSON("/controller/search/airports", $("#searchForm").serialize(), function(result) {
 	       $.each(result, function(i, flight) {
 	    	   		$.tmpl("flightTemplate", flight).appendTo("#response")
 	       });
 	   });
		
		/*e.preventDefault(); // avoid to execute the actual submit of the form.
		$.post({
	           //type: "POST",
	           url: "searchcontroller",
	           data: $("#searchForm").serialize(), //serializes the form's elements.
	           success: function(data)
	           {
	               $("#response").text(data); //show response.
		        	   
	           }
	         });*/
	
	});  
});