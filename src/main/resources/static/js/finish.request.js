var lang = "pt";

$(document).ready( function() {
	configureLang();
	configureAmountOfPeopleChange();
	configureValueChange();
	configurePercentageChange();
});

configureLang = function(){
	searchParams = new URLSearchParams(window.location.search);
	lang = searchParams.get('lang') == undefined ? lang : searchParams.get('lang');
};

configureAmountOfPeopleChange = function(){
	$("#amount-of-people").on("change", function(){
		amountOfPeople = $(this).val();
		requestPrice = $("#request-price").val();

		updateValuePerPerson( requestPrice, amountOfPeople);
		generatePeopleFields( requestPrice, amountOfPeople);
	});
};

configureValueChange = function(){
	$(".people-container").on("change", ".value", function(){
		requestPrice = $("#request-price").val();
		value = $(this).val();
		updatePercentage( requestPrice, value, $(this) );
	});
}

configurePercentageChange = function(){
	$(".people-container").on("change", ".percentage", function(){
		requestPrice = $("#request-price").val();
		percentage = $(this).val();
		updateValue( requestPrice, percentage, $(this) );
	});
}

updateValuePerPerson = function( requestPrice, amountOfPeople ){
	value = requestPrice / amountOfPeople;
	$("#value-per-person").val(value.toFixed(2));
};

updatePercentage = function( requestPrice, value, $valueField ){
	percentage = (value * 100) / requestPrice;
	$valueField.closest(".row").find(".percentage").val( percentage.toFixed(2) );
}

updateValue = function( requestPrice, percentage, $percentageField ){
	value = (percentage * requestPrice) / 100;
	$percentageField.closest(".row").find(".value").val( value.toFixed(2) );
}

generatePeopleFields = function( requestPrice, amountOfPeople ){
	$(".people-container .people").remove();

	for(i=0; i< amountOfPeople; i++){
		$row = `
			<div class="row people">
				<div class="col s4">
					${ t("person") } ${ i + 1}
				</div>
				<div class="col s4">
					<input type="number" class="value" />
				</div>
				<div class="col s4">
					<input type="number" class="percentage" />
				</div>
			</div>
		`;
		$(".people-container").append($row);
	}

};

t = function(key){
	messages = messagesArray();
	if (messages[lang][key]) {
	    return messages[lang][key];
	}
	return key;
}

messagesArray = function(){
	messages = ["pt", "en"];
	messages["en"] = {
			"person": "Person"
	};
	messages["pt"] = {
			"person": "Pessoa"
	};
	return messages;
}
