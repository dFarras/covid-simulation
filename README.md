# COVID-SIMULATION
## INTENTION
This repository represents a simple covid spread model.  
Since it is still a new born project and it is just a "for fun project" the data extracted should not be considered relevant.  
  
The idea is to make this project highly adaptable so in the future it will be easy to add more elements to simulations, and also to modify the configurations that will definitely impact the results so that many little differences in org.dfarras.configuration can be calculated without touching the code.  
  
  
## FLOW
Simulation has been modelled following this concepts:
  1. Every hour is calculated the spread chance between an infected person and every other non infected one sharing space with the first.
  2. The calculation takes into account the place people is  in order to define hourly spread chance.
  
## USAGE
In order to make its usage as easy as possible the simulation has been migrated to a spring boot project providing a REST interface with one endpoint to start the simulation.
TIP -> At this moment I can't provide an ip to start using it, but below explanation is still true if someone wants to install the simulation in his/her own machine to start running different scenarios.

### Request
Url: POST {host}/covid-simulation/start-simulation
Payload:
```
{
	"startConfiguration": {
		"population": 1000,
		"personPerHouse": 3,
		"initialInfectedPopulation": 100
	},
	"simulationConfiguration": {
		"totalSimulatedHours": 160
	},
	"contagionConfiguration": {
		"houseSpreadChance": 10.00,
		"workplaceSpreadChance": 10.00
	}
}
```

### Response
Status: 200 OK
```
{
    "startTime": "2020-04-25T18:21:36.67169",
    "simulationTime": "2020-05-02T10:21:36.67169",
    "totalInfected": 1000,
    "configuration": {
        "population": 1000,
        "personPerHouse": 3,
        "initialInfectedPopulation": 100,
        "totalSimulatedHours": 160,
        "houseSpreadChance": 10.0,
        "schedule": [
            {
                "place": "House",
                "start": "16:00:00",
                "end": "08:00:00"
            },
            {
                "place": "Workplace",
                "start": "08:00:00",
                "end": "16:00:00"
            }
        ]
    }
}
```

## FINAL CONSIDERATIONS
I am doing this on my spare time so I will be updating it probably not everyday.

Thanks in advance!
