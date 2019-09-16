package fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacaows.R;

import java.util.ArrayList;
import java.util.List;

import entities.CustomListAdapter;
import entities.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.RetrofitService;


public class ListaFragment extends Fragment {

    /*private TextView campo;*/
	private List<User> lista;
	private ProgressDialog pDialog;
	private Activity activity;
    private ArrayList<User> users=new ArrayList<User>();
    private ListView listView;
    private String usuario;

    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_lista, container, false);
        //campo =(TextView) view.findViewById(R.id.campo);
        activity = getActivity();
        listView = (ListView) view.findViewById(R.id.listaprincipal);
		
		pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Carregando.. Aguarde...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
		
        buscaDados2();

        return view;
    }

     public void buscaDados2(){

         Call<List<User>> call  =RetrofitService.getServico().obterUsuarios();
         call.enqueue(new Callback<List<User>>() {

             @Override
             public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                 try {

                     users = (ArrayList)response.body();

                     if (pDialog.isShowing())
                         pDialog.dismiss();

                     CustomListAdapter customCountryList = new CustomListAdapter(activity, users);
                     listView.setAdapter(customCountryList);

                     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                         @Override
                         public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                             AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                             alert.setTitle("Usuário:");
                             alert.setMessage("ID: "+users.get(position).getId()+"\nNome: "+users.get(position).getName()+"\nEmail: "+users.get(position).getEmail()+"\nTelefone: "+users.get(position).getPhone()+
                                     "\nWebSite: "+users.get(position).getWebsite()+"\nEndereço : "+users.get(position).getAddress().getCity()+", \nRua: "+users.get(position).getAddress().getStreet());
                             alert.setPositiveButton("OK", null);
                             alert.show();
                         }
                     });
                 } catch (Exception e) {
                     Log.d("onResponse", "Error");
                     e.printStackTrace();
                 }

             }
             @Override
             public void onFailure(Call<List<User>> call, Throwable t) {
                 Log.d("Failure",t.toString());
             }


         });

     }



}
