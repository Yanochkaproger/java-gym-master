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
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;
        Coach coach = (Coach) o;
        return surname.equals(coach.surname) &&
                name.equals(coach.name) &&
                middleName.equals(coach.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, middleName);
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + middleName;
    }
}
