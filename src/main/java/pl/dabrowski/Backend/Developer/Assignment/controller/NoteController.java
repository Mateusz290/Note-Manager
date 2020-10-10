package pl.dabrowski.Backend.Developer.Assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dabrowski.Backend.Developer.Assignment.model.Note;
import pl.dabrowski.Backend.Developer.Assignment.service.NoteService;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    public NoteService noteService;

    @GetMapping("/getNotes")
    public List<Note> getNotes() {
        return noteService.getNotDeletedNotes();
    }

    @GetMapping("/getAllNotes")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/getNoteHistory/{id}")   // the webservice that will return whole history of changes for particular note
    public List<Note> getHistoryForParticularNote(@PathVariable Long id) {
        return noteService.getHistoryForParticularNote(id);
    }

    @GetMapping("/getNote/{id}")
    public Note getNote(@PathVariable Long id) {
        return noteService.getNoteByIdVersion(id);
    }

    @PostMapping("/addNote")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @PutMapping("/updateNote/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note note) {
        return noteService.updateNote(id, note);
    }

    @DeleteMapping("/deleteNote/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }

}
