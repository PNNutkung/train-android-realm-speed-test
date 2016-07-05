package com.example.nut.realmspeedtest.stopwatch;

/**
 * A StopWatch that measures elapsed time between a starting time
 * and stopping time, or until the present time.
 * @author Pipatpol Tanavongchinda
 * @version 1.0
 *
 */
public class StopWatch {

    /** Constant for converting nanoseconds to seconds.	 */
    private static final double NANOSECONDS = 1.0E-9;
    /** Time that the stopwatch was started,in nanoseconds. */
    private long startTime;
    /** Time that the stopwatch stop,in nanoseconds.*/
    private long endTime;
    /** After start will add 1 , stop will subtract 1. Check isRunning*/
    private int start;

    /**
     * Initialize the stopwatch with 0 seconds.
     */
    public StopWatch()
    {
        startTime = 0;
        endTime = 0;

    }
    /**
     * Get the elapsed times.
     *
     * @return returns the elapsed time in seconds with decimal.
     *
     * If called while stopwatch is running
     * the elapsed time since start until the current time.
     *
     * If stopwatch is stopped
     * the time between the start and stop times.
     *
     */
    public double getElapsed()
    {
        if(isRunning())
        {
            return (System.nanoTime() - startTime)*NANOSECONDS;

        }
        else
        {
            return (endTime - startTime)*NANOSECONDS;
        }
    }
    /**
     * Check the stopwatch is running or stop.
     *
     * @return returns true if the stopwatch is running, false if stopwatch is stopped.
     */
    public boolean isRunning()
    {
        return start != 0;
    }
    /**
     * Start the stopwatch
     */
    public void start()
    {
        if(!isRunning())
        {
            startTime = System.nanoTime();
            start++;
        }
    }
    /**
     * Stop the stopwatch.
     */
    public void stop()
    {
        if(isRunning())
        {
            endTime = System.nanoTime();
            start--;
        }
    }
}
