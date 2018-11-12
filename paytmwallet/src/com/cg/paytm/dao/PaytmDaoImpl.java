package com.cg.paytm.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.cg.paytm.bean.Customer;
import com.cg.paytm.bean.Trans;
import com.cg.paytm.db.Database;
import com.cg.paytm.exception.PaytmException;

public class PaytmDaoImpl implements IPaytmDao {

	static ArrayList<Trans> transList = new ArrayList<>();


	public boolean createAccount(Customer customer1) {

		return Database.addAccount(customer1);
	}

	@Override
	public double deposit(double amount, Long phnNo) {
		Customer customer = Database.validatePhnNo(phnNo);
		double balance=0;
		if (customer!=null) {
			customer.setBalance(customer.getBalance() + amount);
			Trans trans = new Trans();
			trans.setAmount(amount);
			trans.setTransType("Deposit");
			trans.setDate(LocalDate.now());
			trans.setTime(LocalTime.now());
			trans.setTo("self");
			transList.add(trans);
			balance=customer.getBalance();
		}
		return balance;
	}

	@Override
	public double withDraw(double amount, Long phnNo) {
		Customer customer = Database.validatePhnNo(phnNo);
		double balance=0;
		if (customer!=null) {
			if (amount < customer.getBalance() - amount) {
				customer.setBalance(customer.getBalance() - amount);
				Trans trans = new Trans();
				trans.setAmount(amount);
				trans.setTransType("Withdraw");
				trans.setDate(LocalDate.now());
				trans.setTime(LocalTime.now());
				transList.add(trans);
				balance = customer.getBalance();
			}
		}
		return balance;
	}

	@Override
	public double fundTransfer(double amount, Long phnNo, Long transPhnNo) throws PaytmException {
		Customer customerBean1 = Database.validatePhnNo(phnNo);
		double balance = 0;
		if (customerBean1!=null) {
			Customer customerBean2	 = Database.validatePhnNo(transPhnNo);
			if (customerBean2!=null) {
				if (amount < customerBean1.getBalance() - amount) {
					customerBean1.setBalance(customerBean1.getBalance() - amount);
					Trans trans = new Trans();
					trans.setAmount(amount);
					trans.setTransType("Funds transfer");
					trans.setDate(LocalDate.now());
					trans.setTime(LocalTime.now());
					trans.setTo(transPhnNo.toString());
					transList.add(trans);
					balance = customerBean1.getBalance();
					
				}
			} else {
				throw new PaytmException("invalid phn number");
			}
		}
		return balance;

	}

	@Override
	public double showBalance(Long phnNo) {
		
		Customer customer = Database.validatePhnNo(phnNo);
		return customer.getBalance();
	}

	@Override
	public ArrayList<Trans> printTransactions() {

		return transList;
	}

}
