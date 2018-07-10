package com.core.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.util.LoggerHelper;

public abstract class FrameUpdateThread extends Thread
  implements IFrameThread
{
  private static final Logger logger = Logger.getLogger(FrameUpdateThread.class);

  private int interval = 0;
  private boolean isstop = false;
  private volatile ConcurrentLinkedQueue<IFrameTask> taskList = new ConcurrentLinkedQueue();

  public FrameUpdateThread(String name, int interval) {
    super(name);
    this.interval = interval;
  }

  public void addTask(IFrameTask task)
  {
    if (task == null) {
      return;
    }
    this.taskList.offer(task);
  }

  public void run()
  {
    long sleeptime = 0L;
    while (!this.isstop) {
      try {
        long now = System.currentTimeMillis();
        for (IFrameTask task = (IFrameTask)this.taskList.poll(); task != null; task = (IFrameTask)this.taskList.poll())
          try {
            task.doAction();
          } catch (Exception ex) {
            ex.printStackTrace();
            LoggerHelper.dealExceptionError(logger, ex);
          }
        try
        {
          update(now);
        } catch (Exception ex) {
          ex.printStackTrace();
          LoggerHelper.dealExceptionError(logger, ex);
        }

        long usetime = System.currentTimeMillis() - now;

        sleeptime += this.interval - usetime;
        if (sleeptime > 0L) {
          Thread.sleep(sleeptime);
          sleeptime = 0L;
        }
      } catch (Exception ex) {
        LoggerHelper.dealExceptionError(logger, ex);
      }
    }
    logger.error(getName() + "线程停止了" + this.isstop);
  }
  public void shutdown() {
    this.isstop = true;
    while (!this.taskList.isEmpty())
      ((IFrameTask)this.taskList.poll()).doAction();
  }

  public int getSize() {
    return this.taskList.size();
  }
  
  @SuppressWarnings("rawtypes")
  public static void main(String[] args) {
    int sleeptime = 0;
    
	ConcurrentLinkedQueue taskList = new ConcurrentLinkedQueue();
    while (true) {
      long now = System.currentTimeMillis();
      try {
        for (IFrameTask task = (IFrameTask)taskList.poll(); task != null; task = (IFrameTask)taskList.poll())
          task.doAction();
      }
      catch (Exception ex) {
        LoggerHelper.dealExceptionError(logger, ex);
      }

      long usetime = System.currentTimeMillis() - now;

      sleeptime = (int)(sleeptime + (2000L - usetime));
      if (sleeptime > 0) {
        logger.info(Integer.valueOf(sleeptime));
        try {
          Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        sleeptime = 0;
      }
    }
  }
}