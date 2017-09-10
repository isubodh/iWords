package i.subodh.iwords;

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
    private static final String twoLetterWords []
            = {"ah","am","an","as","at","be","by","do","go","he",
            "hi","if","in","is","it","me","my","no","of","oh",
            "ok","on","or","ox","so","to","up","us","we"};

    private static final String threeLetterWords []
            = {"ace","act","add","ado","age","ago","aha","aid","aim",
            "air","all","and","ant","any","ape","apt","are",
            "ark","arm","art","ask","ass","ate","axe" };
    private static final String fourLetterWords []
            = {"face","fact","this","that","went","goto","wild","skin","milk",
            "Lion","fall","find","moon","many","bake","dark","cake",
            "kick","sand","bite","bend","wind","tree","look" };
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
        }
        return true;
    }
}
