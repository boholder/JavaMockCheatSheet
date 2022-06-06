import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class how_to_test_an_exception_thrown {

    @Test
    public void t() {
        RuntimeException captured = assertThrows(RuntimeException.class,
                () -> new Testee().throwException());
        assertThat(captured.getMessage(), is("test"));
    }
}
