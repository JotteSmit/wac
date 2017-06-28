var display = document.getElementById("display").innerHTML;
var ingevoerdGetal = 0;
var uitkomst = null;
var operator = null;
var getal1 = 0;

function rekenwijze(op) {
	if (op == '+') {
		operator = "+";
		getal1 = document.getElementById("display").innerHTML;
		console.log("getal1:"+getal1);
		document.getElementById("display").innerHTML = "";
	}
	if (op == '-') {
		operator = "-";
		getal1 = document.getElementById("display").innerHTML;
		console.log("getal1:"+getal1);
		document.getElementById("display").innerHTML = "";
	}
	if (op == '*') {
		operator = "*";
		getal1 = document.getElementById("display").innerHTML;
		console.log("getal1:"+getal1);
		document.getElementById("display").innerHTML = "";
	}
	if (op == '/') {
		operator = "/";
		getal1 = document.getElementById("display").innerHTML;
		console.log("getal1:"+getal1);
		document.getElementById("display").innerHTML = "";
	}
}

function reken() {
	getal2 = document.getElementById("display").innerHTML;
	console.log("getal2:"+getal2);
	if (operator == "+") {
		// optellen
		uitkomst = parseInt(getal1) + parseInt(getal2);
		console.log(uitkomst);
		document.getElementById("display").innerHTML = uitkomst;
	}
	if (operator == "-") {
		// aftrekken
		uitkomst = parseInt(getal1) - parseInt(getal2);
		console.log(uitkomst);
		document.getElementById("display").innerHTML = uitkomst;
	}
	if (operator == "*") {
		// vermenigvuligen
		uitkomst = parseInt(getal1) * parseInt(getal2);
		console.log(uitkomst);
		document.getElementById("display").innerHTML = uitkomst;
	}
	if (operator == "/") {
		// delen
		uitkomst = parseInt(getal1) / parseInt(getal2);
		console.log(uitkomst);
		document.getElementById("display").innerHTML = uitkomst;
	}
}

function getal(getal) {
		document.getElementById("display").innerHTML += getal;
}

function leeg(){
	document.getElementById("display").innerHTML = "";
}