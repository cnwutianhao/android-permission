package com.runtimeperms.app

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.runtimeperms.app.databinding.ActivityMainBinding
import com.runtimeperms.tyhoowu.RuntimePerms
import com.runtimeperms.tyhoowu.callback.ExplainReasonCallbackWithBeforeParam
import com.runtimeperms.tyhoowu.callback.ForwardToSettingsCallback
import com.runtimeperms.tyhoowu.callback.RequestCallback
import com.runtimeperms.tyhoowu.request.ExplainScope
import com.runtimeperms.tyhoowu.request.ForwardScope

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnMakeRequest.setOnClickListener {
            RuntimePerms.init(this)
                .permissions(
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.READ_CONTACTS
                )
                .explainReasonBeforeRequest()
                .onExplainRequestReason(object :ExplainReasonCallbackWithBeforeParam {
                    override fun onExplainReason(scope: ExplainScope, deniedList: List<String>, beforeRequest: Boolean) {
                        val message = "RuntimePerms needs following permissions to continue"
                        scope.showRequestReasonDialog(deniedList, message, "Allow")

//                    val customDialog = CustomDialog(this, message, deniedList)
//                    scope.showRequestReasonDialog(customDialog)
                    }
                })
                .onForwardToSettings(object :ForwardToSettingsCallback{
                    override fun onForwardToSettings(scope: ForwardScope, deniedList: List<String>) {
                        val message = "Please allow following permissions in settings"
                        scope.showForwardToSettingsDialog(deniedList, message, "Allow")
                    }
                })
                .request(object :RequestCallback{
                    override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
                        if (allGranted) {
                            val toastText = "All permissions are granted"
                            Toast.makeText(this@MainActivity, toastText, Toast.LENGTH_SHORT).show()
                        } else {
                            val toastText = "The following permissions are denied：$deniedList"
                            Toast.makeText(this@MainActivity, toastText, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }
}