/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Top-level build file where you can add configuration options common to all sub-projects/modules.
/*
    Please refer to the following URL for the details of
    "can't be called in this context by implicit receiver" errors,
    which will be fixed with Gradle 8.1 or later.
    https://youtrack.jetbrains.com/issue/KTIJ-19369/
 */

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply(false)
    alias(libs.plugins.kotlinAndroid) apply(false)
    alias(libs.plugins.kotlin.serialization) apply(false)
    alias(libs.plugins.hilt) apply(false)
    alias(libs.plugins.ksp) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    alias(libs.plugins.androidTest) apply false
}