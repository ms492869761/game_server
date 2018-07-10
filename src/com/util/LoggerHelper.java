package com.util;
import org.apache.log4j.Logger;

public class LoggerHelper
{
  public static void dealExceptionError(Logger logger, Exception e)
  {
    logger.error(e.getMessage(), e);
  }
  public static void dealExceptionError(Logger logger, Exception e, String content) {
    logger.error(content, e);
  }

  public static Logger getLogger()
  {
    StackTraceElement[] st = Thread.currentThread().getStackTrace();
    StackTraceElement stackTraceElement = st[(st.length - 1)];
    return Logger.getLogger(stackTraceElement.getClassName());
  }

  public static void main(String[] agrs) {
    getLogger();
    TimerTick createTick = TimerTick.createTick();

    System.out.println(createTick.formatString());
  }
}