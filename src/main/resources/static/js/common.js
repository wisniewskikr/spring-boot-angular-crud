$(function() {
	$( document ).tooltip();
});

function setSelected(radio) {
	$('#selected').attr('value', radio.value);
}

function sendAction(actionName) {
	var action = "/" + actionName + "/" + $('#selected').attr('value');
	location.href = action;
}