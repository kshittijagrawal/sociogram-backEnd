# Sociogram - Instagram Inspired Clone

Sociogram is a social media platform inspired by Instagram, offering various features that allow users to share images and videos, create stories, comment on posts, like content, follow and be followed, receive notifications for follow requests, and more. The project is built using Spring Boot and follows a microservices architecture with four main microservices: **Users**, **Posts**, **Stories**, and **Search**. Additionally, a separate microservice called **Common Infra** handles user notification services, login/signup authentication, as well as ad management and personalized content recommendations.

## Features

Sociogram provides the following key features:

1. **User Management**: Users can create accounts, log in, and manage their profiles. They can edit their profile information, upload a profile picture, and update their settings.

2. **Post Management**: Users can upload images and videos as posts, which can be viewed, liked, and commented on by other users.

3. **Story Creation**: Users can create stories containing photos or videos that disappear after a specified time. Followers can view these stories and provide feedback.

4. **Comments**: Users can leave comments on posts. Additionally, comments support two levels of nesting, allowing replies to existing comments.

5. **Likes**: Users can like posts, and the total number of likes is displayed on each post.

6. **Follow/Unfollow**: Users can follow other accounts, and they can view the accounts that are following them.

7. **Follow Requests and Notifications**: Users receive notifications when someone sends them a follow request. Once they accept the request, the two users become mutual followers.

8. **Ads and Recommendations**: Common Infra enables the display of static hyperlink images on the right pane of the home feed page. These ads and recommendations are personalized to specific users based on the interests they added during sign-up.

## Microservices Architecture

The Sociogram backend is designed using a microservices architecture to ensure modularity, scalability, and maintainability. The following are the main microservices:

1. **Users Microservice**: Responsible for user-related operations, including user creation, profile updates, and managing follower/following relationships.

2. **Posts Microservice**: Handles the creation, retrieval, and management of posts made by users.

3. **Stories Microservice**: Manages the creation, retrieval, and display of user stories, which are temporary multimedia posts.

4. **Search Microservice**: Provides search functionality for finding users within the platform.

5. **Common Infra Microservice**: This microservice handles user notification services, authentication, ad management, and personalized content recommendations.

## Database Usage

Sociogram utilizes three databases to store and retrieve different types of data:

1. **MongoDB**: Used for storing user data, post metadata, comments, and follower/following relationships.

2. **Solr**: Utilized for implementing search functionality, allowing users to find relevant content.

3. **Firebase**: Used to store and retrieve multimedia posts and stories uploaded by users.

## Technologies Used

The backend of Sociogram is primarily built using Spring Boot, a powerful Java-based framework for creating web applications. The microservices architecture ensures that each component can be developed independently and deployed as separate services.

Other technologies and tools used in the project include:

- Spring Cloud: For implementing microservices patterns and managing distributed systems.
- MongoDB: For NoSQL database storage.
- Solr: For powerful and efficient search functionality.
- Firebase: For storing and retrieving multimedia content (images and videos).
- RESTful APIs: For communication between microservices and the frontend.

## Getting Started

To run Sociogram locally or deploy it to a server, follow these general steps:

1. Clone the Sociogram repository from the GitHub repository.
2. Set up the necessary environment variables for each microservice to connect to the databases.
3. Build and run each microservice using Spring Boot.
4. Ensure that all microservices are communicating correctly.
5. Deploy the frontend application (not mentioned here) to interact with the backend APIs.
