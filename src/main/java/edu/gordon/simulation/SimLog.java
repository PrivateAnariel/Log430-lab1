/* * ATM Example system - file LogPanel.java   * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.simulation;import java.awt.Button;import java.awt.Color;import java.awt.FlowLayout;import java.awt.Font;import java.awt.GridBagConstraints;import java.awt.GridBagLayout;import java.awt.Label;import java.awt.Panel;import java.awt.TextArea;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import edu.gordon.atm.physical.Log;import edu.gordon.banking.Message;import edu.gordon.banking.Money;import edu.gordon.banking.Status;/** The GUI panel that displays the ATM's internal log */class SimLog extends Panel implements Log{    /** Constructor     *     *  @param gui the the overall GUI     */    SimLog(final GUI gui)    {        GridBagLayout logLayout = new GridBagLayout();        setLayout(logLayout);        setFont(new Font("Monospaced", Font.PLAIN, 14));                Label logPanelLabel = new Label("Log", Label.CENTER);        add(logPanelLabel);        GridBagConstraints constraints =             GUI.makeConstraints(0, 0, 1, 1, GridBagConstraints.NONE);        constraints.weighty = 0;        logLayout.setConstraints(logPanelLabel, constraints);                     logPrintArea = new TextArea();        logPrintArea.setBackground(Color.white);        logPrintArea.setForeground(Color.black);        logPrintArea.setFont(new Font("Monospaced", Font.PLAIN, 12));        logPrintArea.setEditable(false);                add(logPrintArea);        constraints = GUI.makeConstraints(1, 0, 1, 1, GridBagConstraints.BOTH);        constraints.weighty = 1;        logLayout.setConstraints(logPrintArea, constraints);                Panel logButtonPanel = new Panel();        logButtonPanel.setLayout(new FlowLayout());                Button clearLogButton = new Button("Clear Log");        clearLogButton.addActionListener(new ActionListener() {            public void actionPerformed(ActionEvent e)            {                logPrintArea.setText("");            }        });        logButtonPanel.add(clearLogButton);                Button dismissLogButton = new Button(" Hide Log ");        dismissLogButton.addActionListener(new ActionListener() {            public void actionPerformed(ActionEvent e)            {                gui.showCard("ATM");            }        });                logButtonPanel.add(dismissLogButton);        add(logButtonPanel);        constraints = GUI.makeConstraints(2, 0, 1, 1, GridBagConstraints.NONE);        constraints.weighty = 0;        logLayout.setConstraints(logButtonPanel, constraints);    }    	/** Log the sending of a message to the bank	 *	 *  @param message the message to be logged	 */	public void logSend(Message message)	{		 Simulation.getInstance().printLogLine("Message:	" + message.toString()); 	}		/** Log a response received from a message	 *	 *  @param status the status object returned by the bank in response	 */	public void logResponse(Status response)	{		 Simulation.getInstance().printLogLine("Response:  " + response.toString());	}		/** Log the dispensing of cash by the cash dispenser	 *	 *  @param amount the amount of cash being dispensed	 */	public void logCashDispensed(Money amount)	{		 Simulation.getInstance().printLogLine("Dispensed: " + amount.toString());	}		/** Log accepting an envelope.  This method is only called if an envelope	 *  is actually received from the customer	 */	public void logEnvelopeAccepted()	{		 Simulation.getInstance().printLogLine("Envelope:  received");	}        /** Add text to the log     *     *  @param text the text to be printed     */    void println(String text)    {        logPrintArea.append(text + "\n");    }        /** Area into which the log is to be printed     */    private TextArea logPrintArea;}                    