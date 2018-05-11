package com.jpmjavatest;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Hello world!
 *
 */
public class MessageProcessor {

	private final static int STAT_REPORT_POINT = 10;
	private final static int PROCESSING_PAUSE_POINT = 50;
	private static boolean continueProcessing = true;
	private static long messageSeq = 0;

	// input messages
	public static Queue<Message> inputMessages = new LinkedList<Message>();

	public static void processMessage() {

		// poll message
		Message msg = inputMessages.poll();

		if (null == msg) { // we assule msg content is valid
			throw new InvalidParameterException();
		}
		Sale s = new Sale();
		String msgAdujsment = "";

		switch (msg.getMsgType()) {
		case MSG_TYPE_1:
			// [PRODUCT at XXp]
			String[] content = msg.getMsgContent().split(" ");
			s.setProductType(content[0]);
			s.setProductValue(Integer.valueOf(content[2].substring(0, content[2].length() - 1)));
			s.setNumberOfOccurences(1);
			break;
		case MSG_TYPE_2:
			// [YY sales of PRODUCT at XXp each]
			content = msg.getMsgContent().split(" ");
			s.setNumberOfOccurences(Integer.valueOf(content[0]));
			s.setProductType(content[3]);
			s.setProductValue(Integer.valueOf(content[5].substring(0, content[5].length() - 1)));
			break;
		case MSG_TYPE_3:
			// [PRODUCT at XXp].[OPERATION XXp PRODUCT]
			String[] msgParts = msg.getMsgContent().split("\\.");
			String msgDetails = msgParts[0];
			msgAdujsment = msgParts[1];

			content = msgDetails.trim().split(" ");
			s.setProductType(content[0]);
			s.setProductValue(Integer.valueOf(content[2].substring(0, content[2].length() - 1)));
			s.setNumberOfOccurences(1);

			content = msgAdujsment.trim().split(" ");
			char operation = '+';
			if (content[0].toLowerCase().equals("add")) {
				operation = '+';
			} else if (content[0].toLowerCase().equals("multiply")) {
				operation = '*';
			} else if (content[0].toLowerCase().equals("substruct")) {
				operation = '-';
			}
			Integer valueAdjustment = Integer.valueOf(content[1].substring(0, content[1].length() - 1));
			String productType = content[2]; // should be the same as the first part of the message (same product type for every message)

			/**
			 * update product values upon adjusment
			 */
			AllSales.adjustSales(productType, valueAdjustment, operation);

			break;
		default:
			throw new InvalidParameterException();
		}

		/**
		 * update stat on product sales
		 */
		// new sale entry is prepared
		int index = AllSales.getProductIndex(s.getProductType());
		if (index < 0) {

			StatProduct stPro = new StatProduct();
			stPro.setProductType(s.getProductType());
			stPro.setNumberOfSales(1);
			if (!msgAdujsment.isEmpty()) {
				stPro.setAdjusmentsHistory(stPro.getAdjusmentsHistory().concat(msgAdujsment));
			}
			AllSales.products.add(stPro);
		} else {
			// product is already inserted, increment nb of sales
			StatProduct stPro = AllSales.products.get(index);
			stPro.setNumberOfSales(stPro.getNumberOfSales() + 1);

			if (!msgAdujsment.isEmpty()) {
				stPro.setAdjusmentsHistory(stPro.getAdjusmentsHistory().concat(";" + msgAdujsment));
			}
			AllSales.products.set(index, stPro);
		}

	}

	public static void main(String[] args) {

		System.out.println("JPM Java Test, By Maher L.");

		ExternalMessageSender messageSender = new ExternalMessageSender();

		// create messages :
		for (int i = 0; i <= 52; i++) {
			Message msg = messageSender.createMessageRandom();
			// add message
			messageSender.sendMessage(msg);

			// process all messages
			while (!inputMessages.isEmpty()) {
				processMessage();
			}
		}

	}

	/**
	 * 
	 * @param msg
	 * @return
	 */
	public static boolean addMessage(Message msg) {
		if (!continueProcessing)
			return false;

		// add message
		inputMessages.add(msg);
		messageSeq++;

		// report stat on 10th message
		if (messageSeq % STAT_REPORT_POINT == 0) {
			AllSales.reportSales();
		}

		// stop receiving messages after 50 messages and report sales
		if (messageSeq == PROCESSING_PAUSE_POINT) {
			System.out.println("Application is in Pause. No more messages Accepted");
			AllSales.reportSalesOnStop();
			continueProcessing = false;
		}

		return true;
	}
}
