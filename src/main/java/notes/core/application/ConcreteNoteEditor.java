package notes.core.application;

import notes.core.application.interfaces.NoteEditor;
import notes.core.application.interfaces.NotesDatabaseContext;
import notes.core.application.interfaces.NotesPresenter;
import notes.core.domain.Note;

import java.util.Collection;
import java.util.Date;

public class ConcreteNoteEditor implements NoteEditor {

    private final NotesDatabaseContext dbContext;
    private final NotesPresenter notesPresenter;

    public ConcreteNoteEditor(NotesDatabaseContext dbContext,
                              NotesPresenter notesPresenter) {
        this.dbContext = dbContext;
        this.notesPresenter = notesPresenter;
    }

    @Override
    public void add(Note item) {
        Collection<Note> notes = dbContext.getAll();
        notes.add(item);
        dbContext.saveChanges(notes);
    }

    @Override
    public void edit(Note item) {
        Collection<Note> notes = dbContext.getAll();
        for (Note note : notes) {
            if (note.getId() == item.getId()) {
                note.setTitle(item.getTitle());
                note.setDetails(item.getDetails());
                note.setEditDate(new Date());
            }
        }
        dbContext.saveChanges(notes);
    }

    @Override
    public void remove(Note item) {
        Collection<Note> notes = dbContext.getAll();
        for (Note note : notes) {
            if (note.getId() == item.getId()) {
                notes.remove(note);
                break;
            }
        }
        dbContext.saveChanges(notes);
    }

    @Override
    public Note getById(Integer id) {
        Collection<Note> notes = dbContext.getAll();
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }
        throw new RuntimeException("Note not found");
    }

    @Override
    public Collection<Note> getAll() {
        return dbContext.getAll();
    }

    @Override
    public void printAll() {
        notesPresenter.printAll(getAll());
    }
}
