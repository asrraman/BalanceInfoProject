package com.citibank.rewards.balance.dao.impl;

import org.apache.log4j.Logger;

import com.citibank.rewards.balance.dao.BalanceDAO;
import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.BalanceDAORequest;
import com.citibank.rewards.balance.model.BalanceDAOResponse;

public class BalanceDAOImpl implements BalanceDAO {

	private static Logger logger = Logger.getLogger(BalanceDAOImpl.class);

	public BalanceDAOResponse getBalance(BalanceDAORequest daoReq) throws BusinessException, SystemException {

		logger.info("Entered into DAO Layer , Request from Service Layer" + daoReq);

/*		String env = System.getProperty("env");
		String fileName = "properties/balance-" + env + "-db.properties";

		logger.info("env : " + env + " fileName :" + fileName);
*/
		BalanceDAOResponse daoResponse = new BalanceDAOResponse();
		try {
/*			Class.forName("com.mysql.jdbc.Driver");
			InputStream input = BalanceDAOImpl.class.getClassLoader().getResourceAsStream(fileName);

			Properties properties = new Properties();

			properties.load(input);

			String url = properties.getProperty("db-url");
			String uname = properties.getProperty("username");
			String pwd = properties.getProperty("password");

			Connection connection = DriverManager.getConnection(url, uname, pwd);
			String sql = "{call GetBalance(?,?,?,?)}";
			// csmt object
			CallableStatement cs = connection.prepareCall(sql);
			// prepare the input params
			cs.setString(1, daoReq.getClientId());
			cs.setString(2, daoReq.getCardNum());

			// register out params
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);

			// call DB and get the result set

			cs.execute();
			ResultSet rs = cs.executeQuery();

			String dbRespCode = cs.getString(3); // get it from backend
			String dbRespMsg = cs.getString(4);
*/
			daoResponse.setRespCode("000");
			daoResponse.setRespMsg("SUCCESS");

			if ("000".equals("000")) {
				// prepare the dao response. i.e convert ResultSet response into dao response
			//	while (rs.next()) {
					daoResponse.setAvailablePts("100");
					daoResponse.setEarnedPts("100");
					daoResponse.setPendingPts("100");
				//}

			} 
		} catch (Exception e) {
			logger.fatal("Exception in DAO Layer ", e);
			// e.printStackTrace();
			// TODO : print error log
			throw new SystemException("1111", "unknown error from database");
		}
		logger.info("Exit from DAO Layer, Response to Service Layer" + daoResponse);
		return daoResponse;
	}

	public static void main(String[] args) throws BusinessException, SystemException {

		BalanceDAO dao = new BalanceDAOImpl();

		BalanceDAORequest daoReq = new BalanceDAORequest();
		daoReq.setClientId("web");
		daoReq.setCardNum("05211140058239");
		BalanceDAOResponse daoResp = dao.getBalance(daoReq);
		logger.info("daoResp is :" + daoResp);
	}

}
