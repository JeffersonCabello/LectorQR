package com.sigmasoftware.simpleqrreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.sigmasoftware.simpleqrreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botonEscaner.setOnClickListener{
            initScanner()
        }


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                binding.descripcion.text = getString(R.string.la_informacion_escaneada_se_mostrara_debajo)
                Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_LONG).show()
            } else {
                binding.descripcion.text = getString(R.string.el_valor_escaneado_es)
                binding.resultadoLectura.text = result.contents.toString()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Apunte el recuadro al codigo QR")
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }
}