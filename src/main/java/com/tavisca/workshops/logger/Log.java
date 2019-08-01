package com.tavisca.workshops.logger;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {

    public static Logger getLogger(){
         LogManager logManager = LogManager.getLogManager();
         java.util.logging.Logger log = logManager.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
         return log;
    }
}
