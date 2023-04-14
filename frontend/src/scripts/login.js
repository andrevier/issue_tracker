// Login logic.
// Dev: Get the users' data from the json file.
const loginButton = document.getElementById("login-button");
const email = document.getElementById("user-email");
const password = document.getElementById("user-password");
const emailHelper = document.getElementById("email-helper");
const passwordHelper = document.getElementById("password-helper");

loginButton.addEventListener("click", function(e) {
    e.preventDefault();
    if (email.value === "" && password.value === "") {
        emailHelper.innerHTML = "email is required";
        passwordHelper.innerHTML = "password is required";
        email.focus();
        return false;
    } else if (email.value === "") {
        emailHelper.innerHTML = "email is required";
        email.focus();
        return false;
    } else if (password.value === "") {
        passwordHelper.innerHTML = "password is required";
        password.focus();
        return false;
    }
})

