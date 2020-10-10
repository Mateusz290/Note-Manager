package pl.dabrowski.Backend.Developer.Assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dabrowski.Backend.Developer.Assignment.model.Note;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {

    @Query("SELECT max(n.noteId) from Note n")
    public Optional<Integer> getMaxNumber();


    @Query("select n from Note n where n.noteId = :noteId AND n.isDeleted = false")
    public List<Note> findNoteWithVersion(@Param("noteId") Long noteId);


    @Query("select n from Note n where n.noteId = :noteId")
    public List<Note> findAllHistoryForParticularNote(@Param("noteId") Long noteId);
}
