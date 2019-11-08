package org.firstinspires.ftc.teamcode.lschs;

public class Util {

    public static void print(String str) {
        Core.getTelemetry().addLine(str);
    }

    public static void print(String entry, double value) {
        Core.getTelemetry().addData(entry, value);
    }


    /**
     * Timer copied from my FRC 2018 & 2019 code
     */
    public static class Timer {

        private long startTime = 0;
        private long endTime = 0;
        private boolean isBusy = false;
        private String name;

        public Timer(String name) {
            this.name = name;
            startTime = System.currentTimeMillis();
        }

        public Timer() {
            this("Default Timer");
        }

        public void start() {
            startTime = System.currentTimeMillis();
            isBusy = true;
        }

        public boolean isBusy() {
            return isBusy;
        }

        public void stop() {
            endTime = System.currentTimeMillis();
            isBusy = false;
        }

        public long getElaspedTimeInMs() {
            endTime = System.currentTimeMillis();
            return endTime - startTime;
        }

        public String toString() {
            if (isBusy) {
                stop();
            }

            return "[" + name + "] Time elapsed: " + (getElaspedTimeInMs()) + "ms";
        }
    }
}
