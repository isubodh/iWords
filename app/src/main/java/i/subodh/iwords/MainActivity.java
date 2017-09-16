package i.subodh.iwords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "iWORDs";

    String[] toDisplayList ;
    Button btnNext;
    Button btnPrevious;
    TextView txtWord;
    private int currentWord = 4;
    DataManager dm;

    private void jumbleUp(String [] toShow){
        Log.d(TAG, "Jumbling up the list to display");
        currentWord = 0;
        Random random = new Random();
        String temp;
        for(int i=0; i<toShow.length;i++){
            int rand = random.nextInt(toShow.length);
            temp = toShow[rand];
            toShow[rand] = toShow[i];
            toShow[i] = temp;
        }
        toDisplayList = Arrays.copyOf(toShow, toShow.length);
    }

    private void displayWord(){
        Log.d(TAG, "Updating the display word");
        String word = dm.getWord(currentWord);

        Log.d(TAG, "Returned Word is : "+ word);
        txtWord.setText(word);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "on Create now");
        super.onCreate(savedInstanceState);
        dm = new DataManager(this);

        setContentView(R.layout.activity_main);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(this);
        txtWord = (TextView) findViewById(R.id.txtWord);
        Log.d(TAG, "Done initializing variable");


        //jumbleUp(twoLetterWords);
        displayWord();
    }
    @Override
    public void onClick(View v){
        Log.d(TAG, "Button clicked ...handling");
        switch(v.getId()){
            case R.id.btnNext:
                displayWord();
                break;
            case R.id.btnPrevious:
                displayWord();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.word_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Menu clicked...handling");
        switch (item.getItemId()) {
            case R.id.itmTwo:
                currentWord = 2;
                displayWord();
                break;
            case R.id.itmThree:
                currentWord = 3;
                displayWord();
                break;
            case R.id.itmFour:
                currentWord = 4;
                displayWord();
                break;
            case R.id.itmFive:
                currentWord =5 ;
                break;
            case R.id.itmAll:
                currentWord = 6;
                break;
            case R.id.itmManage:
                Intent manage = new Intent(this, activity_manage.class);
                startActivity(manage);
                break;

        }
        return true;
    }
}
