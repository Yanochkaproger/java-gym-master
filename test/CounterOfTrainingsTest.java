import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CounterOfTrainingsTest {

    @Test
    void testCounterCreation() {
        Coach coach = new Coach("Иванов", "Иван", "Иванович");
        CounterOfTrainings counter = new CounterOfTrainings(coach, 5);
        assertEquals(coach, counter.getCoach());
        assertEquals(5, counter.getCount());
    }
}