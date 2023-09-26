package database;

import java.util.ArrayList;
import java.util.Collection;

public class NotesTable {

    private Collection<NotesRecord> records = new ArrayList<>();

    public Collection<NotesRecord> getRecords() {
        return records;
    }

    public void addRecord(NotesRecord record) {
        records.add(record);
    }

}
