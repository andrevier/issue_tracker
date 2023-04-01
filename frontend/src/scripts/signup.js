import AppController from './AppController.js';

var appController = new AppController();
var userName = document.getElementById('userName');
var userEmail = document.getElementById('userEmail');
var userPassword = document.getElementById('userPassword');

// Signup button event handler.
const signupButton = document.getElementById("signupButton");
signupButton.addEventListener("click", function (e) {
    e.preventDefault();
    console.log('signup button clicked');
    appController.userSignup(
        userName.value, 
        userEmail.value,
        userPassword.value);
});
