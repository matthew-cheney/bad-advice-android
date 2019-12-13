package master.cheney.badadvice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import master.cheney.badadvice.Adviser.BadAdvice;

public class MainActivity extends AppCompatActivity {

    BadAdvice badAdvice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        badAdvice = new BadAdvice();

        EditText question = findViewById(R.id.questionEditText);
        TextView answer = findViewById(R.id.answerTextView);

        Button askButton = findViewById(R.id.askButton);
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionString = question.toString();
                String advice = null;
                try {
                    advice = badAdvice.getAdvice(questionString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                answer.setText(advice);
            }
        });

    }
}
