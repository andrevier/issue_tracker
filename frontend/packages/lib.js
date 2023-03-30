// projects.html

// Set the new project form to invisible.
document.getElementById("newProjectFormId").style.display = "none";

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
        number: 3,
        name: "Visit Gana",
        id: "VG",
        deadline: "2023-12-08",
        description:"Save money."
    }
];

updateHtmlTableRows();


// Library


function displayNewProjectForm() {
    console.log("displayNewProjectForm clicked");
    document.getElementById("projectsTableRowId").style.display = "none";
    document.getElementById("newProjectFormId").style.display = "block";
}

function returnToProjectTable() {
    console.log("returnToProjectTable clicked");
    document.getElementById("projectsTableRowId").style.display = "block";
    document.getElementById("newProjectFormId").style.display = "none";
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

    // Get table's html content.
    const projectsTableBody = document.getElementById("projectsTableBody");
    
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
        <div >
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

function editRowButtonClicked() {
    console.log("Clicked!")
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
            <button type="button" class="btn btn-link" onclick="editRowButtonClicked()"><img src="packages/node_modules/bootstrap-icons/icons/pencil.svg" alt="Edit" width="20"></button>
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