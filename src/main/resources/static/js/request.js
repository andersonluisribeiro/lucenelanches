var lang = "pt";

$(document).ready( function() {
	configureLang();
	configureSelects();
	configureBtnPrice();

	loadSnacksModel();
	loadTypesOfBread();
	loadFillings();
	loadCheeses();
	loadSalads();
	loadSauces();
	loadSpices();

	configureOnChangeSelects();
	configureOnChangeChecksAndRadios();
	configureOnChangeSnacksModel();
	configureBtnSave();
	configureBtnFinish();

});

configureLang = function(){
	searchParams = new URLSearchParams(window.location.search);
	lang = searchParams.get('lang') == undefined ? lang : searchParams.get('lang');
};

configureSelects = function(){
	$('select').material_select();
};

loadSnacksModel = function(){
	$snacksModel = $("#snacks-model");
	$.getJSON( "/snacks_model", function( snacksModel ) {
		html = "<option>"+ t('Selecione') +"</option>";
	    $.each( snacksModel, function( key, snackModel ) {
	    	option = "<option value="+ snackModel.id +">"+ snackModel.typeOfBread.description +"</option>";
	        html += option;
	    });
	    $snacksModel.html(html);
	    $snacksModel.material_select();
	});
};

loadTypesOfBread = function(){
	$typeOfBread = $("#type-of-bread");
	$.getJSON( "/types_of_bread", function( typesOfBread ) {
		html = "<option>"+ t('Selecione') +"</option>";
	    $.each( typesOfBread, function( key, typeOfBread ) {
	    	option = "<option price="+ typeOfBread.price +" value="+ typeOfBread.id +">"+ typeOfBread.description +"</option>";
	        html += option;
	    });
	    $typeOfBread.html(html);
	    $typeOfBread.material_select();
	});
};

loadFillings = function(){
	$filling = $("#filling");
	$.getJSON( "/fillings", function( fillings ) {
		html = "<option>"+ t('Selecione') +"</option>";
	    $.each( fillings, function( key, filling ) {
	    	option = "<option price="+ filling.price +" value="+ filling.id +">"+ filling.description +"</option>";
	        html += option;
	    });
	    $filling.html(html);
	    $filling.material_select();
	});
};

loadCheeses = function(){
	$cheese = $("#cheese");
	$.getJSON( "/cheeses", function( cheeses ) {
		html = "<option>"+ t('Selecione') +"</option>";
	    $.each( cheeses, function( key, cheese ) {
	    	option = "<option price="+ cheese.price +" value="+ cheese.id +">"+ cheese.description +"</option>";
	        html += option;
	    });
	    $cheese.html(html);
	    $cheese.material_select();
	});
};

loadSalads = function(){
	$saladsContainer = $(".salads-conteiner");
	$.getJSON( "/salads", function( salads ) {
	    $.each( salads, function( key, salad ) {
	    	column =`
	    		<div class="input-field col s2 center">
					<input name="salad" type="radio" value="${salad.id}" price="${salad.price}" name="salad" id="salad-${salad.id}" />
					<label for="salad-${salad.id}">${t(salad.description)}</label>
				</div>
	    	`;
	    	$saladsContainer.append(column);

	    });

	    double = `
    		<div class="switch input-field col s2 right">
				<label>
			    	Dobrar
			      	<input type="checkbox" id="double-salad"/>
			      	<span class="lever"></span>
			    </label>
			 </div>
    	`;
	    $saladsContainer.append(double);
	});
}

loadSauces = function(){
	$saucesContainer = $(".sauces-container");
	$.getJSON( "/sauces", function( sauces ) {
	    $.each( sauces, function( key, sauce ) {
	    	column =`
	    		<div class="input-field col s3">
					<input type="checkbox" name="sauce" price="${sauce.price}" value="${sauce.id}" id="sauce-${sauce.id}" />
					<label for="sauce-${sauce.id}">${t(sauce.description)}</label>
				</div>
	    	`;
	    	$saucesContainer.append(column);
	    });
	});
}

