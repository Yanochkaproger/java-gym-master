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

    @Override
    public String toString() {
        return "Группа - " + this.group.getTitle() +
                ", Тренер - " + this.coach +
                ", День - " + this.dayOfWeek +
                ", Время - " + this.timeOfDay;
    }
}

