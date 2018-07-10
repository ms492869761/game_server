package com.game.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.game.GameServer;

/**
 * Application Lifecycle Listener implementation class ServletContextListener
 *
 */
@WebListener
public class ServletContextListenerImpl implements javax.servlet.ServletContextListener {

	/**
     * @see ServletContextListenerImpl#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	GameServer instance = GameServer.getInstance();
    	instance.start();
    }

	/**
     * @see ServletContextListenerImpl#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	GameServer.getInstance().stop();
    }
	
}
