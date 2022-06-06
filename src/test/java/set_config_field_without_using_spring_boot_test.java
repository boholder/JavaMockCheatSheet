import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


/**
 * The "@SpringBootTest" is good,
 * but initializing whole context is wasting time when developing on big projects.
 * <p>
 * Yep, we can narrow the context range by creating custom configuration,
 * but it's sort of inconvenient.
 * <p>
 * Why not try out the fancy
 * org.springframework.test.util.ReflectionTestUtils;
 * when you just don't want your testee's config fields remaining null while testing?
 */
@ExtendWith(MockitoExtension.class)
public class set_config_field_without_using_spring_boot_test {
    @InjectMocks
    Testee testee;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(testee, "configSetBySpring", "done");
    }

    @Test
    public void t() {
        assertThat(testee.configSetBySpring, equalTo("done"));
    }

    @Test
    @DisplayName("and you can invoking private methods by this util")
    public void t2() {
        assertThat(ReflectionTestUtils.invokeMethod(testee, "privateMethodAddOne", 1),
                is(2));
    }
}
