// Manage the projects window.
// Dev: for development purposes.
// const response = await fetch('./scripts/fakeUsers.json');
// const fakeUsers = await response.json();

// // Constants and presets.
// const userId = window.localStorage.getItem('userId');
// console.log("user id: ", userId);

const userBar = document.getElementById("dropdownMenuButton");
// Get the current user.
const URL = "http://localhost:8080/api/v1/user";
let currentUser = {};

fetch(URL, {
    method: "GET",
    headers: {"Content-type": "application/json;charset=UTF-8"}
})
.then(response => response.json())
.then(json => {
        currentUser = json;
        console.log(currentUser.userName);
        userBar.textContent = currentUser.userName;
    }
)
.catch(err => console.log(err));


// function showTableContent(projects) {
//     let table = document.getElementById('tableProjectsBody');
//     table.innerHTML = ' ';
//     if (projects.length > 0) {
//         let i = 1;
//         projects.forEach((project) => {
//             let tr = document.createElement('tr');
//             let th = document.createElement('th');
//             let td1 = document.createElement('td');
//             let td2 = document.createElement('td');
//             let td3 = document.createElement('td');
            
//             // Position of the project.
//             th.innerHTML = `${i}`;
//             td1.innerHTML = `${project.name}`;
//             td2.innerHTML = `${project.code}`;
//             td3.innerHTML = `${project.deadline}`;
            
//             tr.appendChild(th);
//             tr.appendChild(td1);
//             tr.appendChild(td2);
//             tr.appendChild(td3);

//             table.appendChild(tr);
//             i += 1;
//         });
//     }
// }

// Execution.

//document.getElementById("dropdownMenuButton").textContent = cuser.name;
//console.log("user", user);

// showTableContent(user.projects);