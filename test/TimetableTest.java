import org.junit.jupiter.api.Test;
import ru.yandex.practicum.gym.*;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession session = new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        timetable.addNewTrainingSession(session);

        List<TrainingSession> mondays = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertEquals(1, mondays.size());
        assertEquals(session, mondays.get(0));

        assertTrue(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);

        timetable.addNewTrainingSession(new TrainingSession(groupAdult, coach, DayOfWeek.THURSDAY, new TimeOfDay(20, 0)));
        timetable.addNewTrainingSession(new TrainingSession(groupChild, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(groupChild, coach, DayOfWeek.THURSDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(groupChild, coach, DayOfWeek.SATURDAY, new TimeOfDay(10, 0)));

        assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        List<TrainingSession> thursdays = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        assertEquals(2, thursdays.size());
        assertEquals("13:00", thursdays.get(0).getTimeOfDay().toString());
        assertEquals("20:00", thursdays.get(1).getTimeOfDay().toString());
        assertTrue(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession session = new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        timetable.addNewTrainingSession(session);

        List<TrainingSession> at13 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        assertEquals(1, at13.size());
        assertEquals(session, at13.get(0));

        assertTrue(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0)).isEmpty());
    }

    @Test
    void testMultipleSessionsSameTime() {
        Timetable timetable = new Timetable();
        Group g1 = new Group("Группа 1", Age.CHILD, 60);
        Group g2 = new Group("Группа 2", Age.ADULT, 90);
        Coach c1 = new Coach("Иванов", "Иван", "Иванович");
        Coach c2 = new Coach("Петров", "Пётр", "Петрович");

        TrainingSession s1 = new TrainingSession(g1, c1, DayOfWeek.MONDAY, new TimeOfDay(10, 0));
        TrainingSession s2 = new TrainingSession(g2, c2, DayOfWeek.MONDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(s1);
        timetable.addNewTrainingSession(s2);

        List<TrainingSession> sessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(10, 0));
        assertEquals(2, sessions.size());
    }

    @Test
    void testGetCountByCoaches() {
        Timetable timetable = new Timetable();
        Coach c1 = new Coach("Иванов", "Иван", "Иванович");
        Coach c2 = new Coach("Петров", "Пётр", "Петрович");
        Group g = new Group("Общая", Age.ADULT, 60);

        timetable.addNewTrainingSession(new TrainingSession(g, c1, DayOfWeek.MONDAY, new TimeOfDay(9, 0)));
        timetable.addNewTrainingSession(new TrainingSession(g, c1, DayOfWeek.TUESDAY, new TimeOfDay(9, 0)));
        timetable.addNewTrainingSession(new TrainingSession(g, c2, DayOfWeek.MONDAY, new TimeOfDay(10, 0)));

        List<CounterOfTrainings> counts = timetable.getCountByCoaches();
        assertEquals(2, counts.size());
        assertEquals(2, counts.get(0).getCount());
        assertEquals(1, counts.get(1).getCount());
        assertEquals(c1, counts.get(0).getCoach());
    }
}