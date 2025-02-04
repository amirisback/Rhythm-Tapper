package sma.rhythmtapper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import sma.rhythmtapper.game.NoteFile.NoteFile;


public class DifficultySelectionActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "DifficultySelection";
    private Button btnEasy;
    private Button btnMid;
    private Button btnHard;

    private ListView lvSongs;
    private SongListViewAdapter adapter;

    /*private final Difficulty _diffEasy =
            new Difficulty(Difficulties.EASY, "Spyro_Year_of_the_Dragon_Acoustic_Fields_OC_ReMix.mp3", 115f / 2, 8);
    private final Difficulty _diffMid =
            new Difficulty(Difficulties.NORMAL, "super_meat_boy_power_of_the_meat.mp3", 128, 10);
    private final Difficulty _diffHard =
            new Difficulty(Difficulties.HARD, "Aquaria_Minibadass_OC_ReMix.mp3", 180, 15);
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection);

        //this.btnEasy = (Button) this.findViewById(R.id.diff_btn_easy);
        //this.btnEasy.setOnClickListener(this);
        //this.btnMid = (Button) this.findViewById(R.id.diff_btn_mid);
        //this.btnMid.setOnClickListener(this);
        //this.btnHard = (Button) this.findViewById(R.id.diff_btn_hard);
        //this.btnHard.setOnClickListener(this);
        lvSongs = (ListView) findViewById(R.id.listViewSongs);
        lvSongs.setAdapter(adapter = new SongListViewAdapter(this));

        String path = getIntent().getStringExtra("path");
        Log.d(TAG, "Path:" + path);
//        File dir = new File(Environment.getExternalStorageDirectory(), "TempestWave");
        File dir = new File(path);
        if (!dir.exists()) {
            Log.d(TAG, "Dir does not exist");
        } else {
            File[] files = dir.listFiles();
            if (files == null) {
                Log.e(TAG, "WWWW");
            } else {
                for (File file : files) {
                    Log.d(TAG, file.getName());
                }
            }
        }
        dir = new File(dir, "Songs");
        dir.mkdirs();
        ArrayList<NoteFile> noteFiles = new ArrayList<>();
        File[] songs = dir.listFiles();
        if (songs == null) {
            Log.d(TAG, "Cannot find songs");
            return;
        }
        if (songs.length == 0) {
            //unpack original songs to the dir
            Log.d(TAG, "No songs found");
        } else {
            for (File song : songs) {
                if (song.isDirectory()) {
                    try {
                        noteFiles.add(new NoteFile(song));
                    } catch (RuntimeException e) {
                        Log.e(TAG, "Notefile: " + song.getName(), e);
                    }
                }
                //Toast.makeText(this,song.getName(),Toast.LENGTH_SHORT).show();
            }
        }
        adapter.addAll(noteFiles);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        /*switch (v.getId()) {
            case R.id.diff_btn_easy:
                i = new Intent(this, GameActivity.class);
                i.putExtra("difficulty", this._diffEasy);
                this.startActivity(i);
                break;
            case R.id.diff_btn_mid:
                i = new Intent(this, GameActivity.class);
                i.putExtra("difficulty", this._diffMid);
                this.startActivity(i);
                break;
            case R.id.diff_btn_hard:
                i = new Intent(this, GameActivity.class);
                i.putExtra("difficulty", this._diffHard);
                this.startActivity(i);
                break;
            default:
                Log.e("", "unexpected id!");
        }*/
    }
}
