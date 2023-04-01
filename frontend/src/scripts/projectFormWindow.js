// Manages the project form window.
import AppController from "./appController.js";


let app = new AppController();

let createProject = document.getElementById('createProjectButton');

createProject.addEventListener('click', (e) => {
    e.preventDefault();

    let projectName = document.getElementById("projectName");
    let projectCode = document.getElementById("projectCode");
    let projectDescription = document.getElementById("projectDescription");
    let projectDeadline = document.getElementById("projectDeadline");
    
    console.log(projectName.value, projectCode.value, projectDeadline.value, projectDescription.value);

    app.addUserProject(
        projectName.value,
        projectCode.value,
        projectDeadline.value,
        projectDescription.value
    );
});
