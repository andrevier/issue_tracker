// Login logic.
// Dev: Get the users' data from the json file.
const response = await fetch('./scripts/fakeUsers.json');
const fakeUsers = await response.json();

const loginButton = document.getElementById("loginButton");
const userEmail = document.getElementById("userEmail");
const userPassword = document.getElementById("userPassword");

loginButton.addEventListener("click", function (e) {
    e.preventDefault();
    
    // Basic authentication.    
    console.log('login button clicked');
    const userId = getUserId(userEmail.value, userPassword.value);
    console.log('userId: ' + userId);
    if (userId > 0) {
        // Pass the user Id to the local storage.
        window.localStorage.setItem('userId', userId);

        // Go to the project's page.
        window.location.href = './projects.html';

    } else {
        console.log('wrong user data');
    }
   
});

function getUserId(email, password) {
    // Authentication logic.
    console.log('checking user data');

    for (let i = 0; i < fakeUsers.length; i++) {
        if (email === fakeUsers[i].email && password === fakeUsers[i].password) {
            return fakeUsers[i].id;
        }
    }
    return -1;
}

