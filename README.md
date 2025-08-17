# Spring6SecurityApp
- Java 21
- Spring Framework v6.2.8
- Spring Security v6.5.2

# App Setup
- After cloning the repo, run 'mvn clean install' to ensure the app is building correctly.
- Once the app compiles and builds correctly, you'll need to install a server to run the app.
-  I've tested this app using Tomcat v10.1. You can download from https://tomcat.apache.org/ or use another server of your choice.
-  To create a server with Eclipse, find the servers window after clicking Window -> Show View -> Other
-  Right click in the servers window to create a new server. You should be able to choose the server runtime environment by providing the installation directory of your downloaded server. Once that has been configured, you should be able to add the Spring6SecurityApp from the list of available apps to configured apps that your server can run.
-  At this point, you should be able to start the server and hit the endpoints via Postman.
-  There are 4 endpoints that you can hit
      - welcome page: a non-secured test endpoint -> http://localhost:8080/Spring6SecurityApp/logout-success
      - a user login: a secure endpoint that can only be used by Spring Security roles USER and ADMIN. You can provide the credentials via             Postman's Basic Authentication. Credentials can be found via SecurityConfig.java -> http://localhost:8080/Spring6SecurityApp/user
      - a admin login: a secure endpoint that can only be used by Spring Security role ADMIN. You can provide the credentials via                      Postman's Basic Authentication. Credentials can be found via SecurityConfig.java -> http://localhost:8080/Spring6SecurityApp/admin
      - a logout endpoint: a non-secured endpoint that redirects users to a html template -> http://localhost:8080/Spring6SecurityApp/logout-success
