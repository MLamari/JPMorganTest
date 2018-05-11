package com.jpmjavatest;

/**
 * 
 * @author maher
 *
 *         Class of the message notifying a sale
 *
 */
public class Message {

	private MessageType msgType;
	private String msgContent;

	/**
	 * @return the msgType
	 */
	public MessageType getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType
	 *            the msgType to set
	 */
	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return the msgContent
	 */
	public String getMsgContent() {
		return msgContent;
	}

	/**
	 * @param msgContent
	 *            the msgContent to set
	 */
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

}
