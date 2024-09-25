package br.uniceub.cc.pdm.fitcalcapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // ----------------------------- ÁREA DE ATRIBUTOS -----------------------------
    // Atributos para Calculadora de IMC
    private Button btnCalcularImc, btnIrParaPesoIdeal, btnIrParaAlturaIdeal;
    private EditText edtAlturaImc, edtPesoImc;
    private TextView txtResultadoImc;
    private RadioGroup radioGroupSexoImc;
    private RadioButton radioMasculinoImc, radioFemininoImc;

    // Atributos para Calculadora de Peso Ideal
    private Button btnCalcularPesoIdeal;
    private EditText edtAlturaPesoIdeal;
    private TextView txtResultadoPesoIdeal;
    private RadioGroup radioGroupSexoPesoIdeal;

    // Atributos para Calculadora de Altura Ideal (Meme)
    private Button btnCalcularAlturaIdeal;
    private EditText edtPesoAlturaIdeal;
    private TextView txtResultadoAlturaIdeal;
    private RadioGroup radioGroupSexoAlturaIdeal;

    // ----------------------------- ÁREA DE MÉTODOS -----------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CarregarTelaPrincipal();  // Chama o método para carregar a tela principal
    }

    // ---------------------- Métodos para Navegação entre Telas ----------------------
    public void CarregarTelaPrincipal() {
        setContentView(R.layout.tela_principal);

        btnIrParaPesoIdeal = findViewById(R.id.btnPesoIdeal);
        btnIrParaAlturaIdeal = findViewById(R.id.btnAlturaIdeal);

        btnIrParaPesoIdeal.setOnClickListener(view -> CarregarCalculadoraPesoIdeal());
        btnIrParaAlturaIdeal.setOnClickListener(view -> CarregarCalculadoraAlturaIdeal());
        btnCalcularImc = findViewById(R.id.btnIMC);

        btnCalcularImc.setOnClickListener(view -> CarregarCalculadoraIMC());
    }

    // Carrega a Calculadora de IMC
    public void CarregarCalculadoraIMC() {
        setContentView(R.layout.calculadora_imc);
        btnCalcularImc = findViewById(R.id.btnCalcularImc);
        edtAlturaImc = findViewById(R.id.edtAlturaImc);
        edtPesoImc = findViewById(R.id.edtPesoImc);
        txtResultadoImc = findViewById(R.id.txtResultadoImc);
        radioGroupSexoImc = findViewById(R.id.radioGroupSexoImc);

        btnCalcularImc.setOnClickListener(view -> calcularImc());
    }

    // Carrega a Calculadora de Peso Ideal
    public void CarregarCalculadoraPesoIdeal() {
        setContentView(R.layout.calculadora_peso_ideal);
        btnCalcularPesoIdeal = findViewById(R.id.btnCalcularPesoIdeal);
        edtAlturaPesoIdeal = findViewById(R.id.edtAlturaPesoIdeal);
        txtResultadoPesoIdeal = findViewById(R.id.txtResultadoPesoIdeal);
        radioGroupSexoPesoIdeal = findViewById(R.id.radioGroupSexoPesoIdeal);

        btnCalcularPesoIdeal.setOnClickListener(view -> calcularPesoIdeal());
    }

    // Carrega a Calculadora de Altura Ideal (Meme)
    public void CarregarCalculadoraAlturaIdeal() {
        setContentView(R.layout.calculadora_altura_ideal);
        btnCalcularAlturaIdeal = findViewById(R.id.btnCalcularAlturaIdeal);
        edtPesoAlturaIdeal = findViewById(R.id.edtPesoAlturaIdeal);
        txtResultadoAlturaIdeal = findViewById(R.id.txtResultadoAlturaIdeal);
        radioGroupSexoAlturaIdeal = findViewById(R.id.radioGroupSexoAlturaIdeal);

        btnCalcularAlturaIdeal.setOnClickListener(view -> calcularAlturaIdeal());
    }

    // ---------------------- Métodos de Cálculo ----------------------
    // Método para calcular o IMC
    public void calcularImc() {
        try {
            float altura = Float.parseFloat(edtAlturaImc.getText().toString());
            float peso = Float.parseFloat(edtPesoImc.getText().toString());
            int sexoId = radioGroupSexoImc.getCheckedRadioButtonId();
            boolean isMasculino = sexoId == R.id.radioMasculinoImc;

            float imc = peso / (altura * altura);
            String resultado;

            if (isMasculino) {
                if (imc < 18.5) resultado = "Abaixo do peso";
                else if (imc < 24.9) resultado = "Peso normal";
                else if (imc < 29.9) resultado = "Pré-obesidade";
                else if (imc < 34.9) resultado = "Obesidade Grau 1";
                else if (imc < 39.9) resultado = "Obesidade Grau 2";
                else resultado = "Obesidade Grau 3";
            } else {
                if (imc < 18.5) resultado = "Abaixo do peso";
                else if (imc < 26.9) resultado = "Peso normal";
                else if (imc < 32.9) resultado = "Pré-obesidade";
                else if (imc < 37.9) resultado = "Obesidade Grau 1";
                else if (imc < 44.9) resultado = "Obesidade Grau 2";
                else resultado = "Obesidade Grau 3";
            }

            txtResultadoImc.setText("IMC: " + String.format("%.2f", imc) + " - " + resultado);
        } catch (NumberFormatException e) {
            txtResultadoImc.setText("Por favor, insira valores válidos.");
        }
    }

    // Método para calcular o Peso Ideal
    public void calcularPesoIdeal() {
        try {
            float altura = Float.parseFloat(edtAlturaPesoIdeal.getText().toString());
            int sexoId = radioGroupSexoPesoIdeal.getCheckedRadioButtonId();
            float imcIdeal = (sexoId == R.id.radioMasculinoPesoIdeal) ? 21.7f : 22.7f;

            float pesoIdeal = imcIdeal * (altura * altura);
            txtResultadoPesoIdeal.setText("Peso Ideal: " + String.format("%.2f", pesoIdeal) + " kg");
        } catch (NumberFormatException e) {
            txtResultadoPesoIdeal.setText("Por favor, insira valores válidos.");
        }
    }

    // Método para calcular a Altura Ideal (Meme)
    public void calcularAlturaIdeal() {
        try {
            float peso = Float.parseFloat(edtPesoAlturaIdeal.getText().toString());
            int sexoId = radioGroupSexoAlturaIdeal.getCheckedRadioButtonId();
            float imcIdeal = (sexoId == R.id.radioMasculinoAlturaIdeal) ? 21.7f : 22.7f;

            float alturaIdeal = (float) Math.sqrt(peso / imcIdeal);
            txtResultadoAlturaIdeal.setText("Altura Ideal: " + String.format("%.2f", alturaIdeal) + " m");
        } catch (NumberFormatException e) {
            txtResultadoAlturaIdeal.setText("Por favor, insira valores válidos.");
        }
    }
}
