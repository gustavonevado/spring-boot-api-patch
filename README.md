# API PATCH - BuildersTalk

## Spec
Java 11
Docker
Mongo

## Run

## Recursos

http://localhost:7070/swagger-ui.html#/

http://localhost:7070/swagger-ui.html#/movie-controller

### Patch Merge

curl -X PATCH "http://localhost:7070/movies/actions/merge/Jo%C3%A3o%20e%20Maria" -H "accept: */*" -H "Content-Type: application/merge-patch+json" -d "{ \"category\": [ \"Adulto\" ]}"

### Patch Cast 

curl -X PATCH "http://localhost:7070/movies/actions/json/O%20Senhor%20dos%20An%C3%A9is" -H "accept: */*" -H "Content-Type: application/json-patch+json" -d "[ { \"op\" : \"add\", \"path\": \"/cast\" , \"value\": [\"Sam Gamgi 3\",\"aaaaa\"] }, { \"op\" : \"add\", \"path\": \"/cast/0\" , \"value\": \"Frodo Baggins\" }, { \"op\" : \"add\", \"path\": \"/cast/0\" , \"value\": \"Elrond\" }]"

### add 
[
    { "op" : "add", "path": "/cast" , "value": ["Sam Gamgi 3","teste123"] },
    { "op" : "add", "path": "/cast/0" , "value": "Frodo Baggins" },
    { "op" : "add", "path": "/cast/1" , "value": "Elrond" },
    { "op" : "add", "path": "/cast/2" , "value": "Galadriel" }
]

### remove
[
    { "op" : "remove", "path": "/cast/2" }
]


### replace
[
{ "op" : "add", "path": "/cast/0" , "value": "Frodo Baggins" },
{ "op" : "add", "path": "/category/0" , "value": "Suspense" },
{ "op" : "replace", "path": "/imdbScore" , "value": "0.5"},
{ "op" : "replace", "path": "/awards" , "value": ["Melhor Builders Talk do Ano"]}
]

### move

[ 
    { "op":"move", "from":"/cast/0", "path":"/cast/-" }
]

### copy

[
    { "op":"copy", "from":"/cast/0", "path":"/cast/-" }
]


//List cast LOTR
"Ian McKellen",
"Liv Tyler",
"Viggo Mortensen",
"Sean Astin ",
"Cate Blanchett",
"JohnRhys-Davies",
"BernardHill",
"ChristopherLee",
"BillyBoyd",
"DominicMonaghan",
"OrlandoBloom",
"HugoWeaving",
"MirandaOtto",
"DavidWenham",
"BradDourif",
"KarlUrban",
"JohnNoble",
"AndySerkis",
"IanHolm",
"SeanBean",
"MartonCsokas",
"CraigParker"]}]

