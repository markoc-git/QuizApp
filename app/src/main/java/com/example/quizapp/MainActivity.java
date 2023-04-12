package com.example.quizapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    ArrayList<Questions> questions = new ArrayList<>();
    RecyclerView recyclerView;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finish = findViewById(R.id.finish);

        new FetchDataTask().execute();

    }

    @SuppressLint("StaticFieldLeak")
    class FetchDataTask extends AsyncTask<Void, Void, String> {
        private static final String API_URL = "https://opentdb.com/api.php?amount=10&type=multiple";

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(API_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Read response from the API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }

                reader.close();
                connection.disconnect();

                return responseBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }



        @Override
        protected void onPostExecute(String response) {
            // Parse the response and do something with the data
            recyclerView = findViewById(R.id.questionVIew);

            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray results = jsonObject.getJSONArray("results");
                    //Iterate through the results and extract the data

                    questions.clear();
                    for(int i=0;i<results.length();i++){
                        //   Log.d("number",results.length() + "");
                        JSONObject result = results.getJSONObject(i);
                        String question = result.getString("question");
                        JSONArray incorrectAnswers = result.getJSONArray("incorrect_answers");
                        String correctAnswer = result.getString("correct_answer");
                        String inCorrect1 = incorrectAnswers.get(0).toString();
                        String inCorrect2 = incorrectAnswers.get(1).toString();
                        String inCorrect3 = incorrectAnswers.get(2).toString();
                        // Do something with the extracted data
                        Questions questions1 = new Questions(question,correctAnswer,inCorrect1,inCorrect2,inCorrect3);
                        questions.add(questions1);




                    }

                    finish.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Thank you for taking this quiz",
                            Toast.LENGTH_SHORT).show());

                    recyclerView.setAdapter(new QuestionsAdapterBuilder().setQuestions(questions).setContext(getBaseContext()).createQuestionsAdapter());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}