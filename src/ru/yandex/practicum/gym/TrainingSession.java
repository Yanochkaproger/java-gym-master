package ru.yandex.practicum.gym;

public class TrainingSession {
    private final Group group;
    private final Coach coach;
    private final DayOfWeek dayOfWeek;
    private final TimeOfDay timeOfDay;

    public TrainingSession(Group group, Coach coach, DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        this.group = group;
        this.coach = coach;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
    }

    public Group getGroup() {
        return this.group;
    }

    public Coach getCoach() {
        return this.coach;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public TimeOfDay getTimeOfDay() {
        return this.timeOfDay;
    }

    public String toString() {
        String var10000 = this.group.getTitle();
        return "Группа - " + var10000 + ", Тренер - " + String.valueOf(this.coach) + ", День - " + String.valueOf(this.dayOfWeek) + ", Время - " + String.valueOf(this.timeOfDay);
    }
}

