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
package com.runtimeperms.tyhoowu.request

/**
 * Define a task interface to request permissions.
 * Not all permissions can be requested at one time. Some permissions need to request separately.
 * So each permission request need to implement this interface, and do the request logic in their implementations.
 *
 * @author Tyhoo Wu
 * @since 2024/10/10
 */
interface ChainTask {

    /**
     * Get the ExplainScope for showing RequestReasonDialog.
     * @return Instance of ExplainScope.
     */
    fun getExplainScope(): ExplainScope

    /**
     * Get the ForwardScope for showing ForwardToSettingsDialog.
     * @return Instance of ForwardScope.
     */
    fun getForwardScope(): ForwardScope

    /**
     * Do the request logic.
     */
    fun request()

    /**
     * Request permissions again when user denied.
     * @param permissions
     *          Permissions to request again.
     */
    fun requestAgain(permissions: List<String>)

    /**
     * Finish this task and notify the request result.
     */
    fun finish()
}