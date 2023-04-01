// Signup logic.
var userName = document.getElementById('userName');
var userEmail = document.getElementById('userEmail');
var userPassword = document.getElementById('userPassword');

// Signup button event handler.
const signupButton = document.getElementById("signupButton");
signupButton.addEventListener("click", function (e) {
    e.preventDefault();
    console.log('signup button clicked');
    var newUser = {
        name: userName.value,
        email: userEmail.value,
        password: userPassword.value
    }
    var newUserId = postNewUser(newUser);
    window.localStorage.setItem('userId', newUserId);
    window.location.href = "./projects.html";
});

function postNewUser(user) {
    console.log(user);
    // Get new user's id.
    var userId = 999;
    return userId;
}



