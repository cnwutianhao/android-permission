package com.runtimeperms.app

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.runtimeperms.app.databinding.FragmentMainBinding
import com.runtimeperms.tyhoowu.RuntimePerms
import com.runtimeperms.tyhoowu.callback.ExplainReasonCallbackWithBeforeParam
import com.runtimeperms.tyhoowu.callback.ForwardToSettingsCallback
import com.runtimeperms.tyhoowu.callback.RequestCallback
import com.runtimeperms.tyhoowu.request.ExplainScope
import com.runtimeperms.tyhoowu.request.ForwardScope

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMakeRequest.setOnClickListener {
            RuntimePerms.init(this)
                .permissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.RECORD_AUDIO
                )
                .setDialogTintColor(Color.parseColor("#1972E8"), Color.parseColor("#8AB6F5"))
                .onExplainRequestReason(object :ExplainReasonCallbackWithBeforeParam {
                    override fun onExplainReason(scope: ExplainScope, deniedList: List<String>, beforeRequest: Boolean) {
                        val message = "RuntimePerms needs following permissions to continue"
                        scope.showRequestReasonDialog(deniedList, message, "Allow", "Deny")

//                        val customDialog = CustomDialogFragment(message, deniedList)
//                        scope.showRequestReasonDialog(customDialog)
                    }
                })
                .onForwardToSettings(object :ForwardToSettingsCallback{
                    override fun onForwardToSettings(scope: ForwardScope, deniedList: List<String>) {
                        val message = "Please allow following permissions in settings"
                        scope.showForwardToSettingsDialog(deniedList, message, "Allow", "Deny")
                    }
                })
                .request(object :RequestCallback{
                    override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
                        if (allGranted) {
                            val toastText = "All permissions are granted"
                            Toast.makeText(activity, toastText, Toast.LENGTH_SHORT).show()
                        } else {
                            val toastText = "The following permissions are denied：$deniedList"
                            Toast.makeText(activity, toastText, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}