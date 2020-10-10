# Note-Manager Application

a) What is required for running the project?

Java 8+, Maven, MySQL Database, Intellij IDEA

b) Steps how to run scripts that will setup database for the project

No script is required. Proper database objects are created by spring jpa.

c) Steps how to build and run the project

Import project from git repository. Run SpringBootApplication in BackendDeveloperAssignmentApplication class.

d) Example usages (ie. like example curl commands)

http://localhost:8080/getNote/2     // get specific note by noteId
http://localhost:8080/getNoteHistory/1 // get all changes for specific note history
http://localhost:8080/getAllNotes // get all notes from database which weren't deleted.
http://localhost:8080/addNote   // add new note
http://localhost:8080/updateNote/4  // update specific note
http://localhost:8080/deleteNote/3   // delete spefic note (deleted note is visible in note history)









