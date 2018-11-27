# assignment
### Design Approach
To create the bookstore APIs, I am using Spring Boot. Using the Spring REST controller, the required end points have been exposed. It is a model driven design where we have two main models as Book and Author.

I have then exposed set methods via an interface. Then the interface has been implemented by a data Repository. Currently I am using in memory LinkedHashMap as my datastore for both Book and the Author. In Future if need be, it can easily be extended to database, but there will be no change to the methods exposed to the Controller. 

### Exception Handling
I have created a few custom exceptions to manage the different scenarios in the application. All the errors will go through a ControllerAdvice. Which then transforms the exception into "application/vnd.error". Some of the examples are

#### BookNotFoundException - 404
This will be thrown when you are trying to access a book which is not in the repository.
#### BookConflictException - 409
This will be thrown when you are trying to add a same book again
#### ConstraintViolationException - 400
This will be thrown with the message which part of the request is problematic.

### Versioning
The API versioning currently it has been done at URI level. However I have not tried to demonstrate mutliple versions of the API code at the moment.






