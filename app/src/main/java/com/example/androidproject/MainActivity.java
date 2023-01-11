package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settings:
                Toast.makeText(this,"setting is clicked ",Toast.LENGTH_SHORT).show();
                break;

            case R.id.groupchat:
                Toast.makeText(this,"group chat is started",Toast.LENGTH_SHORT).show();
                break;

            case R.id.Logout:
                mAuth.signOut();
                Intent intent=new Intent(MainActivity.this,signin.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
