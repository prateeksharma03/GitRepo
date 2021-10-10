# Getting Started


## API Documentation
## API 1 - to generate tiny URL
	HTTP Method - POST
	URI - http://localhost:9090/url
	Payload - 
	{
	    "url":"https://www.youtube.com/watch?v=WTEGvLXxyOY"
	}
	Success Response -
	HTTP Status - 200
	REsponse Body - 
	{
	    "status": "Success",
	    "tiny_url": "http://localhost:9090/url/gJm8gIKQAg"
	}

## API 2 - to use/redirect tiny url to actual destination url
	HTTP Method - GET
	URI - http://localhost:9090/url/{suffix} - This link is available in the response or API 1(tiny_url)
	Success Response -
	HTTP Status - 302 with response header attribute "Location" value as actual url
	Browser/client will automatically redirect to the url found in location header if response status is 302
	
## DB Design(H2 in memory DB just for POC)
	To make it very simple, I have used single table(URL_DETAILS) to store details of URL
	Columns - ID, CLIENT_CODE, TINY_URL, URL
	ID - Primary key
	CLIENT_CODE - Not mandatory value, Client code something like for facebook - 1001. default is 0
	TINY_URL - generated tiny URL 
	URL - Actual URL

##Scope of imporvement
DB -
	Can use multiple tables like - CLIENT_MASTER, URI_MAPPING
Feature -
	Can add few apis to register/view/login client 

## How to run
1) Please import the project and right click on file com.test.tiny.url.tinyurl.TinyurlApplication and select "Run AS"> "Java Application"
2) Once server started please go to postman or any rest client
3) We need to make a call to API 1 with desired payload as described above
4) From the success response please copy "tiny_url"
5) Open browser/postman and hit the copied url, we should get redirected to the original url

Happy Coding!!! :) 






### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

