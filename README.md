# bsa-spring-data
 Learn to associate entities with tables in the database, as well as build requests to read/modify data

# Description of queries

* ### Search users by last name
``` search (like operation) by last name, pagination, sorting by last name in ascending order. Query is case insensitive```
 
 
* ### Search for users by city
``` search the city using the offices table. Sort users by last name in ascending order```
 
* ### Search for the most developing projects by technology
``` search for the first 5 projects that use the technology. Sorting users by the number of people on the project```
 
* ### Search for experienced users
 ```search for users who have experience> = specified. Sort users by experience, descending```
 
 * ### Search for users by location
 ```search by room and city using teams and offices tables. Sort users by last name in ascending order```
 
 * ### Search for the largest project
 ```search for a project with the most teams. If there are the same number of teams, then the project with the most developers is selected. If the number of developers is also the same, then the projects are sorted by name in descending order and the first is taken```
 
 * ### Selection of general information on projects
 ```the following information about the project is selected: name, number of teams, number of developers, list of technologies (separated by commas). Sorting data by project name in ascending order. Because it is rather difficult to turn technology values into a comma-separated list using standard JPQL tools; you can use nativeQuery and data projection onto the ProjectSummaryDto interface. If you cannot build a query in this way, then you can use any other method that matches the tests.```
 
 * ### Number of projects with a role
 ```counting projects that have developers in a specific role```
 
  * ### Finding offices that deal with technology
 ```finding offices that have projects that use a specific technology.```
 
  * ### Creating a new project
 ```create the project, technology and command for the project.```
 
  * ### Updating an existing team
 ```technology updates for a team in which number of users < specific number.```
 
  * ### Updating an existing office
 ```updating the address at the office, if the developers of this office are involved in the project```
 
  * ### Updating an existing team
 ```a request that will add the name of the project and the name of the technology to the name of the specified team (Team_Project_Technology).```
 
   * ### Deleting Users
 ```query that removes users with experience < specified number of years.```
 
   * ### Deleting Roles
 ```a query that will remove all roles by name if they are not tied to users.```
