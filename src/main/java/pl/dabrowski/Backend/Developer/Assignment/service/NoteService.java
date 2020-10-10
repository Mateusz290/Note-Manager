package pl.dabrowski.Backend.Developer.Assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dabrowski.Backend.Developer.Assignment.model.Note;
import pl.dabrowski.Backend.Developer.Assignment.repo.NoteRepo;
import pl.dabrowski.Backend.Developer.Assignment.model.Note;
import pl.dabrowski.Backend.Developer.Assignment.repo.NoteRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepo repo;

    public List<Note> getNotDeletedNotes() {
        return repo.findAll().stream()
                .filter(note -> !note.isDeleted())
                .collect(Collectors.toList());
    }

    public List<Note> getAllNotes() {
        return repo.findAll();
    }

    public List<Note> getHistoryForParticularNote(Long id) {
        return repo.findAllHistoryForParticularNote(id);
    }

    public Note addNote(Note note) {

        long maxNote;
        if (!repo.getMaxNumber().isPresent()) {
            maxNote = 1;
            note.setNoteId(maxNote);
        } else {
            maxNote = repo.getMaxNumber().orElseThrow();
            note.setNoteId(maxNote + 1L);
        }
        note.setCreated(new Date());
        note.setModified(new Date());
        note.setVersion(1);
        note.setDeleted(false);

        return repo.save(note);
    }

    public Optional<Note> getNoteById(Long id) {
        return repo.findById(id);
    }

    public Note getNoteByIdVersion(Long id) {
        return repo.findNoteWithVersion(id).stream().max(Note::compare).get();
    }

    public Note updateNote(Long id, Note note) {
        Note oldNote = repo.findNoteWithVersion(id).stream().max(Note::compare).get();
        Note newNote = new Note();
        if (note.getContent() != null) {
            newNote.setContent(note.getContent());
        }
        if (note.getTitle() != null) {
            newNote.setTitle(note.getTitle());
        }
        newNote.setNoteId(oldNote.getNoteId());
        newNote.setVersion(oldNote.getVersion() + 1);
        newNote.setCreated(new Date());
        newNote.setModified(new Date());

        return repo.save(newNote);
    }

    public Note deleteNote(Long id) {
        Note oldNote = repo.findNoteWithVersion(id).stream().max(Note::compare).get();

        oldNote.setModified(new Date());
        oldNote.setDeleted(true);

        return repo.save(oldNote);
    }
}
