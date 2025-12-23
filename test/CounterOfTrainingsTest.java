import org.junit.Test;
import ru.yandex.practicum.gym.Coach;
import ru.yandex.practicum.gym.CounterOfTrainings;

import static org.junit.Assert.assertEquals;

public class CounterOfTrainingsTest {

    @Test
    public void testCounterCreation() {
        Coach coach = new Coach("Иванов", "Иван", "Иванович");
        CounterOfTrainings counter = new CounterOfTrainings(coach, 5);
        assertEquals(coach, counter.getCoach());
        assertEquals(5, counter.getCount());
    }
}