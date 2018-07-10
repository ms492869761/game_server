package com.game.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.core.net.service.HttpMessageExcuteService;

/**
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {


	/**
	 * Session�����¼�
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
    	
    }

	/**
	 * Session����¼�
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
    	HttpSession session = arg0.getSession();
    	HttpMessageExcuteService.getInstance().destroyedMessage(session);
    }
	
}
