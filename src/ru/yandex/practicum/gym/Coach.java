package ru.yandex.practicum.gym;

import java.util.Objects;

public class Coach {
    private final String surname;
    private final String name;
    private final String middleName;

    public Coach(String surname, String name, String middleName) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Coach)) {
            return false;
        } else {
            Coach coach = (Coach) o;
            return this.surname.equals(coach.surname) && this.name.equals(coach.name) && this.middleName.equals(coach.middleName);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.surname, this.name, this.middleName);
    }

    @Override
    public String toString() {
        return this.surname + " " + this.name + " " + this.middleName;
    }
}