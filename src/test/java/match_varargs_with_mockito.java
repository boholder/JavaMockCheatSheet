import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class match_varargs_with_mockito {
    @Mock
    Dependence mockDependence;
    @InjectMocks
    Testee testee;

    @Test
    public void old() {
        // in older JDK? IntelliJ? mockito? You must write like this, explicitly.
        // or the IDE(IntelliJ) will mark a syntax error on it says it's ambiguous.
        given(mockDependence.addAll(anyInt(), ArgumentMatchers.<Integer>any())).willReturn(0);
        assert testee.callingDependenceToAddAll(1, 2, 3, 4) == 0;
    }

    @Test
    public void new1() {
        // in new version of libraries you can simply write like this.
        given(mockDependence.addAll(anyInt(), any())).willReturn(0);
        assert testee.callingDependenceToAddAll(1, 2, 3, 4) == 0;
    }

    @Test
    public void new2() {
        // or this.
        given(mockDependence.addAll(anyInt(), any(Integer.class))).willReturn(0);
        assertThat(testee.callingDependenceToAddAll(1, 2, 3, 4), is(0));
    }
}
