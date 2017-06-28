var oudeWaarde = document.getElementById("text").value;
setInterval(function(){document.getElementById("text").innerHTML = "test";
			var nieuweWaarde = document.getElementById("text").value;	
			if (nieuweWaarde !== oudeWaarde){console.log(nieuweWaarde); oudeWaarde = nieuweWaarde;}	
			}, 5000);