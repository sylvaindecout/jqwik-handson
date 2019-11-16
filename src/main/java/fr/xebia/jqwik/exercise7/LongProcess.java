package fr.xebia.jqwik.exercise7;

import static java.lang.Thread.sleep;

final class LongProcess {

    String thisProcessIsSoLongYouWouldHardlyBelieveIt(final String parameter) throws InterruptedException {
        sleep(3_000);
        return parameter;
    }

}
