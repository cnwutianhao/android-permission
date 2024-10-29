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

import com.runtimeperms.tyhoowu.request.ForwardScope
import com.runtimeperms.tyhoowu.request.PermissionBuilder

/**
 * Callback for [PermissionBuilder.onForwardToSettings] method.
 *
 * @author Tyhoo Wu
 * @since 2024/10/10
 */
interface ForwardToSettingsCallback {

    /**
     * Called when you should tell user to allow these permissions in settings.
     * @param scope Scope to show rationale dialog.
     * @param deniedList Permissions that should allow in settings.
     */
    fun onForwardToSettings(scope: ForwardScope, deniedList: List<String>)
}