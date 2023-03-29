// projects.html

// Set the new project form to invisible beforehand.
document.getElementById("newProjectFormId").style.display = "none";

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
    
    // Create a table row with the new data.
    let row = `
    <tr>
        <th scope="row">1</th>
        <td>${newProjectName.value}</td>
        <td>${newProjectID.value}</td>
        <td>${newProjectDeadline.value}</td>
        <td>
            <div >
            <button type="button" class="btn btn-outline-danger btn-sm">x</button>
            </div>
        </td>
    </tr>`;
    console.log(row);
    projectsTableBody.innerHTML += row;
    eraseProjectFormFields();
    returnToProjectTable();
}