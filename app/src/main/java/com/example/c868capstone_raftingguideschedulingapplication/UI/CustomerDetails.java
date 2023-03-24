package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;

import java.util.ArrayList;
import java.util.List;


public class CustomerDetails extends AppCompatActivity {

    EditText editCustomerName;
    EditText editCustomerEmail;
    EditText editCustomerPhone;
    NumberPicker editCustomerGroupTotal;
    int customerID;
    String customerName;
    String customerEmail;
    String customerPhone;
    int customerGroupTotal;
    Repository repository;
    int numTrips;
    Customers customer;
    Customers currentCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        editCustomerName = findViewById(R.id.custNameEditText);
        editCustomerEmail = findViewById(R.id.custEmailEditText);
        editCustomerPhone = findViewById(R.id.custPhoneEditText);
        editCustomerGroupTotal = findViewById(R.id.custGroupTotalEditText);

        customerID = getIntent().getIntExtra("customerID", -1);
        customerName = getIntent().getStringExtra("customerName");
        customerEmail = getIntent().getStringExtra("customerEmail");
        customerPhone = getIntent().getStringExtra("customerPhone");
        customerGroupTotal = getIntent().getIntExtra("customerGroupTotal", 0);

        editCustomerName.setText(customerName);
        editCustomerEmail.setText(customerEmail);
        editCustomerPhone.setText(customerPhone);
        editCustomerGroupTotal.setMinValue(1);
        editCustomerGroupTotal.setMaxValue(11);
        editCustomerGroupTotal.setValue(customerGroupTotal);

        repository = new Repository(getApplication());

        RecyclerView recyclerView = findViewById(R.id.assocRiverTripsRecView);
        repository = new Repository(getApplication());
        final TripAssocCustomerAdapter tripAssocCustomerAdapter = new TripAssocCustomerAdapter(this);
        recyclerView.setAdapter(tripAssocCustomerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Trips> filteredTrips = new ArrayList<>();
        tripAssocCustomerAdapter.setTrips(filteredTrips);
        for (Trips t : repository.getAllTrips()) {
            if (t.getCustomerID() == customerID) filteredTrips.add(t);
        }

        // Button Listener for Contact Customer Button
        Button contactCustBtn = findViewById(R.id.custEmailBtn);
        contactCustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get customer email address
                String customerEmail = editCustomerEmail.getText().toString();
                // Launch email intent with customer email address populated
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{customerEmail});
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        Button textCustBtn = findViewById(R.id.custTextBtn);
        textCustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get customer phone number
                String customerPhone = editCustomerPhone.getText().toString();
                // Launch text message intent with customer phone number populated
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:" + customerPhone));
                startActivity(intent);
            }


        });

        Button button = findViewById(R.id.custSaveBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check to make sure all the fields are not empty
                if (editCustomerName.getText().toString().isEmpty() || editCustomerEmail.getText().toString().isEmpty() || editCustomerPhone.getText().toString().isEmpty() || editCustomerGroupTotal.getValue() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Validate phone number
                    String phoneNumber = editCustomerPhone.getText().toString();
                    String pattern = "\\d{10}";
                    if (!phoneNumber.matches(pattern)) {
                        editCustomerPhone.setError("Please enter a valid 10-digit phone number");
                    } else {
                        if (customerID == -1) {

                            customer = new Customers(0, editCustomerName.getText().toString(), editCustomerEmail.getText().toString(),
                                    editCustomerPhone.getText().toString(), editCustomerGroupTotal.getValue());
                            repository.insert(customer);
                            Toast.makeText(getApplicationContext(), "New Customer added!", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            customer = new Customers(customerID, editCustomerName.getText().toString(), editCustomerEmail.getText().toString(),
                                    editCustomerPhone.getText().toString(), editCustomerGroupTotal.getValue());
                            repository.update(customer);
                            Toast.makeText(getApplicationContext(), "Customer updated!", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }
                }
            }
        });

        Button deleteButton = findViewById(R.id.custDeleteBtn);
        deleteButton.setOnClickListener(view -> {
            for (Customers cust : repository.getAllCustomers()) {
                if (cust.getCustomerID() == customerID) currentCustomer = cust;
            }
            numTrips = 0;
            for( Trips trips: repository.getAllTrips()) {
                if(trips.getCustomerID() == customerID)   ++numTrips;
            }
            if(numTrips == 0) {
                repository.delete(currentCustomer);
                Toast.makeText(CustomerDetails.this, currentCustomer.getCustomerName() + " was deleted", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(CustomerDetails.this, "Can't Delete a customer that has a scheduled trip", Toast.LENGTH_LONG).show();

            }
        });
    }


    public static class TripAssocCustomerAdapter extends RecyclerView.Adapter<TripAssocCustomerAdapter.TripViewHolder>{

        class TripViewHolder extends RecyclerView.ViewHolder {

            private final TextView tripItemView;

            private TripViewHolder (View view) {
                super(view);
                tripItemView = view.findViewById(R.id.assocTripsTxView);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        final Trips current = mTrips.get(position);
                        Intent intent = new Intent(context, TripDetails.class);
                        intent.putExtra("tripID", current.getTripID());
                        intent.putExtra("tripName", current.getTripName());
                        intent.putExtra("guideID", current.getGuideID());
                        intent.putExtra("customerID", current.getCustomerID());
                        intent.putExtra("equipmentID", current.getEquipmentID());
                        intent.putExtra("tripStart", current.getTripStart());
                        intent.putExtra("tripEnd", current.getTripEnd());
                        intent.putExtra("tripNotes", current.getTripNotes());
                        context.startActivity(intent);
                    }
                });
            }
        }
        private List<Trips> mTrips;

        private final Context context;

        private final LayoutInflater mInflater;

        public TripAssocCustomerAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @NonNull
        @Override
        public TripViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list_item, parent, false);

            return new TripViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TripViewHolder holder, int position){
            if(mTrips != null) {
                Trips currentTrips = mTrips.get(position);
                String name = currentTrips.getTripName();
                holder.tripItemView.setText(name);
            } else {
                holder.tripItemView.setText("No Trip Name");
            }
        }

        public void setTrips(List<Trips> trips){
            mTrips = trips;
            notifyDataSetChanged();
        }

        public int getItemCount() {
            if(mTrips != null) {
                return mTrips.size();
            } else return 0;
        }

    }
}