package com.example.fitnessapp.feature_app.data.network

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {

    val client = createSupabaseClient(
        supabaseUrl = "https://qappxorzuldxgbbwlxvt.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFhcHB4b3J6dWxkeGdiYndseHZ0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzc2OTM1NjIsImV4cCI6MjA1MzI2OTU2Mn0.M0CB6Q95_fwctR51CYIp8Tb_cRAWJx6HjB98w1mA3Dw"
    ){
        install(Auth)
        install(Postgrest)
        install(ComposeAuth){
            googleNativeLogin(
                "856230107300-pnmfipb7p76cprnns2pf8nkdc01p2f49.apps.googleusercontent.com"
            )
        }
        install(Storage)
    }
}