loadSpices = function(){
	$spicesContainer = $(".spices-container");
	$.getJSON( "/spices", function( spices ) {
	    $.each( spices, function( key, spice ) {
	    	column =`
	    		<div class="input-field col s3">
					<input type="checkbox" name="spice" price="${spice.price}" value="${spice.id}" id="spice-${spice.id}" />
					<label for="spice-${spice.id}">${t(spice.description)}</label>
				</div>
	    	`;
	    	$spicesContainer.append(column);
	    });
	});
}

configureBtnPrice = function(){
	$(".btn-price").on( "click", function(e ) {
		e.preventDefault();
	});
};

configureOnChangeSelects = function(){
	$("select").on( "change", function(){
		updateSnackPrice();
	});
};

configureOnChangeChecksAndRadios = function(){
	$(".checks-and-radios, .switch").on("change", "input", function(){
		updateSnackPrice();
	});
};

configureOnChangeSnacksModel = function(){
	$("#snacks-model").on( "change", function(){
		updateSnackFields();
	});
};

configureBtnSave = function(){
	$(".btn-save").on( "click", function(e){
		e.preventDefault();
		clearValidations();
		if( validate(["name", "address", "type-of-bread"]) ){
			addSnack();
			$("#snack-form").trigger("reset");
			$("select").material_select();
		}
	});
};

configureBtnFinish = function(){
	$(".btn-finish").on( "click", function(e){
		e.preventDefault();
		$("#request-size").val( $("#snacks-container li").length );
		$("#request-form").submit();
	});
}

updateSnackPrice = function(){
	totalPrice = ( typeOfBreadPrice() + cheesePrice() + fillingPrice() + checksAndRadiosPrices() ).toFixed(2);
	$("#btn-snack-price").text("R$ " + totalPrice );
	pulseSnackPrice();
};

updateRequestPrice = function(){
	totalPrice = 0;
	$("#snacks-container .snacks-prices").each(function(){
		totalPrice += Number( $(this).val() );
	});
	$("#btn-request-price").text("R$ " + totalPrice.toFixed(2) );
	pulseRequestPrice();
}

updateSnackFields = function(){
	snackId = $("#snacks-model").val();
	$typeOfBread = $("#type-of-bread");
	$cheese = $("#cheese");
	$filling = $("#filling");

	$.getJSON( "/snack_model", {id: snackId}, function( snackModel ) {
		$typeOfBread.val(snackModel.typeOfBread.id);
		$cheese.val(snackModel.cheese.id);
		$filling.val(snackModel.filling.id);

		$( "input:checked" ).each( function(){
			$(this).prop("checked", false);
		});

		$("#salad-" + snackModel.salad.id).prop("checked", true);

		$.each( snackModel.sauces, function( key, sauce ) {
			$("#sauce-" + sauce.id).prop("checked", true);
		});

		$.each( snackModel.spices, function( key, spice ) {
			$("#spice-" + spice.id).prop("checked", true);
		});

		$typeOfBread.material_select();
		$cheese.material_select();
		$filling.material_select();
		updateSnackPrice();
	});
}

typeOfBreadPrice = function(){
	$field = $("#type-of-bread");
	price = 0;
	if( $field.val() !== "Selecione" ){
		price = Number( $("#type-of-bread option[value="+ $field.val() +"]").attr("price") );
	}
	return price;
}

cheesePrice = function(){
	$field = $("#cheese");
	price = 0;
	if( $field.val() !== "Selecione" ){
		price = Number( $("#cheese option[value="+ $("#cheese").val() +"]").attr("price") );
		if( $("#double-cheese").is(":checked") ){
			price += price;
		}
	}
	return price;
}

fillingPrice = function(){
	$field = $("#filling");
	price = 0;
	if( $field.val() !== "Selecione" ){
		price = Number( $("#filling option[value="+ $("#filling").val() +"]").attr("price") );
		if( $("#double-filling").is(":checked") ){
			price += price;
		}
	}
	return price;
}

checksAndRadiosPrices = function(){
	total = 0;
	$( "input:checked" ).each( function(){
		if( $(this).attr("price") ){
			total += Number( $(this).attr("price") );
		}

		if( ($(this).attr("name") == "salad") && $("#double-salad").is(":checked") ){
			total += Number( $(this).attr("price") );
		}

	});
	return total;
};

