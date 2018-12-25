package com.AkmalProgrammingInc.akmality.Contacts;


import java.util.List;
import java.util.Map;

public class ContactsPresenter implements ContactsContract.IContractsPresenter, ContactsContract.IContractsModel.onContactsLoadCallback {

    private ContactsContract.IContractsModel model;
    private ContactsContract.IContractsView view;

    public ContactsPresenter(ContactsContract.IContractsModel model, ContactsContract.IContractsView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadContacts(Object cursor) {
        model.getContacts(cursor, this);
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onContactsLoaded(List<Map<String, String>> maps) {
        if (view != null){
            view.showContacts(maps);
        }
    }
}
