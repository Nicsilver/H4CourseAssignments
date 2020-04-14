package com.example.eggtimer;

import java.io.IOException;

public interface ITimerState
{
    void startTimer();
    void setTimer(long time);
    void stopTimer();
}
