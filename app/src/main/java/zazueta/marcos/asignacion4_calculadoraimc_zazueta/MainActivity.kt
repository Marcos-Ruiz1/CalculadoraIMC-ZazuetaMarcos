package zazueta.marcos.asignacion4_calculadoraimc_zazueta
import kotlin.math.pow
import android.widget.Toast
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

var estaturaNumber:Float = 0.0F
var pesoNumber: Float = 0.0F
var categoria: String = "undefined"
var color: Int = R.color.white;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnCalcular: Button = findViewById(R.id.btnCalcular);
        val estaturaValue: EditText = findViewById(R.id.etAltura);
        val pesoValue: EditText = findViewById(R.id.etPeso);
        val imc: TextView = findViewById(R.id.imc);
        val range: TextView = findViewById(R.id.range);

        btnCalcular.setOnClickListener{


            var estatura = estaturaValue.text.toString();
            var peso = pesoValue.text.toString()

            if(!(estatura.isEmpty() || peso.isEmpty())){

                estaturaNumber = estatura.toFloat()
                pesoNumber = peso.toFloat()
                var imcValue = CalculateIMC(estaturaNumber, pesoNumber);
                var(categoriaLocal, colorLocal) = DeterminateRange(imcValue);

                categoria = categoriaLocal;
                color = colorLocal

                imc.setText(imcValue.toString());
                range.setText(categoria);
                range.setBackgroundResource(color);

                imc.visibility = View.VISIBLE;
                range.visibility = View.VISIBLE;

            }else{
                imc.setText("ingrese altura y peso valido");
                imc.visibility= View.VISIBLE
            }

        }
    }

    fun CalculateIMC(estatura:Float, peso:Float): Float{

        var imcValue: Float;
        imcValue = peso / (estatura.pow(2));
        return imcValue;
    }

    fun DeterminateRange(imc: Float): Pair<String, Int>{

        var categoria: String = "undefined";
        var color = R.color.white;
        if(imc < 18.5){
            categoria = "bajo peso"
            color = R.color.colorRed
        }
        if(imc>= 18.5 && imc<=24.9){
            categoria = "normal"
            color = R.color.colorGreen
        }
        if(imc>24.9 && imc <=29.9 ) {
            categoria = "sobrepeso"
            color = R.color.colorYellow
        }
        if(imc > 29.9 && imc<= 34.9){
            categoria = "obesidad grado 1"
            color = R.color.colorOrange
        }
        if(imc > 34.9 && imc<=39.9){
            categoria = "obesidad grado 2"
            color = R.color.colorBrown
        }
        if(imc > 39.9){
            categoria = "obesidad grado 3"
            color = R.color.colorRed
        }

        return Pair(categoria, color);
    }

}