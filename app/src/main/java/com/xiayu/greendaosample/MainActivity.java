package com.xiayu.greendaosample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.greenrobot.greendao.query.Query;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private NoteDao noteDao;
    private Query<Note> notesQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        noteDao = daoSession.getNoteDao();

        // query all notes, sorted a-z by their text
        notesQuery = noteDao.queryBuilder().orderAsc(NoteDao.Properties.Text).build();
        addNote();

        List<Note> notes = notesQuery.list();

        
        Log.d(TAG, "notes:size:"+notes.size() + notes);
    }

    private void addNote() {


        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());

        Note note = new Note();
        note.setText("first");
        note.setComment(comment);
        note.setDate(new Date());
        noteDao.insert(note);
        Log.d("DaoExample", "Inserted new note, ID: " + note.getId());
    }

}
