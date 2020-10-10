package pl.dabrowski.Backend.Developer.Assignment.exception;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(Long id) {
        super("Could not found note: " + id);
    }
}
