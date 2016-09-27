$(document).ready(function() {
	
	$('#selectedNumber').change(function() {
		$('#fields').empty();
		$('#fields').append('<label class="form-control all">Enter x, y in this form: x;y </label>');
		for (var i = 0; i < $('#selectedNumber').val(); i++) {
			$('#fields').append('<input type="text" class="form-control all" name="' + i + 'element"/>');
		}
	});
	
	function sendForm() {
		var formData = $('form[name="calculatingForm"]').serializeArray();
		
		var dataSendObj = {};
		var dataStr = "";
		formData.forEach(function(item) {
			dataSendObj[item.name] = item.value;
			console.log( item.value);
			dataStr += item.value + ", ";
		});
		console.log(dataStr);
		var sendingObj = {};
		sendingObj["obj"] = dataStr;
		return $.ajax({
			url : 'http://localhost:8080/calculatingmethod/rest/calculator/calculate',
			method : 'POST',
			data : sendingObj,
			dataType : 'json'
		}).error(function(result) {
			console.log('error');
		}).success(function(result) {
			console.log(result);
			
			var aArray = result.map(function(item) {
				return item.a;
			});
			var bArray = result.map(function(item) {
				return item.b;
			});
			
			var labels = [];
			var n = $('#selectedNumber').val();
			
			
			var chart = new CanvasJS.Chart("chartContainer", {
				theme: "theme1",//theme1
				title:{
					text: "Test Chart(Based on CanvasJS)"              
				},
				animationEnabled: false,   // change to true
				data: [              
				{
					// Change type to "bar", "area", "spline", "pie",etc.
					type: "line",
					dataPoints: [
						{ x: aArray[0],  y: bArray[0] },
						{ x: aArray[1],  y: bArray[1] },
						{ x: aArray[2],  y: bArray[2] },
						{ x: aArray[3],  y: bArray[3] }
					]
				}
				]
			});
			chart.render();
			
		});
	}
	$('#sendForm').on('click', function() {
		sendForm();
	});
})