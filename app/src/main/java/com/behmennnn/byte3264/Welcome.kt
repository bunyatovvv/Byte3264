package com.behmennnn.byte3264

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.behmennnn.byte3264.databinding.ActivityWelcomeWindowBinding
import java.util.Base64

class Welcome : AppCompatActivity() {
    private lateinit var bind: ActivityWelcomeWindowBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityWelcomeWindowBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.createButton.setOnClickListener {
            val input = bind.inputEditText.text.toString()
            val base32Encoded = encodeToBase32(input)
            val base64Encoded = encodeToBase64(input)

            when(bind.radiogroup.checkedRadioButtonId){
                bind.base32.id -> bind.resultText.text = base32Encoded
                bind.base64.id -> bind.resultText.text = base64Encoded
            }
        }
    }

    fun encodeToBase32(input: String): String {
        val base32Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567"
        val inputBytes = input.toByteArray(Charsets.UTF_8)
        val bitString = inputBytes.joinToString("") { String.format("%8s", it.toUByte().toString(2)).replace(' ', '0') }

        val chunks = bitString.chunked(5)
        val encoded = chunks.map { chunk ->
            val padding = if (chunk.length < 5) "0".repeat(5 - chunk.length) else ""
            val index = Integer.parseInt(chunk + padding, 2)
            base32Alphabet[index]
        }

        return encoded.joinToString("").padEnd(((encoded.size + 7) / 8) * 8, '=')
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encodeToBase64(input: String): String {
        val inputBytes = input.toByteArray(Charsets.UTF_8)
        return Base64.getEncoder().encodeToString(inputBytes)
    }
}