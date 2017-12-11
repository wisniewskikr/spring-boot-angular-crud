$(function() {
	$( document ).tooltip();
	$('#view').addClass("disabled");
	$('#edit').addClass("disabled");
	$('#delete').addClass("disabled");
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