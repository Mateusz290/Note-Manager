package pl.dabrowski.Backend.Developer.Assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.dabrowski.Backend.Developer.Assignment.model.Note;
import pl.dabrowski.Backend.Developer.Assignment.repo.NoteRepo;
import pl.dabrowski.Backend.Developer.Assignment.service.NoteService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private NoteController noteController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteRepo repo;

    @MockBean
    private NoteService service;

    private static List<Note> notesList;

    @BeforeAll
    public static void setTestDate() {

        notesList = new ArrayList<>();
        notesList.add(new Note(1L,"Day 7", "One Flew Over the Cuckoo's Nest",
                new Date(), new Date(), 1));
        notesList.add(new Note(1L ,"Day 8", "The Shawshank Redemption",
                new Date(), new Date(), 2));
        notesList.add(new Note(1L, "Day 9", "The Godfather", new Date(), new Date(), 3));
        notesList.add(new Note(2L, "Day 10", "12 Angry Men", new Date(), new Date(), 1));
    }

    @Test
    void getAllNotes() throws Exception {

        Mockito.when(service.getAllNotes()).thenReturn(notesList);

        this.mvc.perform(MockMvcRequestBuilders.get("/getAllNotes")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @Test
    void getHistoryForParticularNote() throws Exception {
        Mockito.when(service.getHistoryForParticularNote(1L)).thenReturn(notesList);

        this.mvc.perform(MockMvcRequestBuilders.get("/getNoteHistory/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @Test
    void getNote() throws Exception {
        Note note = new Note();
        note.setTitle("Day 7");
        note.setContent("One Flew Over the Cuckoo's Nest");

        Mockito.when(service.getNoteByIdVersion(1L)).thenReturn(note);

        mvc.perform(MockMvcRequestBuilders.get("/getNote/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Day 7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content")
                        .value("One Flew Over the Cuckoo's Nest"))
                .andExpect(status().isOk());

    }

    @Test
    void addNote() throws Exception {
        Note note = new Note();
        note.setId(1L);
        note.setContent("abc");
        note.setTitle("1234");
        note.setNoteId(1L);
        note.setModified(new Date());
        note.setCreated(new Date());
        note.setDeleted(false);
        note.setVersion(5);

        Mockito.when(service.addNote(any(Note.class))).thenReturn(note);

        mvc.perform(MockMvcRequestBuilders.post("/addNote")
                .content(new ObjectMapper().writeValueAsString(note)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()).
                andExpect(MockMvcResultMatchers.jsonPath("$.content").value("abc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.noteId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.version").value("5"));



    }

    @Test
    void updateNote() throws Exception {
        Note note = new Note();
        note.setContent("abc");
        note.setTitle("1234");
        note.setModified(new Date());

        Mockito.when(service.updateNote(any(Long.class), any(Note.class))).thenReturn(note);

        this.mvc.perform(MockMvcRequestBuilders.put("/updateNote/1")
                .content(new ObjectMapper().writeValueAsString(note))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(print());

    }

    @Test
    void deleteNote() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/deleteNote/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(print());
    }
}