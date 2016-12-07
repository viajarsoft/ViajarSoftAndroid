package com.app.viajarsoft.ventatiquetes.presenters;


import com.app.viajarsoft.ventatiquetes.utilities.helpers.IValidateInternet;
import com.app.viajarsoft.ventatiquetes.view.views_activities.IBaseView;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public class BasePresenter<T extends IBaseView> {

    private T view;
    private IValidateInternet validateInternet;

    public void inject(T view, IValidateInternet validateInternet) {
        this.view = view;
        this.validateInternet = validateInternet;
    }

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public IValidateInternet getValidateInternet() {
        return validateInternet;
    }

    public void setValidateInternet(IValidateInternet validateInternet) {
        this.validateInternet = validateInternet;
    }
}
