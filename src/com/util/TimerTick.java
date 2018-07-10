package com.util;

public class TimerTick
{
  private String tickName;
  private long startTime;

  private TimerTick(String tickName, long startTime)
  {
    this.tickName = tickName;
    this.startTime = startTime;
  }

  public static TimerTick createTick(String tickName)
  {
    return new TimerTick(tickName, System.currentTimeMillis());
  }

  public static TimerTick createTick(long startTime) {
    return new TimerTick("TICK", startTime);
  }

  public static TimerTick createTick() {
    return new TimerTick("TICK", System.currentTimeMillis());
  }

  public static TimerTick createTick(String tickName, long startTimer) {
    return new TimerTick(tickName, startTimer);
  }

  public long tickNow()
  {
    return System.currentTimeMillis() - this.startTime;
  }

  public long resetStartTime()
  {
    return this.startTime = System.currentTimeMillis();
  }

  public String formatString() {
    return String.format("%s耗时%sms", new Object[] { this.tickName, Long.valueOf(tickNow()) });
  }
  public static void main(String[] agrs) {
    try {
      Thread.sleep(20L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}