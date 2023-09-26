import database.NotesDatabase;
import notes.core.application.ConcreteNoteEditor;
import notes.core.domain.Note;
import notes.infrastructure.persistance.NotesDbContext;
import notes.presentation.queries.controllers.NotesController;
import notes.presentation.queries.views.NotesConsolePresenter;

import java.util.Date;

public class Program {

    public static void main(String[] args) {
        // данные для проверки
        Note note1 = new Note(1, 1, "1", "1", new Date());
        Note note2 = new Note(2, 2, "2", "2", new Date());
        Note note3 = new Note(3, 3, "3", "3", new Date());
        Note note4 = new Note(4, 4, "4", "4", new Date());
        Note note5 = new Note(5, 5, "5", "5", new Date());

        NotesController controller = new NotesController(
                new ConcreteNoteEditor(new NotesDbContext(new NotesDatabase()), new NotesConsolePresenter()));
        // добавляем заметки
        controller.routeAddNote(note1);
        controller.routeAddNote(note2);
        controller.routeAddNote(note3);
        controller.routeAddNote(note4);
        controller.routeAddNote(note5);

        // проверяем доабвление
        controller.routeGetAll();

        // удаляем заметку
        controller.routeRemoveNote(note3);

        // проверяем удаление
        controller.routeGetAll();

    }

}
