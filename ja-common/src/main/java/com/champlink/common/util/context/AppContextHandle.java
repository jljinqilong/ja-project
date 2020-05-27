package com.champlink.common.util.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContextHandle implements ApplicationContextAware {

    /** {@inheritDoc} */

    public void setApplicationContext(ApplicationContext app) {
        ContextUtils.setApplicationContext(app);
    }

}
