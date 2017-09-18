package i.subodh.iwords;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_manage extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "iWORDs-MANAGE";
    DataManager dm;
    String add_delete_word = "";
    Button btnAdd;
    Button btnDelete;
    EditText editWord;
    TextView txtAddDelete;
    SharedPreferences myPerfs;
    SharedPreferences.Editor myPerfEditor;

    private void addWord(String word){
        Log.d(TAG, "Inside the addWord call");
        dm.insertWord(word.split(" ")[0]);

    }
    private void deleteWord(String word){
        Log.d(TAG, "Inside the deleteWord call");
        dm.deleteWord(word.split(" ")[0]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "on Create of Manage now");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage);

        dm = new DataManager(this);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        editWord = (EditText) findViewById(R.id.edtWord);
        txtAddDelete = (TextView) findViewById(R.id.txtAddDelete);
        txtAddDelete.setText("To add or delete a word just type it and click the Add/Delete Button.");

        //Saved Prefrences
        myPerfs = getSharedPreferences("iWords", MODE_PRIVATE);
        myPerfEditor = myPerfs.edit();

        Log.d(TAG, "Done initializing variable in Manage");
    }
    @Override
    public void onClick(View v){
        Log.d(TAG, "Button clicked ...handling");
        switch(v.getId()){
            case R.id.btnAdd:
                add_delete_word = editWord.getText().toString();
                Log.d(TAG, "Word to ADD was : "+ add_delete_word);
                addWord(add_delete_word);
                editWord.setText("");
                Toast.makeText(this,"Added the word : "+ add_delete_word,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDelete:
                add_delete_word = editWord.getText().toString();
                Log.d(TAG, "Word to DELETE was : "+ add_delete_word);
                deleteWord(add_delete_word);
                editWord.setText("");
                Toast.makeText(this,"Deleted the word : "+ add_delete_word,Toast.LENGTH_SHORT).show();
                break;
        }
    }
/*
Handing Menu option below
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.word_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Menu clicked...handling");
        Intent manage;
        switch (item.getItemId()) {
            case R.id.itmTwo:
                myPerfEditor.putInt("currentWord", 2);
                myPerfEditor.commit();
                 manage = new Intent(this, MainActivity.class);
                startActivity(manage);
                break;
            case R.id.itmThree:
                myPerfEditor.putInt("currentWord", 3);
                myPerfEditor.commit();
                 manage = new Intent(this, MainActivity.class);
                startActivity(manage);
                break;
            case R.id.itmFour:
                myPerfEditor.putInt("currentWord", 4);
                myPerfEditor.commit();
                 manage = new Intent(this, MainActivity.class);
                startActivity(manage);
                break;
            case R.id.itmFive:
                myPerfEditor.putInt("currentWord", 5);
                myPerfEditor.commit();
                 manage = new Intent(this, MainActivity.class);
                startActivity(manage);
                break;
            case R.id.itmAll:
                myPerfEditor.putInt("currentWord", 6);
                myPerfEditor.commit();
                 manage = new Intent(this, MainActivity.class);
                startActivity(manage);
                break;
            case R.id.itmManage:
                Toast.makeText(this,"We are already on Manage Page",Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
