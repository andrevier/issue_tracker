// Script to manage the content of the table of projects in ./projects.html

class TableProjects {
    
    constructor(projects) {
        this.projects = projects;
    }

    setProjects(projects) {
        this.projects = projects;
    }

    updateTable(projects) {
        let tableProjects = document.getElementById("tableProjectsBody");
        for (let i = 0; i < projects.length; i++) {
            const row = `       
            <tr>
                <th scope="row">${i + 1}</th>
                <td>${projects[i].name}</td>
                <td>${projects[i].code}</td>
                <td>${projects[i].deadline}</td>
                <td>
                <div>
                    <button type="button" class="btn btn-link" onclick="editProject(${i + 1})">
                        <img src="packages/node_modules/bootstrap-icons/icons/pencil.svg" alt="Edit" width="20">
                    </button>
                    <button type="button" class="btn btn-link" onclick="deleteProject(${i + 1})">
                        <img src="packages/node_modules/bootstrap-icons/icons/trash3.svg" alt="Edit" width="20">
                    </button>
                </div>
                </td>
            </tr>`;
            tableProjects.innerHTML += row;
        }
    }
}

export default TableProjects;
