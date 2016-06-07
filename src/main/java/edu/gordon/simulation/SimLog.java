package edu.gordon.simulation;

import edu.gordon.atm.physical.Log;
import edu.gordon.banking.Message;
import edu.gordon.banking.Money;
import edu.gordon.banking.Status;

public class SimLog implements Log{

	public SimLog() {
		// TODO Auto-generated constructor stub
	}
    
	/** Log the sending of a message to the bank
	 *
	 *  @param message the message to be logged
	 */
	public void logSend(Message message)
	{
		 Simulation.getInstance().printLogLine("Message:	" + message.toString()); 
	}
	
	/** Log a response received from a message
	 *
	 *  @param status the status object returned by the bank in response
	 */
	public void logResponse(Status response)
	{
		 Simulation.getInstance().printLogLine("Response:  " + response.toString());
	}
	
	/** Log the dispensing of cash by the cash dispenser
	 *
	 *  @param amount the amount of cash being dispensed
	 */
	public void logCashDispensed(Money amount)
	{
		 Simulation.getInstance().printLogLine("Dispensed: " + amount.toString());
	}
	
	/** Log accepting an envelope.  This method is only called if an envelope
	 *  is actually received from the customer
	 */
	public void logEnvelopeAccepted()
	{
		 Simulation.getInstance().printLogLine("Envelope:  received");
	}

}
