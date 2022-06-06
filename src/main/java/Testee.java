import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

@Slf4j
public class Testee {
    @Resource
    Dependence dependence;
    @Value("${from.config.file:config}")
    String configSetBySpring;

    public void throwException() {
        throw new RuntimeException("test");
    }

    public int callingStaticMethodToAddOne(int i) {
        return Dependence.addOne(i);
    }

    public void printLogs() {
        log.info("log-{}", "one");
        log.info("log-{}", "another");
    }

    private int privateMethodAddOne(int i) {
        return i + 1;
    }
}
