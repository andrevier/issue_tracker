// Login logic.

import AppController from './AppController.js';

var appController = new AppController();

var userEmail = document.getElementById("userEmail");
var userPassword = document.getElementById("userPassword");

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
    console.log(userEmail.value, userPassword.value);
    appController.userLogin(userEmail.value, userPassword.value);        
    //appController.userLogin(user.email, user.password);
});


