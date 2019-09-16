package entities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.aplicacaows.R;
import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private Activity context;
    ArrayList<User> users;

    public CustomListAdapter(Activity context, ArrayList<User> users) {
        //   super(context, R.layout.row_item, countries);
        this.context = context;
        this.users=users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int i) {
        return this.users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = context.getLayoutInflater();

        if(view==null)
        {
            view= inflater.inflate(R.layout.user_list,null,true);
        }

        TextView id = (TextView) view.findViewById(R.id.id);
        TextView name = (TextView) view.findViewById(R.id.nome);
        TextView emailtxt =(TextView) view.findViewById(R.id.email);

        User user = this.users.get(i);
        String nome = user.getName();
        Integer identificador = user.getId();
        String email = user.getEmail();

        id.setText(identificador.toString());
        name.setText(nome);
        emailtxt.setText(email);


        return view;
    }
}
