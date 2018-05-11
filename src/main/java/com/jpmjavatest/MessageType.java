package com.jpmjavatest;

/**
 * 
 * @author maher
 *
 *         Enumeration of message types. We assume here : - messages of type-1 follow the format [PRODUCT at XXp] where XXp is the product value, PRODUCT is the
 *         product type - messages of type-2 follow the format [YY sales of PRODUCT at XXp each] where XXp is the product value, YY is number of products -
 *         messages of type-3 follow the format [PRODUCT at XXp].[OPERATION XXp PRODUCT], OPERATION can be add or substract or multiply
 */
public enum MessageType {

	MSG_TYPE_1, // apple at 10p
	MSG_TYPE_2, // 20 sales of apples at 10p each
	MSG_TYPE_3, // Add (or substract or multiply) 20p apples. Messages of type 3 contains also details of sale.

}
