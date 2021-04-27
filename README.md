# booksApp
## Instructions  how to run booksApp
* Download by clicking on the "Code" button --> "Download Zip"
* Extract the downloaded file to your computer
* Go in the terminal to the location where you extracted the file, to the location "booksApp-main/"
* Run the command "mvn spring-boot:run"
* go to your browser and type the command according to the following templates
## URI int App
* "/books/{bookIsbn}" - returns a JSON for one book by the specified isbn
* "books/category/{category}" - returns JSON with a list of books for a given category
* "books/authors" - returns JSON with a list of authors with their average rating
* "books/volume/{pageNumber}" - returns the JSON of the first book whose number of pages is greater than the {pageNumber} 
* "books/hours/{hours}/pages/{pages}" - returns JSON with the book/books the user is able to read in a month, and the rating is the best possible
* "books/recently" - returns JSON with the book/books that were last viewed using the endpoint "books/{bookIsbn}", uses LRUCache implementations that peak after an hour


### Short boksApp description
An application that takes data from a JSON file, and returns it to the browser, as expected by the user. For example, for the url "books/{bookIsbn}" returns a JSON document regarding the book with the specified {bookIsbn}.
### Spring 
Spring is the most popular application development framework for enterprise Java. Millions of developers around the world use Spring Framework to create high performing, easily testable, and reusable code.
### Design paterns
* Dependency Injection (DI) - One of the main design patterns in Spring. The class is independent of any particular implementation. This makes it configurable by the client, which promotes reusability and maintainability.
* Model-View-Controller -  helps you create applications that separate the different aspects of the application (input logic, business logic, and UI logic), while providing a loose coupling between these elements.

### Application testing
* JUNIT - used to create simple unit tests.
* POSTMAN - used to test the endpoints and see what they return. A simple and interesting tool to make web application testing easier. 