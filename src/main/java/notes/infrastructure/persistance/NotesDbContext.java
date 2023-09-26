package notes.infrastructure.persistance;

import database.NotesDatabase;
import database.NotesRecord;
import database.NotesTable;
import notes.core.application.interfaces.NotesDatabaseContext;
import notes.core.domain.Note;
import notes.infrastructure.persistance.configurations.NoteConfiguration;

import java.util.ArrayList;
import java.util.Collection;

public class NotesDbContext extends DbContext implements NotesDatabaseContext {


    @Override
    public Collection<Note> getAll() {
        Collection<Note> notesList = new ArrayList<>();
        //TODO: Этого кастинга быть не должно, тут должен работать внутренний механизм фреймворка
        for (NotesRecord record : ((NotesDatabase) database).getNotesTable().getRecords()) {
            notesList.add(new Note(
                    record.getId(),
                    record.getUserId(),
                    record.getTitle(),
                    record.getDetails(),
                    record.getCreationDate()
            ));
        }
        return notesList;
    }

    @Override
    public void saveChanges(Collection<Note> notesList) {
        NotesTable notesTable = new NotesTable();
        for (Note note : notesList) {
            notesTable.addRecord(new NotesRecord(
                    note.getId(),
                    note.getUserId(),
                    note.getTitle(),
                    note.getDetails(),
                    note.getCreationDate(),
                    note.getEditDate()));
        }
        ((NotesDatabase) database).setNotesTable(notesTable);
    }


    public NotesDbContext(Database database) {
        super(database);
    }

    @Override
    protected void onModelCreating(ModelBuilder builder) {
        builder.applyConfiguration(new NoteConfiguration());
    }


}
