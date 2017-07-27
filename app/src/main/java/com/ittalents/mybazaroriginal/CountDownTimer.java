package com.ittalents.mybazaroriginal;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * Created by ASUS on 27.7.2017 г..
 */

public class CountDownTimer extends AppCompatActivity {

    private static final int MSG = 1;

    /**
     * Millis since boot when alarm should stop.
     */
    private long pastCountDown;

    /**
     * Real time remaining until timer completes
     */
    private long restOfTimer;

    /**
     * Total time on timer at start
     */
    private final long totalCountdown;

    /**
     * The interval in millis that the user receives callbacks
     */
    private final long countdownInterval;

    /**
     * The time remaining on the timer when it was paused, if it is currently paused; 0 otherwise.
     */
    private long pauseTimeRemaining;

    /**
     * True if timer was started running, false if not.
     */
    private boolean isStart;

    /**
     * @param millisOnTimer The number of millis in the future from the call
     *   to {@link #start} until the countdown is done and {@link #onFinish()}
     *   is called
     * @param countDownInterval The interval in millis at which to execute
     *   {@link #onTick(millisUntilFinished)} callbacks
     * @param runAtStart True if timer should start running, false if not
     */
    public CountDownTimer(long millisOnTimer, long countDownInterval, boolean isStart) {
        restOfTimer = millisOnTimer;
        totalCountdown = restOfTimer;
        countdownInterval = countDownInterval;
        this.isStart = isStart;
    }

    /**
     * Cancel the countdown and clears all remaining messages
     */
    public final void cancel() {
        mHandler.removeMessages(MSG);
    }

    /**
     * Create the timer object.
     */
    public synchronized final CountDownTimer create() {
        if (restOfTimer <= 0) {
            onFinish();
        } else {
            pauseTimeRemaining = restOfTimer;
        }

        if (isStart) {
            resume();
        }

        return this;
    }

    /**
     * Pauses the counter.
     */
    public void pause () {
        if (isRunning()) {
            pauseTimeRemaining = timeLeft();
            cancel();
        }
    }

    /**
     * Resumes the counter.
     */
    public void resume () {
        if (isPaused()) {
            restOfTimer = pauseTimeRemaining;
            pastCountDown = SystemClock.elapsedRealtime() + restOfTimer;
            mHandler.sendMessage(mHandler.obtainMessage(MSG));
            pauseTimeRemaining = 0;
        }
    }

    /**
     * Tests whether the timer is paused.
     * @return true if the timer is currently paused, false otherwise.
     */
    public boolean isPaused () {
        return (pauseTimeRemaining > 0);
    }

    /**
     * Tests whether the timer is running. (Performs logical negation on {@link #isPaused()})
     * @return true if the timer is currently running, false otherwise.
     */
    public boolean isRunning() {
        return (!isPaused());
    }

    /**
     * Returns the number of milliseconds remaining until the timer is finished
     * @return number of milliseconds remaining until the timer is finished
     */
    public long timeLeft() {
        long millisUntilFinished;
        if (isPaused()) {
            millisUntilFinished = pauseTimeRemaining;
        } else {
            millisUntilFinished = pastCountDown - SystemClock.elapsedRealtime();
            if (millisUntilFinished < 0) {
                millisUntilFinished = 0;
            }
        }
        return millisUntilFinished;
    }

    /**
     * Returns the number of milliseconds in total that the timer was set to run
     * @return number of milliseconds timer was set to run
     */
    public long totalCountdown() {
        return totalCountdown;
    }

    /**
     * Returns the number of milliseconds that have elapsed on the timer.
     * @return the number of milliseconds that have elapsed on the timer.
     */
    public long timePassed() {
        return totalCountdown - timeLeft();
    }

    /**
     * Returns true if the timer has been started, false otherwise.
     * @return true if the timer has been started, false otherwise.
     */
    public boolean hasBeenStarted() {
        return (pauseTimeRemaining <= restOfTimer);
    }

    /**
     * Callback fired on regular interval
     * @param millisUntilFinished The amount of time until finished
     */
    public void onTick(long millisUntilFinished){
        long remaining = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
       //String remainingTime = getFormattedString(StringsConstants.COUNDOWN_SECONDS_FORMAT, remaining);
       // swapCompletionTimerData.setTimerValue(remainingTime);

        Toast.makeText(this, remaining + "", Toast.LENGTH_SHORT).show();
    }

    /**
     * Callback fired when the time is up.
     */
    public void onFinish(){
        Toast.makeText(this, "SWAP_COMPLETED", Toast.LENGTH_SHORT).show();
    }

    // handles counting down
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (CountDownTimer.this) {
                long millisLeft = timeLeft();

                if (millisLeft <= 0) {
                    cancel();
                    onFinish();
                } else if (millisLeft < countdownInterval) {
                    // no tick, just delay until done
                    sendMessageDelayed(obtainMessage(MSG), millisLeft);
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(millisLeft);

                    // take into account user's onTick taking time to execute
                    long delay = countdownInterval - (SystemClock.elapsedRealtime() - lastTickStart);

                    // special case: user's onTick took more than countdownInterval to
                    // complete, skip to next interval
                    while (delay < 0) delay += countdownInterval;

                    sendMessageDelayed(obtainMessage(MSG), delay);
                }
            }
        }
    };

}
