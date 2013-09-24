package woofer.service;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Everything that should be done at Spring initialization
 * should be put in this class.
 */

@Lazy(false)
@Service
public class StartupService {
    
    private static final Logger logger =
            Logger.getLogger(StartupService.class);
    private String defaultTimeZoneName;
    
    public StartupService() {
        
    }
    
    public String getDefaultTimeZoneName() {
        return defaultTimeZoneName;
    }
    
    public void setDefaultTimeZoneName(String defaultTimeZoneName) {
        this.defaultTimeZoneName = defaultTimeZoneName;
    }
    
    @PostConstruct
    public void postConstruct() {
        logger.trace("Setting TimeZone to: " + defaultTimeZoneName);
        TimeZone.setDefault(TimeZone.getTimeZone(
                defaultTimeZoneName));
    }
    
}
