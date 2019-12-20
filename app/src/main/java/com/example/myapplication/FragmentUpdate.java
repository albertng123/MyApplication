package com.example.myapplication;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentUpdate.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentUpdate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUpdate extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private String email;
    private boolean isDataExist = true;
    private DatabaseReference database;
    private Button updateModal, hoe, sickle, sprayer, fertilizer, pesticides, seed;
    private EditText jhektar, jmodal, worker;
    UserModel model;

    public FragmentUpdate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUpdate.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUpdate newInstance(String param1, String param2) {
        FragmentUpdate fragment = new FragmentUpdate();
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
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.child("childName");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() == null) {
                    isDataExist = false;
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        if (!isDataExist) {
            v = inflater.inflate(R.layout.fragment_update_zero, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_update, container, false);
            database = FirebaseDatabase.getInstance().getReference();
            email = getArguments().getString("email");
            jmodal = v.findViewById(R.id.jmodal);
            jhektar = v.findViewById(R.id.jhektar);
            worker = v.findViewById(R.id.worker);
            hoe = v.findViewById(R.id.cangkul);
            sickle = v.findViewById(R.id.sickle);
            sprayer = v.findViewById(R.id.penyemprot);
            fertilizer = v.findViewById(R.id.fertilizer);
            pesticides = v.findViewById(R.id.hama);
            seed = v.findViewById(R.id.seed);
            updateModal = v.findViewById(R.id.greenButton);

            hoe.setOnClickListener(this);
            sickle.setOnClickListener(this);
            sprayer.setOnClickListener(this);
            fertilizer.setOnClickListener(this);
            pesticides.setOnClickListener(this);
            seed.setOnClickListener(this);
            updateModal.setOnClickListener(this);
            readDataFirebase();
        }
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void readDataFirebase(){
        database.child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model = new UserModel();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    model = ds.getValue(UserModel.class);
                }
                jmodal.setText(String.valueOf(model.getModal()));
                jhektar.setText(String.valueOf(model.getJhektar()));
                worker.setText(String.valueOf(model.getWorker()));
                hoe.setText(String.valueOf(model.getHoe()));
                sickle.setText(String.valueOf(model.getSickle()));
                sprayer.setText(String.valueOf(model.getSprayer()));
                fertilizer.setText(String.valueOf(model.getFertilizer()));
                pesticides.setText(String.valueOf(model.getPesticides()));
                seed.setText(String.valueOf(model.getSeed()));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void updateModal() {
        try {
            int jlhHa = Integer.valueOf(jhektar.getText().toString());
            int jarakPerHa = model.getDhektar();
            int jlhPekerja = Integer.valueOf(worker.getText().toString());
            int jlhCangkul = Integer.valueOf(hoe.getText().toString());
            int jlhSickle = Integer.valueOf(sickle.getText().toString());
            int jlhSprayer = Integer.valueOf(sprayer.getText().toString());
            int jlhFertilizer = Integer.valueOf(fertilizer.getText().toString());
            int jlhPesticides = Integer.valueOf(pesticides.getText().toString());
            int jlhSeed = Integer.valueOf(seed.getText().toString());
            long modal = jlhHa*50000000 + jlhPekerja*3000000 + jlhCangkul*50000 + jlhSickle*70000 + jlhSprayer*125000 +
                    jlhFertilizer*100000 + jlhPesticides*30000 + jlhSeed*5000;
            UserModel userModel = new UserModel(model.getId(),
                    jlhHa, jarakPerHa, jlhPekerja, jlhCangkul, jlhSickle, jlhSprayer,
                    jlhFertilizer, jlhPesticides, jlhSeed, modal);
            database.child(email).child(String.valueOf(model.getId())).setValue(userModel);
            Toast.makeText(getContext(), "Update Modal Berhasil", Toast.LENGTH_SHORT).show();
            readDataFirebase();
        } catch (Exception ex) {
            Toast.makeText(getContext(), "Ada data yang Kosong atau tidak berupa angka", Toast.LENGTH_SHORT).show();
        }
    }

    private void jumlahAlat(final Button b) {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);

        final EditText editText = dialogView.findViewById(R.id.editText);
        Button ok = dialogView.findViewById(R.id.okButton);
        Button cancel = dialogView.findViewById(R.id.cancelButton);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.setText(editText.getText().toString());
                dialogBuilder.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.greenButton:
                updateModal();
                break;
            case R.id.cangkul:
                jumlahAlat(hoe);
                break;
            case R.id.sickle:
                jumlahAlat(sickle);
                break;
            case R.id.penyemprot:
                jumlahAlat(sprayer);
                break;
            case R.id.fertilizer:
                jumlahAlat(fertilizer);
                break;
            case R.id.hama:
                jumlahAlat(pesticides);
                break;
            case R.id.seed:
                jumlahAlat(seed);
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
