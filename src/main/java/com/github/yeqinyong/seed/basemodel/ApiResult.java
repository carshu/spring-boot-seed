package com.github.yeqinyong.seed.basemodel;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * api 返回结果
 * @author 叶勤勇(卡虚)
 * @date 2019/12/05
 **/
@Data
@Accessors(chain = true)
public class ApiResult<T> {

	/**
	 * 返回值
	 */
	private T content;
	/**
	 * 操作是否成功
	 */
	private boolean success = true;

	/**
	 * 错误码
	 */
	private String code;

	/**
	 * 错误信息
	 */
	private String message;

	public static <T> ApiResult<T> success(T content) {
		return new ApiResult<T>().setContent(content).setSuccess(true);
	}

	public static <T> ApiResult<T> fail(String code, String message) {
		return new ApiResult<T>().setSuccess(true).setCode(code).setMessage(message);
	}
}
