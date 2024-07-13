package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab4_sqllite.R;


import java.util.List;

import model.Contact;

public class ContactAdapter extends BaseAdapter {
    Context context;
    List<Contact> contactArrayList;

    public ContactAdapter(Context context, List<Contact> contactArrayList) {
        this.context = context;
        this.contactArrayList = contactArrayList;
    }

    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_contact, null,false);
        }

        TextView tvInfo = view.findViewById(R.id.tv_contact_info);

        String str = "ID: " + contactArrayList.get(i).getId()
                + ". Name: " + contactArrayList.get(i).getName()
                + ". Phone number: " + contactArrayList.get(i).getPhoneNumber();
        tvInfo.setText(str);

        return view;
    }
}
