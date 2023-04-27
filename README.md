# My Issue Tracker (MIT)
## Intro
An issue tracker is a software that allow users to record issues from a certain project. Issues can represent bugs, tasks that must be done or a to-do list. In summary, the following functions are to be implemented in the 1.0 version of the project:
* Entering of dysfunctions, errors and requests;
* Distribution and assignment of issues to persons in charge;
* Monitoring of handling, time spent and quality of work;
* Statistical analysis of the number of tickets;

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

1) Sql folder has cheat-sheet of sql commands to test the database;
2) src/main/java/andrevier/myissuetracker/myissuetracker is the main project folder;
3) Inside the main folder, the project is divided into packages of classes. 
* config - main configuration classes;
* controller - controller layer of API;
* dao - repositories that extends JPARepository interface;
* dto - Send and receive format of data to the client;
* model - classes related to the tables;
* service - Business rules.
