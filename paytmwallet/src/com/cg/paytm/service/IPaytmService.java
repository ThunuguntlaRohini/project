package com.cg.paytm.service;

import java.util.ArrayList;

import com.cg.paytm.bean.Customer;
import com.cg.paytm.bean.Trans;
import com.cg.paytm.exception.PaytmException;

public interface IPaytmService {

	boolean createAccount(Customer customer) throws PaytmException;

	double deposit(double amount, Long phnNo);

	double showBalance(Long phnNo);

	ArrayList<Trans> printTransactions();

	public boolean validate(Customer customer) throws PaytmException;

	double withDraw(double amount, Long phnNo);

	double fundTransfer(double amount, Long phnNo, Long transPhnNo) throws PaytmException;

}
