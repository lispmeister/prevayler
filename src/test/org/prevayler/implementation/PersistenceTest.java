// Prevayler(TM) - The Open-Source Prevalence Layer.
// Copyright (C) 2001 Klaus Wuestefeld.
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation. This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details. You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA.

package org.prevayler.implementation;

import java.io.File;
import java.io.IOException;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;
import org.prevayler.foundation.FileManager;

public class PersistenceTest extends PrevalenceTest {

	private Prevayler prevayler;
	private String prevalenceBase;

	public void testPersistence() throws Exception {

		newPrevalenceBase();

		crashRecover(); //There is nothing to recover at first. A new system will be created.
		crashRecover();
		append("a","a");
		append("b","ab");
		verify("ab");

		crashRecover();
		verify("ab");

		append("c","abc");
		append("d","abcd");
		snapshot();
		snapshot();
		verify("abcd");

		crashRecover();
		snapshot();
		append("e","abcde");
		snapshot();
		append("f","abcdef");
		append("g","abcdefg");
		verify("abcdefg");

		crashRecover();
		append("h","abcdefgh");
		verify("abcdefgh");

		snapshot();
		File snapshot =   new File(prevalenceBase, "0000000000000000008.snapshot");
		newPrevalenceBase();
		FileManager.produceDirectory(prevalenceBase);
		snapshot.renameTo(new File(prevalenceBase, "0000000000000000008.snapshot"));
		
		crashRecover();
		append("i","abcdefghi");
		append("j","abcdefghij");
		crashRecover();
		append("k","abcdefghijk");
		append("l","abcdefghijkl");
		crashRecover();
		append("m","abcdefghijklm");
		append("n","abcdefghijklmn");
		crashRecover();
		verify("abcdefghijklmn");
	}

	private void crashRecover() throws Exception {
		out("CrashRecovery.");
		prevayler = PrevaylerFactory.createPrevayler(new AppendingSystem(), prevalenceBase());
	}

	private void snapshot() throws IOException {
		out("Snapshot.");
		prevayler.takeSnapshot();
	}


	private void append(String appendix, String expectedResult) throws Exception {
		out("Appending " + appendix);
		prevayler.execute(new Appendix(appendix));
		verify(expectedResult);
	}


	private void verify(String expectedResult) {
		out("Expecting result: " + expectedResult);
		compare(system().value(), expectedResult, "Result");
	}


	private AppendingSystem system() {
		return (AppendingSystem)prevayler.prevalentSystem();
	}


	private String prevalenceBase() {
		return prevalenceBase;
	}


	private void newPrevalenceBase() throws Exception {
		prevalenceBase = _testDirectory + "\\" + System.currentTimeMillis();
	}

	private void compare(String observed, String expected, String measurement) {
		verify(observed.equals(expected), measurement + ": " + observed + "   Expected: " + expected);
	}

	private static void verify(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException(message);
		}
	}

	private static void out(Object obj) {
		if (false) System.out.println(obj);   //Change this line to see what the test is doing.
	}

}