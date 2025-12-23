import java.util.*;

public class Timetable {
    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession session) {
        timetable.computeIfAbsent(session.getDayOfWeek(), k -> new TreeMap<>())
                .computeIfAbsent(session.getTimeOfDay(), k -> new ArrayList<>())
                .add(session);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(dayOfWeek);
        if (dayMap == null || dayMap.isEmpty()) {
            return Collections.emptyList();
        }
        List<TrainingSession> result = new ArrayList<>();
        for (List<TrainingSession> sessions : dayMap.values()) {
            result.addAll(sessions);
        }
        return result; // уже отсортировано благодаря TreeMap
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(dayOfWeek);
        if (dayMap == null) {
            return Collections.emptyList();
        }
        return dayMap.getOrDefault(timeOfDay, Collections.emptyList());
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Integer> counts = new HashMap<>();
        for (TreeMap<TimeOfDay, List<TrainingSession>> dayMap : timetable.values()) {
            for (List<TrainingSession> sessions : dayMap.values()) {
                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    counts.put(coach, counts.getOrDefault(coach, 0) + 1);
                }
            }
        }

        return counts.entrySet().stream()
                .map(entry -> new CounterOfTrainings(entry.getKey(), entry.getValue()))
                .sorted((a, b) -> Integer.compare(b.getCount(), a.getCount())) // по убыванию
                .toList();
    }
}



