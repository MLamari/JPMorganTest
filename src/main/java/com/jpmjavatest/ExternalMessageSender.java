package com.jpmjavatest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ExternalMessageSender implements IMessage {

	private static final Random RANDOM = new Random();

	private static final List<MessageType> msgTypes = Collections.unmodifiableList(Arrays.asList(MessageType.values()));
	private static final int SIZE = msgTypes.size();

	private static final List<Character> productTypesCons = Collections.unmodifiableList(Arrays.asList('A', 'B', 'C', 'D', 'E'));
	private static final int SIZE_PRODUCTNAMES = productTypesCons.size();

	public static MessageType randomType() {
		return msgTypes.get(RANDOM.nextInt(SIZE));
	}

	public static String randamProductType() {

		StringBuilder productType = new StringBuilder();
		int len = RANDOM.nextInt(10); // length max of product type
		while (len >= 0) {

			productType.append(productTypesCons.get(RANDOM.nextInt(SIZE_PRODUCTNAMES)));
			len--;
		}
		return productType.toString();

	}

	public boolean sendMessage(Message msg) {

		return MessageProcessor.addMessage(msg);

	}

	public Message createMessageRandom() {

		Message msg = new Message();
		MessageType msgType = randomType();
		msg.setMsgType(msgType);
		String msgContent = "";
		Integer productValue = RANDOM.nextInt(100);
		Integer numberOccur = RANDOM.nextInt(100);
		Integer adjustValue = RANDOM.nextInt(100);
		int op = RANDOM.nextInt(3);
		String operation = "";
		switch (op) {
		case 0:
			operation = "Add";
			break;
		case 1:
			operation = "Multiply";
			break;
		case 2:
			operation = "Substruct";
			break;
		}

		String productType = randamProductType();

		switch (msgType) {
		case MSG_TYPE_1:

			// [PRODUCT at XXp]
			msgContent = String.format("%s at %dp", productType, productValue);
			break;
		case MSG_TYPE_2:
			// [YY sales of PRODUCT at XXp each]
			msgContent = String.format("%d sales of %s at %dp each", numberOccur, productType, productValue);
			break;
		case MSG_TYPE_3:
			// [PRODUCT at XXp].[OPERATION XXp PRODUCT]
			msgContent = String.format("%s at %dp. %s %dp %s", productType, productValue, operation, adjustValue, productType);
			break;

		}
		msg.setMsgContent(msgContent);
		System.out.println("New Random message : " + msgContent);
		return msg;
	}

}