pulseSnackPrice = function(){
	$("#btn-snack-price").addClass("pulse");
	setTimeout(function(){
		$("#btn-snack-price").removeClass("pulse");
    }, 300);
};

pulseRequestPrice = function(){
	$("#btn-request-price").addClass("pulse");
	setTimeout(function(){
		$("#btn-request-price").removeClass("pulse");
    }, 300);
};

addSnack = function(){
	index = $("#snacks-container li").length;
	hiddens = nameHidden( index ) +
				addressHidden( index ) +
				typeOfBreadHidden( index ) +
				cheeseHidden( index ) +
				fillingHidden( index ) +
				saladHidden( index ) +
				saucesHidden( index ) +
				spicesHidden( index ) +
				snackPriceHidden( index );
	bread = $('#type-of-bread').parent(["0"]).children()[1].value;
	$("#snacks-container").append(`<li class="collection-item">${index + 1} - ${ bread }</li> ${hiddens}`);
	updateRequestPrice();
};

nameHidden = function( index ){
	return `<input type="hidden" name="snack[${index}][name]" value="${ $("#name").val() }"/>`;
};

addressHidden = function( index ){
	return `<input type="hidden" name="snack[${index}][address]" value="${ $("#address").val() }"/>`;
};

typeOfBreadHidden = function( index ){
	return `<input type="hidden" name="snack[${index}][typeOfBread]" value="${ $("#type-of-bread").val() }"/>`;
};

cheeseHidden = function( index ){
	return `<input type="hidden" name="snack[${index}][cheese]" value="${ $("#cheese").val() }"/>`;
};

fillingHidden = function( index ){
	return `<input type="hidden" name="snack[${index}][filling]" value="${ $("#filling").val() }"/>`;
};

saladHidden = function( index ){
	salad = $("input[name='salad']:checked").val();
	return `<input type="hidden" name="snack[${index}][salad]" value="${ salad }"/>`;
};

saucesHidden = function( index ){
	hiddens = "";
	$("input[name='sauce']:checked").each(function(){
		hiddens += `<input type="hidden" name="snack[${index}][sauces]" value="${ $(this).val() }"/>`;
	});
	return hiddens;
};

spicesHidden = function( index ){
	hiddens = "";
	$("input[name='spice']:checked").each(function(){
		hiddens += `<input type="hidden" name="snack[${index}][spices]" value="${ $(this).val() }"/>`;
	});
	return hiddens;
};

snackPriceHidden = function( index ){
	price = ( typeOfBreadPrice() + cheesePrice() + fillingPrice() + checksAndRadiosPrices() ).toFixed(2);
	return `<input type="hidden" class="snacks-prices" name="snack[${index}][price]" value="${ price }"/>`;
};

clearValidations = function(){
	$("#snack-form .validation").remove();
}

validate = function(fields){
  valid = true;
  for( i = 0; i < fields.length; i++ ){
    $field = $("#"+fields[i]);
    if($field.val() == "" || $field.val() == null || $field.val() == "Selecione" || $field.val() == "Select"){
      $field.closest(".input-field").append(validateMessage());
      valid = false;
    }
  }
  return valid;
};

validateMessage = function(){
	return `<div class="chip validation">${ t("validate_presence") }</div>`;
}


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
			"lettuce": "Salad",
			"arugula": "Arugula",
			"chard": "Chard",
			"italian": "Italian",
			"spicy": "Spicy",
			"chili": "Chili",
			"salt": "Salt",
			"oregano": "Oregano",
			"Selecione": "Select",
			"validate_presence" : "This fields is required."
	};
	messages["pt"] = {
			"lettuce": "Alface",
			"arugula": "Rúcula",
			"chard": "Acelga",
			"italian": "Italiano",
			"spicy": "Apimentado",
			"chili": "Pimenta",
			"salt": "Sal",
			"oregano": "Orégano",
			"Selecione": "Selecione",
			"validate_presence" : "Este campo é obrigatório."
	};
	return messages;
}
