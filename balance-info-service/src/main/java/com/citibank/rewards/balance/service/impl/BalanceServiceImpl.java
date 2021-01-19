package com.citibank.rewards.balance.service.impl;

import org.apache.log4j.Logger;

import com.citibank.rewards.balance.dao.BalanceDAO;
import com.citibank.rewards.balance.dao.impl.BalanceDAOImpl;
import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.BalanceDAORequest;
import com.citibank.rewards.balance.model.BalanceDAOResponse;
import com.citibank.rewards.balance.model.BalanceInfo;
import com.citibank.rewards.balance.model.BalanceRequest;
import com.citibank.rewards.balance.model.BalanceResponse;
import com.citibank.rewards.balance.model.StatusBlock;
import com.citibank.rewards.balance.service.BalanceService;

public class BalanceServiceImpl implements BalanceService{
	
	private Logger logger=Logger.getLogger(BalanceServiceImpl.class);

	public BalanceResponse getBalance(BalanceRequest request) throws BusinessException, SystemException {
		
		//prepare the dao request
		logger.info("In Service Layer, Request from Controller "+request);
		BalanceDAORequest daoReq = new BalanceDAORequest();
		daoReq.setCardNum(request.getCardNum());
		daoReq.setClientId(request.getClientId());
		
		BalanceDAO dao = new BalanceDAOImpl();
		BalanceDAOResponse daoResp = dao.getBalance(daoReq);
		
		//prepare the service response
		
		BalanceResponse response = new BalanceResponse();
		
		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setRespCode(daoResp.getRespCode());
		statusBlock.setRespMsg(daoResp.getRespMsg());
		
		BalanceInfo balanceInfo = new BalanceInfo();
		balanceInfo.setAvailablePts(daoResp.getAvailablePts());
		balanceInfo.setEarnedPts(daoResp.getEarnedPts());
		balanceInfo.setPendingPts(daoResp.getPendingPts());
		
		response.setStatusBlock(statusBlock );
		response.setBalanceInfo(balanceInfo);
		logger.info("In Service Layer, Response to Controller "+response);

		return response;
	}

}
