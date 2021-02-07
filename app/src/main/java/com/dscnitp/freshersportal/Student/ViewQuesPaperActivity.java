package com.dscnitp.freshersportal.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.dscnitp.freshersportal.Adapter.AdapterMaterials;
import com.dscnitp.freshersportal.Model.PdfFileInfo;
import com.dscnitp.freshersportal.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import am.appwise.components.ni.NoInternetDialog;

public class ViewQuesPaperActivity extends AppCompatActivity {

    RecyclerView quesPaperListrecyclerView;
    AdapterMaterials adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ques_paper);


        quesPaperListrecyclerView = (RecyclerView) findViewById(R.id.ques_paper_list);
        quesPaperListrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PdfFileInfo> options = new FirebaseRecyclerOptions.Builder<PdfFileInfo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Question-papers"),PdfFileInfo.class).build();

        adapter = new AdapterMaterials(options);
        quesPaperListrecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_file_menu,menu);

        MenuItem item = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {
        //FirebaseRecyclerOptions<PdfFileInfo> options = new FirebaseRecyclerOptions.Builder<PdfFileInfo>()
        //       .setQuery(FirebaseDatabase.getInstance().getReference()
        //             .child("Question-papers").orderByChild("filename").startAt(s).endAt(s+"\uf8ff"),PdfFileInfo.class).build();

        //adapter = new AdapterMaterials(options);
        //adapter.startListening();
        //pdfListrecyclerView.setAdapter(adapter);

    }
}