# chatroom
The program implements chatroom exe.  
users can login and chat in forum, with many other users.  
The program keep the users names & messages in Database.  
The program allowed the search messages by user name or by part of message.  
The program introduce to users list of all connected users.  
We check every 10 sec. if the user send fetch request to the server.  
The user checks every 10 sec. for new messages and only if founded, the message list updates.  
In this program we use Spring Boot technology,  
we use controller to handle/mapping all the requests.  
We use bean session as a user session.  
We implement thymeleaf in login.html and in chatroom.html.  
We use filters to check if the user allowed to access the chatroom.  
