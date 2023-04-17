// Signup logic.
const userName = document.getElementById("user-name");
const userNameHelper = document.getElementById("name-helper");

const userEmail = document.getElementById("user-email");
const emailHelper = document.getElementById("email-helper");

const userPassword = document.getElementById("user-password");
const passwordHelper = document.getElementById("password-helper");

const signupButton = document.getElementById("signup-button");

const URL = "http://localhost:8080/api/v1/register"

signupButton.addEventListener("click", function(e) {
    e.preventDefault();
    if (userName.value === "") {
        userNameHelper.innerHTML = "user name is required";
        userName.focus();
        return false;
    } else if (userEmail.value === "") {
        emailHelper.innerHTML = "email is required";
        userEmail.focus();
        return false;
    } else if (userPassword.value === "") {
        passwordHelper.innerHTML = "password is required";
        userPassword.focus();
        return false;
    }

    let data = {
        userName: userName.value,
        email: userEmail.value,
        password: userPassword.value
    }

    // Post request.
    fetch(URL, {
      method: "POST",
      headers: {"Content-type": "application/json;charset=UTF-8"},
      body:JSON.stringify(data)
    })
    .then(response => {
        // If the user is registered, the status is 200.
        if (response.status === 200) {
            window.location.href = "./projects.html";
        } else {
            console.log("Error: " + response.status);
        }
        console.log(response.json());
    })
    .catch(err => console.log(err));

})

// POST method implementation:
// async function postData(url = "", data = {}) {
//     // Default options are marked with *
//     const response = await fetch(url, {
//       method: "POST", // *GET, POST, PUT, DELETE, etc.
//       mode: "no-cors", // no-cors, *cors, same-origin
//       cache: "default", // *default, no-cache, reload, force-cache, only-if-cached
//       credentials: "same-origin", // include, *same-origin, omit
//       headers: {
//         "Content-Type": "application/json",
//         "Access-Control-Allow-Origin" : "*", 
//         "Access-Control-Allow-Credentials" : "true"
//         // 'Content-Type': 'application/x-www-form-urlencoded',
//       },
//       redirect: "follow", // manual, *follow, error
//       referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
//       body: JSON.stringify(data) // body data type must match "Content-Type" header
//     });
//     return response.json(); // parses JSON response into native JavaScript objects
//   }
  
//   postData("https://example.com/answer", { answer: 42 }).then((data) => {
//     console.log(data); // JSON data parsed by `data.json()` call
//   });
  
