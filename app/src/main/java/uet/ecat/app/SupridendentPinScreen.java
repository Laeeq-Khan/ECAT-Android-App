package uet.ecat.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SupridendentPinScreen extends AppCompatActivity {

    EditText supridentPin;
    Button supridentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supridendent_pin_screen);

        registerControls();
        events();
    }

    private void registerControls(){
        supridentPin = findViewById(R.id.supridentpinfield);
        supridentButton = findViewById(R.id.supridentloginbutton);
    }
    private void events(){
        supridentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = supridentPin.getText().toString();
                if(pin.equalsIgnoreCase("123")){
                    Intent intent = new Intent(SupridendentPinScreen.this, ExamInstructionsScreen.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SupridendentPinScreen.this, "Wrong Pin", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}