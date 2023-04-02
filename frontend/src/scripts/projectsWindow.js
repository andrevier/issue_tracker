// Manage the projects window.
// Dev: for development purposes.
const response = await fetch('./scripts/fakeUsers.json');
const fakeUsers = await response.json();

// Constants and presets.
const userId = window.localStorage.getItem('userId');
console.log("user id: ", userId);

// Functions.
function getUserById(userId) {
    // Get user projects from the database.
    let i = 0;
    while (`${fakeUsers[i].id}` !== userId) {
        console.log("user id: ", fakeUsers[i].id);
        i += 1;
    }

    return fakeUsers[i];
}

function showTableContent(projects) {
    let table = document.getElementById('tableProjectsBody');
    table.innerHTML = ' ';
    if (projects.length > 0) {
        let i = 1;
        projects.forEach((project) => {
            let tr = document.createElement('tr');
            let th = document.createElement('th');
            let td1 = document.createElement('td');
            let td2 = document.createElement('td');
            let td3 = document.createElement('td');
            
            // Position of the project.
            th.innerHTML = `${i}`;
            td1.innerHTML = `${project.name}`;
            td2.innerHTML = `${project.code}`;
            td3.innerHTML = `${project.deadline}`;
            
            tr.appendChild(th);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);

            table.appendChild(tr);
            i += 1;
        });
    }
}

// Execution.
const user = getUserById(userId);

document.getElementById("dropdownMenuButton").textContent = user.name;
console.log("user", user);

showTableContent(user.projects);