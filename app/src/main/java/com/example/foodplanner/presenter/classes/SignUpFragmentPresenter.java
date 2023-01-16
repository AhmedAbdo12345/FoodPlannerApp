package com.example.foodplanner.presenter.classes;

import android.content.Context;

import com.example.foodplanner.model.ModelClasses.AuthModel;
import com.example.foodplanner.presenter.interfaces.SignUpFragmentInterface;
import com.example.foodplanner.presenter.repository.SignUpRepository;

public class SignUpFragmentPresenter {

    SignUpRepository signUpRepository;

    public SignUpFragmentPresenter(SignUpFragmentInterface signUpFragmentInterface) {
        signUpRepository = new SignUpRepository(signUpFragmentInterface);

    }

  public  void createUser(String nameuser ,String emailUser, String passwordUser){

        signUpRepository.createNewUser(nameuser,emailUser,passwordUser);
    }


}
