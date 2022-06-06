import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class test_an_exception_thrown {

    @Test
    public void t() {
        RuntimeException captured = assertThrows(RuntimeException.class,
                () -> new Testee().throwException());
        assert captured.getMessage().equals("test");
    }
}
