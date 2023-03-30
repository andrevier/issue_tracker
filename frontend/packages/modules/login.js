// Main application function.

import AppController from './AppController.js';
import User from './model/user.js';
import fakeUser from './fakeUser.js';

var appController = new AppController();

// Default login data for development.
const user = {
    name:"John Doe",
    email:"anpch@example.com",
    password:"123456"
}

// Login button event handler.
const loginButton = document.getElementById("loginButton");
loginButton.addEventListener("click", function (e) {
    e.preventDefault();

    // Basic authentication.
    console.log('login button clicked');
    appController.userLogin(user.email, user.password);        
});

// // Signup button event handler.
// const signupButton = document.getElementById("signupButton");
// signupButton.addEventListener("click", function (e) {
//     e.preventDefault();
//     console.log('signup button clicked');
//     appController.userSignup(
//         document.getElementById("userFullName").value, 
//         document.getElementById("userEmail").value,
//         document.getElementById("userPassword").value);
// });

// let appController = new AppController();

