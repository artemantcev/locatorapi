# locatorapi

LocatorAPI Trilateration Service (using non-linear least squares method)

This application calculates the approximate position of mobile station point based on information from various base stations.
Base stations communicate with API using these methods:

Firstly, the base station needs to initialize itself on API server and get its own identifier. 
Base station should provide to API its coordinates in two dimensions. Here's an example of request:
`/stations/base/new?xCoord=67&yCoord=8`  

Response is: 

```json
{  
      "newBaseStationId": 57  
}
```  


Now your base station can send the information about mobile points using `/stations/update` endpoint. 

Here's an example of request body:  
```json
{
	"stationId": 57,
	"mobileStations": [
		{"stationId": 101, "distance": 69.5} //you can add more points here
	]
}
```

After that, the system will automatically create all non-existent mobile stations and persist them.
Or, if these mobile points are already known to server by their identifiers (stationId), 
their calculated data will be overwritten each time the base stations update position records. 

Now, you need to look at the position of some mobile station. Just use `/stations/mobile/find` route.  
Request is:  
```/stations/mobile/find?stationId=101```

Response is:  
```json
{
    "stationId": 101,
    "xCoord": -38.354332552623994,
    "yCoord": 9.016995594006096,
    "error": 4.598659769237272
}
```
There's nothing more to add. Maybe, someone will find it useful.