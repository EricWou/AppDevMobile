package math.operation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import math.operation.model.MathOperation;

public class MainActivity extends AppCompatActivity {

    private EditText xVarEditText;
    private EditText yVarEditText;
    private TextView sumTextView;
    private TextView differenceTextView;
    private TextView productTextView;
    private TextView divisionTextView;
    private Button calculateButton;
    private double xVar;
    private double yVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calculateButton = (Button) findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xVarEditText = (EditText) findViewById(R.id.x_var_edit_text);
                yVarEditText = (EditText) findViewById(R.id.y_var_edit_text);

                xVar = Double.parseDouble(xVarEditText.getText().toString());
                yVar = Double.parseDouble(yVarEditText.getText().toString());

                MathOperation op1 = new MathOperation(xVar, yVar);

                String sum = "Sum: "+op1.calcSum();
                String difference = "Difference: "+op1.calcDifference();
                String product = "Product: "+op1.calcProduct();
                String division = "Division: "+String.format("%.2f", op1.calcDivision());

                sumTextView = (TextView) findViewById(R.id.sum_text_view);
                sumTextView.setText(sum);

                differenceTextView = (TextView) findViewById(R.id.difference_text_view);
                differenceTextView.setText(difference);

                productTextView = (TextView) findViewById(R.id.product_text_view);
                productTextView.setText(product);

                divisionTextView = (TextView) findViewById(R.id.division_text_view);
                divisionTextView.setText(division);
            }
        });
    }
}