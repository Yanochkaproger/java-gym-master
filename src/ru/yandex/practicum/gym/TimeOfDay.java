package ru.yandex.practicum.gym;

import java.util.Objects;

public class TimeOfDay implements Comparable<TimeOfDay> {
    private final int hours;
    private final int minutes;

    public TimeOfDay(int hours, int minutes) {
        if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59) {
            this.hours = hours;
            this.minutes = minutes;
        } else {
            throw new IllegalArgumentException("Invalid time");
        }
    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int compareTo(TimeOfDay other) {
        return this.hours != other.hours ? Integer.compare(this.hours, other.hours) : Integer.compare(this.minutes, other.minutes);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof TimeOfDay)) {
            return false;
        } else {
            TimeOfDay that = (TimeOfDay)o;
            return this.hours == that.hours && this.minutes == that.minutes;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.hours, this.minutes});
    }

    public String toString() {
        return String.format("%02d:%02d", this.hours, this.minutes);
    }
}

