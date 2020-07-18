package com.bsav157.lastfm.presenter;


import com.bsav157.lastfm.interfaces.IModel;
import com.bsav157.lastfm.interfaces.IPresenter;
import com.bsav157.lastfm.interfaces.IView;
import com.bsav157.lastfm.model.Interactor;

public class Presenter implements IPresenter {

    private IView view;
    private IModel interactor;

    public Presenter(IView view) {
        this.view = view;
        this.interactor = new Interactor(this);
    }

    @Override
    public void makeApiQuery() {
        if(interactor != null){
            interactor.makeApiQuery();
        }

    }

    @Override
    public void showData(String result) {
        if(view != null){
            view.showData(result);
        }
    }
}
