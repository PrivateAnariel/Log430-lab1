/* * ATM Example system - file Simulation.java * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.simulation;import java.util.Observable;import java.util.Observer;import edu.gordon.atm.ATM;import edu.gordon.atm.physical.CustomerConsole;import edu.gordon.atm.physical.Log;import edu.gordon.banking.Balances;import edu.gordon.banking.Card;import edu.gordon.banking.Message;import edu.gordon.banking.Money;import edu.gordon.banking.Status;/** Simulation of the physical components of the ATM, including its network  *  connection to the bank.  An instance is created at startup by either the *  application's main() program or the applet's init() method. * *  The individual components are displayed in a panel belonging to class GUI. *  The bank is simulated by an object belonging to class SimulatedBank.  The *  constructor for this class creates one instance of each. * *  The static method getInstance() allows components of the ATM to access the one *  and only instance of this class in order to simulate various operations.  The *  remaining methods simulate specific operations of the ATM, and are forwarded *  to either the GUI panel or the simulated bank to actually carry them out. */public class Simulation implements Observer{    public Simulation()    {                // Create the simulated individual components of the ATM's GUI        operatorPanel = new SimOperatorPanel(this);        cardReader = new SimCardReader(this);        customerConsole = new SimCustomerConsole();        cashDispenser = new SimCashDispenser(null);        envelopeAcceptor = new SimEnvelopeAcceptor(null);        receiptPrinter = new SimReceiptPrinter();        keyboard = new SimKeyboard(customerConsole, envelopeAcceptor);                // Create the GUI containing the above                gui = new GUI(operatorPanel, cardReader, customerConsole, keyboard,                      cashDispenser, envelopeAcceptor, receiptPrinter);                SimLog log = new SimLog(gui);        simulatedNetworkToBank = new SimulatedNetworkToBank(log, null);     // create the ATM to be simulated        this.atm = new ATM(42,         			"Gordon College",         			"First National Bank of Podunk",        			null,         			log,         			cardReader,         			cashDispenser,         			customerConsole,         			envelopeAcceptor,         			simulatedNetworkToBank,         			operatorPanel,         			receiptPrinter );                // Create the edu.gordon.simulation of the bank                theInstance = this;    }        /** Accessor for the one and only instance of this class     *     *  @return the instance of this class     */    public static Simulation getInstance()    {        return theInstance;    }        /** Simulated getting initial amount of cash from operator     *     *  @return value of initial cash entered     */    public Money getInitialCash()    {        return gui.getInitialCash();    }        /** Simulate reading of a card     *     *     *  @return Card object representing information on the card if read     *          successfully, null if not read successfully     */    public Card readCard()    {        // Machine can't be turned off while there is a card in it        operatorPanel.setEnabled(false);        cardReader.animateInsertion();                // Since we don't have a magnetic stripe reader, we'll simulate by        // having customer type the card number in                return gui.readCard();    }        /** Simulate ejecting a card      */    public void ejectCard()    {        cardReader.animateEjection();        // Re-enable on-off switch        operatorPanel.setEnabled(true);    }        /** Simulate retaining a card     */    public void retainCard()    {        cardReader.animateRetention();        // Re-enable on-off switch        operatorPanel.setEnabled(true);    }         /** Simulate reading input from the keyboard     *     *  @param mode the input mode to use - one of the constants defined below.     *  @param maxValue the maximum acceptable value (used in MENU_MODE only)     *  @return the line that was entered - null if user pressed CANCEL.     */    public String readInput(int mode, int maxValue)    {        return keyboard.readInput(mode, maxValue);    }        /** Simulate dispensing cash to a customer     *     *  @param amount the amount of cash to dispense     *     *  Precondition: amount is <= cash on hand     */    public void dispenseCash(Money amount)    {        cashDispenser.animateDispensingCash(amount);    }        /** Simulate printing a line to the log     *     *  @param text the line to print     */    public void printLogLine(String text)    {        gui.printLogLine(text);    }    /** Notify the ATM that the state of the on-off switch has been changed     *     *  @param on true if state is now "on", false if it is "off"     */    void switchChanged(boolean on)    {        // The card reader is only enabled when the switch is on                cardReader.setVisible(on);                if (on)            atm.switchOn();        else            atm.switchOff();    }        /** Notify ATM that a card has been inserted     */    void cardInserted()    {        atm.cardInserted();    }        /** Accessor for GUI Panel that simulates the ATM     *     *  @return the GUI Panel     */    public GUI getGUI()    {        return gui;    }        /** Accessor for simulated bank     *     *  @return simulated bank     */    public SimulatedNetworkToBank getSimulatedBank()    {        return simulatedNetworkToBank;    }        public ATM getAtm(){    	return atm;    }    /* Possible values for mode parameter to readInput() */        /** Read input in PIN mode - allow user to enter several characters,     *  and to clear the line if the user wishes; echo as asterisks     */    public static final int PIN_MODE = 1;        /** Read input in amount mode - allow user to enter several characters,     *  and to clear the line if the user wishes; echo what use types     */    public static final int AMOUNT_MODE = 2;        /** Read input in menu choice mode - wait for one digit key to be pressed,     *  and return value immediately.     */    public static final int MENU_MODE = 3;        /** The ATM object for the ATM being simulated     */    private ATM atm;        /** The simulated operator panel     */    private SimOperatorPanel operatorPanel;        /** The simulated card reader     */    private SimCardReader cardReader;        /** The simulated display     */    private SimCustomerConsole customerConsole;        /** The simulated keyboard     */    private SimKeyboard keyboard;        /** The simulated cash dispenser     */    private SimCashDispenser cashDispenser;        /** The simulated envelope acceptor     */    private SimEnvelopeAcceptor envelopeAcceptor;        /** The simulated receipt printer     */    private SimReceiptPrinter receiptPrinter;        /** Panel containing the GUI that simulates the ATM     */    private GUI gui;        /** Simulated bank     */    private SimulatedNetworkToBank simulatedNetworkToBank;        /** The one and only instance of this class     */    private static Simulation theInstance;	@Override	public void update(Observable o, Object arg) {		// TODO Auto-generated method stub	}}    