package sk.ondrejhirjak.jserver.core;

import sun.misc.Signal;
import sun.misc.SignalHandler;


class ServerSignalHandler implements SignalHandler {

    private SignalHandler oldHandler;

    private String signalName;


    ServerSignalHandler(String signalName) {
        this.signalName = signalName;
    }


    void installHandler() {
        Signal diagSignal = new Signal(signalName);
        oldHandler = Signal.handle(diagSignal, this);
    }


    @Override
    public void handle(Signal sig) {
        ServerRunner.shutdownServer();

        debugLog(sig);
    }


    private void debugLog(Signal sig) {
        System.out.println("Diagnostic Signal handler called for signal "+sig);
        try {
            // Output information for each thread
            Thread[] threadArray = new Thread[Thread.activeCount()];
            int numThreads = Thread.enumerate(threadArray);
            System.out.println("Current threads:");
            for (int i=0; i < numThreads; i++) {
                System.out.println(" "+threadArray[i]);
            }
            // Chain back to previous handler, if one exists
            if ( oldHandler != SIG_DFL && oldHandler != SIG_IGN ) {
                oldHandler.handle(sig);
            }
        } catch (Exception e) {
            System.out.println("Signal handler failed, reason "+e);
        }
    }
}
