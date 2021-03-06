package com.example.monitorwise.screen.calendar;

import com.example.monitorwise.base.BaseContract;
import com.example.monitorwise.base.BaseView;
import com.example.monitorwise.base.BaseViewModel;

public interface CalendarContract {

    interface View extends BaseView {
        int getSize();
    }

    interface ViewModel extends BaseContract.ViewModel {

    }

    interface Repository {

    }
}
