<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search flights</title>
<link rel="stylesheet" href="webjars/jquery-ui/1.9.2/css/smoothness/jquery-ui-1.9.2.custom.min.css">
<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
<script src="js/espatial.js"></script>
<script src="webjars/jquery-ui/1.9.2/js/jquery-ui-1.9.2.custom.min.js"></script>
</head>
<body>

	<h1>Search flights</h1>

	<form id="searchForm" action="#" method="post">
	
		<input type="radio" id="singleFlightType" name="flightType" value="single"> Single
		<input type="radio" id="returnFlightType" name="flightType" value="return"> Return
		<br><br>
		
		From: 
		<select name="airportFrom" id="airportFrom">
			
		</select> 
		To: 
		<select name="airportTo" id="airportTo">
			
		</select>
		
		<br><br> 
		
		Date from:
		
		<input type="text" id="datepickerFrom" name="dateFrom">
		
		Date to:
		
		<input type="text" id="datepickerTo" name="dateTo">
		
		<br><br>
		
		<input type="button" value="Search" id="buttonSearch">
	</form>

	<br>
	<br>
	<div id="response"></div>



</body>
</html>
