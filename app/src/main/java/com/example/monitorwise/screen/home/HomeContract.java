package com.example.monitorwise.screen.home;


import com.example.monitorwise.base.BaseView;
import com.example.monitorwise.main.MainViewModel;

public interface HomeContract {

    interface View extends BaseView {

    }

    interface ViewModel extends MainViewModel<View> {

        void displayLocationSettingsRequest();
    }
}
