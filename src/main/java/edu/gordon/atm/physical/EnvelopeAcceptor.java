/* * ATM Example system - file EnvelopeAcceptor.java * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.physical;import edu.gordon.atm.physical.CustomerConsole;/** Manager for the ATM's envelope acceptor.  In a real ATM, this would  *  manage a physical device; in this edu.gordon.simulation,  it uses classes  *  in package edu.gordon.simulation to simulate the device. */ public interface EnvelopeAcceptor{        /** Accept an envelope from customer.     *     *  @exception CustomerConsole.Cancelled if operation timed out or the     *             customer cancelled it     */    public void acceptEnvelope() throws CustomerConsole.Cancelled;}