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
package com.runtimeperms.tyhoowu

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * An open source Android library that makes handling runtime permissions extremely easy.
 *
 * The following snippet shows the simple usage:
 * <pre>
 *   RuntimePerms.init(activity)
 *       .permissions(Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA)
 *       .request { allGranted, grantedList, deniedList ->
 *           // handling the logic
 *       }
 *</pre>
 *
 * @author Tyhoo Wu
 * @since 2024/10/10
 */
object RuntimePerms {

    /**
     * Init PermissionX to make everything prepare to work.
     *
     * @param activity An instance of FragmentActivity
     * @return PermissionCollection instance.
     */
    fun init(activity: FragmentActivity): PermissionMediator {
        return PermissionMediator(activity)
    }

    /**
     * Init PermissionX to make everything prepare to work.
     *
     * @param fragment An instance of Fragment
     * @return PermissionCollection instance.
     */
    fun init(fragment: Fragment): PermissionMediator {
        return PermissionMediator(fragment)
    }

    /**
     *  A helper function to check a permission is granted or not.
     *
     *  @param context Any context, will not be retained.
     *  @param permission Specific permission name to check. e.g. [android.Manifest.permission.CAMERA].
     *  @return True if this permission is granted, False otherwise.
     */
    fun isGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * A helper function to check are notifications are enabled for current app.
     * @param context Any context, will not be retained.
     * @return Note that if Android version is lower than N, the return value will always be true.
     */
    fun areNotificationsEnabled(context: Context): Boolean {
        return NotificationManagerCompat.from(context).areNotificationsEnabled()
    }

    /**
     * Define the const to compat with system lower than T.
     */
    const val POST_NOTIFICATIONS = "android.permission.POST_NOTIFICATIONS"
}