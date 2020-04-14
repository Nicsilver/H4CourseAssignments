package com.example.eggtimer;

public class PresenterFactory implements IPresenterFactory
{
    private static IPresenterFactory presenterFactory = null;
    
    
    public static IPresenterFactory getInstance()
    {
        if (presenterFactory == null)
            presenterFactory = new PresenterFactory();
        
        return presenterFactory;
    }
    
    @Override
    public IEggTimerContract.presenter createPresenter(IEggTimerContract.view view)
    {
        return new Presenter(view);
    }
}
