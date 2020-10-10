package pl.dabrowski.Backend.Developer.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.dabrowski.Backend.Developer.Assignment.model.Note;
import pl.dabrowski.Backend.Developer.Assignment.repo.NoteRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BackendDeveloperAssignmentApplication {

	@Autowired
	private NoteRepo repo;

	@EventListener(ApplicationReadyEvent.class)
	public void runExample() {

		List<Note> notesList = new ArrayList<>();
		notesList.add(new Note(1L,"Day 7", "It was a good day", new Date(), new Date(), 1));
		notesList.add(new Note(1L ,"Day 8", "It was a confusing day", new Date(), new Date(), 2));
		notesList.add(new Note(1L, "Day 9", "It was a boring day", new Date(), new Date(), 3));
		notesList.add(new Note(2L, "Day 10", "It was a pleasing day", new Date(), new Date(), 1));

		repo.saveAll(notesList);

	}

	public static void main(String[] args) {
		SpringApplication.run(BackendDeveloperAssignmentApplication.class, args);
	}

}
