# Spring6SecurityApp
- Java 21
- Spring Framework v6.2.8
- Spring Security v6.5.2

# App Setup
- After cloning the repo, run 'mvn clean package' to ensure the app is building correctly.
- Once the app compiles and builds correctly, you'll need to install a server to run the app.
-  I've tested this app using Tomcat v10.1. You can download from https://tomcat.apache.org/ or use another server of your choice.
-  To create a server with Eclipse, find the servers window after clicking Window -> Show View -> Other
-  Right click in the servers window to create a new server. You should be able to choose the server runtime environment by providing the installation directory of your downloaded server. Once that has been configured, you should be able to add the Spring6SecurityApp from the list of available apps to configured apps that your server can run.
-  At this point, you should be able to start the server and call the endpoints via a web browser.
-  Once you start the server, enter url 'http://localhost:8080/Spring6SecurityApp/login' into your browser's address bar. You'll be prompted for login credentials that you can find for user and admin roles in SecurityConfig.java via the Spring bean 'InMemoryUserDetailsManager'. You won't be able to call any of the endpoints listed in UserPathsController.java until login is successful.
-  When you login, you'll be presented with a welcome page that is available to all authenticated users: 'http://localhost:8080/Spring6SecurityApp/welcome'.
-  If you logged in as a user, you should be able to retrieve user data via the url 'http://localhost:8080/Spring6SecurityApp/user'. However, if you attempt to retrieve admin data via 'http://localhost:8080/Spring6SecurityApp/admin', you will receive a 403 Forbidden response.
-  If you logged in as a admin, you should be able to retrieve both user and admin data via urls 'http://localhost:8080/Spring6SecurityApp/user' and 'http://localhost:8080/Spring6SecurityApp/admin' respectively.
-  You can logout to login as another user via url 'http://localhost:8080/Spring6SecurityApp/logout'.
