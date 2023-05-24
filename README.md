# My Issue Tracker
## Intro
An issue tracker is a software that allow users to record issues from a certain project. Issues can represent bugs, tasks that must be done or a to-do list. In summary, the following functions are to be implemented in the 1.0 version of the project:
[1] Register an user and basic information;
[2] Login/logout hability;
[3] Create a project for a User;
[4] Entering issues (tickets), which can be dysfunctions, errors and requests to a project;
[5] API security;
[6] Basic frontend.

In later versions:
* Distribution and assignment of issues to persons in charge;
* Monitoring of handling, time spent and quality of work;
* Statistical analysis of the number of tickets;

Currently, the project is in the backend stage, only accessed through an API client, such as Postman or cURL. The steps 1, 2, 3, and 4 are completed.

## Installing dependencies
First, you have to install and configure: 
* the docker engine;
* the Java Development Kit (JDK);
* Maven.

To install the docker engine in Windows systems go to [here](https://www.docker.com/products/docker-desktop/). The procedure to install the JDK can be found [here](https://www.freecodecamp.org/news/how-to-install-java-on-windows/) and the Maven, [here](https://www.baeldung.com/install-maven-on-windows-linux-mac).

# How to start
Once everything is setup, clone this repository in a folder. Start the docker engine by clicking on it (Docker Desktop). After the Docker is ready, open the folder in the terminal and type the following:
```
<folder_path>\issue_tracker\backend\myissuetracker> docker compose up
```
It configures the services in the docker container. If the following message appears, the database is ready:

```
LOG:  database system is ready to accept connections
```
Then, start the project with:
```
<folder_path>\issue_tracker\backend\myissuetracker> mvn spring-boot:run
```
## Basic API Contract
The project is run by default in localhost:8080.

To get all pre-set users:
```
curl http://localhost:8080/api/v1/all-users
```
One of the users is: 
~~~ JSON
{
    "email":"marco@gmail.com",
    "password":"marconet",
    "userId":1,
    "userName":"Marco de la Vega"
}
~~~

With Postman, to register a new user, send a post request to

```
http://localhost:8080/api/v1/register-user
```

With the json body:
```
{
    "email": "tomassen@gmail.com",
    "password": "tomasssen",
    "userName": "Tomas Sen"
}
```
The item email and password are the credentials associated with the user whereas userName is the full name.

The body of the response is:
```
{
    "userId": 4,
    "userName": "Tomas Sen",
    "password": "tomasssen",
    "email": "tomassen@gmail.com"
}
```

To open a project, send a POST request to the following address:
```
localhost:8080/api/v1/create-project/<userId>
```
The json body items are:
```
{
    "projectName": "Go to Panama",
    "projectDescription": "Save money to go to Panama",
    "startingDate": "2023-05-17T09:20:30",
    "deadline": "2023-12-01T09:20:30"
}
```
The response body is:
```
{
    "projectId": 10,
    "projectName": "Go to Panama",
    "projectDescription": "Save money to go to Panama",
    "startingDate": "2023-05-17T09:20:30",
    "deadline": "2023-12-01T09:20:30"
}
```
To create an issue in this project, send a post request to:
```
localhost:8080/api/v1/create-issue/<userId>/<projectId>
```
With the json body as:
```
{
    "issueName":"Look for a place to stay",
    "issueDescription": "Select a place to stay near Rome.",
    "priorityLabel":"normal",
    "startingDate":"2023-05-09T15:17:37.359673",
    "deadline":"2024-05-17T00:00:00"
}

```

## Entity-Relationship diagram
The app has three main objects connected: User, project, and issue. The constraints are:
1) Each user can create many projects and a user can be registered without creating a project;
2) The user has a name, e-mail, password, and ID;
3) Each project has one main user, the admin;
4) Each project has time attributes: starting date, deadline and closing date;
5) Each project can be created, deleted, closed, or updated;
6) The project has name and description;
7) A user can open an issue related to a project;
8) The issue has time attributes: starting date, deadline and closing date;
9) The issue can be created, updated, and closed;


<p style="text-align:center;">
<img src="https://github.com/andrevier/issue_tracker/blob/main/images/data_model.png" alt="Entity-relationship diagram." style="width:900px;"/>
</p>


## Table diagram
<p style="text-align:center;">
<img src="https://github.com/andrevier/issue_tracker/blob/main/images/schema.png" alt="Entity-relationship diagram." style="width:900px;"/>
</p>

## File structure
<pre>
├───sql
├───src
│   ├───main
│   │   ├───java
│   │   │   └───andrevier
│   │   │       └───myissuetracker
│   │   │           └───myissuetracker
│   │   │               ├───config
│   │   │               ├───controller
│   │   │               ├───dao
│   │   │               ├───dto
│   │   │               ├───model
│   │   │               └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates
│   └───test
│       └───java
│           └───andrevier
│               └───myissuetracker
│                   └───myissuetracker
</pre>
1) Sql folder has cheat-sheet of sql commands to test the database;
2) src/main/java/andrevier/myissuetracker/myissuetracker is the main project folder;
3) Inside the main folder, the project is divided into packages of classes. 
* config - main configuration classes;
* controller - controller layer of API;
* dao - repositories that extends JPARepository interface;
* dto - Send and receive format of data to the client;
* model - classes related to the tables;
* service - Business rules.
