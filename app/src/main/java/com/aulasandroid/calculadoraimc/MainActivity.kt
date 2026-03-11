package com.aulasandroid.calculadoraimc

import android.R.attr.font
import android.R.attr.fontFamily
import android.R.attr.label
import android.R.attr.name
import android.R.attr.onClick
import android.R.attr.text
import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.Key.Companion.H
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aulasandroid.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculadoraIMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun CalculadoraIMCScreen(modifier: Modifier = Modifier) {



    Column(modifier = modifier.fillMaxSize()) {

        var altura by remember {
            mutableStateOf("")
        }

        var peso by remember {
            mutableStateOf("")
        }

        var imc by remember {
            mutableStateOf(0.0)
        }

        var classificacao = when {
            imc in 0.0..18.4 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Peso ideal"
            imc in 25.0..29.9 -> "Sobrepeso"
            imc in 30.0..34.9 -> "Obesidade I"
            imc in 35.0..39.9 -> "Obesidade II"
            else -> "Obesidade III"
        }
        fun calcularIMC(altura: Double, peso: Double): Double{

            var alturaMetros = altura/100

            var resultado = peso/(alturaMetros * alturaMetros)

            return resultado
        }



        //Header

        Column(modifier = Modifier.fillMaxWidth()
            .height(160.dp)
            .background(color = colorResource(R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "IMC Logo",
                modifier = Modifier.size( 80.dp)
                    .padding(vertical = 16.dp)
            )

            Text(
                text = "Calculadora de IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 32.dp),
         //   verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {

            // Form

            Card(
                modifier = Modifier.fillMaxWidth()
//                    .height(300.dp)
                    .size(300.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0XFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                //shape = CircleShape,
                //border = BorderStroke(width = 4.dp, Color.Black)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)

                ) {
                    Text(
                        modifier = Modifier
                            .offset(y = 20.dp),
                        text = "Seus dados",
                        color = colorResource(R.color.cor_app),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )


                   OutlinedTextField(
                        value = altura,
                        onValueChange = { novoValor ->
                            altura = novoValor
                          //  altura = it
                        },
                       shape = RoundedCornerShape(16.dp),
                       colors = OutlinedTextFieldDefaults.colors(
                           focusedBorderColor = Color.Black,
                           unfocusedBorderColor = colorResource(R.color.cor_app),
                           focusedPlaceholderColor = Color.Black,
                       ),
                        label = {
                            Text(text = "Altura")
                        }
                    )

                    OutlinedTextField(
                        value = peso163,
                        onValueChange = { novoValor ->
                            peso = novoValor
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = colorResource(R.color.cor_app),
                            focusedPlaceholderColor = Color.Black,
                        ),

                        label = {
                            Text(text = "Peso")
                        }

                    )



                    Button(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 30.dp)
                            .height(56.dp),
                        onClick = {
                            imc =  calcularIMC(altura.toDouble(), peso.toDouble())
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.cor_app),
                            contentColor = Color.White
                        ),

                    ) {
                        Text(text = "CALCULAR")
                    }

                }
            }

            // -- RESULT
            Card (
                 modifier = Modifier.fillMaxWidth()
                     .clip(
                         shape = RoundedCornerShape(15.dp)
                     ),

             ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .height(80.dp)
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,

                ) {
                    Text(text = "%.1f".format(imc))

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = classificacao)
                }
             }
        }
    }
}