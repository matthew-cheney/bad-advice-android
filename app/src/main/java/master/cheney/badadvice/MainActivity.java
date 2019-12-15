package master.cheney.badadvice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import master.cheney.badadvice.models.AdviceResult;

import static master.cheney.badadvice.json_util.JsonConverter.jsonToModel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText question = findViewById(R.id.questionEditText);
        // TextView answer = findViewById(R.id.answerTextView);

        Button askButton = findViewById(R.id.askButton);
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                String questionString = question.getText().toString();
                GetAdviceTask task = new GetAdviceTask();
                task.execute(questionString);

                // String advice = null;
                // advice = new ServerProxy().getAdvice(questionString);
                // answer.setText(advice);
            }
        });

    }

    private class GetAdviceTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... questions) {

            ServerProxy serverProxy = new ServerProxy();

            String raw_advice = serverProxy.getAdvice(questions[0]);

            AdviceResult adviceResult = jsonToModel(raw_advice, AdviceResult.class);

            return adviceResult.getAdvice();
        }

        @Override
        protected void onPostExecute(String advice) {
            TextView answer = findViewById(R.id.answerTextView);
            answer.setText(advice);
        }
    }
}
