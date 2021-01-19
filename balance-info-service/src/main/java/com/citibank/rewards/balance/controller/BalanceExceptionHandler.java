package com.citibank.rewards.balance.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.citibank.rewards.balance.exception.BalanceRequestInvalidDataException;
import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.ErrorResponse;
import com.citibank.rewards.balance.model.StatusBlock;

@RestControllerAdvice
public class BalanceExceptionHandler {

	private Logger logger = Logger.getLogger(BalanceExceptionHandler.class);
	// org.springframework.web.bind.ServletRequestBindingException
	// BalanceRequestInvalidDataException

	@ExceptionHandler(value = BalanceRequestInvalidDataException.class)
	public ErrorResponse handleRequestInvalidException(BalanceRequestInvalidDataException exception) {

		logger.error("BalanceRequestInvalidDataException occured ", exception);
		ErrorResponse response = buildErrorResp(exception.getRespCode(), exception.getRespMsg());

		return response;
	}

	@ExceptionHandler(value = BusinessException.class)
	public ErrorResponse handleDataErrors(BusinessException exception) {

		logger.error("BusinessException occured ", exception);
		ErrorResponse response = buildErrorResp(exception.getRespCode(), exception.getRespMsg());

		return response;
	}

	@ExceptionHandler(value = SystemException.class)
	public ErrorResponse handleSystemErrors(SystemException exception) {

		logger.error("SystemException occured ", exception);
		ErrorResponse response = buildErrorResp(exception.getRespCode(), exception.getRespMsg());

		return response;
	}

	@ExceptionHandler(value = Exception.class)
	public ErrorResponse handleGenericErrors(Exception exception) {

		// exception.printStackTrace();
		logger.fatal("Exception occured ", exception);

		ErrorResponse response = buildErrorResp("22222", "unknown error from database:" + exception.getMessage());

		return response;
	}

	private ErrorResponse buildErrorResp(String respCode, String respMsg) {
		ErrorResponse response = new ErrorResponse();
		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setRespCode(respCode);
		statusBlock.setRespMsg(respMsg);
		response.setStatusBlock(statusBlock);
		return response;
	}

}
