package com.example.vibeus.Features.auth.SignUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val signUpSuccess = MutableLiveData<Boolean>()
    val saveSuccess = MutableLiveData<Boolean>()

    fun signUp(email: String, password: String, name: String, gender: String, hometown: String, onError: (String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = hashMapOf(
                        "name" to name,
                        "gender" to gender,
                        "email" to email,
                        "hometown" to hometown
                    )
                    db.collection("users").document(auth.currentUser!!.uid).set(user)
                        .addOnSuccessListener {
                            signUpSuccess.value = true
                        }
                        .addOnFailureListener { e ->
                            onError(e.message)
                        }
                } else {
                    onError(task.exception?.message)
                }
            }
    }

    fun savePersonalityTraits(trait1: String, trait2: String, trait3: String, onError: (String?) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        val traits = hashMapOf(
            "trait1" to trait1,
            "trait2" to trait2,
            "trait3" to trait3
        )
        db.collection("users").document(userId).update(traits as Map<String, Any>)
            .addOnSuccessListener {
                saveSuccess.value = true
            }
            .addOnFailureListener { e ->
                onError(e.message)
            }
    }

    fun savePreferences(lookingFor: String, dealBreakers: String, partnerPersonality: String, onError: (String?) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        val preferences = hashMapOf(
            "lookingFor" to lookingFor,
            "dealBreakers" to dealBreakers,
            "partnerPersonality" to partnerPersonality
        )
        db.collection("users").document(userId).update(preferences as Map<String, Any>)
            .addOnSuccessListener {
                saveSuccess.value = true
            }
            .addOnFailureListener { e ->
                onError(e.message)
            }
    }

    fun saveLifestyle(diet: String, exercise: String, sleep: String, onError: (String?) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        val lifestyle = hashMapOf(
            "diet" to diet,
            "exercise" to exercise,
            "sleep" to sleep
        )
        db.collection("users").document(userId).update(lifestyle as Map<String, Any>)
            .addOnSuccessListener {
                saveSuccess.value = true
            }
            .addOnFailureListener { e ->
                onError(e.message)
            }
    }

    fun saveValuesAndBeliefs(value1: String, value2: String, value3: String, onError: (String?) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        val valuesAndBeliefs = hashMapOf(
            "value1" to value1,
            "value2" to value2,
            "value3" to value3
        )
        db.collection("users").document(userId).update(valuesAndBeliefs as Map<String, Any>)
            .addOnSuccessListener {
                saveSuccess.value = true
            }
            .addOnFailureListener { e ->
                onError(e.message)
            }
    }

    fun saveFunQuestions(question1: String, question2: String, question3: String, onError: (String?) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        val funQuestions = hashMapOf(
            "question1" to question1,
            "question2" to question2,
            "question3" to question3
        )
        db.collection("users").document(userId).update(funQuestions as Map<String, Any>)
            .addOnSuccessListener {
                saveSuccess.value = true
            }
            .addOnFailureListener { e ->
                onError(e.message)
            }
    }
}