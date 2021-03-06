package com.cg.paytm.dao;

import java.util.ArrayList;

import com.cg.paytm.bean.Customer;
import com.cg.paytm.bean.Trans;
import com.cg.paytm.exception.PaytmException;

public interface IPaytmDao {

	boolean createAccount(Customer customer);

	double deposit(double amount, Long phnNo);

	ArrayList<Trans> printTransactions();

	double fundTransfer(double amount, Long phnNo, Long transPhnNo) throws PaytmException;

	double withDraw(double amount, Long phnNo);

	double showBalance(Long phnNo);

}
