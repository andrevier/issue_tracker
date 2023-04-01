// Login logic.
import fakeUsers from "./fakeUsers";

// Dev: Login data for development.
const user = {
    id: 1,
    name:"John Doe",
    email:"johndoe@example.com",
    password:"123456"
}

const loginButton = document.getElementById("loginButton");
const userEmail = document.getElementById("userEmail");
const userPassword = document.getElementById("userPassword");

loginButton.addEventListener("click", function (e) {
    e.preventDefault();
    
    // Basic authentication.    
    console.log('login button clicked');
    if (checkUserData(userEmail.value, userPassword.value)) {
        // Pass the user Id to the local storage.
        window.localStorage.setItem('userId', user.id);

        // Go to the project's page.
        window.location.href = './projects.html';

    } else {
        console.log('wrong user data');
    }
   
});

function checkUserData(email, password) {
    // Authentication logic.
    console.log('checking user data');
    for (let i = 0; i < fakeUsers.length; i++) {
        if (email === fakeUsers[i].email && password === fakeUsers[i].password) {
            return true;
        } else {
            return false;
        }

    }
    
}


