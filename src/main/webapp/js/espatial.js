
$(document).ready(function() {	
	
	var flightTemplate = "<div class=\"container-fluid\" id=\"${routeId}\">\
		<div class=\"row bg-warning\">\
			<div class=\"col\">From ${departure} to ${arrival}</div>\
			<div class=\"col-1\">${date}</div>\
		</div>\
		<div class=\"row bg-light\" id=\"${code}\">\
		</div>\
	</div>";
	
	var flightTime = "<div class=\"col\">\
		<div>Departure at ${hourDeparture}</div>\
	    <div>Arrival at ${hourArrival}</div>\
		<div>Tickets ${availableTicket}</div>\
		<div>Price ${price}&euro;</div>\
		<button type=\"button\" class=\"btn\" id=\"${bookId}\">BOOK</button>\
	</div>";

	// Store it as a named template
	$.template("flightTemplate", flightTemplate);
	$.template("flightTime", flightTime);
	
	
	//set default radio button
	$("#returnFlightType").prop("checked", true);
	
	//if single option selected, disable select To and Date To
	$("#singleFlightType").on("change",function(){
		$("#datepickerTo").prop('disabled', 'disabled');
    });
	
	//if return option selected, remove disabled property
	$("#returnFlightType").on("change",function(){
		$("#datepickerTo").removeAttr("disabled");
    });
	
	
	//add default option to the select
	$("#airportFrom").append($('<option></option>').attr('value', '0').text('Choose..'));
	$("#airportTo").append($('<option></option>').attr('value', '0').text('Choose..'));
	
	//read all active airports
	$.getJSON("controller/retrieve/activeAirports", function(result) {
       $.each(result, function(key, entry) {
    	   		$("#airportFrom").append($('<option></option>').attr('value', entry.id).text(entry.name));
       });
	});
	
	//after departure selection
	$("#airportFrom").change(function(){
		var idSelected = this.value;
		$('#airportTo').empty();
		$("#airportTo").append($('<option></option>').attr('value', '0').text('Choose..'));
        $.getJSON("controller/retrieve/activeAirports", function(result) {
            $.each(result, function(key, entry) {
         	   	if(entry.id != idSelected){
         	   		$("#airportTo").append($('<option></option>').attr('value', entry.id).text(entry.name));
         	   	}
            });
     			
     	});
    });
	
	//retrive available date and initialize datepicker with the specific range
	$.getJSON("controller/retrieve/availableDates", function(availableDates) {
		var activeDates = function(date) {
			var day = date.getDate();
			day = day < 10 ? "0" + day : day;
			var month = date.getMonth() + 1;
			month = month < 10 ? "0" + month : month;
			dmy = day + "-" + month + "-" + date.getFullYear();
			if ($.inArray(dmy, availableDates) == -1) {
				return [false,"","Unavailable"];				
			} else {
				return [true, ""]
			}
		};
		
		$("#datepickerFrom").datepicker({
			dateFormat: "dd-mm-yy", 
			beforeShowDay: activeDates
		});
		
		$("#datepickerTo").datepicker({
			dateFormat: "dd-mm-yy", 
			beforeShowDay: activeDates
		});
	});
	
	
	//field validations and search flights
	$("#buttonSearch").click(function() {
		//field validations
		var flightType = $('input[name=flightType]:checked').val();
		var airportFrom = $('#airportFrom').val();
		var airportTo = $('#airportTo').val();		
		var datepickerFrom = $('#datepickerFrom').val();
		var datepickerTo = $('#datepickerTo').val();
		
		if (airportFrom == "0" || airportTo == "0") {
			alert("Please select departure and arrival");
			return;
		}
		
		if (flightType == "return") {
			if (datepickerFrom == "" || datepickerTo == "") {
				alert("Please select date departure and date arrival");
				return;
			}
			
			var parts = datepickerFrom.split('-');
			var dateFrom = new Date(parts[2], parseInt(parts[1])-1 , parts[0]);
			var parts = datepickerTo.split('-');
			var dateTo = new Date(parts[2], parseInt(parts[1])-1, parts[0]);		
			
			if (dateTo.getTime() < dateFrom.getTime()) {
				alert("Date arrival is before date departure");
				return;
			}
		} else {
			if (datepickerFrom == "") {
				alert("Please select date departure");
				return;
			}
		}			
		
		//search flights
		$("#response").html("");
        $.getJSON("controller/search/flights", $("#searchForm").serialize(), function(result) {
            $.each(result, function(key, flight) {     
            	   flight["routeId"] = "route-" + flight["idRoute"] ;
            	   $.tmpl("flightTemplate", flight).appendTo("#response");
            	   
            	   $.each(flight["flightTimeList"], function (key, flightTime) {
            		   flightTime["bookId"] = "book-" + flight["id"];
            		   $.tmpl("flightTime", flightTime).appendTo("#" + flight["code"]);
            		   if(flightTime["availableTicket"] == 0){
            			   $("#" + flightTime["bookId"]).attr("disabled","disabled");
            			   $("#" + flightTime["bookId"]).addClass("btn-disabled");
                	   }
            		   
            		   //book flight
            		   $("#" + flightTime["bookId"]).click(function() {
            			   var idFlight = this.id;
            			   idFlight = idFlight.substring(5);
            			   $.post("controller/book/flight",
    				        {
    				   			idFlight: idFlight
    				        },
    				        function(data, status){
    				            if(data == 0){
    				            		alert("Booking failed!");
    				            }
    				            else{
    				            		alert("Booking completed!");
    				            		 $('div[id=' + flight["routeId"] + ']').remove();
    				            }
    				        });
            				
            		   });
            	   })
            });
     			
     	});
    });
	
	
});