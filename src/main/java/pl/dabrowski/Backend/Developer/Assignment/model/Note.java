package pl.dabrowski.Backend.Developer.Assignment.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long noteId;

    @NotNull(message = "title cannot be null")
    @Size(min = 3, message = "Title show have at least 3 characters")
    private String title;

    @NotNull(message = "content cannot be null")
    @Size(min = 3, message = "content show have at least 3 characters")
    private String content;

    private Date created;

    private Date modified;

    @Column(name = "noteVersion")
    private Integer version;

    private boolean isDeleted;

    public Note() {
    }

    public Note(String title, String content, Date created, Date modified) {
        this.title = title;
        this.content = content;
        this.created = created;
        this.modified = modified;
    }

    public Note(@NotNull Long note_id, @NotNull(message = "title cannot be null") @Size(min = 3, message = "Title show have at least 3 characters") String title, @NotNull(message = "content cannot be null") @Size(min = 3, message = "content show have at least 3 characters") String content, Date created, Date modified, Integer version) {
        this.noteId = note_id;
        this.title = title;
        this.content = content;
        this.created = created;
        this.modified = modified;
        this.version = version;
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public static int compare(Note x, Note y) {
        return (x.getVersion() < y.getVersion()) ? -1 : ((x.getVersion() == y.getVersion()) ? 0 : 1);
    }
}
