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
package com.runtimeperms.tyhoowu.callback

import com.runtimeperms.tyhoowu.request.PermissionBuilder

/**
 * Callback for [PermissionBuilder.request] method.
 *
 * @author Tyhoo Wu
 * @since 2024/10/10
 */
interface RequestCallback {

    /**
     * Callback for the request result.
     * @param allGranted Indicate if all permissions that are granted.
     * @param grantedList All permissions that granted by user.
     * @param deniedList All permissions that denied by user.
     */
    fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>)
}
