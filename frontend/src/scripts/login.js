// Login logic.

import AppController from './AppController.js';
import User from './model/user.js';

// Define the user as the variable in the local storage.
let user = new User("", "", "", "", []);
window.localStorage.setItem('user', user);

var app = new AppController();

// Get the elements of the form.
var userEmail = document.getElementById("userEmail");
var userPassword = document.getElementById("userPassword");

// Default login data for development.
// const user = {
//     name:"John Doe",
//     email:"anpch@example.com",
//     password:"123456"
// }

// Login button event handler.
const loginButton = document.getElementById("loginButton");
loginButton.addEventListener("click", function (e) {
    e.preventDefault();

    // Basic authentication.
    console.log('login button clicked');
    console.log(userEmail.value, userPassword.value);
    app.userLogin(userEmail.value, userPassword.value);
});


