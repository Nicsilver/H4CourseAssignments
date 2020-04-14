package com.example.eggtimer;

public interface IPresenterFactory
{
    IEggTimerContract.presenter createPresenter(IEggTimerContract.view v);
}
