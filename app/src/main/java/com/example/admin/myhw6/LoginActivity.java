package com.example.admin.myhw6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.myhw6.Model.User;
import com.example.admin.myhw6.Model.UserRepository;

import java.util.List;


public class LoginActivity extends AppCompatActivity {



    //Declaration EditTexts
    EditText editTextName;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;
    Button buttonSignUp;
    Button buttonGuest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextName =  findViewById(R.id.login_userName);
        editTextPassword =  findViewById(R.id.editTextPassword);
        textInputLayoutEmail =  findViewById(R.id.text);
        textInputLayoutPassword =  findViewById(R.id.textInputLayoutPassword);
        buttonLogin =  findViewById(R.id.buttonLogin);
        buttonSignUp =  findViewById(R.id.buttonSignUp);
        buttonGuest =  findViewById(R.id.buttonGuest);


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myInt=UserDetailActivity.newIntent(LoginActivity.this);
                startActivity(myInt);

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = editTextName.getText().toString();
                String Password = editTextPassword.getText().toString();


//                Long correctUserId = UserRepository.getInstance(LoginActivity.this).userExistId(Name,Password);

                List<User> userList = UserRepository.getInstance(LoginActivity.this).getUserByName(Name);

                if (userList != null) {
                    for (int i = 0; i < userList.size(); i++) {
                        User user = userList.get(i);

                        Long correctUserId = user.getUserId();

                        if (user.getPassword().equals(Password)) {

//                        Toast.makeText(LoginActivity.this, "true user: ", Toast.LENGTH_SHORT).show();
                            Intent myInt = MainActivity.newIntent(LoginActivity.this, correctUserId);
                            startActivity(myInt);
                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Please Check Your Password...", Toast.LENGTH_SHORT).show();

                        }

                    }
                }
                    else{

                        // buttonLogin.setEnabled(false);
                        Toast.makeText(LoginActivity.this, "Please First Create Your User", Toast.LENGTH_SHORT).show();

                    }


            }
        });

        buttonGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long guest=Long.MAX_VALUE;
                Intent intent=MainActivity.newIntent(LoginActivity.this,guest);
                Toast.makeText(LoginActivity.this, "max long"+guest.toString(), Toast.LENGTH_SHORT).show();

                startActivity(intent);

            }
        });




//            sqliteHelper = new SqliteHelper(this);
//            initCreateAccountTextView();

        //set click event of login button
//            buttonLogin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    //Check user input is correct or not
//                    if (validate()) {
//
//                        //Get values from EditText fields
//                        String Email = editTextEmail.getText().toString();
//                        String Password = editTextPassword.getText().toString();
//
//                        //Authenticate user
//                        User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));
//
//                        //Check Authentication is successful or not
//                        if (currentUser != null) {
//                            Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
//
//                            //User Logged in Successfully Launch You home screen activity
//                       Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                        } else {
//
//                            //User Logged in Failed
//                            Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();
//
//                        }
//                    }
//                }
//            });


    }

    //this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
//        private void initCreateAccountTextView() {
//            TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
//            textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
//            textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                    startActivity(intent);
//                }
//            });
//        }

    //this method is used to connect XML views to its Objects
//    private void initViews() {


}

//This method is for handling fromHtml method deprecation
//        @SuppressWarnings("deprecation")
//        public static Spanned fromHtml(String html) {
//            Spanned result;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
//            } else {
//                result = Html.fromHtml(html);
//            }
//            return result;
//        }

//This method is used to validate input given by user
//        public boolean validate() {
//            boolean valid = false;
//
//            //Get values from EditText fields
//            String Email = editTextEmail.getText().toString();
//            String Password = editTextPassword.getText().toString();
//
//            //Handling validation for Email field
//            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
//                valid = false;
//                textInputLayoutEmail.setError("Please enter valid email!");
//            } else {
//                valid = true;
//                textInputLayoutEmail.setError(null);
//            }
//
//            //Handling validation for Password field
//            if (Password.isEmpty()) {
//                valid = false;
//                textInputLayoutPassword.setError("Please enter valid password!");
//            } else {
//                if (Password.length() > 5) {
//                    valid = true;
//                    textInputLayoutPassword.setError(null);
//                } else {
//                    valid = false;
//                    textInputLayoutPassword.setError("Password is to short!");
//                }
//            }
//
//            return valid;
//        }



