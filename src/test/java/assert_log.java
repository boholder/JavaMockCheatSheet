import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

@ExtendWith(MockitoExtension.class)
public class assert_log {
    /**
     * Change to your logging library's appender.
     * Here is logback library.
     */
    @Mock
    Appender<ILoggingEvent> mockAppender;
    @InjectMocks
    Testee testee;
    @Captor
    ArgumentCaptor<ILoggingEvent> loggingEventArgumentCaptor;

    @BeforeEach
    void init() {
        // The "@Slf4j" will inject a logger into Testee with the name "log"
        Logger logger = (Logger) ReflectionTestUtils.getField(testee, "log");
        Objects.requireNonNull(logger).addAppender(mockAppender);
        // You don't want test cases affect each others.
        clearInvocations(mockAppender);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void t() {
        testee.printLogs();
        // the logger will call the "doAppend" method of appenders that registered on it.
        verify(mockAppender, atLeastOnce()).doAppend(loggingEventArgumentCaptor.capture());
        List<String> capturedLog = loggingEventArgumentCaptor.getAllValues().stream()
                .map(ILoggingEvent::getFormattedMessage)
                .collect(Collectors.toList());
        assertThat(capturedLog, hasItems(
                Stream.of("log-one", "log-another")
                        .map(Matchers::containsString)
                        .toArray(Matcher[]::new)
        ));
    }
}
