package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAccount.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAccount extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private DatabaseReference database;
    private TextInputEditText nama;
    private TextView a_email;
    private Button s_save;
    private TextView s_phone,s_email,s_password,s_photo;
    private String mParam1;
    private String mParam2;
    private String email;
    private OnFragmentInteractionListener mListener;

    public FragmentAccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAccount newInstance(String param1, String param2) {
        FragmentAccount fragment = new FragmentAccount();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v = inflater.inflate(R.layout.fragment_account, container, false);
        database = FirebaseDatabase.getInstance().getReference();
        email = getArguments().getString("email");
        a_email = v.findViewById(R.id.account_email);
        a_email.setText(email);
        nama = v.findViewById(R.id.name);
        s_save = v.findViewById(R.id.change_save);
        s_email = v.findViewById(R.id.change_email);
        s_password = v.findViewById(R.id.change_pass);
        s_phone = v.findViewById(R.id.change_no);
        s_photo = v.findViewById(R.id.change_picture);
        nama.setText(email);

        s_save.setOnClickListener(this);
        s_email.setOnClickListener(this);
        s_password.setOnClickListener(this);
        s_photo.setOnClickListener(this);
        s_phone.setOnClickListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_email:
                Toast.makeText(getContext(), "Still develop", Toast.LENGTH_SHORT).show();
                break;
            case R.id.change_no:
                Toast.makeText(getContext(), "Still develop", Toast.LENGTH_SHORT).show();
                break;
            case R.id.change_pass:
                Toast.makeText(getContext(), "Still develop", Toast.LENGTH_SHORT).show();
                break;
            case R.id.change_picture:
                Toast.makeText(getContext(), "Still develop", Toast.LENGTH_SHORT).show();
                break;
            case R.id.change_save:
                Toast.makeText(getContext(), "Still develop", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
