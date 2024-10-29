/*
 * Copyright (C)  Tyhoo Wu, RuntimePerms Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.runtimeperms.tyhoowu.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

/**
 * Base DialogFragment class to inherits to display a rationale dialog and show user why you need the permissions that you asked.
 * Your DialogFragment must have a positive button to proceed request and an optional negative button to cancel request. Override
 * [RationaleDialogFragment.getPositiveButton] and [RationaleDialogFragment.getNegativeButton] to implement that.
 *
 * @author Tyhoo Wu
 * @since 2024/10/10
 */
abstract class RationaleDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            dismiss()
        }
    }

    /**
     * Return the instance of positive button on the DialogFragment. Your DialogFragment must have a positive button to proceed request.
     * @return The instance of positive button on the DialogFragment.
     */
    abstract fun getPositiveButton(): View

    /**
     * Return the instance of negative button on the DialogFragment.
     * If the permissions that you request are mandatory, your DialogFragment can have no negative button.
     * In this case, you can simply return null.
     * @return The instance of positive button on the DialogFragment, or null if your DialogFragment has no negative button.
     */
    abstract fun getNegativeButton(): View?

    /**
     * Provide permissions to request. These permissions should be the ones that shows on your RationaleDialogFragment.
     * @return Permissions list to request.
     */
    abstract fun getPermissionsToRequest(): List<String>
}