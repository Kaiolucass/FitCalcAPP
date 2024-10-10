package br.uniceub.cc.pdm.fitcalcapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Atributos para a Calculadora de IMC
    private Button btnCalcularImc, btnIrParaPesoIdeal, btnIrParaAlturaIdeal;
    private EditText edtAlturaImc, edtPesoImc;
    private TextView txtResultadoImc;
    private RadioGroup radioGroupSexoImc;

    // Atributos para a Calculadora de Peso Ideal
    private Button btnCalcularPesoIdeal;
    private EditText edtAlturaPesoIdeal;
    private TextView txtResultadoPesoIdeal;
    private RadioGroup radioGroupSexoPesoIdeal;

    // Atributos para a Calculadora de Altura Ideal
    private Button btnCalcularAlturaIdeal;
    private EditText edtPesoAlturaIdeal;
    private TextView txtResultadoAlturaIdeal;
    private RadioGroup radioGroupSexoAlturaIdeal;

    private float startX;
    private int currentScreen; // 0: Tela Principal, 1: IMC, 2: Peso Ideal, 3: Altura Ideal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentScreen = 0; // Começa na tela principal
        carregarTelaPrincipal();  // Chama o método para carregar a tela principal
    }

    // Métodos para Navegação entre Telas
    public void carregarTelaPrincipal() {
        setContentView(R.layout.tela_principal);
        currentScreen = 0;

        btnIrParaPesoIdeal = findViewById(R.id.btnPesoIdeal);
        btnIrParaAlturaIdeal = findViewById(R.id.btnAlturaIdeal);
        btnCalcularImc = findViewById(R.id.btnIMC);

        btnIrParaPesoIdeal.setOnClickListener(view -> carregarCalculadoraPesoIdeal());
        btnIrParaAlturaIdeal.setOnClickListener(view -> carregarCalculadoraAlturaIdeal());
        btnCalcularImc.setOnClickListener(view -> carregarCalculadoraIMC());

        // Adiciona o OnTouchListener ao layout principal
        findViewById(R.id.tela_principal).setOnTouchListener(this::handleSwipeGesture);
    }

    // Carrega a Calculadora de IMC
    public void carregarCalculadoraIMC() {
        setContentView(R.layout.calculadora_imc);
        currentScreen = 1;

        btnCalcularImc = findViewById(R.id.btnCalcularImc);
        edtAlturaImc = findViewById(R.id.edtAlturaImc);
        edtPesoImc = findViewById(R.id.edtPesoImc);
        txtResultadoImc = findViewById(R.id.txtResultadoImc);
        radioGroupSexoImc = findViewById(R.id.radioGroupSexoImc);

        // Botão Voltar
        Button btnVoltar = findViewById(R.id.btnVoltarIMC);
        btnVoltar.setOnClickListener(view -> carregarTelaPrincipal());

        btnCalcularImc.setOnClickListener(view -> calcularImc());

        // Adiciona o OnTouchListener ao layout de IMC
        findViewById(R.id.layoutCalculadoraIMC).setOnTouchListener(this::handleSwipeGesture);
    }

    // Carrega a Calculadora de Peso Ideal
    public void carregarCalculadoraPesoIdeal() {
        setContentView(R.layout.calculadora_peso_ideal);
        currentScreen = 2;

        btnCalcularPesoIdeal = findViewById(R.id.btnCalcularPesoIdeal);
        edtAlturaPesoIdeal = findViewById(R.id.edtAlturaPesoIdeal);
        txtResultadoPesoIdeal = findViewById(R.id.txtResultadoPesoIdeal);
        radioGroupSexoPesoIdeal = findViewById(R.id.radioGroupSexoPesoIdeal);

        // Botão Voltar
        Button btnVoltar = findViewById(R.id.btnVoltarPesoIdeal);
        btnVoltar.setOnClickListener(view -> carregarTelaPrincipal());

        btnCalcularPesoIdeal.setOnClickListener(view -> calcularPesoIdeal());

        // Adiciona o OnTouchListener ao layout de Peso Ideal
        findViewById(R.id.layoutCalculadoraPesoIdeal).setOnTouchListener(this::handleSwipeGesture);
    }

    // Carrega a Calculadora de Altura Ideal
    public void carregarCalculadoraAlturaIdeal() {
        setContentView(R.layout.calculadora_altura_ideal);
        currentScreen = 3;

        btnCalcularAlturaIdeal = findViewById(R.id.btnCalcularAlturaIdeal);
        edtPesoAlturaIdeal = findViewById(R.id.edtPesoAlturaIdeal);
        txtResultadoAlturaIdeal = findViewById(R.id.txtResultadoAlturaIdeal);
        radioGroupSexoAlturaIdeal = findViewById(R.id.radioGroupSexoAlturaIdeal);

        // Botão Voltar
        Button btnVoltar = findViewById(R.id.btnVoltarAlturaIdeal);
        btnVoltar.setOnClickListener(view -> carregarTelaPrincipal());

        btnCalcularAlturaIdeal.setOnClickListener(view -> calcularAlturaIdeal());

        // Adiciona o OnTouchListener ao layout de Altura Ideal
        findViewById(R.id.layoutCalculadoraAlturaIdeal).setOnTouchListener(this::handleSwipeGesture);
    }

    // Método para lidar com o gesto de deslizar
    private boolean handleSwipeGesture(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = motionEvent.getX();
                return true;

            case MotionEvent.ACTION_UP:
                float endX = motionEvent.getX();
                float deltaX = endX - startX;

                // Verifica se o movimento foi para a direita
                if (deltaX > 100) { // Ajuste o valor 100 conforme necessário
                    switch (currentScreen) {
                        case 1: // IMC para Tela Principal
                            carregarTelaPrincipal();
                            break;
                        case 2: // Peso Ideal para IMC
                            carregarCalculadoraIMC();
                            break;
                        case 3: // Altura Ideal para Peso Ideal
                            carregarCalculadoraPesoIdeal();
                            break;
                    }
                }
                // Verifica se o movimento foi para a esquerda
                else if (deltaX < -100) {
                    switch (currentScreen) {
                        case 0: // Tela Principal para IMC
                            carregarCalculadoraIMC();
                            break;
                        case 1: // IMC para Peso Ideal
                            carregarCalculadoraPesoIdeal();
                            break;
                        case 2: // Peso Ideal para Altura Ideal
                            carregarCalculadoraAlturaIdeal();
                            break;
                        case 3: // Altura Ideal para Tela Principal (opcional)
                            carregarTelaPrincipal();
                            break;
                    }
                }
                return true;

            default:
                return false;
        }
    }

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

    // Método para calcular a Altura Ideal
    public void calcularAlturaIdeal() {
        try {
            float peso = Float.parseFloat(edtPesoAlturaIdeal.getText().toString());
            int sexoId = radioGroupSexoAlturaIdeal.getCheckedRadioButtonId();
            float imcIdeal = (sexoId == R.id.radioMasculinoAlturaIdeal) ? 21.7f : 22.7f;

            float alturaIdeal = (peso / imcIdeal);
            txtResultadoAlturaIdeal.setText("Altura Ideal: " + String.format("%.2f", alturaIdeal) + " m");
        } catch (NumberFormatException e) {
            txtResultadoAlturaIdeal.setText("Por favor, insira valores válidos.");
        }
    }
}
