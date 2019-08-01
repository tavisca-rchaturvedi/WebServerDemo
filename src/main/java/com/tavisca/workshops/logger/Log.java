package com.tavisca.workshops.logger;

import java.util.logging.Level;
import java.util.logging.LogManager;

public class Log {

    public static void infoLog(String info){
         LogManager logManager = LogManager.getLogManager();
         java.util.logging.Logger log = logManager.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
         log.log(Level.INFO, info);
    }

    public static void warningLog(String warning){
        LogManager logManager = LogManager.getLogManager();
        java.util.logging.Logger log = logManager.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        log.log(Level.WARNING, warning);
    }
}
