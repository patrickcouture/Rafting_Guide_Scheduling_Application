package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TripDetails extends BaseActivity {

    Spinner editTripName;
    Spinner editLocation;
    Spinner editGuide;
    Spinner editCustomer;
    Spinner editEquipment;
    EditText editStartDate;
    EditText editEndDate;
    EditText editTripNotes;

    int tripID;
    int equipmentID;
    int guideID;
    int customerID;
    String location;
    String tripName;
    String tripStart;
    String tripEnd;
    String tripNotes;


    Repository repository;
    int numTrips;
    Trips trip;
    Trips currentTrip;

    DatePickerDialog.OnDateSetListener tStartDate;
    DatePickerDialog.OnDateSetListener tEndDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        editTripName = (Spinner) findViewById(R.id.tripNameEditText);
        editLocation = (Spinner) findViewById(R.id.locationEditText);
        editGuide = (Spinner) findViewById(R.id.tripGuideNameEditText);
        editCustomer = (Spinner) findViewById(R.id.tripCustNameEditText);
        editEquipment = (Spinner) findViewById(R.id.tripEquipNameEditText);
        editStartDate = findViewById(R.id.tripstartDateEditText);
        editEndDate = findViewById(R.id.tripEndDateEditText);
        editTripNotes = findViewById(R.id.tripNotesEditText);

        tripID = getIntent().getIntExtra("tripID", -1);
        tripName = getIntent().getStringExtra("tripName");
        location = getIntent().getStringExtra("location");
        guideID = getIntent().getIntExtra("guideID", -1);
        customerID = getIntent().getIntExtra("customerID", -1);
        equipmentID = getIntent().getIntExtra("equipmentID", -1);
        tripStart = getIntent().getStringExtra("tripStart");
        tripEnd = getIntent().getStringExtra("tripEnd");
        tripNotes = getIntent().getStringExtra("tripNotes");
        String myFormat = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);

        ArrayAdapter<CharSequence> tripNameAdapter = ArrayAdapter.createFromResource(this, R.array.trip_name, android.R.layout.simple_spinner_item);
        tripNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTripName.setAdapter(tripNameAdapter);
        editTripName.setSelection(tripNameAdapter.getPosition(tripName));


        ArrayAdapter<CharSequence> tripLocationAdapter = ArrayAdapter.createFromResource(this, R.array.trip_location, android.R.layout.simple_spinner_item);
        tripLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editLocation.setAdapter(tripLocationAdapter);
        editLocation.setSelection(tripLocationAdapter.getPosition(location));

        repository = new Repository(getApplication());
        List<String> guideNames = new ArrayList<>();
        for (Guides g : repository.getAllGuides()) {
            guideNames.add(g.getGuideName());
        }
        ArrayAdapter<String> guideNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, guideNames);
        guideNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editGuide.setAdapter(guideNameAdapter);
        for (Guides g : repository.getAllGuides()) {
            if (g.getGuideID() == guideID)
                editGuide.setSelection(guideNameAdapter.getPosition(g.getGuideName()));
        }

        repository = new Repository(getApplication());
        List<String> customerNames = new ArrayList<>();
        for (Customers c : repository.getAllCustomers()) {
            customerNames.add(c.getCustomerName());
        }
        ArrayAdapter<String> customerNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, customerNames);
        customerNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editCustomer.setAdapter(customerNameAdapter);
        for (Customers c : repository.getAllCustomers()) {
            if (c.getCustomerID() == customerID)
                editCustomer.setSelection(customerNameAdapter.getPosition(c.getCustomerName()));
        }

        repository = new Repository(getApplication());
        List<String> equipNames = new ArrayList<>();
        for (Equipment e : repository.getAllEquipment()) {
            equipNames.add(e.getEquipmentName());
        }
        ArrayAdapter<String> equipNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, equipNames);
        equipNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editEquipment.setAdapter(equipNameAdapter);
        for (Equipment e : repository.getAllEquipment()) {
            if (e.getEquipmentID() == equipmentID)
                editEquipment.setSelection(equipNameAdapter.getPosition(e.getEquipmentName()));
        }

        editStartDate.setText(tripStart);
        editEndDate.setText(tripEnd);
        editTripNotes.setText(tripNotes);



        RecyclerView recyclerViewCustomer = findViewById(R.id.custAssignedRecView);
        repository = new Repository(getApplication());
        final TripsCustomerAdapter tripsCustomerAdapter = new TripsCustomerAdapter(this);
        recyclerViewCustomer.setAdapter(tripsCustomerAdapter);
        recyclerViewCustomer.setLayoutManager(new LinearLayoutManager(this));
        List<Customers> filteredCustomers = new ArrayList<>();
        tripsCustomerAdapter.setCustomers(filteredCustomers);
        for (Customers c : repository.getAllCustomers()) {
            if (c.getCustomerID() == customerID) filteredCustomers.add(c);
        }

        RecyclerView recyclerViewGuide = findViewById(R.id.guideAssignedRecView);
        repository = new Repository(getApplication());
        final TripsGuideAdapter tripsGuideAdapter = new TripsGuideAdapter(this);
        recyclerViewGuide.setAdapter(tripsGuideAdapter);
        recyclerViewGuide.setLayoutManager(new LinearLayoutManager(this));
        List<Guides> filteredGuides = new ArrayList<>();
        tripsGuideAdapter.setGuides(filteredGuides);
        for (Guides g : repository.getAllGuides()) {
            if (g.getGuideID() == guideID) filteredGuides.add(g);
        }

        RecyclerView recyclerViewEquip = findViewById(R.id.equipAssignedRecView);
        repository = new Repository(getApplication());
        final TripsEquipmentAdapter tripsEquipmentAdapter = new TripsEquipmentAdapter(this);
        recyclerViewEquip.setAdapter(tripsEquipmentAdapter);
        recyclerViewEquip.setLayoutManager(new LinearLayoutManager(this));
        List<Equipment> filteredEquipment = new ArrayList<>();
        tripsEquipmentAdapter.setEquipment(filteredEquipment);
        for (Equipment e : repository.getAllEquipment()) {
            if (e.getEquipmentID() == equipmentID) filteredEquipment.add(e);
        }

        //From DatePicker Video Presented by Carolyn J. Sher-Decusatis
        editStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date date;
                String info = editStartDate.getText().toString();
                try {
                    myCalendarStart.setTime(simpleDateFormat.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TripDetails.this, tStartDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //From DatePicker Video Presented by Carolyn J. Sher-Decusatis
        tStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthofYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelStart();
            }
        };

        //From DatePicker Video Presented by Carolyn J. Sher-Decusatis
        editEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(TripDetails.this, tEndDate, myCalendarStart.get(Calendar.YEAR),
                        myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //From DatePicker Video Presented by Carolyn J. Sher-Decusatis
        tEndDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthofYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
                updateLabelEnd();
            }
        };

        Button button = findViewById(R.id.tripSaveBtn);
        button.setOnClickListener(view -> {
            for (Guides g : repository.getAllGuides()) {
                if (g.getGuideName().equals(editGuide.getSelectedItem())) {
                    guideID = g.getGuideID();
                }
            }

            for (Customers c : repository.getAllCustomers()) {
                if (c.getCustomerName().equals(editCustomer.getSelectedItem())) {
                    customerID = c.getCustomerID();
                }
            }
            for (Equipment e : repository.getAllEquipment()) {
                if (e.getEquipmentName().equals(editEquipment.getSelectedItem())) {
                    equipmentID = e.getEquipmentID();
                }
            }
            //Validation check for valid date
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            try {
                Date startDate = sdf.parse(editStartDate.getText().toString());
                Date endDate = sdf.parse(editEndDate.getText().toString());
                Long dateDifference = Math.abs(endDate.getTime() - startDate.getTime());
                Long dateDifferenceInDays = TimeUnit.DAYS.convert(dateDifference, TimeUnit.MILLISECONDS);

                //Check to ensure no guides, or equipment are assigned on trips with overlapping dates
                boolean isOverlapping = false;
                for (Trips t : repository.getAllTrips()) {
                    Date tStartDate = sdf.parse(t.getTripStart());
                    Date tEndDate = sdf.parse(t.getTripEnd());
                    if ((startDate.after(tStartDate) && startDate.before(tEndDate))
                            || (endDate.after(tStartDate) && endDate.before(tEndDate))) {
                        isOverlapping = true;
                    }
                }
                if (isOverlapping) {
                    Toast.makeText(getApplicationContext(), "Dates cannot overlap!", Toast.LENGTH_SHORT).show();
                } else {
                    //Validation check to ensure the customerGroupTotal doesn't exceed the equipmentMaxCapacity
                    int customerGroupTotal = 0;
                    for (Customers c : repository.getAllCustomers()) {
                        if (c.getCustomerName().equals(editCustomer.getSelectedItem())) {
                            customerGroupTotal= c.getCustomerGroupTotal();
                        }
                    }
                    int equipmentMaxCapacity = 0;
                    for (Equipment e : repository.getAllEquipment()) {
                        if (e.getEquipmentName().equals(editEquipment.getSelectedItem())) {
                            equipmentMaxCapacity = e.getEquipmentMaxCapacity();
                        }
                    }
                    if (customerGroupTotal > equipmentMaxCapacity) {
                        Toast.makeText(getApplicationContext(), "Customer group total exceeds equipment maximum capacity!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (startDate.after(endDate)) {
                            Toast.makeText(getApplicationContext(), "Start date must be before end date!", Toast.LENGTH_SHORT).show();
                        } else {
                            String tripName = editTripName.getSelectedItem().toString();
                            if (tripName.equals("The OverNighter") && dateDifferenceInDays != 1) {
                                Toast.makeText(getApplicationContext(), "Date range must be 2 days for this trip!", Toast.LENGTH_SHORT).show();
                            } else if (tripName.equals("Three on Class Three") && dateDifferenceInDays != 2) {
                                Toast.makeText(getApplicationContext(), "Date range must be 3 days for this trip!", Toast.LENGTH_SHORT).show();
                            } else if (tripName.equals("Five Alive") && dateDifferenceInDays != 4) {
                                Toast.makeText(getApplicationContext(), "Date range must be 5 days for this trip!", Toast.LENGTH_SHORT).show();
                            } else if (tripName.equals("The Triple Sevens") && dateDifferenceInDays != 6) {
                                Toast.makeText(getApplicationContext(), "Date range must be 7 days for this trip!", Toast.LENGTH_SHORT).show();
                            } else if (tripName.equals("Extreme Ten") && dateDifferenceInDays != 9) {
                                Toast.makeText(getApplicationContext(), "Date range must be 10 days for this trip!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (tripID == -1) {

                                    trip = new Trips(0, editTripName.getSelectedItem().toString(), editLocation.getSelectedItem().toString(),
                                            guideID, customerID, equipmentID, editStartDate.getText().toString(), editEndDate.getText().toString(),
                                            editTripNotes.getText().toString());
                                    repository.insert(trip);
                                    Toast.makeText(getApplicationContext(), "New Trip added!", Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {
                                    trip = new Trips(tripID, editTripName.getSelectedItem().toString(), editLocation.getSelectedItem().toString(),
                                            guideID, customerID, equipmentID, editStartDate.getText().toString(), editEndDate.getText().toString(),
                                            editTripNotes.getText().toString());
                                    repository.update(trip);
                                    Toast.makeText(getApplicationContext(), "Trip updated!", Toast.LENGTH_SHORT).show();
                                    finish();

                                }
                            }
                        }
                    }
                    }
                } catch(ParseException e){
                    Toast.makeText(getApplicationContext(), "Invalid Date entered! Please try again.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }




        });

        Button textNotesGuideButton = findViewById(R.id.textNotesToGuideBtn);
        textNotesGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get guide phone number
                String guidePhone = "";
                for (Guides g : repository.getAllGuides()) {
                    if (g.getGuideID() == guideID) {
                        guidePhone = g.getGuidePhone();
                        break;
                    }
                }

                // Get trip notes
                String tripNotes = "";
                for (Trips t : repository.getAllTrips()) {
                    if (t.getTripID() == tripID) {
                        tripNotes = t.getTripNotes();
                        break;
                    }
                }

                // Launch text message intent with customer phone number and trip notes populated
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:" + guidePhone));
                intent.putExtra("sms_body", tripNotes);
                startActivity(intent);
            }


        });

        Button deleteTripButton = findViewById(R.id.tripDeleteBtn);
        deleteTripButton.setOnClickListener(view -> {
            for (Trips trips : repository.getAllTrips()) {
                if (trips.getTripID() == tripID) currentTrip = trips;
            }
            repository.delete(currentTrip);
            Toast.makeText(TripDetails.this, currentTrip.getTripName() + " was deleted", Toast.LENGTH_LONG).show();
            finish();
        });

    }



    private void updateLabelStart() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editStartDate.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editEndDate.setText(sdf.format(myCalendarStart.getTime()));
    }
}