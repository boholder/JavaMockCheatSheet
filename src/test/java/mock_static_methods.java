import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * Remember to add "mockito-inline" as dependency,
 * and exclude "mockito-core" from "spring-boot-starter-test".
 */
@ExtendWith(MockitoExtension.class)
public class mock_static_methods {
    Testee testee = new Testee();

    @Test
    public void t() {
        assertThat(testee.callingStaticMethodToAddOne(1), is(2));

        try (MockedStatic<Dependence> mockDependence = Mockito.mockStatic(Dependence.class)) {
            mockDependence.when(() -> Dependence.addOne(anyInt())).thenReturn(0);
            assertThat(testee.callingStaticMethodToAddOne(1), is(0));
        }
    }
}
