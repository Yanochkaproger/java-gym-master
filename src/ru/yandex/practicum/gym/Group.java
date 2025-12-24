package ru.yandex.practicum.gym;

public class Group {
    private final String title;
    private final Age age;
    private final int duration;

    public Group(String title, Age age, int duration) {
        this.title = title;
        this.age = age;
        this.duration = duration;
    }

    public String getTitle() {
        return this.title;
    }

    public Age getAge() {
        return this.age;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "Group{title='" + this.title + "', age=" + this.age + ", duration=" + this.duration + "}";
    }
}
