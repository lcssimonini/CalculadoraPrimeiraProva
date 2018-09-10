package simonini.com.br.calculadoraprimeiraprova;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

import static java.lang.String.format;

public class MainActivity extends Activity {

    DecimalFormat formato = new DecimalFormat("0.00");
    EditText visor;
    Integer operando1;
    Integer operando2;
    String resultado;
    String operador;
    Boolean equalsClicked = Boolean.FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        visor = findViewById(R.id.visor_id);
    }

    public void acao(View v) {
        Button tecla = (Button) v;
        switch (tecla.getText().toString()) {
            case "=":
                equalsClicked = Boolean.TRUE;
                if (operador != null && visor.getText().length() > 0) {
                    operando2 = Integer.parseInt(visor.getText().toString());
                    switch (operador) {
                        case "/":
                            resultado = formato.format(operando1 * 1.0 / operando2);
                            visor.setText(format("%s%s", getString(R.string.resultado), resultado));
                            break;
                        case "*":
                            resultado = Integer.valueOf(operando1 * operando2).toString();
                            visor.setText(format("%s%s", getString(R.string.resultado), resultado));
                            break;
                        case "-":
                            resultado = Integer.valueOf(operando1 - operando2).toString();
                            visor.setText(String.format("%s%s", getString(R.string.resultado), resultado));
                            break;
                        case "+":
                            resultado = Integer.valueOf(operando1 + operando2).toString();
                            visor.setText(String.format("%s%s", getString(R.string.resultado), resultado));
                            break;
                    }
                }
                break;
            case "/":
                setOperador((Button) v);
                break;
            case "*":
                setOperador((Button) v);
                break;
            case "-":
                setOperador((Button) v);
                break;
            case "M":
                if (equalsClicked) {
                    visor.setText(String.format(" %s %s %s = %s ", operando1, operador, operando2, resultado));
                    equalsClicked = Boolean.FALSE;
                }
                break;
            case "C":
                visor.setText("");
                operador = "";
                operando1 = null;
                operando2 = null;
                resultado = null;
                break;
            case "+":
                setOperador((Button) v);
                break;
            default:
                equalsClicked = Boolean.FALSE;
                if(visor.getText().toString().contains(getString(R.string.resultado)))
                    visor.setText("");
                visor.append(((Button) v).getText());
                break;
        }
    }

    private void setOperador(Button v) {
        if (visor.getText().length() > 0) {
            operando1 = Integer.parseInt(visor.getText().toString());
            operador = v.getText().toString();
            visor.setText("");
        }
    }
}

