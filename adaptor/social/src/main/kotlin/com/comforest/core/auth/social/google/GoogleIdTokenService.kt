package com.comforest.core.auth.social.google

import com.comforest.core.SocialLoginFailedException
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import java.security.GeneralSecurityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
internal class GoogleIdTokenService(
    private val googleApiProperties: GoogleApiProperties,
) {
    private val idTokenVerifier =
        GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory.getDefaultInstance())
            .setAudience(listOf(googleApiProperties.clientId))
            .build()

    suspend fun getUserId(token: String): String? = withContext(Dispatchers.IO) {
        try {
            val idToken = idTokenVerifier.verify(token)
            idToken?.payload?.subject
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
            throw SocialLoginFailedException(e)
        }
    }
}
