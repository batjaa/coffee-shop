package com.batjaa.coffeeshop.service;

import com.batjaa.coffeeshop.config.auth.firebase.FirebaseTokenHolder;

public interface FirebaseService {

	FirebaseTokenHolder parseToken(String idToken);

}
