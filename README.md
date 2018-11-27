# Assignment
## BookStore (http://bookstore-env.jfysm3bbsa.eu-west-2.elasticbeanstalk.com/v1/books)
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

### Security
API security has been implemented with Oauth 2.0. This has been achieved by enabling at the controller level using @EnableResourceServer and then configuring * security.oauth2.resource.userInfoUri: http://oauth.eu-west-2.elasticbeanstalk.com/tcs/user *

### Validation
I have tried to put basic Javax validation onto the entities/models.

## Oauth Service
Oauth service is a custom built spring boot service. I have used Josh Webb's reference and built Oauth server ontop of it. Internally it is using an in memory database to store the users and api client secrets. 

## Deployment
There are two spring boot applications developed and deployed on AWS Elastic Beanstack
###### BookStore (http://bookstore-env.jfysm3bbsa.eu-west-2.elasticbeanstalk.com/v1/books)
###### Oauth-Service (http://oauth.eu-west-2.elasticbeanstalk.com)

## Invoke/Access the APIs

I have tested the APIs using Postman most of the times. I have also tested the application using CURL
First get the auth token
###### curl -X POST -vu acme:acmesecret http://oauth.eu-west-2.elasticbeanstalk.com/tcs/oauth/token -H "Accept: application/json" -d "password=boot&username=pwebb&grant_type=password&scope=openid&client_secret=acmesecret&client_id=acme"

oauth response: (lots of metadata info)
###### {"access_token":"29e620a0-71ee-4832-b4c1-0dfdb47a50b6","token_type":"bearer","refresh_token":"ff17320e-1da1-4c16-8fcf-85c34a8f7792","expires_in":17651,"scope":"openid"}* Connection #0 to host oauth.eu-west-2.elasticbeanstalk.com left intact

and then with the auth token you have got, 
###### curl http://bookstore-env.jfysm3bbsa.eu-west-2.elasticbeanstalk.com/v1/books -H "Authorization: Bearer 29e620a0-71ee-4832-b4c1-0dfdb47a50b6"

Response is : (This is because I have already added some books onto the system, if there are no books, you will get an empty array)

###### [{"title":"Java 8","id":1,"publisher":"Pearson","year":"2017","price":"123.45","author":{"name":"James","id":1,"dateOfBirth":"2018-11-24T23:17:17.791+0000","placeOfBirth":"London","dateOfDeath":"2018-11-24T23:17:17.791+0000","placeOfDeath":""}}]

if you dont pass the auth token, you will be given an error message with the description saying, You need to fully authenitcate to access resource.

if you pass an invalid/expired auth token, respecetive message will be shown.

To Create books in the bookstore, you will POST to
###### http://bookstore-env.jfysm3bbsa.eu-west-2.elasticbeanstalk.com/v1/books along with auth token and json array of books data in the body of the request.

## BookStore Lambda Deployment
I have not tried this before, but will the references available on the internet I tried to build one and deploy to AWS Lambda. 
I could deploy it successfully at https://y8zn667m5l.execute-api.eu-west-2.amazonaws.com/Stage/v2/books
But for some reason, it is taking the https scheme rather http which I have enforced to bear the Oauth security. I am getting a strange error saying {{"message":"Missing Authentication Token"}} But I am not throwing it. 

It was a surprise and taking much time to debug. I have very little informatiom on Lambdas.








