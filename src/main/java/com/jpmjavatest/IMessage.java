package com.jpmjavatest;

/**
 * 
 * @author maher
 *
 *         Message Interface
 */

public interface IMessage {

	/**
	 * Send message method
	 * 
	 * @param type
	 * @param message
	 * @return boolean. True if message is well received, false otherwise
	 */
	boolean sendMessage(Message msg);
}
