// projects.html

// Set the new project form to invisible.
//document.getElementById("newProjectFormId").style.display = "none";

// Array to save the json with data from the projects.
var projectsArray = [
    {
        number: 1,
        name: "My Project",
        id: "MP",
        deadline: "2023-03-29",
        description: "About freedom."
    },
    {
        number: 2,
        name: "Buy a car",
        id: "MP",
        deadline: "2023-04-08",
        description:"Save money."
    },
    {
        number: 3,
        name: "Merry in Canada",
        id: "MC",
        deadline: "2023-04-08",
        description:"Save money."
    },
    {
        number: 4,
        name: "Visit Gana",
        id: "VG",
        deadline: "2023-12-08",
        description:"Save money."
    }
];

//updateHtmlTableRows();

// Library
function displayProjectForm() {
    console.log("displayNewProjectForm clicked");
    document.getElementById("projectsTableRowId").style.display = "none";
    //document.getElementById("newProjectFormId").style.display = "block";
    
    const projectFormHtml = `
    <div class="mb-3">
        <label for="newProjectName" class="form-label">Project's name</label>
        <input type="text" class="form-control" id="newProjectName" placeholder="A beautiful name">
    </div>
    <div class="row g-3 align-items-center py-3">
        <div class="col-auto">
        <label for="newProjectId" class="col-form-label">ID</label>
        </div>
        <div class="col-auto">
        <input type="text" id="newProjectID" class="form-control">
        </div>
        <div class="col-auto">
        <label for="newProjectDeadline" class="col-form-label">Deadline</label>
        </div>
        <div class="col-auto">
        <input type="date" id="newProjectDeadline" class="form-control">
        </div>
    </div>        
    <div class="mb-3">
        <label for="newProjectDescription" class="form-label">Description</label>
        <textarea class="form-control" id="newProjectDescription" rows="5" placeholder="This project is about..."></textarea>
    </div>   
    <hr>
    <div class="row">
        <div class="">
        <button class="btn btn-primary" onclick="createNewProject()">Create</button>
        <button class="btn btn-secondary" onclick="returnToProjectTable()">Cancel</button>
        </div>
    </div>
    `
    document.getElementById("projectForm").innerHTML = projectFormHtml;
}

function returnToProjectTable() {
    console.log("returnToProjectTable clicked");
    document.getElementById("projectsTableRowId").style.display = "block";
    document.getElementById("projectForm").innerHTML = "";
}

function eraseProjectFormFields() {
    document.getElementById("newProjectName").value = "";
    document.getElementById("newProjectID").value = "";
    document.getElementById("newProjectDeadline").value = "";
    document.getElementById("newProjectDescription").value = "";

}

function createNewProject() {
    // Project's data.
    const newProjectName = document.getElementById("newProjectName");
    const newProjectID = document.getElementById("newProjectID");
    const newProjectDeadline = document.getElementById("newProjectDeadline");
    const newProjectDescription = document.getElementById("newProjectDescription");

    // // Get table's html content.
    // const projectsTableBody = document.getElementById("projectsTableBody");
    
    let projectJson = {
        number: projectsArray.length,
        name: newProjectName.value,
        id: newProjectID.value,
        deadline: newProjectDeadline.value,
        description: newProjectDescription.value
    };
    
    // Push the project's data to the array of projects.
    projectsArray.push(projectJson);

    // Create a table row with the new data.
    let row = `
    <tr>
        <th scope="row">${projectsArray.length}</th>
        <td>${newProjectName.value}</td>
        <td>${newProjectID.value}</td>
        <td>${newProjectDeadline.value}</td>
        <td>
        <div>
        <button type="button" class="btn btn-link" onclick="editRowButtonClicked()"><img src="packages/node_modules/bootstrap-icons/icons/pencil.svg" alt="Edit" width="20"></button>
        <button type="button" class="btn btn-link" onclick="deleteRow(${projectsArray.length})"><img src="packages/node_modules/bootstrap-icons/icons/trash3.svg" alt="Edit" width="20"></button>
        </div>
        </td>
    </tr>`;

    // Add the html to the table.
    projectsTableBody.innerHTML += row;

    eraseProjectFormFields();
    returnToProjectTable();

    console.log(projectsArray);
}

function editRowButtonClicked(rowNumber) {
    console.log("Clicked!");
    displayProjectForm();
    document.getElementById("newProjectName").value = projectsArray[rowNumber - 1].name;
    document.getElementById("newProjectID").value = projectsArray[rowNumber - 1].id;
    document.getElementById("newProjectDeadline").value = projectsArray[rowNumber - 1].deadline;
    document.getElementById("newProjectDescription").value = projectsArray[rowNumber - 1].description;    

}

function updateRowNumber() {
    for (let i = 0; i < projectsArray.length; i++) {
        projectsArray[i].number = i + 1;
    };

}

function updateHtmlTableRows() {
    const projectsTableBody = document.getElementById("projectsTableBody");
    projectsTableBody.innerHTML = "";
    for (let i = 0; i < projectsArray.length; i++) {
        const row = `
        <tr>
            <th scope="row">${projectsArray[i].number}</th>
            <td>${projectsArray[i].name}</td>
            <td>${projectsArray[i].id}</td>
            <td>${projectsArray[i].deadline}</td>
            <td>
            <div >
            <button type="button" class="btn btn-link" onclick="editRowButtonClicked(${projectsArray[i].number})"><img src="packages/node_modules/bootstrap-icons/icons/pencil.svg" alt="Edit" width="20"></button>
            <button type="button" class="btn btn-link" onclick="deleteRow(${projectsArray[i].number})"><img src="packages/node_modules/bootstrap-icons/icons/trash3.svg" alt="Edit" width="20"></button>
            </div>
            </td>
        </tr>`;
        projectsTableBody.innerHTML += row;
    };     
}

function deleteRow(rowNumber) {
    console.log("delete row number ", rowNumber);
    console.log("Array before ", projectsArray);

    // Remove the item from the array.
    projectsArray.splice(rowNumber - 1, 1);
    console.log("Array after ", projectsArray);

    document.getElementsByTagName("tr")[rowNumber].remove();
    
    updateRowNumber();

    updateHtmlTableRows();

}