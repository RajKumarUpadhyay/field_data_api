# FIELD DATA API

Field data api is service to manage CRUD operation on the field data and fetch weather history for third party client without passing any auth token. This service has one end point for all operation GET/DELETE/PUT/POST.

# Project structure
  * Backend - Spring boot framework used to build the backend service and for content deliver used inbuild tomcat apache server which us running on default port 8080.
  * Database - Use H2 in memoery data base for this project

# How to use this?
  - Use has need to clone this repo at host machine and build docker image using DOckerfile. Below is the setps to perform the build operation.
   1) Clone repo
        * git clone paste_repo_url
        
    2) Go to inside project dir and run below command to build docker image.
        * docker build -t filed-data-api Dockerfile
        
    3) RUN docker container
        * docker run  -name filed-data-api -p 8080:8080 -d  field_data:latest
    Container will start running at the host machine and user can check the running container by using below command: Docker ps\

# Features!
    This service has provide five operation. Below is the list of service and corresponding the definition.
    
|  HTTP METHOD | END POINT   |  DESCRIPTION |
|---|---|---|
|  GET | http://localhost:8080/fields  | This end point will return all available field data as a list if present else return "NO Record Found" as response   |
|  POST | http://localhost:8080/fields  | This will persist field data into database |
|  PUT | http://localhost:8080/fields/{fieldId}  |  This will update the data if fieldId is exist into db else reposne as "Resource Not Found" |
|  DELETE | http://localhost:8080/fields/{fieldId}  |  This will delete the fieldId if exist else return as reposne "Resource Not Found" |
|  Get | http://localhost:8080/fields/{fieldId}/wetaher  |  This will fetach the wetaher history last 7 days |


### Important 
 - In this Service used H2 databse that is not able to store Geometry data means the polygon coordinates will dropped fromm request if it is send as request and other details will persist
 into database. For geometry the mongodb and other database is the best choice. I'm sorry for the inconvenience here but it just a matter of database change and used any 
 third party jar to serilize/deserilize the data.
 
 ##Maintainer
 Raj K Upadhyay

