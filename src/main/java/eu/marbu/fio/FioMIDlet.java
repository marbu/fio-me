/*
 * Copyright 2017 Martin Bukatovič <martin.bukatovic@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.marbu.fio;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 * FIO API client MIDlet.
 *
 * @author Martin Bukatovič
 */
public class FioMIDlet extends MIDlet implements CommandListener {

	// length of FIO API token
	public static final int API_TOKEN_LEN = 64;

	// max. length of Account name
	public static final int NAME_LEN = 20;

	/*
	 * GUI components
	 */
	private Display display;

	// Account list screen
	private List accountList = new List("Accounts", Choice.IMPLICIT);
	private Command selectCmd = new Command("Select", Command.ITEM, 0);
	private Command addCmd = new Command("Add", Command.SCREEN, 1);
	private Command delCmd = new Command("Delete", Command.ITEM, 1);
	private Command renameCmd = new Command("Rename", Command.ITEM, 1);
	private Command exitCmd = new Command("Exit", Command.EXIT, 1);

	// Add Account screen
	private Form addAccountForm = new Form("Add Account");
	private TextField accountNameField = new TextField("Name:", "", NAME_LEN, TextField.ANY);
	private TextField accountTokenField = new TextField("FIO API Token:", "", API_TOKEN_LEN, TextField.SENSITIVE);
	private Command okCmd = new Command("Ok", Command.OK, 0);
	private Command cancelCmd = new Command("Cancel", Command.CANCEL, 1);

	// Rename Account screen
	private Form renameAccountForm = new Form("Rename Account");
	private TextField accountReNameField = new TextField("New name:", "", NAME_LEN, TextField.ANY);

	// Account screen
	private Form accountForm = new Form("Account");
	private StringItem accountNameItem = new StringItem("Name", null);
	private Command backCmd = new Command("Back", Command.BACK, 1);

	/**
	 * Constructor - initializes GUI components.
	 */
	public FioMIDlet() {
		// Account list screen
		accountList.addCommand(addCmd);
		accountList.setSelectCommand(selectCmd);
		// accountList.addCommand(delCmd);
		// accountList.addCommand(renameCmd);
		accountList.addCommand(exitCmd);
		accountList.setCommandListener(this);

		// Add Account screen
		addAccountForm.append(accountNameField);
		addAccountForm.append(accountTokenField);
		addAccountForm.addCommand(okCmd);
		addAccountForm.addCommand(cancelCmd);
		addAccountForm.setCommandListener(this);

		// Rename Account screen
		renameAccountForm.append(accountReNameField);
		renameAccountForm.addCommand(okCmd);
		renameAccountForm.addCommand(cancelCmd);
		renameAccountForm.setCommandListener(this);

		// Account screen
		accountForm.append(accountNameItem);
		accountForm.addCommand(backCmd);
		accountForm.setCommandListener(this);
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	public void startApp() {
		display = Display.getDisplay(this);
		display.setCurrent(accountList);
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 */
	public void pauseApp() {
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 */
	public void destroyApp(boolean unconditional) {
	}

	/**
	 * Handles command actions from all forms.
	 *
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
	 *      javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command aCmd, Displayable aDisp) {
		if (aDisp == accountList) {
			if (aCmd == selectCmd) {
				accountNameItem.setText(accountList.getString(accountList.getSelectedIndex()));
				display.setCurrent(accountForm);
			} else if (aCmd == delCmd) {
				accountList.delete(accountList.getSelectedIndex());
				if (accountList.size() == 0) {
					accountList.removeCommand(delCmd);
					accountList.removeCommand(renameCmd);
				}
			} else if (aCmd == renameCmd) {
				int renameIndex = accountList.getSelectedIndex();
				// use the current name as a starting point
				accountReNameField.setString(accountList.getString(renameIndex));
				display.setCurrent(renameAccountForm);
			} else if (aCmd == exitCmd) {
				notifyDestroyed();
			} else if (aCmd == addCmd) {
				display.setCurrent(addAccountForm);
			}
		} else if (aDisp == addAccountForm) {
			if (aCmd == okCmd) {
				accountList.append(accountNameField.getString(), null);
				if (accountList.size() == 1) {
					accountList.addCommand(delCmd);
					accountList.addCommand(renameCmd);
				}
			}
			display.setCurrent(accountList);
			accountNameField.setString("");
			accountTokenField.setString("");
		} else if (aDisp == renameAccountForm) {
			if (aCmd == okCmd) {
				int renameIndex = accountList.getSelectedIndex();
				accountList.set(renameIndex, accountReNameField.getString(), null);
			}
			display.setCurrent(accountList);
		} else if (aDisp == accountForm) {
			if (aCmd == backCmd) {
				display.setCurrent(accountList);
			}
		}
	}
}
