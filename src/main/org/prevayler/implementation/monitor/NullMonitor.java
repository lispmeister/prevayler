// Prevayler(TM) - The Free-Software Prevalence Layer.
//Copyright (C) 2001-2003 Klaus Wuestefeld
//This library is distributed in the hope that it will be useful, but WITHOUT
// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// FOR A PARTICULAR PURPOSE.
// Contributed by Carlos Villela.
package org.prevayler.implementation.monitor;

import java.io.File;
import java.io.IOException;

import org.prevayler.Monitor;

/**
 * A Null Monitor, that does no logging at all.
 * 
 * @author Carlos Villela
 * @since May 14, 2004
 */
public class NullMonitor implements Monitor {

    /**
     * @see org.prevayler.Monitor#snapshotTaken(long)
     */
    public void snapshotTaken(long version) {
    }

    /**
     * @see org.prevayler.Monitor#handleExceptionWhileCreatingLogFile(java.io.IOException, java.io.File)
     */
    public void handleExceptionWhileCreatingLogFile(IOException exception, File file) {
    }

    /**
     * @see org.prevayler.Monitor#handleExceptionWhileWritingLogFile(java.io.IOException, java.io.File)
     */
    public void handleExceptionWhileWritingLogFile(IOException exception, File file) {
    }

    /**
     * @see org.prevayler.Monitor#ignoringStreamCorruption(java.lang.Exception, java.io.File)
     */
    public void ignoringStreamCorruption(Exception exception, File file) {
    }

    /**
     * @see org.prevayler.Monitor#loggerInitialized(java.io.File, java.lang.ClassLoader, long, long)
     */
    public void loggerInitialized(File directory, ClassLoader loader, long sizeThresholdInBytes, long ageThresholdInMillis) {
    }

    /**
     * @see org.prevayler.Monitor#readingTransactionLogFile(java.io.File, java.lang.ClassLoader)
     */
    public void readingTransactionLogFile(File file, ClassLoader loader) {
    }

    /**
     * @see org.prevayler.Monitor#lastSnapshotRecovered(long)
     */
    public void lastSnapshotRecovered(long version) {
    }
}
