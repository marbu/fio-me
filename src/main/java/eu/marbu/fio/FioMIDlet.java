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

	/*
	 * GUI components
	 */
	private Display display;

	// Account selection form
	private Form accountsForm;

	/**
	 * Constructor - initializes GUI components.
	 */
	public FioMIDlet() {
		// Account selection form
		accountsForm = new Form("Accounts");
		accountsForm.setCommandListener(this);
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	public void startApp() {
		display = Display.getDisplay(this);
		display.setCurrent(accountsForm);
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
	}
}
