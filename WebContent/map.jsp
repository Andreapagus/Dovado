<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<!--  dichiarazione e istanziazione di una variabile di un
login bean!. !-->
<!--  lo scope è il livello di persistenza della variabile e può essere tipo
request e session (richiesta e sessione) oppure application o page (vive fino a chiusura applicazione o 
vive fino a ricaricamento pagina). !-->

	<%	if(session.isNew()) {
		
		response.sendRedirect("login.jsp");
	} else {
		session.setMaxInactiveInterval(10);	
	}%>
	
<html>
	<head>
		<meta charset="ISO-8859-1"></meta>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
		<title>Ricerca evento</title>
		<link
		rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
		integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
		crossorigin=""/>
		<script
		src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
		integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
		crossorigin=""
		></script>
		<!-- Load Esri Leaflet from CDN -->
		<script src="https://unpkg.com/esri-leaflet@2.5.3/dist/esri-leaflet.js"
		    integrity="sha512-K0Vddb4QdnVOAuPJBHkgrua+/A9Moyv8AQEWi0xndQ+fqbRfAFd47z4A9u1AW/spLO0gEaiE1z98PK1gl5mC5Q=="
		    crossorigin=""></script>

		  <!-- Load Esri Leaflet Geocoder from CDN -->
		<link rel="stylesheet" href="https://unpkg.com/esri-leaflet-geocoder@2.3.3/dist/esri-leaflet-geocoder.css"
		    integrity="sha512-IM3Hs+feyi40yZhDH6kV8vQMg4Fh20s9OzInIIAc4nx7aMYMfo+IenRUekoYsHZqGkREUgx0VvlEsgm7nCDW9g=="
		    crossorigin="">
		<script src="https://unpkg.com/esri-leaflet-geocoder@2.3.3/dist/esri-leaflet-geocoder.js"
		    integrity="sha512-HrFUyCEtIpxZloTgEKKMq4RFYhxjJkCiF5sDxuAokklOeZ68U2NPfh4MFtyIVWlsKtVbK5GD2/JzFyAfvT5ejA=="
		    crossorigin=""></script>
		<style>
			#Map {
				Height: 520px;
			}
		</style>
	</head>
<body>
	<script type="text/javascript">
	var lat,long;
	var i=0;

	/*Se sono riuscito a prendere informazioni sulla geolocalizzazione dell'utente parte la funzione success(position)*/
	
	function success(position){
			console.log("latitudine posizione =",position.coords.latitude,"longitudine posizione =",position.coords.longitude);
			setCoords(position.coords.latitude,position.coords.longitude);
	}
	
	/*Se NON sono riuscito a prendere informazioni sulla geolocalizzazione dell'utente parte la funzione error() e mi informa di quanto successo*/

	function error(){
		 alert("Nessuna informazione sulla geolocalizzazione dell'utente trovata.");
	}

	const options = {
		enableHighAccuracy: true,
		maximumAge: 30000,
		timeout: 27000
	};

	/*Cerco di prendere anche la posizione dell'utente tramite la geolocalizzazione.*/
	
	if('geolocation' in navigator) {
	  console.log("geolocation available");
		console.log(navigator.geolocation.getCurrentPosition(success));
	} else {
	  console.log("geolocation not available");
	}
	
	/*Tramite fetchPlaces carico sulla mappa tutti i posti che sono salvati nel nostro db e con loro in seguito si mostreranno sulla pagina 
	* anche i loro eventi.
	*/
	fetchPlaces().then(response => {
		console.log("Ricevuti i dati sui places.");
	}).catch(error => {
		console.error(error);
	});

	async function fetchPlaces(){
		const response = await fetch('places.json');
		const result = await response.json();
		for (place in result.places){
			console.log("indirizzo =",result.places[i].address,"civico =",result.places[i].civico);
			
			/*Grazie a Geocode si effettua una ricerca del Place in base all'indirizzo; il risultato della ricerca è un oggetto contenente la percentuale di precisione
			*della ricerca, la latitudine e longitudine ed altre informazioni. Facciamo uso della latitudine e longitudine per indicare i posti che si trovano in una città nella mappa.*/
			
			L.esri.Geocoding.geocode().address(result.places[i].civico+' '+result.places[i].address).city(result.places[i].city).region(result.places[i].region).run(function (err, results, response) {
  				if (err) {
    				console.log(err);
    				return;
  				}
  				console.log(results.results[0].latlng.lat+' '+results.results[0].latlng.lng);
				addMarker(results.results[0].latlng.lat,results.results[0].latlng.lng,result.places[i].name);
			});

			i++;
		}
		i=0;
	}

	async function spotPlace(){
		L.esri.Geocoding.geocode().nation('Niger').run(function (err, results, response) {
				if (err) {
				console.log(err);
				return;
				}
				console.log(results.results[0].latlng.lat+' '+results.results[0].latlng.lng);
			setCoords(position.coords.latitude,position.coords.longitude);
		});
	}
	
	</script>
	<h1>Mappa e geolocalizzazione.</h1>
	<div id="Map"></div>
	<script type="text/javascript" src="js/map.js">
	</script>


</body>

	
</html>
