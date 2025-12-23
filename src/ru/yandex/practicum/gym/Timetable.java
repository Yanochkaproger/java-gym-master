package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {
    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap();

    public void addNewTrainingSession(TrainingSession session) {
        ((List)((TreeMap)this.timetable.computeIfAbsent(session.getDayOfWeek(), (k) -> new TreeMap())).computeIfAbsent(session.getTimeOfDay(), (k) -> new ArrayList())).add(session);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = (TreeMap)this.timetable.get(dayOfWeek);
        if (dayMap != null && !dayMap.isEmpty()) {
            List<TrainingSession> result = new ArrayList();

            for(List<TrainingSession> sessions : dayMap.values()) {
                result.addAll(sessions);
            }

            return result;
        } else {
            return Collections.emptyList();
        }
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = (TreeMap)this.timetable.get(dayOfWeek);
        return dayMap == null ? Collections.emptyList() : (List)dayMap.getOrDefault(timeOfDay, Collections.emptyList());
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Integer> counts = new HashMap();

        for(TreeMap<TimeOfDay, List<TrainingSession>> dayMap : this.timetable.values()) {
            for(List<TrainingSession> sessions : dayMap.values()) {
                for(TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    counts.put(coach, (Integer)counts.getOrDefault(coach, 0) + 1);
                }
            }
        }

        return counts.entrySet().stream().map((entry) -> new CounterOfTrainings((Coach)entry.getKey(), (Integer)entry.getValue())).sorted((a, b) -> Integer.compare(b.getCount(), a.getCount())).toList();
    }
}

