import AppController from './AppController.js';
import User from './model/user.js';
import fakeUser from './fakeUser.js';

var appController = new AppController();

// Signup button event handler.
const signupButton = document.getElementById("signupButton");
signupButton.addEventListener("click", function (e) {
    e.preventDefault();
    console.log('signup button clicked');
    appController.userSignup(
        document.getElementById("userFullName").value, 
        document.getElementById("userEmailAddress").value,
        document.getElementById("userPassword").value);
});
