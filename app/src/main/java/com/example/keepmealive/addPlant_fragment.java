package com.example.keepmealive;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class addPlant_fragment extends DialogFragment implements View.OnClickListener{

    private Context mContext;

    String urltxt = "https://open.plantbook.io/api/v1/plant/search?alias=";
    String searchtxt;
    TextWatcher search = null;

    public addPlant_fragment() {
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mContext=context;
    }

    EditText name_input, water_input;
    AutoCompleteTextView type_input;
    Button add_button;
    String typeresult="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_plant_layout,container,false);
        name_input= (EditText) view.findViewById(R.id.PlantName);
        type_input= (AutoCompleteTextView) view.findViewById(R.id.PlantType);
        water_input= (EditText) view.findViewById(R.id.WateringTime);
        add_button = (Button) view.findViewById(R.id.addPlantButton);
        add_button.setOnClickListener(this);
        List<String> types = new ArrayList<>();
        types.add("Echeveria Peacockii");
        types.add("Echeveria Black Prince");
        types.add("Echeveria Elegans");
        types.add("Crassula Ovata Gollum");
        types.add("Crassula Jade");


        search=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    urltxt = "https://open.plantbook.io/api/v1/plant/search?alias=";
                    searchtxt=type_input.getText().toString();
                    urltxt = urltxt + searchtxt;

                    new JSONTask().execute(urltxt);
                    types.add(typeresult);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        type_input.addTextChangedListener(search);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,types);
        type_input.setAdapter(adapter);


        return view;
    }

    @Override
    public void onClick(View view) {
        String namestr = name_input.getText().toString();
        String typestr = type_input.getText().toString();
        int water = Integer.parseInt(water_input.getText().toString());
        PlantDatabaseHelper plantDb = new PlantDatabaseHelper(getContext());
        plantDb.addPlant(namestr, typestr,water);
        dismiss();
//        Toast.makeText(mContext, namestr,Toast.LENGTH_SHORT).show();
    }

    public class JSONTask extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String ... params) {

            HttpURLConnection connection = null;
            BufferedReader reader=null;


            try {
                URL planturl = new URL(params[0]);
                connection = (HttpURLConnection) planturl.openConnection();
                connection.setRequestProperty("Authorization","Bearer 7rCjP5KF6h6tiJIPy2O8KrfL2QwR8E");
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                String line = "";
                StringBuffer buffer = new StringBuffer();

                while ((line = reader.readLine() )!= null)
                {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("results");

                JSONObject finalObject = parentArray.getJSONObject(0);

                String pid = finalObject.getString("pid");
                String display = finalObject.getString("display_pid");
                String alias = finalObject.getString("alias");


                return display;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
                try {
                    if(reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            typeresult=result;
        }
    }

}
