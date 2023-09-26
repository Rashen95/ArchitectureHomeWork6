package database;

import notes.infrastructure.persistance.Database;

public class NotesDatabase implements Database {

    private NotesTable notesTable = new NotesTable();

    public NotesTable getNotesTable() {
        return notesTable;
    }

    public void setNotesTable(NotesTable notesTable) {
        this.notesTable = notesTable;
    }
}
