## Java Servlet Form Application with MySQL Database Integration. ♨️

This Java Servlet Form Application demonstrates the development of a web application that utilizes Java Servlets for server-side processing, uses MySQL as the database, and incorporates filters for enhanced functionality. The application also features session management to provide a secure and personalized user experience.

### Prerequisites

Before running the application, make sure you have Tomcat configured, and integrate the MySQL connector (JDBC) in your project.

Database configuration:

To run the application you have to also make sure that you have a MySQL database created (`biblio`), as well as the tables used for logging in (`admin`) and the one in which the subscribers are stored (`abonne`).

You can use a custom port number and table names as long as it is changed in the URL used in the code when connecting to the database.

### Features
- Login and Registration: Users can register for a new account and log in with their credentials.
- Session Management: Sessions are managed to display useful data. Users are authenticated before accessing certain resources.
- Form Submission: The application allows users to submit forms with relevant data. Data is validated on the server side to ensure data integrity using filters.
- MySQL Integration: Utilizes JDBC to connect to a MySQL database for data storage and retrieval.

Feel free to contribute and enhance the application!
