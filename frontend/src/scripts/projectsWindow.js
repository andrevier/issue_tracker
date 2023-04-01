// Manage the projects window.
import AppController from "./appController.js";
import User from "./model/user.js";
import fakeUser from "./fakeUser.js";

// Temporary initialization for development purposes.
let user = new User(
    fakeUser.name,
    fakeUser.email,
    fakeUser.password,
    fakeUser.projects
);

window.localStorage.setItem("user", user);
//

let app = new AppController();

app.updateTableProjects(fakeUser.projects);


// let createProject = document.getElementById('createProjectButton');

// createProject.addEventListener('click', (e) => {
//     e.preventDefault();

//     let projectName = document.getElementById("projectName");
//     let projectCode = document.getElementById("projectCode");
//     let projectDescription = document.getElementById("projectDescription");
//     let projectDeadline = document.getElementById("projectDeadline");
    
//     console.log(projectName.value, projectCode.value, projectDeadline.value, projectDescription.value);

//     app.addUserProject(
//         projectName.value,
//         projectCode.value,
//         projectDeadline.value,
//         projectDescription.value
//     );
// });
