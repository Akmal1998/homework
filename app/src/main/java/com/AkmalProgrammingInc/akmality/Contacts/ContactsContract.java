package com.AkmalProgrammingInc.akmality.Contacts;

import java.util.List;
import java.util.Map;

public interface ContactsContract {

    public interface IContractsView{
        void showContacts(List<Map<String, String>> contacts);
    }

    public interface IContractsPresenter{
        void loadContacts(Object cr);
        void detachView();
    }

    public interface IContractsModel{
        public interface onContactsLoadCallback{
            void onContactsLoaded(List<Map<String, String>> maps);
        }

        void getContacts(Object cr, onContactsLoadCallback callback);
    }


}
