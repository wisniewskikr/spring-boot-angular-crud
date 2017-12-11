$(function() {
	$( document ).tooltip();
	$('#view').addClass("disabled");
	$('#edit').addClass("disabled");
	$('#delete').addClass("disabled");
	$('#create').attr("disabled", true);
	$('#name').keyup(function(){
			if( !$(this).val() ) {
				$('#create').attr("disabled", true);
				$('#update').attr("disabled", true);
			} else {
				$('#create').attr("disabled", false);
				$('#update').attr("disabled", false);
			}
	});
});

function setSelected(radio) {
	$('#selected').attr('value', radio.value);
	$('#view').removeClass("disabled");
	$('#edit').removeClass("disabled");
	$('#delete').removeClass("disabled");
}

function sendAction(actionName) {
	var action = "/" + actionName + "/" + $('#selected').attr('value');
	location.href = action;
}