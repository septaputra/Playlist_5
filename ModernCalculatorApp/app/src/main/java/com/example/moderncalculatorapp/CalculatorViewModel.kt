package com.example.moderncalculatorapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculatorViewModel : ViewModel() {

    private val _equationText = mutableStateOf("")
    val equationText: State<String> = _equationText

    private val _resultText = mutableStateOf("0")
    val resultText: State<String> = _resultText

    fun onButtonClick(button: String) {
        when (button) {
            "AC" -> {
                _equationText.value = ""
                _resultText.value = "0"
            }

            "C" -> {
                val cur = _equationText.value
                if (cur.isNotEmpty()) {
                    _equationText.value = cur.dropLast(1)
                    calculateResult(_equationText.value)
                }
            }

            "=" -> {
                // Hasil terakhir tetap ditampilkan
                calculateResult(_equationText.value)
                _equationText.value = _resultText.value
            }

            else -> {
                // Tambahkan karakter baru dan evaluasi
                _equationText.value += button
                calculateResult(_equationText.value)
            }
        }
    }

    fun calculateResult(equation: String) {
        if (equation.isBlank()) {
            _resultText.value = "0"
            return
        }

        try {
            // Ganti 'x' jadi '*' agar bisa dievaluasi
            var sanitized = equation.replace('x', '*')

            // Jangan evaluasi jika berakhir dengan operator atau titik
            if (sanitized.endsWith("+") || sanitized.endsWith("-") ||
                sanitized.endsWith("*") || sanitized.endsWith("/") ||
                sanitized.endsWith(".")
            ) {
                _resultText.value = "0"
                return
            }

            // Jangan evaluasi jika jumlah kurung tidak seimbang
            if (sanitized.count { it == '(' } != sanitized.count { it == ')' }) {
                _resultText.value = "0"
                return
            }

            // Evaluasi ekspresi di thread lain agar UI tidak freeze
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val cx = Context.enter()
                    cx.optimizationLevel = -1
                    val scope: Scriptable = cx.initStandardObjects()

                    val raw = cx.evaluateString(scope, sanitized, "calc", 1, null)
                    var result = raw.toString()

                    // Hapus ".0" jika hasil bilangan bulat
                    if (result.endsWith(".0")) {
                        result = result.dropLast(2)
                    }

                    // Kembalikan hasil ke UI thread
                    withContext(Dispatchers.Main) {
                        _resultText.value = result
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        _resultText.value = "Error"
                    }
                } finally {
                    Context.exit()
                }
            }

        } catch (e: Exception) {
            _resultText.value = "Error"
        }
    }
}
