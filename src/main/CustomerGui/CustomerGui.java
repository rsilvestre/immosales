/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.CustomerGui;

import identity.Customer;
import identity.Person;
import util.Observer;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 9/04/13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
public class CustomerGui {

	private Customer customer;
	private Person person;

	private ArrayList<Observer> liste = new ArrayList<Observer>();

	public CustomerGui() {

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public synchronized void addObserver(Observer observer) {
		if (!liste.contains(observer)) {
			liste.add(observer);
		}
	}

	public synchronized void removeObserver(Observer observer) {
		liste.remove(observer);
	}

	protected synchronized void notifierObservers() {
		for (int i = 0, c = liste.size();i<c;++i) {
			liste.get(i).update();
		}
	}

}
