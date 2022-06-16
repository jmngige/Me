package com.starsolns.me.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.starsolns.me.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(
        name = Constants.ME_DATASTORE_NAME
    )

    suspend fun saveToken(token: String){
        context.datastore.edit { Pref->
            Pref[ACCESS_TOKEN] = token
        }
    }

    suspend fun  isLoggedIn(isLogged: Boolean){
        context.datastore.edit { Pref->
            Pref[IS_LOGGED_IN] = isLogged
        }
    }

    val readAccessToken: Flow<String> = context.datastore.data
        .map { Pref->
            Pref[ACCESS_TOKEN] ?: "No token"
        }

    val readIsLoggedIn: Flow<Boolean> = context.datastore.data
        .map { Pref->
            Pref[IS_LOGGED_IN] ?: false
        }


    companion object {
        val ACCESS_TOKEN = stringPreferencesKey(Constants.PREF_ACCESS_TOKEN)
        val IS_LOGGED_IN = booleanPreferencesKey(Constants.PREF_IS_LOGGED_IN)
    }

}