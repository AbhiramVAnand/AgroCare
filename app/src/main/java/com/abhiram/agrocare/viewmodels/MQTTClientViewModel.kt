package com.abhiram.agrocare.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.abhiram.agrocare.data.repository.PreferencesRepo
import com.hivemq.client.mqtt.MqttGlobalPublishFilter
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import dagger.hilt.android.lifecycle.HiltViewModel
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MQTTClientViewModel @Inject constructor(
    appContext : Application,
    preferenceRepo : PreferencesRepo
) : ViewModel() {
    val wsUri = "7509b34df87c47ad8a934836f0109ec8.s2.eu.hivemq.cloud" // Replace with your WebSocket address
    val uid = preferenceRepo.getUserID()
    val sharedPreferences = preferenceRepo// Replace with your HiveMQ password (optional)
    val client = Mqtt5Client.builder()
        .identifier(uid)
        .serverHost(wsUri)
        .serverPort(8883)
        .sslWithDefaultConfig()
        .buildBlocking()

    fun getUname() : String{
        return sharedPreferences.getUname()
    }

    fun setIsNotFirst(){
        sharedPreferences.setNotFirst()
    }

    fun getIsFirst(): Boolean {
        return sharedPreferences.getIsFirst()
    }

    fun Connect(username : String, password : String): Boolean {
        sharedPreferences.setUname(username)
        var status : Boolean = false
        try {
            client.connectWith()
                .simpleAuth()
                .username(username)
                .password(StandardCharsets.UTF_8.encode(password))
                .applySimpleAuth()
                .send()

            client.subscribeWith()
                .topicFilter("led")
                .send()
            client.toAsync().publishes(MqttGlobalPublishFilter.ALL) { publish: Mqtt5Publish ->
                Log.e(
                    "Response From NodeMcu",
                    "Received message: " +
                            publish.topic + " -> " +
                            StandardCharsets.UTF_8.decode(
                                publish.payload.get()
                            )
                )
            }

            Log.e("OnConnect","Success ${client.state}")
            status = true
        }catch (e : Exception){
            Log.e("OnConnect",e.message.toString())
        }
        return status
    }

    fun DeviceOn(device : String, status : Boolean ){
        if (status){
            client.publishWith()
                .topic(device)
                .payload("1".toByteArray())
                .send()
        }else{
            client.publishWith()
                .topic(device)
                .payload("0".toByteArray())
                .send()
        }

    }
}