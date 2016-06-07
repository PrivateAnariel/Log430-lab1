package edu.gordon.atm.transaction;

import edu.gordon.atm.Session;
import edu.gordon.atm.physical.CustomerConsole;
import edu.gordon.atm.physical.Keyboard;
import edu.gordon.banking.Card;



/** Create a transaction of an appropriate type by asking the customer
 *  what type of transaction is desired and then returning a newly-created
 *  member of the appropriate subclass
 *
 *  @param edu.gordon.atm the ATM used to communicate with customer
 *  @param session the session in which this transaction is being performed
 *  @param card the customer's card
 *  @param pin the PIN entered by the customer
 *  @return a newly created Transaction object of the appropriate type
 *  @exception CustomerConsole.Cancelled if the customer presses cancel instead
 *         of choosing a transaction type
 */
public class TransactionFactory {
	public Transaction makeTransaction(Session session,Card card, int pin) throws Keyboard.Cancelled {
		int choice = session.getAtm().getKeyboard().readMenuChoice("Please choose transaction type", TRANSACTION_TYPES_MENU);
        if(choice == 0)
            return new Withdrawal(session, card, pin);
        else if(choice == 1)
        	return new Deposit(session, card, pin);
        else if(choice == 2)   
        	return new Transfer(session, card, pin);
        else if(choice == 3)    
        	return new Inquiry(session, card, pin);
        return null;
	}
	
	// List of available transaction types to display as a menu
    private static final String [] TRANSACTION_TYPES_MENU = 
        { "Withdrawal", "Deposit", "Transfer", "Balance Inquiry" };
}
