![ ](https://github.com/YASSINE-ZENNED/Ticket-Spring-Boot/assets/52501790/1620c592-0a49-4603-8ad0-a0af0f7c6b7c)



The application depicted in the architecture diagram allows users to create events and get tickets for those events.

# It consists of several microservices and key components:

Frontend: The application has a frontend built using Angular for web and also includes a mobile application built with Flutter. The mobile app has the ability to scan QR codes to validate tickets.

API Gateway: KrakenD is used as the API gateway to manage and route requests from the frontend to the backend services securely.

Authentication: Keycloak handles authentication, ensuring that users are properly authenticated before accessing the services.

Microservices:
      Authentication Service: Manages user authentication and authorization.
      Event Service: Handles the creation and management of events.
      Ticket Service: Manages ticketing for the events.
  
Message Queue: Kafka is used for communication between the services, allowing them to push and consume messages from queues.

Databases: PostgreSQL databases are used for storing data related to authentication, events, and tickets.

Management Tools: PGAdmin is used for managing PostgreSQL databases, and Eureka Server is used for service discovery.

Docker is used to containerize the services, making them easier to deploy and manage.
