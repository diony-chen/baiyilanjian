package com.diony.shopping.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: TODO
 * @author diony_chen
 * @date 2021/3/30 11:22
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {

	/**
	 *	执行成功
	 */
	SUCCESS(200, "执行成功"),
	/**
	 *
	 */
	FAIL(9999, "服务异常"),

	/**
	 *	参数错误
	 */
	PARAM_ERROR(0100, "参数错误"),

	/**
	 *	端口被占用
	 */
	BIN_ERROR(0101, "端口被占用");

	/**
	 * 错误编码
	 */
	private Integer code;

	/**
	 * 错误描述
	 */
	private String msg;
}
