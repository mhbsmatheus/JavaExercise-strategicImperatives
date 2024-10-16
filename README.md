
## Overview

*Please read the whole document carefully*

Using Spring Boot we would like you to design and build a microservice to help organise corporate parties. Your service will need to expose a RESTful API to manage the guest list.  
IMPORTANT: The number of tables and seat capacity are subject to change.

When the party begins, guests will arrive with their friends. This number may not be the size indicated on the guest list. However, if it is expected that the guest's table can accommodate the extra people, then they all should be let in. Otherwise, they will be turned away.
Guests will also leave throughout the course of the party. Note that when a guest leaves, their accompanying guests will leave with them.

At any point in the party, we should be able to know:
- Our guests at the party
- How many empty seats there are

Hopefully this tech task allows you to show yourself off as much as you decide to!  
How long you spend on this is up to you, but we suggest spending no more than two or three hours.

## Sample API guide

This is a directional API guide.

### Add a table to use

```
POST /table
body: 
{
    "tableNumber": 1,
    "capacity": 30
}
response: 
{
      "idTable": 2,
      "tableNumber": 2,
      "capacity": 30,
      "availableSeats": 30,
      "guest": null
}
```

### Get the one specific table

```
GET /table/{tableNumber}
response: 
{
      "tableNumber": 1,
      "capacity": 30
}
```

### Add a guest to the guestlist

If there is insufficient space at the specified table, then an error should be thrown.
Another Validation has been added for the guest name.

```
POST /guest_list/name
body: 
{
    "table": int,
    "accompanying_guests": int
}
response: 
{
    "name": "string"
}
```

### Get the guest list

```
GET /guest_list
response: 
{
    "guests": [
        {
            "name": "string",
            "table": int,
            "accompanying_guests": int
        }, ...
    ]
}
```

### Guest Arrives

A guest may arrive with his friends that are not the size indicated at the guest list.
If the table is expected to have space for the extras, allow them to come. Otherwise, this method should throw an error.

```
PUT /guests/name
body:
{
    "accompanying_guests": int
}
response:
{
    "name": "string"
}
```

### Guest Leaves

When a guest leaves, all their accompanying friends leave as well.

```
DELETE /guests/name
```

### Get arrived guests

```
GET /guests
response: 
{
    "guests": [
        {
            "name": "string",
            "accompanying_guests": int,
            "time_arrived": "string"
        }
    ]
}
```

### Count number of empty seats

```
GET /seats_empty
response:
{
    "seats_empty": int
}
```

## Deliverables
To generate a bundle from your git project, go to the project root folder and type:
```
git bundle create repo.bundle --branches --tags
git bundle verify repo.bundle
```

# General notes
- Docker composer set to run postgres to execute get into the project folder and execute the comand: docker compose up
- To run the projet need to run on local.
- The project use LOMBOK, make sure to have set.
- The properties had been set the postgres conection
- The API Usage can be done via swagger: http://localhost:8080/swagger-ui/index.html#/


# Personal notes
- There is no tests in the project due time - 3hr-3:30hr, this time includes everything in this repo. 
- TODO List: Finish the README, Do the case tests, finish the docker setup.



